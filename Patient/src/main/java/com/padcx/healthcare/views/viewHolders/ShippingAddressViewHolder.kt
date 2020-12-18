package com.padcx.healthcare.views.viewHolders

import android.util.Log
import android.view.View
import com.padcx.healthcare.R
import com.padcx.healthcare.delegate.ShippingAddressDelegate
import com.padcx.shared.view.viewholder.BaseViewHolder
import kotlinx.android.synthetic.main.listitem_shipping_address.view.*

/**
 * Created by Hnin Hsu Hlaing
 * on 12/18/2020
 */
class ShippingAddressViewHolder(itemView: View, var previousPosition : Int, private val mDelegate: ShippingAddressDelegate) :
        BaseViewHolder<String>(itemView) {

    override fun bindData(data: String) {
        itemView.listitem_address.setBackgroundResource(R.drawable.bg_rounded_border_grey)
        Log.d("previous Postion ${previousPosition}", "currentPosition ${adapterPosition}")
        if (adapterPosition == previousPosition) {
            itemView.listitem_address.setBackgroundResource(R.drawable.bg_rounded_corner_blue)
        }
        data?.let {
            itemView.listitem_address.text = data
        }

        itemView.listitem_address.setOnClickListener {
            mDelegate.onTapSelected(data, adapterPosition)
        }

    }
}