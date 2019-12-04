package app.storytel.candidate.com.ui

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.storytel.candidate.com.R
import app.storytel.candidate.com.data.Repository
import app.storytel.candidate.com.data.model.PostAndImages
import app.storytel.candidate.com.utils.NetworkUtils.isConnectedToNetwork
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListViewModel @Inject constructor(
        private val application: Application,
        private val repository: Repository
) : ViewModel() {

    private val _postAndImageLiveData: MutableLiveData<PostAndImages> = MutableLiveData()
    val postAndImageLiveData: LiveData<PostAndImages>
        get() = _postAndImageLiveData

    private val _isLoadingLiveData = MutableLiveData<Boolean>()
    val isLoadingLiveData: LiveData<Boolean>
        get() = _isLoadingLiveData

    fun getAllData() {
        if (isConnectedToNetwork(application)) {
            _isLoadingLiveData.value = true
            viewModelScope.launch {
                val result = repository.getPostAndImage()
//                if (result.isNotEmpty())
                _postAndImageLiveData.value = result
//                else
//                    Toast.makeText(application, R.string.try_again, Toast.LENGTH_SHORT).show()
                _isLoadingLiveData.value = false
            }
        } else
            Toast.makeText(application, R.string.not_connected, Toast.LENGTH_SHORT).show()
    }

}
