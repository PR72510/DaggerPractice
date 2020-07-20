package com.example.daggerpractice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.daggerpractice.models.User
import com.example.daggerpractice.ui.auth.AuthResource
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by PR72510 on 17/7/20.
 */

@Singleton
class SessionManager @Inject constructor() {
    private val TAG = "SessionManager"
    private val cachedUser = MediatorLiveData<AuthResource<out User?>>()

    fun authenticateWithId(source: LiveData<AuthResource<out User?>>){
        if(cachedUser != null){
            cachedUser.value = AuthResource.loading(User(id = -2))
            cachedUser.addSource(source){ user ->
                cachedUser.value = user
                cachedUser.removeSource(source)
            }
        }
    }

    fun logout(){
        cachedUser.value = AuthResource.logout<User>()
    }

    fun getAuthUser(): LiveData<AuthResource<out User?>> {
        return cachedUser
    }
}