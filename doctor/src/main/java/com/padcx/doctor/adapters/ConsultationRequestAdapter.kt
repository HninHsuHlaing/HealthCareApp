package com.padcx.doctor.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.padcx.doctor.R
import com.padcx.doctor.delegate.ConsultationRequestDelegate
import com.padcx.doctor.viewHolder.ConsultationRequestViewHolder
import com.padcx.shared.adapters.BaseRecyclerAdapter
import com.padcx.shared.data.vo.ConsulationChatVO
import com.padcx.shared.data.vo.ConsulationRequestVO
import com.padcx.shared.data.vo.converters.ConsultatedPatientVO

/**
 * Created by Hnin Hsu Hlaing
 * on 12/9/2020
 */
class ConsultationRequestAdapter(private val mDelegate: ConsultationRequestDelegate):
    BaseRecyclerAdapter<ConsultationRequestViewHolder, ConsulationRequestVO>()  {
    var consulted_patient : List<ConsultatedPatientVO> = arrayListOf()

    fun setConsultedPatientList(list: List<ConsultatedPatientVO>)
    {
        consulted_patient =list
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ConsultationRequestViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.consultation_request_item, parent, false)
        return ConsultationRequestViewHolder(view, consulted_patient,mDelegate)
    }
}