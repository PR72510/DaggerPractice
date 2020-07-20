package com.example.daggerpractice.ui.auth

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.RequestManager
import com.example.daggerpractice.common.toast
import com.example.daggerpractice.databinding.ActivityAuthBinding
import com.example.daggerpractice.ui.main.MainActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity() {

    private val TAG = "AuthActivity"
    private lateinit var binding: ActivityAuthBinding

    @Inject
    lateinit var providerFactory: ViewModelProvider.Factory

    @Inject
    lateinit var logo: Drawable

    @Inject
    lateinit var requestManager: RequestManager

    private val viewModel: AuthViewModel by viewModels { providerFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setLogo()
        handleObservers()
        setListeners()
    }

    private fun setListeners() {
        binding.btnLogin.setOnClickListener {
            attemptLogin()
        }
    }

    private fun attemptLogin() {
        val id = binding.tietUserId.text.toString()
        if (id.isBlank())
            return
        else
            viewModel.authenticateWithId(id.toInt())
    }

    private fun handleObservers() {
        viewModel.observeAuthState().observe(this, Observer { user ->
            when (user.status) {
                AuthResource.AuthStatus.LOADING -> {
                    showProgress(true)
                }
                AuthResource.AuthStatus.AUTHENTICATED -> {
                    showProgress(false)
                    navMainScreen()
                }
                AuthResource.AuthStatus.ERROR -> {
                    toast(user.message.toString().plus("\nEnter no. between 1 & 10") )
                    showProgress(false)
                }
                AuthResource.AuthStatus.NOT_AUTHENTICATED -> {
                    showProgress(false)
                }
            }
        })
    }

    private fun navMainScreen() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun setLogo() {
        requestManager.load(logo)
            .into(binding.ivLogo)
    }

    private fun showProgress(isVisible: Boolean) {
        if (isVisible)
            binding.rlProgress.visibility = View.VISIBLE
        else
            binding.rlProgress.visibility = View.GONE
    }
}