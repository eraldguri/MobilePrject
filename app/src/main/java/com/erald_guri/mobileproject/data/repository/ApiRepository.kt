package com.erald_guri.mobileproject.data.repository

import com.erald_guri.mobileproject.data.service.ApiService
import javax.inject.Inject

class ApiRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getPhotos() = apiService.getPhotos()

}