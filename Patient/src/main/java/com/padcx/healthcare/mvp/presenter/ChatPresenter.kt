package com.padcx.healthcare.mvp.presenter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.padcx.healthcare.R
import com.padcx.healthcare.delegate.ChatHistoryDelegate
import com.padcx.healthcare.mvp.view.ChatHistoryView
import com.padcx.healthcare.views.viewHolders.ChatHistoryViewHolder
import com.padcx.shared.adapters.BaseRecyclerAdapter
import com.padcx.shared.data.vo.ConsulationChatVO
import com.padcx.shared.mvp.presenter.BasePresenter

/**
 * Created by Hnin Hsu Hlaing
 * on 12/16/2020
 */
interface ChatPresenter: BasePresenter<ChatHistoryView>, ChatHistoryDelegate {

}