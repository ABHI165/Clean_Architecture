package com.abhi165.kooassignment.presentation.post_screen

import com.abhi165.kooassignment.domain.model.PostsList

data class PostState(
    val isLoading: Boolean = false,
    val posts: List<PostsList> = emptyList(),
    val error: String = ""
)