package app.storytel.candidate.com.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.storytel.candidate.com.data.Repository
import app.storytel.candidate.com.data.model.PostAndImages
import app.storytel.candidate.com.utils.ApiStatus
import app.storytel.candidate.com.utils.NetworkUtils
import kotlinx.coroutines.launch

class ListViewModel(
        private val networkUtils: NetworkUtils,
        private val repository: Repository
) : ViewModel() {

    private val _postAndImage = MutableLiveData<PostAndImages>()
    val postAndImage: LiveData<PostAndImages> = _postAndImage

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus> = _status

    init {
        getPostAndImageData()
    }

    fun getPostAndImageData() {
        if (networkUtils.isConnectedToNetwork()) {
            viewModelScope.launch {
                try {
                    _status.value = ApiStatus.LOADING
                    val result = repository.getPostAndImage()
                    _status.value = ApiStatus.DONE
                    _postAndImage.value = result
                } catch (e: Exception) {
                    _status.value = ApiStatus.ERROR
                    _postAndImage.value = PostAndImages(emptyList(), emptyList())
                }
            }
        } else
            _status.value = ApiStatus.ERROR
    }
}
