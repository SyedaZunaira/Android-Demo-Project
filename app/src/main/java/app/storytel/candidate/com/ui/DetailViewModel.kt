package app.storytel.candidate.com.ui

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.storytel.candidate.com.data.Repository
import app.storytel.candidate.com.data.model.Comment
import app.storytel.candidate.com.data.model.Post
import app.storytel.candidate.com.utils.ApiStatus
import app.storytel.candidate.com.utils.NetworkUtils.isConnectedToNetwork
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(
        private val application: Application,
        private val repository: Repository
) : ViewModel() {

    private val _imageUrl = MutableLiveData<String>()
    val imageUrl: LiveData<String>
        get() = _imageUrl

    private val _post = MutableLiveData<Post>()
    val post: LiveData<Post>
        get() = _post

    private val _comments: MutableLiveData<List<Comment>> = MutableLiveData()
    val comments: LiveData<List<Comment>>
        get() = _comments

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    fun setData(imageUrl: String, post: Post) {
        this._imageUrl.value = imageUrl
        this._post.value = post
        getComments(post.id)
    }

    fun getComments(id: Int) {
        if (isConnectedToNetwork(application)) {
            viewModelScope.launch {
                try {
                    _status.value = ApiStatus.LOADING
                    val result = repository.getComments(id)
                    _status.value = ApiStatus.DONE
                    _comments.value = result
                } catch (e: Exception) {
                    _status.value = ApiStatus.ERROR
                    _comments.value = emptyList()
                }
            }
        } else
            _status.value = ApiStatus.ERROR
    }
}
