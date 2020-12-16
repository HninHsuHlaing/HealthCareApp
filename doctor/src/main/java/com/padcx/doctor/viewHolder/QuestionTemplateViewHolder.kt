package com.padcx.doctor.viewHolder

import android.view.View
import com.padcx.doctor.delegate.QuestionTemplateDelegate
import com.padcx.shared.data.vo.GeneralQuestionTemplateVO
import com.padcx.shared.view.viewholder.BaseViewHolder
import kotlinx.android.synthetic.main.listitem_question_template.view.*

/**
 * Created by Hnin Hsu Hlaing
 * on 12/16/2020
 */
class QuestionTemplateViewHolder(itemView: View, private val mDelegate: QuestionTemplateDelegate) :
        BaseViewHolder<GeneralQuestionTemplateVO>(itemView) {

    override fun bindData(data: GeneralQuestionTemplateVO) {

        data?.let {
            itemView.question_template.text = data.question
        }

        itemView.question_template.setOnClickListener {
            mDelegate.onTapOneQuestion(data)
        }
    }
}