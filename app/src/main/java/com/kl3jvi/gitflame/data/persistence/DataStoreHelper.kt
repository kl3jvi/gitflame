package com.kl3jvi.gitflame.data.persistence

import android.app.Activity
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

object DataStoreHelper {
    private val Activity.tokenDataStore: DataStore<Preferences> by preferencesDataStore(name = "token")
}