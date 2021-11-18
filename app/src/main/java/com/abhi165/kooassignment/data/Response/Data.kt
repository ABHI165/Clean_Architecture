package com.abhi165.kooassignment.data.Response

import com.squareup.moshi.Json



data class Data(
    @Json(name = "body")
    val body: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "title")
    val title: String,
    @Json(name = "user_id")
    val userId: Int
)