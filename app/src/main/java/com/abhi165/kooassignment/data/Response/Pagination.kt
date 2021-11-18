package com.abhi165.kooassignment.data.Response


import com.squareup.moshi.Json


data class Pagination(
    @Json(name = "limit")
    val limit: Int,
    @Json(name = "links")
    val links: Links,
    @Json(name = "page")
    val page: Int,
    @Json(name = "pages")
    val pages: Int,
    @Json(name = "total")
    val total: Int
)