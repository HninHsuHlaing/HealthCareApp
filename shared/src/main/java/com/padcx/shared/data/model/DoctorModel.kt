package com.padcx.shared.data.model

import com.padcx.shared.data.vo.DoctorVO

/**
 * Created by Hnin Hsu Hlaing
 * on 11/26/2020
 */
interface DoctorModel {
    fun saveNewDoctorRecord(
        doctorVO: DoctorVO,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    )


    fun saveSpecility(

    )
}