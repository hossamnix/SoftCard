package com.surepay.auth_domain.user_case

import com.surepay.auth_domain.model.Login
import com.surepay.auth_domain.repositpry.AuthRepository
import com.surepay.core.util.Resource

class LoginUseCase(
    private val authRepository: AuthRepository
    ){
        suspend operator fun invoke(
        email: String,
        password: String
        ):Resource<Login>{
            return authRepository.login(email,password)

        }


}