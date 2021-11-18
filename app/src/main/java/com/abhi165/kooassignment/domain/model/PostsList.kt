package com.abhi165.kooassignment.domain.model

import androidx.compose.ui.graphics.Color
import com.abhi165.kooassignment.data.Response.Data

data class PostsList (
    val user_id:Int,
    val posts:List<Data>,
    val background:Color = Color.Magenta
        )
