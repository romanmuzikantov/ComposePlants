package com.example.soplant.domain.repositories

import com.example.soplant.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    fun loginWithCredentials(username: String, password: String): Flow<Resource<Boolean>>
    fun loginWithSSO()
    fun signupUser(email: String, username: String, password: String, location: String): Flow<Resource<Boolean>>
    fun validateUser(email: String, code: String): Flow<Resource<Boolean>>
    fun resendCode(email: String): Flow<Resource<Boolean>>
    fun resetPassword(email: String): Flow<Resource<Boolean>>
    fun confirmReset(newPassword: String, code: String): Flow<Resource<Boolean>>
    fun federateSignIn(username: String, location: String): Flow<Resource<Boolean>>
    fun signOut(): Flow<Resource<Boolean>>
}