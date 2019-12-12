package app.storytel.candidate.com.data

import app.storytel.candidate.com.data.rest.ApiClient
import io.mockk.clearMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Test

class RepositoryTest {

    private val apiClient: ApiClient = mockk()
    private val repository = Repository(apiClient)

    @Test
    fun `call to getPostAndImage() calls getAllPosts() and getAllPhotos() on apiClient`() = runBlocking {
        coEvery { apiClient.getAllPosts() } returns emptyList()
        coEvery { apiClient.getAllPhotos() } returns emptyList()
        repository.getPostAndImage()
        coVerify(exactly = 1) {
            apiClient.getAllPosts()
            apiClient.getAllPhotos()
        }
    }

    @Test
    fun `call to getComments() calls getComments() on apiClient`() = runBlocking {
        val id = 1
        coEvery { apiClient.getComments(id) } returns emptyList()
        repository.getComments(id)
        coVerify(exactly = 1) { apiClient.getComments(id) }
    }

    @After
    fun teardown() = clearMocks(apiClient)

}