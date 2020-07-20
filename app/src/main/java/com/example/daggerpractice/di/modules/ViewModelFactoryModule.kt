package com.example.daggerpractice.di.modules

import androidx.lifecycle.ViewModelProvider
import com.example.daggerpractice.di.factory.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

/**
 * Created by PR72510 on 15/7/20.
 */

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(modelProviderFactory: ViewModelProviderFactory): ViewModelProvider.Factory
}