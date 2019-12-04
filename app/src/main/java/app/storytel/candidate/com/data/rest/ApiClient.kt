package app.storytel.candidate.com.data.rest

import app.storytel.candidate.com.data.model.Comment
import app.storytel.candidate.com.data.model.Photo
import app.storytel.candidate.com.data.model.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ApiClient @Inject constructor(private val apiService: ApiService) {

    suspend fun getAllPosts(): MutableList<Post> {
        return withContext(Dispatchers.IO) {
            apiService.getAllPosts().execute().body()!!
        }
    }

    suspend fun getAllPhotos(): MutableList<Photo> {
        return withContext(Dispatchers.IO) {
            apiService.getAllPhotos().execute().body()!!
        }
    }

    suspend fun getComments(id: Int): MutableList<Comment> {
        return withContext(Dispatchers.IO) {
            apiService.getComments(id).execute().body()!!
        }
    }
}
