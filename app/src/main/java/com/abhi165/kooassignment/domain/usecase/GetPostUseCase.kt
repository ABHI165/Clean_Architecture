package com.abhi165.kooassignment.domain.usecase

import com.abhi165.kooassignment.domain.model.PostsList
import com.abhi165.kooassignment.domain.repository.PostRespository
import com.abhi165.kooassignment.utill.Resource
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.lang.Exception
import javax.inject.Inject

class GetPostUseCase @Inject constructor(private val repository:PostRespository) {
      operator fun invoke() = flow<Resource<List<PostsList>>>{
       try {
           emit(Resource.Loading())
           val posts =  repository.getPosts()
          val postByUserId =  posts.data.groupBy { it.userId }
           val postModel = mutableListOf<PostsList>()
           postByUserId.forEach {
              val postsList = PostsList(it.key,it.value)
               postModel.add(postsList)
           }
           emit(Resource.Success(postModel))
       }
       catch (e:HttpException){
           emit(Resource.Error(e.message()))
       }
          catch (e:Exception){  emit(Resource.Error("Something Went Wrong"))}

    }
}