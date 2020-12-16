package com.padcx.healthcare.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.padcx.healthcare.R
import com.padcx.healthcare.delegate.ChatHistoryDelegate
import com.padcx.healthcare.views.viewHolders.ChatHistoryViewHolder
import com.padcx.shared.adapters.BaseRecyclerAdapter
import com.padcx.shared.data.vo.ConsulationChatVO

/**
 * Created by Hnin Hsu Hlaing
 * on 12/16/2020
 */
class ChatAdapter(private val mDelegate: ChatHistoryDelegate) :
        BaseRecyclerAdapter<ChatHistoryViewHolder, ConsulationChatVO>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatHistoryViewHolder {

        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.listitem_chathistory, parent, false)
        return ChatHistoryViewHolder(view, mDelegate)

    }
}