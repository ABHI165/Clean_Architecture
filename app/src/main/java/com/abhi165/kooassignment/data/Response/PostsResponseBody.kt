package com.abhi165.kooassignment.data.Response


import com.squareup.moshi.Json


data class PostsResponseBody(
    @Json(name = "data")
    val `data`: List<Data>,
    @Json(name = "meta")
    val meta: Meta
)