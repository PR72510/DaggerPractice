package com.example.daggerpractice.ui.main.profile

import androidx.lifecycle.ViewModel
import com.example.daggerpractice.SessionManager
import javax.inject.Inject

/**
 * Created by PR72510 on 20/7/20.
 */

class ProfileViewModel @Inject constructor(private val sessionManager: SessionManager) :
    ViewModel() {

    private val TAG = "ProfileViewModel"

    fun getAuthenticatedUser() = sessionManager.getAuthUser()
}