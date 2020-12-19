package com.padcx.healthcare.delegate

import com.padcx.shared.data.vo.RecentlyDoctorVO

/**
 * Created by Hnin Hsu Hlaing
 * on 12/9/2020
 */
interface RecentlyDoctorViewItemDelegate {
    fun onTapRecentDoctor(doctorVO: RecentlyDoctorVO)
}