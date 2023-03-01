package com.erald_guri.mobileproject.view_models

import androidx.lifecycle.*
import com.erald_guri.mobileproject.data.model.PhotoModel
import com.erald_guri.mobileproject.data.repository.ApiRepository
import com.erald_guri.mobileproject.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(private val repository: ApiRepository): ViewModel() {

    fun getPhotos() = liveData(Dispatchers.IO) {
        emit(NetworkResult.loading(data = null))
        try {
            emit(NetworkResult.success(data = repository.getPhotos()))
        } catch (e: Exception) {
            emit(NetworkResult.error(data = null, message = e.message ?: "Error occurred"))
        }
    }

}