package com.padcx.healthcare.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.padcx.healthcare.R
import com.padcx.healthcare.delegate.ConsultationAcceptDelegate
import com.padcx.healthcare.views.viewHolders.ConsultationAcceptViewHolder
import com.padcx.shared.adapters.BaseRecyclerAdapter
import com.padcx.shared.data.vo.ConsulationRequestVO

/**
 * Created by Hnin Hsu Hlaing
 * on 12/11/2020
 */
class ConsultationAapter(private val mDelegate: ConsultationAcceptDelegate) :
    BaseRecyclerAdapter<ConsultationAcceptViewHolder, ConsulationRequestVO>()  {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ConsultationAcceptViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.consultation_request_viewpod, parent, false)
        return ConsultationAcceptViewHolder(view, mDelegate)
    }
}