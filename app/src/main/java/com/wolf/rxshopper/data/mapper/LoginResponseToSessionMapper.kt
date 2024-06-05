package com.wolf.rxshopper.data.mapper

import com.wolf.rxshopper.domain.models.services.LoginResponse
import com.wolf.rxshopper.data.utils.Mapper
import com.wolf.rxshopper.domain.models.Session
import javax.inject.Inject


class LoginResponseToSessionMapper
@Inject constructor(): Mapper<LoginResponse?, Session> {
    override fun map(input: LoginResponse?): Session {
        requireNotNull(input)
        return Session(
            input.life
        )
    }
}