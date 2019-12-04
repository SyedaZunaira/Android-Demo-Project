package app.storytel.candidate.com.data.rest

import app.storytel.candidate.com.data.model.Comment
import app.storytel.candidate.com.data.model.Photo
import app.storytel.candidate.com.data.model.Post
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("posts")
    fun getAllPosts(): Call<MutableList<Post>>

    @GET("photos")
    fun getAllPhotos(): Call<MutableList<Photo>>

    @GET("posts/{id}/comments")
    fun getComments(@Path("id") id: Int): Call<MutableList<Comment>>
}
