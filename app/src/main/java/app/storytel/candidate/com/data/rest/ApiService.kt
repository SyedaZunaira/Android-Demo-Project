package app.storytel.candidate.com.data.rest

import app.storytel.candidate.com.data.model.Comment
import app.storytel.candidate.com.data.model.Photo
import app.storytel.candidate.com.data.model.Post
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("posts")
    suspend fun getAllPosts(): List<Post>

    @GET("photos")
    suspend fun getAllPhotos(): List<Photo>

    @GET("comments?_limit=3")
    suspend fun getComments(@Query("postId") id: Int): List<Comment>
}
