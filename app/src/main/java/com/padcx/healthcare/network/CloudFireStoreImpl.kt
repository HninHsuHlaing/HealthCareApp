package com.padcx.healthcare.network


import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

/**
 * Created by Hnin Hsu Hlaing
 * on 11/24/2020
 */
object CloudFireStoreImpl : PatientApi {
    val db = Firebase.firestore
    private val storage = FirebaseStorage.getInstance()
    private val storageReference = storage.reference


}