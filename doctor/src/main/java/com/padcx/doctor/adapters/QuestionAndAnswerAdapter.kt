package com.padcx.doctor.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.padcx.doctor.R
import com.padcx.doctor.delegate.QuestionAndAnswerDelegate
import com.padcx.doctor.viewHolder.QuestionAndAnswerViewHolder
import com.padcx.shared.adapters.BaseRecyclerAdapter
import com.padcx.shared.data.vo.QuestionAndAnswerVO

/**
 * Created by Hnin Hsu Hlaing
 * on 12/12/2020
 */
class QuestionAndAnswerAdapter(private val mDelegate: QuestionAndAnswerDelegate, var type : String) :
        BaseRecyclerAdapter<QuestionAndAnswerViewHolder, QuestionAndAnswerVO>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionAndAnswerViewHolder {

        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.listitem_question_answer, parent, false)
        return QuestionAndAnswerViewHolder(view, mDelegate)

    }

    override fun getItemCount(): Int {
        if (type == "chat") {
            if (mData.size > 0) {
                return 2
            } else  return super.getItemCount()
        } else {
            return super.getItemCount()
        }
    }
}