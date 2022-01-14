package com.kl3jvi.gitflame.presentation.activities.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.kl3jvi.gitflame.BuildConfig
import com.kl3jvi.gitflame.R
import com.kl3jvi.gitflame.common.Constants.APPLICATION_ID
import com.kl3jvi.gitflame.common.Constants.CLIENT_ID
import com.kl3jvi.gitflame.common.Constants.CLIENT_SECRET
import com.kl3jvi.gitflame.common.Constants.REDIRECT_URL
import com.kl3jvi.gitflame.common.NetworkUtil
import com.kl3jvi.gitflame.common.State
import com.kl3jvi.gitflame.common.ViewUtils.showSnack
import com.kl3jvi.gitflame.common.ViewUtils.showToast
import com.kl3jvi.gitflame.data.model.AccessTokenModel
import com.kl3jvi.gitflame.databinding.ActivityLoginBinding
import com.kl3jvi.gitflame.presentation.activities.MainActivity
import com.kl3jvi.gitflame.presentation.base.BaseActivity
import com.skydoves.transformationlayout.TransformationCompat
import com.skydoves.transformationlayout.onTransformationStartContainer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login), Authentication {

    private lateinit var customTabsIntent: CustomTabsIntent
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        onTransformationStartContainer()
        super.onCreate(savedInstanceState)
        installSplashScreen()
        checkIfUserLoggedIn()
        binding {
            button.setOnClickListener {
                customTabsIntent = CustomTabsIntent.Builder().build()
                customTabsIntent.launchUrl(this@LoginActivity, getAuthorizationUrl())
            }
        }
    }

    private fun checkIfUserLoggedIn(): Boolean {
        var isLoggedIn = false
        lifecycleScope.launchWhenStarted {
            viewModel.getToken().collect { token ->
                isLoggedIn = token.isNotEmpty()
                if (isLoggedIn) {
                    val intent = Intent(
                        this@LoginActivity,
                        MainActivity::class.java
                    )
                    startActivity( intent)
                    finish()
                }
            }
        }
        return isLoggedIn
    }

    override fun onResume() {
        super.onResume()
        if (!checkIfUserLoggedIn()) onHandleAuthIntent(intent)
    }

    override fun onStart() {
        super.onStart()
        handleNetworkChanges()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (!checkIfUserLoggedIn()) onHandleAuthIntent(intent)
    }

    override fun getAuthorizationUrl(): Uri {
        return Uri.Builder().scheme("https")
            .authority("github.com")
            .appendPath("login")
            .appendPath("oauth")
            .appendPath("authorize")
            .appendQueryParameter("client_id", BuildConfig.GIT_ID)
            .appendQueryParameter("redirect_uri", BuildConfig.REDIRECT_URI)
            .appendQueryParameter("scope", "user,repo,gist,notifications,read:org")
            .appendQueryParameter("state", BuildConfig.APPLICATION_ID)
            .build()
    }

    override fun onHandleAuthIntent(intent: Intent?) {
        if (intent != null && intent.data != null) {
            val uri = intent.data
            if (uri.toString().startsWith(BuildConfig.REDIRECT_URI)) {
                val tokenCode = uri?.getQueryParameter("code")
                if (!tokenCode.isNullOrEmpty()) {
                    lifecycleScope.launch {
                        viewModel.getAccessToken(
                            tokenCode,
                            CLIENT_ID,
                            CLIENT_SECRET,
                            APPLICATION_ID,
                            REDIRECT_URL
                        ).collect { state ->
                            when (state) {
                                is State.Error -> {
                                    showSnack(binding.root, state.message)
                                }
                                is State.Loading -> {}
                                is State.Success -> {
                                    onTokenResponse(state.data)
                                }
                            }
                        }
                    }
                } else {
                    // show error couldn't login
                    Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun handleNetworkChanges() {
        NetworkUtil.getNetworkLiveData(applicationContext).observe(this) { isConnected ->
            if (!isConnected) showToast("No Internet Connection!")
            binding.button.isEnabled = isConnected
        }
    }


    override fun onTokenResponse(response: AccessTokenModel?) {
        if (response != null) {
            val token: String? = response.token ?: response.accessToken
            if (!token.isNullOrEmpty()) {
                Log.e("Token saved", token)
                viewModel.saveTokenToDataStore(token)
                lifecycleScope.launch {
                    viewModel.getUser(token).collect { state ->
                        when (state) {
                            is State.Error -> {}
                            is State.Loading -> {}
                            is State.Success -> {
                                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                startActivity(intent)
                                finish()
                            }
                        }
                    }
                }
                return
            }
        }
        showToast("Couldn't Login!!")
    }


    override fun login(
        username: String,
        password: String,
        twoFactorCode: String?,
        isBasicAuth: Boolean,
        endpoint: String?
    ) {
        TODO("Not yet implemented")
    }

}