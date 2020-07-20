package com.example.daggerpractice.di.modules.main

import com.example.daggerpractice.ui.main.post.PostsFragment
import com.example.daggerpractice.ui.main.profile.ProfileFragment
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

/**
 * Created by PR72510 on 20/7/20.
 */

@Module
abstract class MainFragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun providesProfileFragment(): ProfileFragment

    @ContributesAndroidInjector
    abstract fun providesPostsFragment(): PostsFragment
}