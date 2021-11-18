package com.abhi165.kooassignment.data.Response


import com.squareup.moshi.Json


data class Meta(
    @Json(name = "pagination")
    val pagination: Pagination
)