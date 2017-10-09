package com.example.kotlinmvvm.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Login : Serializable {

    @SerializedName("username")
    var userName: String? = null
}
