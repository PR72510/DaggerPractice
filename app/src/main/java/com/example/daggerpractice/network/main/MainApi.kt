package com.example.daggerpractice.network.main

import com.example.daggerpractice.models.Post
import com.example.daggerpractice.models.User
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by PR72510 on 20/7/20.
 */


interface MainApi {

    @GET("posts")
    fun getPostsFromUser(@Query("userId") id: Int): Flowable<List<Post>>
}