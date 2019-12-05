package app.storytel.candidate.com.data.rest

import app.storytel.candidate.com.data.model.Comment
import app.storytel.candidate.com.data.model.Photo
import app.storytel.candidate.com.data.model.Post
import javax.inject.Inject

class ApiClient @Inject constructor(private val apiService: ApiService) {

    suspend fun getAllPosts(): List<Post> {
        return apiService.getAllPosts()
    }

    suspend fun getAllPhotos(): List<Photo> {
        return apiService.getAllPhotos()
    }

    suspend fun getComments(id: Int): List<Comment> {
        return apiService.getComments(id)
    }
}
