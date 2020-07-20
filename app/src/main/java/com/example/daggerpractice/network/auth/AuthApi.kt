package com.example.daggerpractice.network.auth

import com.example.daggerpractice.models.User
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by PR72510 on 16/7/20.
 */
interface AuthApi {

    @GET("users/{id}")
    fun getUser(@Path("id") id: Int): Flowable<User>
}