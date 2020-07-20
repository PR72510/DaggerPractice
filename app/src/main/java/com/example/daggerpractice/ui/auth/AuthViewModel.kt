package com.example.daggerpractice.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.example.daggerpractice.SessionManager
import com.example.daggerpractice.models.User
import com.example.daggerpractice.network.auth.AuthApi
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


/**
 * Created by PR72510 on 15/7/20.
 */
class AuthViewModel @Inject constructor(
    private val authApi: AuthApi,
    private val sessionManager: SessionManager
) : ViewModel() {
    private val TAG = "AuthViewModel"


    fun authenticateWithId(id: Int) {
        sessionManager.authenticateWithId(queryUserId(id))
    }

    private fun queryUserId(userId: Int): LiveData<AuthResource<out User?>> {
        return LiveDataReactiveStreams.fromPublisher(
            authApi.getUser(userId)
                // instead of calling onError, do this
                .onErrorReturn { User("error@email.com", -1, "Error McErrorFace", "error.com") }
                .map { user ->
                    if (user.id == -1) {
                        return@map AuthResource.error("Could not authenticate", null)
                    } else {
                        return@map AuthResource.authenticated(user)
                    }
                }
                .subscribeOn(Schedulers.io())
        )
    }

    fun observeAuthState() = sessionManager.getAuthUser()
}