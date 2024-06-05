package com.wolf.rxshopper.domain.models.services

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ServiceError(
    val timestamp: String,
    val status : Int,
    val error: String,
    val message: String
)