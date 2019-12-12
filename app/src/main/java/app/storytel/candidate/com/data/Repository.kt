package app.storytel.candidate.com.data

import app.storytel.candidate.com.data.model.Comment
import app.storytel.candidate.com.data.model.PostAndImages
import app.storytel.candidate.com.data.rest.ApiClient

class Repository(private val apiClient: ApiClient) {

    suspend fun getPostAndImage(): PostAndImages {
        val posts = apiClient.getAllPosts()
        val images = apiClient.getAllPhotos()
        return PostAndImages(posts, images)
    }

    suspend fun getComments(id: Int): List<Comment> {
        return apiClient.getComments(id)
    }
}
