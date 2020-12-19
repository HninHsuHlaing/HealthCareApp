package com.padcx.healthcare.views.viewHolders

import android.view.View
import com.padcx.healthcare.R
import com.padcx.healthcare.delegate.RecentlyDoctorViewItemDelegate
import com.padcx.shared.data.vo.RecentlyDoctorVO
import com.padcx.shared.util.ImageUtil
import com.padcx.shared.view.viewholder.BaseViewHolder
import kotlinx.android.synthetic.main.recent_doctor_item.view.*

/**
 * Created by Hnin Hsu Hlaing
 * on 12/9/2020
 */
class RecentlyDoctorViewHolder(itemView: View, private val mDelegate: RecentlyDoctorViewItemDelegate) :
    BaseViewHolder<RecentlyDoctorVO>(itemView) {
    override fun bindData(data: RecentlyDoctorVO) {
        data?.let {
            itemView.txt_doctorname.text =data?.name
            itemView.txt_specialityname.text =data?.speciality
            data?.photo?.let{
                ImageUtil().showImage(itemView.img_doctor_profile,it, R.drawable.doctor_thumbnail)
            }

            itemView.card_recentdoctor.setOnClickListener {
                mDelegate.onTapRecentDoctor(data)
            }

        }
    }
}