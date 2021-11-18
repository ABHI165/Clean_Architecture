package com.abhi165.kooassignment.data

import com.abhi165.kooassignment.data.Response.PostsResponseBody
import retrofit2.http.GET

interface NetworkApi {

    @GET("v1/posts")
    suspend fun getPosts():PostsResponseBody
}