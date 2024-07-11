package com.compass.compasschallenge.data.api.model.response

import com.google.gson.annotations.SerializedName

data class NetworkErrorResponse(
    @SerializedName("description")
    val message: String?,
)