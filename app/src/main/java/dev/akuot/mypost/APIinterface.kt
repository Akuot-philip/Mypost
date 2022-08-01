package dev.akuot.mypost

import retrofit2.Call
import retrofit2.http.GET

interface APIinterface {
    @GET("/post")
    fun getPosts():Call<List<Post>>


}