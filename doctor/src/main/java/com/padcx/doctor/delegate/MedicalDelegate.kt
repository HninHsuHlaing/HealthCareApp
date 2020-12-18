package com.padcx.doctor.delegate

import com.padcx.shared.data.vo.FrequentlyMedicineVO

/**
 * Created by Hnin Hsu Hlaing
 * on 12/16/2020
 */
interface MedicalDelegate {
    fun onTapSelectMedicine(medicineVO: FrequentlyMedicineVO)
    fun onTapRemoveMedicine(medicineVO: FrequentlyMedicineVO)
}