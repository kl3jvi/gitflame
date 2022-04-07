package com.kl3jvi.gitflame.data.persistence

interface LocalStorage {
    var userName: String?
    var authToken: String?
    var refreshToken: String?
    fun clearStorage()
}