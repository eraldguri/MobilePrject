package com.erald_guri.mobileproject.data.model

import com.google.gson.annotations.SerializedName

data class PhotoModel(
    @SerializedName("id")
    var id: Int,

    @SerializedName("author")
    var author: String,

    @SerializedName("width")
    var width: Int,

    @SerializedName("height")
    var height: Int,

    @SerializedName("url")
    var url: String,

    @SerializedName("download_url")
    var downloadUrl: String
)
