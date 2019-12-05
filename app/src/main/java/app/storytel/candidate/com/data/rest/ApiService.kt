package app.storytel.candidate.com.data.rest

import app.storytel.candidate.com.data.model.Comment
import app.storytel.candidate.com.data.model.Photo
import app.storytel.candidate.com.data.model.Post
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("posts")
    suspend fun getAllPosts(): List<Post>

    @GET("photos")
    suspend fun getAllPhotos(): List<Photo>

    @GET("posts/{id}/comments")
    suspend fun getComments(@Path("id") id: Int): List<Comment>
}
