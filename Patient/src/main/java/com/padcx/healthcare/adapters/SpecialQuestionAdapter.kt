package com.padcx.healthcare.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.padcx.healthcare.R
import com.padcx.healthcare.delegate.SpecialQuestionDelegate
import com.padcx.healthcare.views.viewHolders.SpecialQuestionViewHolder
import com.padcx.shared.adapters.BaseRecyclerAdapter
import com.padcx.shared.data.vo.QuestionAndAnswerVO
import com.padcx.shared.data.vo.SpecialquestionVO

/**
 * Created by Hnin Hsu Hlaing
 * on 12/9/2020
 */
class SpecialQuestionAdapter (private val mDelegate: SpecialQuestionDelegate
) : BaseRecyclerAdapter<SpecialQuestionViewHolder, SpecialquestionVO>() {
    var mQuestionAnswerList: List<QuestionAndAnswerVO> = arrayListOf()

    fun setQuestionAnswerList( questionAnswerList: List<QuestionAndAnswerVO>)
    {
        mQuestionAnswerList =questionAnswerList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialQuestionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.listitem_special_question_edittext, parent, false)
        return SpecialQuestionViewHolder(view,mQuestionAnswerList, mDelegate)
    }


    override fun onBindViewHolder(holder: SpecialQuestionViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
    }
}