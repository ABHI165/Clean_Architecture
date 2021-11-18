package com.abhi165.kooassignment.presentation.post_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhi165.kooassignment.domain.usecase.GetPostUseCase
import com.abhi165.kooassignment.utill.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject
constructor(private val getPostUseCase: GetPostUseCase) :ViewModel() {

    private  val  _state = mutableStateOf(PostState())
    val state get() =  _state

    private val _expandedPostIds = mutableStateOf(listOf<Int>())
    val expandedPostIds: State<List<Int>> get() = _expandedPostIds


 fun getPosts(){
     getPostUseCase().onEach {resource ->

         when(resource){
             is Resource.Loading -> {
                 _state.value = PostState(true)
             }

             is Resource.Success ->{
                 _state.value = PostState(false,resource.data?: emptyList())
             }

             is Resource.Error ->{
                 _state.value = PostState(false, emptyList(),resource.message?:"")
             }
         }



     }.launchIn(viewModelScope)
 }


    fun onPostClicked(id:Int){
        _expandedPostIds.value = _expandedPostIds.value.toMutableList().also {
            if(it.contains(id)) it.remove(id) else it.add(id)
        }
    }

}