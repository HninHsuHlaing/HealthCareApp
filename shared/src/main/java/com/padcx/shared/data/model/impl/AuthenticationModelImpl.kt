package com.padcx.shared.data.model.impl

import com.padcx.shared.data.model.AuthenticationModel
import com.padcx.shared.network.auth.AuthManager
import com.padcx.shared.network.auth.FirebaseAuthManager

/**
 * Created by Hnin Hsu Hlaing
 * on 11/25/2020
 */
object AuthenticationModelImpl :AuthenticationModel  {
    override var mAuthManager: AuthManager = FirebaseAuthManager

    override fun login(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mAuthManager.login(email, password, onSuccess, onFailure)
    }

    override fun register(
        username: String,
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mAuthManager.register(username, email, password, onSuccess, onFailure)
    }

    override fun updateProfile(photoUrl: String, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        mAuthManager.updateProfile(photoUrl, onSuccess, onFailure)
    }
}