package com.erald_guri.mobileproject.data.service

import com.erald_guri.mobileproject.data.model.PhotoModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("list")
    suspend fun getPhotos(): Response<List<PhotoModel>>

}