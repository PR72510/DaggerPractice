package com.example.daggerpractice.di.modules

import com.example.daggerpractice.di.AuthScope
import com.example.daggerpractice.di.MainScope
import com.example.daggerpractice.di.modules.auth.AuthModule
import com.example.daggerpractice.di.modules.auth.AuthViewModelModule
import com.example.daggerpractice.di.modules.main.MainFragmentBuilderModule
import com.example.daggerpractice.di.modules.main.MainModule
import com.example.daggerpractice.di.modules.main.MainViewModelModule
import com.example.daggerpractice.ui.auth.AuthActivity
import com.example.daggerpractice.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by PR72510 on 14/7/20.
 */

@Module
abstract class ActivityBuilderModule {

    @AuthScope
    @ContributesAndroidInjector(modules = [AuthViewModelModule::class, AuthModule::class])
    abstract fun providesAuthActivity(): AuthActivity

    @MainScope
    @ContributesAndroidInjector(
        modules = [MainFragmentBuilderModule::class,
            MainViewModelModule::class, MainModule::class]
    )
    abstract fun providesMainActivity(): MainActivity
}