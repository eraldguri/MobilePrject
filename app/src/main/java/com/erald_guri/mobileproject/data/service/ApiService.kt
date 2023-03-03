package com.erald_guri.mobileproject.data.service

import com.erald_guri.mobileproject.data.model.PhotoModel
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("v2/list")
    suspend fun getPhotos(): Response<List<PhotoModel>>

    @GET("id/{id}/info")
    suspend fun getPhotoDetails(@Path("id") id: Int): Response<PhotoModel>

    @GET("id/{id}/800/1000")
    suspend fun getPhotoNormal(@Path("id") id: Int): ResponseBody

    @GET("id/{id}/800/1000")
    suspend fun getPhotoBlurred(@Path("id") id: Int, @Query("blur") blur: Int): ResponseBody

    @GET("id/{id}/800/1000")
    suspend fun getPhotoGrayScale(@Path("id") id: Int, @Query("grayscale") grayscale: Int): ResponseBody
}