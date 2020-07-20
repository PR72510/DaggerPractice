package com.example.daggerpractice.ui.main.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.daggerpractice.SessionManager
import com.example.daggerpractice.models.Post
import com.example.daggerpractice.network.main.MainApi
import com.example.daggerpractice.ui.main.Resource
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by PR72510 on 20/7/20.
 */
class PostsViewModel @Inject constructor(
    private val sessionManager: SessionManager,
    private val mainApi: MainApi
) : ViewModel() {
    private val TAG = "PostsViewModel"
    private val posts: MediatorLiveData<Resource<List<Post>>> = MediatorLiveData()

    fun observePosts(): LiveData<Resource<List<Post>>>{
        posts.value = Resource.Loading(null)

        val source = LiveDataReactiveStreams.fromPublisher(
            mainApi.getPostsFromUser(sessionManager.getAuthUser().value?.data?.id!!)
                .onErrorReturn {
                    val post = Post("", -1, "", -1)
                    listOf(post)
                }
                .map {
                    if(it.isNotEmpty() && it[0].id != -1)
                        Resource.Success(it)
                    else
                        Resource.Error<List<Post>>("Something went wrong..", null)
                }
                .subscribeOn(Schedulers.io())
        )

        posts.addSource(source) {
            posts.value = it
            posts.removeSource(source)
        }
        return posts
    }
}