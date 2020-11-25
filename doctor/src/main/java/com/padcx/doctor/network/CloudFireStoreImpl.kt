package com.padcx.doctor.network

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage

/**
 * Created by Hnin Hsu Hlaing
 * on 11/24/2020
 */
object CloudFireStoreImpl : DoctorApi {
    val db = Firebase.firestore
    private val storage = FirebaseStorage.getInstance()
    private val storageReference = storage.reference
}