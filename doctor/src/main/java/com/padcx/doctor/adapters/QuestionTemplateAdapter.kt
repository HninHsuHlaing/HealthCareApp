package com.padcx.doctor.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.padcx.doctor.R
import com.padcx.doctor.delegate.QuestionTemplateDelegate
import com.padcx.doctor.viewHolder.QuestionTemplateViewHolder
import com.padcx.shared.adapters.BaseRecyclerAdapter
import com.padcx.shared.data.vo.GeneralQuestionTemplateVO

/**
 * Created by Hnin Hsu Hlaing
 * on 12/16/2020
 */
class QuestionTemplateAdapter (private val mDelegate: QuestionTemplateDelegate) :
        BaseRecyclerAdapter<QuestionTemplateViewHolder, GeneralQuestionTemplateVO>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionTemplateViewHolder {

        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.listitem_question_template, parent, false)
        return QuestionTemplateViewHolder(view, mDelegate)

    }
}