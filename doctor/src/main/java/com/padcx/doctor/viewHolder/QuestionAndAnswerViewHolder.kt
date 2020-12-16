package com.padcx.doctor.viewHolder

import android.view.View
import com.padcx.doctor.delegate.QuestionAndAnswerDelegate
import com.padcx.shared.data.vo.QuestionAndAnswerVO
import com.padcx.shared.view.viewholder.BaseViewHolder
import kotlinx.android.synthetic.main.listitem_question_answer.view.*

/**
 * Created by Hnin Hsu Hlaing
 * on 12/12/2020
 */
class QuestionAndAnswerViewHolder (itemView: View, private val mDelegate: QuestionAndAnswerDelegate) :
        BaseViewHolder<QuestionAndAnswerVO>(itemView) {

    override fun bindData(data: QuestionAndAnswerVO) {

        data?.let {
            itemView.txt_question.text = "(${adapterPosition}) "+ data.question
            itemView.txt_answer.text =data.answer
        }

    }
}