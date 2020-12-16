package com.padcx.shared.data.model.impl

import com.padcx.shared.data.model.BaseModel
import com.padcx.shared.data.model.DoctorModel
import com.padcx.shared.data.vo.DoctorVO

/**
 * Created by Hnin Hsu Hlaing
 * on 11/26/2020
 */
object DoctorModelImpl: DoctorModel,BaseModel() {
    override fun saveNewDoctorRecord(
        doctorVO: DoctorVO,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        mTheDB.doctorDao().insertDoctorData(doctorVO)
    }

    override fun saveSpecility() {

    }
}