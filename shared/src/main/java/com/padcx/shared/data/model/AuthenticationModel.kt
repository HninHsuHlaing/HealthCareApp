package com.padcx.shared.data.model

import com.padcx.shared.network.auth.AuthManager

/**
 * Created by Hnin Hsu Hlaing
 * on 11/25/2020
 */
interface AuthenticationModel {
    var mAuthManager: AuthManager

    fun login(email: String, password: String, onSuccess: () -> Unit, onFailure: (String) -> Unit)

    fun register(
        username: String,
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )


    fun updateProfile(
        photoUrl : String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )
}