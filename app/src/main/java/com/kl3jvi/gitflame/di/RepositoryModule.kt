package com.kl3jvi.gitflame.di

import com.kl3jvi.gitflame.data.network.LoginService
import com.kl3jvi.gitflame.data.network.UserService
import com.kl3jvi.gitflame.data.repository.LoginRepositoryImpl
import com.kl3jvi.gitflame.data.repository.UserRepositoryImpl
import com.kl3jvi.gitflame.domain.repository.LoginRepository
import com.kl3jvi.gitflame.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideLoginRepository(
        loginService: LoginService
    ): LoginRepository {
        return LoginRepositoryImpl(loginService)
    }

    @Provides
    @ViewModelScoped
    fun provideUserRepository(
        userService: UserService
    ): UserRepository {
        return UserRepositoryImpl(userService)
    }

}