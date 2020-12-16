package com.padcx.doctor.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.padcx.doctor.R
import com.padcx.doctor.delegate.ConsultationAcceptDelegate
import com.padcx.doctor.viewHolder.ConsultationAcceptViewHolder
import com.padcx.shared.adapters.BaseRecyclerAdapter
import com.padcx.shared.data.vo.ConsulationChatVO
import com.padcx.shared.data.vo.ConsulationRequestVO

/**
 * Created by Hnin Hsu Hlaing
 * on 12/9/2020
 */
class ConsultationAcceptAdapter(private val mDelegate: ConsultationAcceptDelegate) :
    BaseRecyclerAdapter<ConsultationAcceptViewHolder, ConsulationChatVO>()  {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ConsultationAcceptViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.consultation_accept_item, parent, false)
        return ConsultationAcceptViewHolder(view, mDelegate)
    }
}