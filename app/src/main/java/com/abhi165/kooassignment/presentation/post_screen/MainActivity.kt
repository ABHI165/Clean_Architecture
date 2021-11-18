package com.abhi165.kooassignment.presentation.post_screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.abhi165.kooassignment.presentation.components.PostItem
import com.abhi165.kooassignment.presentation.theme.BGMaterial
import com.abhi165.kooassignment.presentation.theme.DarkYellow
import com.abhi165.kooassignment.presentation.theme.KooAssignmentTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val mainViewModel:MainViewModel by viewModels()

    @ExperimentalMaterialApi
    @ExperimentalFoundationApi
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel.getPosts()
        setContent {
            val state =  mainViewModel.state.value
            val expendedids = mainViewModel.expandedPostIds.value
            val scaffoldState = rememberScaffoldState()
            val coroutinescope = rememberCoroutineScope()
            Scaffold(scaffoldState = scaffoldState,backgroundColor = BGMaterial) {
                when {
                    state.isLoading -> Column(Modifier.fillMaxWidth().fillMaxHeight(),horizontalAlignment = Alignment.CenterHorizontally,verticalArrangement = Arrangement.Center){
                        CircularProgressIndicator(
                            modifier = Modifier.size(50.dp),
                            color = DarkYellow,
                            strokeWidth = 3.dp
                        )
                    }
                    state.error!="" -> coroutinescope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(state.error,"Ok",SnackbarDuration.Short)
                    }
                    else -> {
                        state.posts.forEach { items->
                            LazyColumn{
                               /* stickyHeader(items.user_id) {
                                    Text(text = "User Id ${items.user_id}",style = MaterialTheme.typography.subtitle1,textAlign = TextAlign.Center,color = Color.Black)
                                }*/

                                itemsIndexed(items.posts) {id,post->
                                    PostItem(postdata = post, onPostClicked = {mainViewModel.onPostClicked(id)}, isExpanded =expendedids.contains(id) )

                                }
                            }
                        }
                    }
                }


            }
        }
    }
}

