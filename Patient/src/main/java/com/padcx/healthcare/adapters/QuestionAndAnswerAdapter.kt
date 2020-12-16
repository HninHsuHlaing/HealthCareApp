package com.padcx.healthcare.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.padcx.healthcare.R
import com.padcx.healthcare.delegate.QuestionAndAnswerDelegate
import com.padcx.healthcare.views.viewHolders.QuestionAndAnswerViewHolder
import com.padcx.shared.adapters.BaseRecyclerAdapter
import com.padcx.shared.data.vo.QuestionAndAnswerVO

/**
 * Created by Hnin Hsu Hlaing
 * on 12/9/2020
 */
class QuestionAndAnswerAdapter(private val mDelegate: QuestionAndAnswerDelegate, var type : String):
    BaseRecyclerAdapter<QuestionAndAnswerViewHolder, QuestionAndAnswerVO>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionAndAnswerViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.special_question_item, parent, false)
        return QuestionAndAnswerViewHolder(view, mDelegate)

    }
}