package com.example.daggerpractice.di.modules.auth

import com.example.daggerpractice.di.AuthScope
import com.example.daggerpractice.network.auth.AuthApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Created by PR72510 on 16/7/20.
 */
@Module
class AuthModule {

    @AuthScope
    @Provides
    fun providesAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }
}