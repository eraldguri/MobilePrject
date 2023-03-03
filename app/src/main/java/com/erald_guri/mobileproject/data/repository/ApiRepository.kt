package com.erald_guri.mobileproject.data.repository

import com.erald_guri.mobileproject.data.service.ApiService
import javax.inject.Inject

class ApiRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getPhotos() = apiService.getPhotos()

    suspend fun getPhotoDetails(id: Int) = apiService.getPhotoDetails(id)

    suspend fun getPhotoNormal(id: Int) = apiService.getPhotoNormal(id)

    suspend fun getPhotoBlurred(id: Int, blur: Int) = apiService.getPhotoBlurred(id, blur)

    suspend fun getPhotoGrayScale(id: Int, grayscale: Int) = apiService.getPhotoGrayScale(id, grayscale)

}