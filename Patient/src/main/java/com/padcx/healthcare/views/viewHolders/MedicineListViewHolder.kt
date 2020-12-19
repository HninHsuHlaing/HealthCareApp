package com.padcx.healthcare.views.viewHolders

import android.view.View
import com.padcx.healthcare.delegate.CheckoutDelegate
import com.padcx.shared.data.vo.PrescriptionVO
import com.padcx.shared.view.viewholder.BaseViewHolder
import kotlinx.android.synthetic.main.medicine_listitem.view.*

/**
 * Created by Hnin Hsu Hlaing
 * on 12/18/2020
 */
class MedicineListViewHolder(itemView: View, private val mDelegate: CheckoutDelegate) :
        BaseViewHolder<PrescriptionVO>(itemView) {

    override fun bindData(data: PrescriptionVO) {

        data?.let {
            var subtotal = data.price.toInt() * data.count.toInt()
            itemView.txt_price.text = subtotal.toString()+" ကျပ်"
            itemView.txt_tablet.text = data.count +" ကတ်"
            itemView.txt_medicinename.text = data.medicine_name
        }

    }
}