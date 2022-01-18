package com.kl3jvi.gitflame.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DataStoreServiceModule {
    private val Context.tokenDataStore: DataStore<Preferences> by preferencesDataStore(name = "token")

    @Singleton
    @Provides
    fun provideUserPreferencesDataStore(
        @ApplicationContext app: Context
    ): DataStore<Preferences> = app.tokenDataStore

}
