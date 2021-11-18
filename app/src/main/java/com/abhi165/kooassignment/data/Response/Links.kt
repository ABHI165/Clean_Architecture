package com.abhi165.kooassignment.data.Response


import com.squareup.moshi.Json


data class Links(
    @Json(name = "current")
    val current: String,
    @Json(name = "next")
    val next: String,
    @Json(name = "previous")
    val previous: Any
)