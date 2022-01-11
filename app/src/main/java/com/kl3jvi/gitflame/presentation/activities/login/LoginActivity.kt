package com.kl3jvi.gitflame.presentation.activities.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.kl3jvi.gitflame.BuildConfig
import com.kl3jvi.gitflame.common.Constants.APPLICATION_ID
import com.kl3jvi.gitflame.common.Constants.CLIENT_ID
import com.kl3jvi.gitflame.common.Constants.CLIENT_SECRET
import com.kl3jvi.gitflame.common.Constants.REDIRECT_URL
import com.kl3jvi.gitflame.common.NetworkUtil
import com.kl3jvi.gitflame.common.State
import com.kl3jvi.gitflame.common.ViewUtils.showToast
import com.kl3jvi.gitflame.data.model.AccessTokenModel
import com.kl3jvi.gitflame.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : AppCompatActivity(), Authentication {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var customTabsIntent: CustomTabsIntent
    private val viewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            customTabsIntent = CustomTabsIntent.Builder().build()
            customTabsIntent.launchUrl(this, getAuthorizationUrl())
        }
    }

    override fun onResume() {
        super.onResume()
        onHandleAuthIntent(intent)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        onHandleAuthIntent(intent)
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
                    println("$tokenCode")
                    lifecycleScope.launch {
                        viewModel.getAccessToken(
                            tokenCode,
                            CLIENT_ID,
                            CLIENT_SECRET,
                            APPLICATION_ID,
                            REDIRECT_URL
                        ).collect { state ->
                            when (state) {
                                is State.Error -> {}
                                is State.Loading -> {}
                                is State.Success -> {

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
            if (!isConnected) showToast("No Connection")
        }
    }


    override fun onTokenResponse(response: AccessTokenModel?) {
        if (response != null) {
            val token: String? = response.token ?: response.accessToken
            if (!token.isNullOrEmpty()) {
//                PrefGetter.setToken(token)  todo save token on preferences
//                makeRestCall(RestProvider.getUserService(false).getUser()) { userModel: Login? ->
//                    this.onUserResponse(
//                        userModel
//                    )
//                } todo make method for geting user service
                return
            }
        }
        Toast.makeText(this, "Couldn't Login!!", Toast.LENGTH_SHORT).show()
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