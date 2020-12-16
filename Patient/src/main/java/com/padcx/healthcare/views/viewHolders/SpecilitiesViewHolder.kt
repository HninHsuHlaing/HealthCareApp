package com.padcx.healthcare.views.viewHolders

import android.view.View
import com.padcx.healthcare.R
import com.padcx.healthcare.delegate.SpecilitiesViewItemDelegate
import com.padcx.shared.data.vo.SpecialitiesVO
import com.padcx.shared.util.ImageUtil
import com.padcx.shared.view.viewholder.BaseViewHolder
import kotlinx.android.synthetic.main.specilities_item.view.*

/**
 * Created by Hnin Hsu Hlaing
 * on 12/9/2020
 */
class SpecilitiesViewHolder (itemView: View, private val mDelegate: SpecilitiesViewItemDelegate) :
    BaseViewHolder<SpecialitiesVO>(itemView) {
    override fun bindData(data: SpecialitiesVO) {
        data?.let {
            itemView.txt_specialityname.text =data.name
            data?.photo?.let{
                ImageUtil().showImage(itemView.img_speciality,it,  R.drawable.speciality_thumbnail)
            }
        }

        itemView.card_speciality.setOnClickListener{
            mDelegate.onTapSpeciality(data)
        }
    }
}