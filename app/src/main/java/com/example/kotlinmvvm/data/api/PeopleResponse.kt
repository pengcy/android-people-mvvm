package com.example.kotlinmvvm.data.api

import com.example.kotlinmvvm.data.models.People
import com.google.gson.annotations.SerializedName

class PeopleResponse {

    @SerializedName("results")
    var peopleList: List<People>? = null
}