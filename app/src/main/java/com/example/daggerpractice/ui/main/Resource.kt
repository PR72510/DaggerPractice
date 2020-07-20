package com.example.daggerpractice.ui.main

/**
 * Created by PR72510 on 20/7/20.
 */
sealed class Resource<T> {
    class Success<T>(val data: T) : Resource<T>()
    class Error<T>(val msg: String, val data: T?): Resource<T>()
    class Loading<T>(val data: T?): Resource<T>()
}