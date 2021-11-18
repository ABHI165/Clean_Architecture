package com.abhi165.kooassignment.data.repository

import com.abhi165.kooassignment.data.NetworkApi
import com.abhi165.kooassignment.data.Response.PostsResponseBody
import com.abhi165.kooassignment.domain.repository.PostRespository
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(private val apiservice:NetworkApi):PostRespository {

    override suspend fun getPosts(): PostsResponseBody {
      return  apiservice.getPosts()
    }
}