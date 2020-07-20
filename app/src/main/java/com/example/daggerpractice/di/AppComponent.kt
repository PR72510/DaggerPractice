package com.example.daggerpractice.di

import android.app.Application
import com.example.daggerpractice.BaseApplication
import com.example.daggerpractice.SessionManager
import com.example.daggerpractice.di.modules.ActivityBuilderModule
import com.example.daggerpractice.di.modules.AppModule
import com.example.daggerpractice.di.modules.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by PR72510 on 14/7/20.
 */

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class, ActivityBuilderModule::class, AppModule::class,
        ViewModelFactoryModule::class]
)
interface AppComponent : AndroidInjector<BaseApplication> {

    fun sessionManager(): SessionManager

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}