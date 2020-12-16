package com.padcx.shared.view.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Hnin Hsu Hlaing
 * on 11/24/2020
 */
abstract class BaseViewHolder <T>(itemView: View)
    : RecyclerView.ViewHolder(itemView) {

    var mData : T? = null

    abstract fun bindData(data : T)
}