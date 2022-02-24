package com.kl3jvi.gitflame.presentation.activities.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.kl3jvi.gitflame.BuildConfig
import com.kl3jvi.gitflame.R
import com.kl3jvi.gitflame.common.network_state.State
import com.kl3jvi.gitflame.common.utils.*
import com.kl3jvi.gitflame.common.utils.Constants.APPLICATION_ID
import com.kl3jvi.gitflame.common.utils.Constants.CLIENT_ID
import com.kl3jvi.gitflame.common.utils.Constants.CLIENT_SECRET
import com.kl3jvi.gitflame.common.utils.Constants.REDIRECT_URL
import com.kl3jvi.gitflame.data.remote.dto.AccessTokenModel
import com.kl3jvi.gitflame.databinding.ActivityLoginBinding
import com.kl3jvi.gitflame.presentation.activities.MainActivity
import com.kl3jvi.gitflame.presentation.base.BindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BindingActivity<ActivityLoginBinding>(R.layout.activity_login),
    Authentication {

    private lateinit var customTabsIntent: CustomTabsIntent
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkIfUserLoggedIn()
        binding {
            button.setOnClickListener {
                customTabsIntent = CustomTabsIntent.Builder().build()
                customTabsIntent.launchUrl(this@LoginActivity, getAuthorizationUrl())
            }
        }
    }

    /**
     * Moves to main activity and also returns logged in state to check it
     * when reopening app and not transitioning 2 times to home page after login
     */
    private fun checkIfUserLoggedIn(): Boolean {
        var isLoggedIn = false
        collectFlow(viewModel.getToken()) { token ->
            isLoggedIn = token.isNotEmpty()
            if (isLoggedIn) {
                binding.progressBar.show()
                launchActivity<MainActivity> {}
                finish()
            }
        }
        return isLoggedIn
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
                    collectFlow(
                        viewModel.getAccessToken(
                            tokenCode,
                            CLIENT_ID,
                            CLIENT_SECRET,
                            APPLICATION_ID,
                            REDIRECT_URL
                        )
                    ) { state ->
                        when (state) {
                            is State.Error -> {
                                showSnack(binding.root, state.message)
                            }
                            is State.Loading -> {
                                binding.progressBar.show()
                            }
                            is State.Success -> {
                                onTokenResponse(state.data)
                            }
                        }
                    }
                } else {
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
                viewModel.saveTokenToDataStore(token)
                return
            }
        }
        showToast("Couldn't Login!!")
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
        onHandleAuthIntent(intent)
    }
}