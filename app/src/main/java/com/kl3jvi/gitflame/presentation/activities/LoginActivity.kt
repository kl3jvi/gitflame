package com.kl3jvi.gitflame.presentation.activities

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.kl3jvi.gitflame.databinding.ActivityLoginBinding
import androidx.browser.customtabs.CustomTabColorSchemeParams

import android.graphics.Color
import androidx.core.app.ShareCompat
import com.kl3jvi.gitflame.BuildConfig
import com.kl3jvi.gitflame.common.Constants.OAUTH_URL


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val clientId = BuildConfig.GIT_ID
        binding.button.setOnClickListener {
            CustomTabsIntent.Builder()
                .build()
                .launchUrl(this, Uri.parse(OAUTH_URL))
        }
    }
}