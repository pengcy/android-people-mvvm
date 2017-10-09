package com.example.kotlinmvvm.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class Location : Serializable {

    @SerializedName("street")
    var street: String? = null

    @SerializedName("city")
    var city: String? = null

    @SerializedName("state")
    var state: String? = null

    @SerializedName("zip")
    var zip: String? = null
}
