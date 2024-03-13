package com.conexa.conexachallenge.data.api.model.response

import com.google.gson.annotations.SerializedName

data class NetworkErrorResponse(
    @SerializedName("description")
    val message: String?,
)