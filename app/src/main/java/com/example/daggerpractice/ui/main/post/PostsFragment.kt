package com.example.daggerpractice.ui.main.post

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.daggerpractice.databinding.FragmentPostsBinding
import com.example.daggerpractice.ui.main.Resource
import com.example.daggerpractice.utils.VerticalSpaceItemDecoration
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class PostsFragment : DaggerFragment() {
    private val TAG = "PostsFragment"
    private lateinit var binding: FragmentPostsBinding

    @Inject
    lateinit var adapter: PostsAdapter
    @Inject
    lateinit var providerFactory: ViewModelProvider.Factory
    @Inject
    lateinit var itemDecorator: VerticalSpaceItemDecoration

    private val viewModel: PostsViewModel by viewModels { providerFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        handleObservers()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.rvPosts.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPosts.adapter = adapter
        binding.rvPosts.addItemDecoration(itemDecorator)
    }

    private fun handleObservers() {
        viewModel.observePosts().removeObservers(viewLifecycleOwner)
        viewModel.observePosts().observe(viewLifecycleOwner, Observer { posts ->
            when (posts) {
                is Resource.Success -> {
                    adapter.posts = posts.data
                }
                is Resource.Error -> {

                }
                is Resource.Loading -> {

                }
            }
        })
    }
}