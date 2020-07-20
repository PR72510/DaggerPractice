package com.example.daggerpractice.ui.auth

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import com.example.daggerpractice.models.User

/**
 * Created by PR72510 on 17/7/20.
 */

class AuthResource<T>(
    @field:NonNull @NonNull val status: AuthStatus,
    @field:Nullable  val data: T?,
    @field:Nullable @Nullable val message: String?
) {

    enum class AuthStatus {
        AUTHENTICATED, ERROR, LOADING, NOT_AUTHENTICATED
    }

    companion object {
        fun <T> authenticated(@Nullable data: T): AuthResource<T> {
            return AuthResource(
                AuthStatus.AUTHENTICATED,
                data,
                null
            )
        }

        fun <T> error(@NonNull msg: String?, @Nullable data: T): AuthResource<T> {
            return AuthResource(
                AuthStatus.ERROR,
                data,
                msg
            )
        }

        fun <T> loading(@Nullable data: T): AuthResource<T> {
            return AuthResource(
                AuthStatus.LOADING,
                data,
                null
            )
        }

        fun <T> logout(): AuthResource<User?>?{
            return AuthResource(
                AuthStatus.NOT_AUTHENTICATED,
                null,
                null
            )
        }
    }
}