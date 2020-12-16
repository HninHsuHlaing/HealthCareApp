package com.padcx.shared.data.model

import com.padcx.shared.data.vo.PatientVO

/**
 * Created by Hnin Hsu Hlaing
 * on 11/26/2020
 */
interface PatientModel {
    fun saveNewPatientRecord(
        patientVO: PatientVO,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    )

    fun saveSpecility()

    fun BroadCastConsulationRequest()

    fun CheckOutMedicine()


}