package com.padcx.healthcare.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.padcx.healthcare.R
import com.padcx.healthcare.activities.ChatRoomActivity
import com.padcx.healthcare.adapters.ChatAdapter
import com.padcx.healthcare.dialog.PrescriptionDialog
import com.padcx.healthcare.mvp.presenter.ChatPresenter
import com.padcx.healthcare.mvp.presenter.Impl.ChatPresenterImpl
import com.padcx.healthcare.mvp.view.ChatHistoryView
import com.padcx.healthcare.mvp.view.HomeView
import com.padcx.shared.activities.Basefragment
import com.padcx.shared.data.vo.ConsulationChatVO
import com.padcx.shared.data.vo.ConsulationRequestVO
import com.padcx.shared.data.vo.RecentlyDoctorVO
import com.padcx.shared.data.vo.SpecialitiesVO
import kotlinx.android.synthetic.main.fragment_consultation.*

/**
 * Created by Hnin Hsu Hlaing
 * on 12/9/2020
 */
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"
class ConsultationFragment : Basefragment(), ChatHistoryView {
    private lateinit var mPresenter: ChatPresenter

    private lateinit var adapter:  ChatAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_consultation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpPresenter()
        setUpRecyclerView()
        setUpActionListener()
    }

    private fun setUpActionListener() {

    }

    private fun setUpRecyclerView() {
        rc_chat_history?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        adapter = ChatAdapter(mPresenter)
        rc_chat_history?.adapter = adapter
        rc_chat_history?.setHasFixedSize(false)
    }

    private fun setUpPresenter() {
        mPresenter = getPresenter<ChatPresenterImpl, ChatHistoryView>()
        activity?.let { mPresenter.onUiReady(it, this) }
    }

    override fun displayChatHistoryList(list: List<ConsulationChatVO>) {
        if(list.isNotEmpty()) {
            rc_chat_history.visibility = View.VISIBLE
            empty_view.visibility = View.GONE
        }
        else{
            rc_chat_history.visibility = View.GONE
            empty_view.visibility =View.VISIBLE
        }
        adapter.setNewData(list.toMutableList())
    }

    override fun nextPageToChatRoom(consulationchatId: String) {
        startActivity(activity?.let { ChatRoomActivity.newIntent(it, consulationchatId) })
    }

    override fun showPrescriptionDialog(finish_consulation: Boolean, consulationchatId: String, patient_name: String, start_conservation_date: String) {
        ///Toast.makeText(activity,"ဆေးညွန်းမရှိသေးပါ", Toast.LENGTH_SHORT).show()
        if(finish_consulation)
        {
            val dialog: PrescriptionDialog = PrescriptionDialog.newInstance(consulationchatId, patient_name, start_conservation_date)
            fragmentManager?.let { dialog.show(it, "") }

        }else {
            Toast.makeText(activity,"ဆေးညွန်းမရှိသေးပါ",Toast.LENGTH_SHORT).show()
        }

    }


}