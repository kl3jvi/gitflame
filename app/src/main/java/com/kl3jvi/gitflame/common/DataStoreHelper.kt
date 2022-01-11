package com.kl3jvi.gitflame.common

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

object DataStoreHelper {
    private val Context.tokenDataStore: DataStore<Preferences> by preferencesDataStore(name = "token")
}