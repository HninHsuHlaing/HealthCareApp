package com.padcx.healthcare.views.viewHolders

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.padcx.healthcare.delegate.SpecialQuestionDelegate
import com.padcx.shared.data.vo.QuestionAndAnswerVO
import com.padcx.shared.data.vo.SpecialquestionVO
import com.padcx.shared.view.viewholder.BaseViewHolder
import kotlinx.android.synthetic.main.listitem_special_question_edittext.view.*
import kotlinx.android.synthetic.main.special_question_item.view.*

/**
 * Created by Hnin Hsu Hlaing
 * on 12/9/2020
 */
class SpecialQuestionViewHolder (itemView: View, var mQuestionAnswerList: List<QuestionAndAnswerVO>,
                                 private val mDelegate: SpecialQuestionDelegate
) : BaseViewHolder<SpecialquestionVO>(itemView) {
    override fun bindData(data: SpecialquestionVO) {
        data?.let {
            itemView.txt_special_questions.text = "(${adapterPosition+1}) ${data.question}"
        }

        mQuestionAnswerList?.let {
            itemView.ed_answer.text = Editable.Factory.getInstance().newEditable(mQuestionAnswerList[adapterPosition].answer)
        }

        itemView.ed_answer.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(charSequence: CharSequence, start: Int, before: Int, count: Int) {
                var questionAnswerVO= QuestionAndAnswerVO(data.id,data.question,itemView.ed_answer.text.toString())
                mDelegate.onAnswerChange(adapterPosition,questionAnswerVO)
            }

            override fun afterTextChanged(editable: Editable) {}
        })
    }
}