package com.kl3jvi.gitflame.di

import com.kl3jvi.gitflame.common.Constants.BASE_URL
import com.kl3jvi.gitflame.data.network.LoginService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideLoginRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

//    @Provides
//    @Singleton
//    fun provideUserDataRetrofit(): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl(BASE_LOGIN_URL)
//            .addConverterFactory(MoshiConverterFactory.create())
//            .build()
//    }

    @Provides
    @Singleton
    fun provideLoginService(retrofit: Retrofit): LoginService {
        return retrofit.create(LoginService::class.java)
    }


}