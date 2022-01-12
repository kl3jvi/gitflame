package com.kl3jvi.gitflame.di

import com.kl3jvi.gitflame.data.persistence.DataStoreManager
import com.kl3jvi.gitflame.data.persistence.DataStoreManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataStoreManagerModule {

    @Singleton
    @Binds
    abstract fun bindDataStoreService(impl: DataStoreManagerImpl): DataStoreManager
}
