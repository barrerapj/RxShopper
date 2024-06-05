package com.wolf.rxshopper.data.mapper

import com.wolf.rxshopper.domain.models.services.UserRegisterRequest
import com.wolf.rxshopper.data.utils.Mapper
import com.wolf.rxshopper.domain.models.UserRegister
import javax.inject.Inject


class UserRegisterToUserRegisterRequest
@Inject constructor() : Mapper<UserRegister, UserRegisterRequest> {
    override fun map(input: UserRegister): UserRegisterRequest {
        return UserRegisterRequest(
            input.name,
            input.email,
            input.password
        )
    }
}