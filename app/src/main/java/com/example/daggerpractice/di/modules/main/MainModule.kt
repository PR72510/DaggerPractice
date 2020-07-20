package com.example.daggerpractice.di.modules.main

import com.example.daggerpractice.di.MainScope
import com.example.daggerpractice.network.main.MainApi
import com.example.daggerpractice.ui.main.post.PostsAdapter
import com.example.daggerpractice.utils.VerticalSpaceItemDecoration
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Created by PR72510 on 20/7/20.
 */

@Module
class MainModule {

    @MainScope
    @Provides
    fun providesPostAdapter(): PostsAdapter {
        return PostsAdapter()
    }

    @MainScope
    @Provides
    fun provideItemDecorator(): VerticalSpaceItemDecoration = VerticalSpaceItemDecoration(15)

    @MainScope
    @Provides
    fun provideMainApi(retrofit: Retrofit): MainApi {
        return retrofit.create(MainApi::class.java)
    }
}