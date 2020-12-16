package com.padcx.healthcare.views.viewHolders

import android.view.View
import com.padcx.healthcare.delegate.QuestionAndAnswerDelegate
import com.padcx.shared.data.vo.QuestionAndAnswerVO
import com.padcx.shared.view.viewholder.BaseViewHolder
import kotlinx.android.synthetic.main.special_question_item.view.*

/**
 * Created by Hnin Hsu Hlaing
 * on 12/9/2020
 */
class QuestionAndAnswerViewHolder(itemView: View, private val mDelegate: QuestionAndAnswerDelegate) :
    BaseViewHolder<QuestionAndAnswerVO>(itemView) {

    override fun bindData(data: QuestionAndAnswerVO) {

        data?.let {
            itemView.txt_question.text = "(${adapterPosition}) "+ data.question
            itemView.txt_answer.text =data.answer
        }

    }
}