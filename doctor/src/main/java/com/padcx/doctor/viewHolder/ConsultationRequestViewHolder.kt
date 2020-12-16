package com.padcx.doctor.viewHolder

import android.view.View
import com.padcx.doctor.R
import com.padcx.doctor.delegate.ConsultationRequestDelegate
import com.padcx.shared.data.vo.ConsulationRequestVO
import com.padcx.shared.data.vo.converters.ConsultatedPatientVO
import com.padcx.shared.util.ImageUtil
import com.padcx.shared.view.viewholder.BaseViewHolder
import kotlinx.android.synthetic.main.consultation_request_item.view.*

/**
 * Created by Hnin Hsu Hlaing
 * on 12/10/2020
 */
class ConsultationRequestViewHolder(itemView: View, var consulted_patient :List<ConsultatedPatientVO>,
                                    private val mDelegate: ConsultationRequestDelegate) :
        BaseViewHolder<ConsulationRequestVO>(itemView)  {
    override fun bindData(data: ConsulationRequestVO) {

        data?.let {
            ImageUtil().showImage(itemView.img_patient,data.patient?.photo.toString(), R.drawable.user)
            itemView.txt_patient_name.text = data.patient?.name
            itemView.txt_patient_dateofbirth.text = data.patient?.dob

            var data = consulted_patient.filter {
                it.patient_id == data.patient.id
            }

            if(data.isEmpty())
            {
                itemView.txt_patient_type.text =  itemView.resources.getString(R.string.new_patient)
                itemView.btnNext.visibility =View.GONE
                itemView.btnPostpone.visibility = View.GONE
                itemView.btnSkip.visibility = View.VISIBLE
            }
            else
            {
                itemView.txt_patient_type.text =  itemView.resources.getString(R.string.consulated_patient)
                itemView.btnNext.visibility =View.VISIBLE
                itemView.btnPostpone.visibility = View.VISIBLE
                itemView.btnSkip.visibility = View.GONE
            }
        }

        itemView.btnAccept.setOnClickListener {
            mDelegate.onTapAccept(data)
        }

        itemView.btnNext.setOnClickListener {
            mDelegate.onTapNext(data)
        }

        itemView.btnPostpone.setOnClickListener {
            mDelegate.onTapPostpone(data)
        }

        itemView.btnSkip.setOnClickListener {
            mDelegate.onTapSkip(data)
        }

    }
}