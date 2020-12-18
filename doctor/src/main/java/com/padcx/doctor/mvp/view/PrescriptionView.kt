package com.padcx.doctor.mvp.view

import com.padcx.shared.data.vo.FrequentlyMedicineVO
import com.padcx.shared.mvp.view.BaseView

/**
 * Created by Hnin Hsu Hlaing
 * on 12/16/2020
 */
interface PrescriptionView: BaseView {
    fun displayMedicineList(list: List<FrequentlyMedicineVO>)
    fun displayRoutinechooseDialog(medicineVO: FrequentlyMedicineVO)
    fun removeMedicine(medicineVO: FrequentlyMedicineVO)
    fun finishConsulation()
}