package com.example.kotlinmvvm.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Picture : Serializable {

    @SerializedName("large")
    var large: String? = null

    @SerializedName("medium")
    var medium: String? = null

    @SerializedName("thumbnail")
    var thumbnail: String? = null
}