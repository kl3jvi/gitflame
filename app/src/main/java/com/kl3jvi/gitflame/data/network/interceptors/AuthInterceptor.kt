package com.kl3jvi.gitflame.data.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AuthInterceptor(token: String? = null, otp: String? = null) : Interceptor {

    private var token: String? = null
    private var otp: String? = null

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val builder = original.newBuilder()
        val authToken = token
        val otpCode = otp
        if (!authToken.isNullOrBlank()) {
            builder.header(
                "Authorization",
                if (authToken.startsWith("Basic")) authToken else "token $authToken"
            )
        }
        if (!otpCode.isNullOrBlank()) {
            builder.addHeader("X-GitHub-OTP", otpCode.trim())
        }
        val request = builder.build()
        return chain.proceed(request)
    }



}