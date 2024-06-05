package com.wolf.rxshopper.domain.models.services

data class UserRegisterRequest(
    val name: String,
    val email: String,
    val password: String
)