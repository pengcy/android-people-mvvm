package com.example.kotlinmvvm.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class People : Serializable {

    @SerializedName("gender")
    var gender: String? = null

    @SerializedName("name")
    var name: Name? = null

    @SerializedName("location")
    var location: Location? = null

    @SerializedName("email")
    var mail: String? = null

    @SerializedName("login")
    var userName: Login? = null

    @SerializedName("phone")
    var phone: String? = null

    @SerializedName("cell")
    var cell: String? = null

    @SerializedName("picture")
    var picture: Picture? = null

    var fullName: String? = null

    fun hasEmail(): Boolean {
        return mail != null && !mail!!.isEmpty()
    }
}