package com.abhi165.kooassignment.domain.repository

import com.abhi165.kooassignment.data.NetworkApi
import com.abhi165.kooassignment.data.Response.PostsResponseBody
import dagger.Provides
import javax.inject.Inject



interface PostRespository  {
    suspend fun getPosts() :PostsResponseBody
}