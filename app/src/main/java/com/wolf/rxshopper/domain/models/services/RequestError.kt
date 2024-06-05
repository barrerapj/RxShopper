package com.wolf.rxshopper.domain.models.services

import com.wolf.rxshopper.data.utils.ErrorType

data class RequestError(
    val type: ErrorType = ErrorType.UNKNOWN,
    val httpCode: Int? = null,
    val message: String? = null
)