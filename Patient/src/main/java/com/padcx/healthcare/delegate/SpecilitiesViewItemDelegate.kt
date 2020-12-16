package com.padcx.healthcare.delegate

import com.padcx.shared.data.vo.SpecialitiesVO

/**
 * Created by Hnin Hsu Hlaing
 * on 12/9/2020
 */
interface SpecilitiesViewItemDelegate {
    fun onTapSpeciality(specialitiesVO: SpecialitiesVO)
}