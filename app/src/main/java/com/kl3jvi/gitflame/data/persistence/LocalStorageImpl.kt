package com.kl3jvi.gitflame.data.persistence

import android.content.SharedPreferences
import javax.inject.Inject

class LocalStorageImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : LocalStorage {

    companion object {
        private val AUTH_TOKEN = "authentication_token"
        private val REFRESH_TOKEN = "refresh_token"
        private val USER_NAME = "user_data"
    }

    override var authToken: String?
        get() = getData(AUTH_TOKEN)
        set(value) {
            setData(AUTH_TOKEN, value)
        }

    override var refreshToken: String?
        get() = getData(REFRESH_TOKEN)
        set(value) {
            setData(REFRESH_TOKEN, value)
        }

    override var userName: String?
        get() = getData(USER_NAME)
        set(value) {
            setData(USER_NAME, value)
        }

    private fun getData(key: String): String? {
        return sharedPreferences.getString(key, null)
    }

    private fun setData(key: String, value: String?) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    override fun clearStorage() {
        sharedPreferences.edit().clear().apply()
    }
}