package com.padcx.shared.data.model.impl

import com.padcx.shared.data.model.BaseModel
import com.padcx.shared.data.model.PatientModel
import com.padcx.shared.data.vo.PatientVO

/**
 * Created by Hnin Hsu Hlaing
 * on 11/26/2020
 */
object PatientModelImpl: PatientModel,BaseModel() {
    override fun saveNewPatientRecord(
        patientVO: PatientVO,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
       // mTheDB.patientDao().insertPatientData(patientVO)
//        mTheDB.patientDao().insertNewPatient(patientVO)
    }

    override fun saveSpecility() {

    }

    override fun BroadCastConsulationRequest() {

    }

    override fun CheckOutMedicine() {

    }
}