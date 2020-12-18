package com.padcx.healthcare.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.padcx.healthcare.R
import com.padcx.healthcare.delegate.ShippingAddressDelegate
import com.padcx.healthcare.views.viewHolders.ShippingAddressViewHolder
import com.padcx.shared.adapters.BaseRecyclerAdapter

/**
 * Created by Hnin Hsu Hlaing
 * on 12/18/2020
 */
class ShippingAddressAdapter(private val mDelegate: ShippingAddressDelegate, var mpreviousPosition: Int) :
        BaseRecyclerAdapter<ShippingAddressViewHolder, String>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShippingAddressViewHolder {

        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.listitem_shipping_address, parent, false)
        return ShippingAddressViewHolder(view,  mpreviousPosition, mDelegate)

    }
}