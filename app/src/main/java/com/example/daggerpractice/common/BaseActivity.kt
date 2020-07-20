package com.example.daggerpractice.common

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.daggerpractice.SessionManager
import com.example.daggerpractice.ui.auth.AuthActivity
import com.example.daggerpractice.ui.auth.AuthResource
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

/**
 * Created by PR72510 on 17/7/20.
 */
abstract class BaseActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleObservers()
    }

    private fun handleObservers() {
        sessionManager.getAuthUser().observe(this, Observer { user ->
            when (user.status) {
                AuthResource.AuthStatus.LOADING -> {
                }
                AuthResource.AuthStatus.AUTHENTICATED -> {
                }
                AuthResource.AuthStatus.ERROR -> {
                    toast(user.message.toString().plus("\nEnter no. between 1 & 10"))
                }
                AuthResource.AuthStatus.NOT_AUTHENTICATED -> {
                    navLoginScreen()
                }
            }
        })
    }

    private fun navLoginScreen() {
        startActivity(Intent(this, AuthActivity::class.java))
        finish()
    }
}