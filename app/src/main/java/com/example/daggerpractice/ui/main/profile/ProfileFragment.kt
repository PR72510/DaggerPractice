package com.example.daggerpractice.ui.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.daggerpractice.common.toast
import com.example.daggerpractice.databinding.FragmentProfileBinding
import com.example.daggerpractice.models.User
import com.example.daggerpractice.ui.auth.AuthResource
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class ProfileFragment : DaggerFragment() {
    private val TAG = "ProfileFragment"

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private lateinit var binding: FragmentProfileBinding
    private val viewModel: ProfileViewModel by viewModels { factory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        handleObservers()
    }

    private fun handleObservers() {
        viewModel.getAuthenticatedUser().removeObservers(viewLifecycleOwner)
        viewModel.getAuthenticatedUser().observe(viewLifecycleOwner, Observer { user ->
            when(user.status){
                AuthResource.AuthStatus.AUTHENTICATED -> {
                    showDetails(user.data)
                }
                AuthResource.AuthStatus.ERROR -> {
                    showError(user.message)
                }
                else -> {}
            }
        })
    }

    private fun showError(message: String?) {
        binding.tvEmail.text = message
    }

    private fun showDetails(data: User?) {
        binding.tvEmail.text = data?.email
        binding.tvName.text = data?.name
        binding.tvUsername.text = data?.username
    }
}