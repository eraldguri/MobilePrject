package com.erald_guri.mobileproject.view_models

import androidx.lifecycle.*
import com.erald_guri.mobileproject.data.repository.ApiRepository
import com.erald_guri.mobileproject.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(private val repository: ApiRepository): ViewModel() {

    fun getPhotos(page: Int, limit: Int) = liveData(Dispatchers.IO) {
        emit(NetworkResult.loading(data = null))
        try {
            emit(NetworkResult.success(data = repository.getPhotos(page, limit)))
        } catch (e: Exception) {
            emit(NetworkResult.error(data = null, message = e.message ?: "Error occurred"))
        }
    }

    fun getPhotoDetails(id: Int) = liveData(Dispatchers.IO) {
        emit(NetworkResult.loading(data = null))
        try {
            emit(NetworkResult.success(data = repository.getPhotoDetails(id)))
        } catch (e: Exception) {
            emit(NetworkResult.error(data = null, message = e.message ?: "Error occurred"))
        }
    }

    fun getPhotoNormal(id: Int) = liveData(Dispatchers.IO) {
        emit(NetworkResult.loading(data = null))
        try {
            emit(NetworkResult.success(data = repository.getPhotoNormal(id)))
        } catch (e: Exception) {
            emit(NetworkResult.error(data = null, message = e.message ?: "Error occurred"))
        }
    }

    fun getPhotoBlurred(id: Int, blur: Int) = liveData(Dispatchers.IO) {
        emit(NetworkResult.loading(data = null))
        try {
            emit(NetworkResult.success(data = repository.getPhotoBlurred(id, blur)))
        } catch (e: Exception) {
            emit(NetworkResult.error(data = null, message = e.message ?: "Error occurred"))
        }
    }

    fun getPhotoGrayScaled(id: Int, grayscale: Int) = liveData(Dispatchers.IO) {
        emit(NetworkResult.loading(data = null))
        try {
            emit(NetworkResult.success(data = repository.getPhotoGrayScale(id, grayscale)))
        } catch (e: Exception) {
            emit(NetworkResult.error(data = null, message = e.message ?: "Error occurred"))
        }
    }

}