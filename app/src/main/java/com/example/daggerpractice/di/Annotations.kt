package com.example.daggerpractice.di

import androidx.lifecycle.ViewModel
import dagger.MapKey
import javax.inject.Scope
import kotlin.reflect.KClass

/**
 * Created by PR72510 on 15/7/20.
 */

@MapKey
@Target(AnnotationTarget.FUNCTION)
annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Scope
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
internal annotation class AuthScope

@Scope
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
internal annotation class MainScope