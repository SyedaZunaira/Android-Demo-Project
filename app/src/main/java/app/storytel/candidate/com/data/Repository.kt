package app.storytel.candidate.com.data

import app.storytel.candidate.com.data.model.Comment
import app.storytel.candidate.com.data.model.Photo
import app.storytel.candidate.com.data.model.Post
import app.storytel.candidate.com.data.model.PostAndImages
import app.storytel.candidate.com.data.rest.ApiClient
import javax.inject.Inject

class Repository @Inject constructor(private val apiClient: ApiClient) {

    suspend fun getPostAndImage(): PostAndImages {
        val posts = getAllPosts()
        val images = getAllPhotos()
        return PostAndImages(posts, images)
    }

    suspend fun getAllPosts(): MutableList<Post> {
        return apiClient.getAllPosts()
    }

    suspend fun getAllPhotos(): MutableList<Photo> {
        return apiClient.getAllPhotos()
    }

    suspend fun getComments(id: Int): MutableList<Comment> {
        return apiClient.getComments(id)
    }
}
