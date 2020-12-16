package com.padcx.healthcare.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.padcx.healthcare.R
import com.padcx.healthcare.activities.CaseSummaryActivity
import com.padcx.healthcare.activities.ChatRoomActivity
import com.padcx.healthcare.adapters.ConsultationAapter
import com.padcx.healthcare.adapters.RecentDoctorAdapter
import com.padcx.healthcare.adapters.SpecilitiesAdapter
import com.padcx.healthcare.mvp.presenter.HomePresenter
import com.padcx.healthcare.mvp.presenter.Impl.HomePresenterImpl
import com.padcx.healthcare.mvp.view.HomeView
import com.padcx.shared.activities.Basefragment
import com.padcx.shared.data.vo.ConsulationRequestVO
import com.padcx.shared.data.vo.RecentlyDoctorVO
import com.padcx.shared.data.vo.SpecialitiesVO
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.request_confirm_dialog.view.*
import org.mmtextview.components.MMTextView

/**
 * Created by Hnin Hsu Hlaing
 * on 12/9/2020
 */


class HomeFragment :Basefragment() , HomeView {
    lateinit var mPresenter :HomePresenter
    lateinit var mRecentlyDoctorAdapter : RecentDoctorAdapter
    lateinit var mSpecialitiesAdapter : SpecilitiesAdapter
    lateinit var mConsultationAdapter : ConsultationAapter
    private var param1: String? = null
    private var param2: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpPresenter()
        setUpActionListener()
        setUpRecycler()
    }

    private fun setUpRecycler() {
        rc_consulation.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        mConsultationAdapter = ConsultationAapter (mPresenter)
        rc_consulation.adapter = mConsultationAdapter

        rcRecentlyDoctor.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        mRecentlyDoctorAdapter = RecentDoctorAdapter (mPresenter)
        rcRecentlyDoctor.adapter = mRecentlyDoctorAdapter

        rcTypeOfSymptom.layoutManager = GridLayoutManager(activity ,2)
        mSpecialitiesAdapter = SpecilitiesAdapter(mPresenter  )
        rcTypeOfSymptom.adapter = mSpecialitiesAdapter
    }

    private fun setUpActionListener() {

    }

    private fun setUpPresenter() {
        activity?.let {
            mPresenter = getPresenter<HomePresenterImpl,HomeView>()
            context?.let { it1 -> mPresenter.onUiReady(it1,this) }
        }


    }

    override fun displayConsultationRequest(consultationRequestVO: List<ConsulationRequestVO>) {
        mConsultationAdapter.setNewData(consultationRequestVO.toMutableList())
    }

    override fun displayRecentDoctorList(list: List<RecentlyDoctorVO>) {
        if(list.isNotEmpty()) {
            recentlyDoctorLayout.visibility =View.VISIBLE
            mRecentlyDoctorAdapter.setNewData(list.toMutableList())
        }else{
            recentlyDoctorLayout.visibility =View.GONE
        }
    }

    override fun displaySpecialityList(list: List<SpecialitiesVO>) {
        mSpecialitiesAdapter.setNewData(list.toMutableList())
    }

    override fun nextPageToCaseSummary(specialitiesVO: SpecialitiesVO) {
        val view = layoutInflater.inflate(R.layout.request_confirm_dialog, null)
        val dialog = context?.let { Dialog(it) }
        val name = view?.findViewById<MMTextView>(R.id.consultation_request_name_id)
        name?.text = specialitiesVO?.name + resources.getString(R.string.consultation_request_message)

        dialog?.apply {
            setCancelable(false)
            setContentView(view)
            window?.setBackgroundDrawableResource(android.R.color.transparent)
        }

        view.cancel_btn.setOnClickListener {
            dialog?.dismiss()
        }

        view.confirm_btn.setOnClickListener {
            startActivity(  activity?.applicationContext?.let{ CaseSummaryActivity.newIntent(it, specialitiesVO.id)})
            dialog?.dismiss()
        }
        dialog?.show()
    }

    override fun nextPageToChatRoom(
        consulation_chat_id: String,
        consultationRequestVO: ConsulationRequestVO
    ) {
        activity?.let {
            mPresenter.statusUpdateForCompleteType(it,consulation_chat_id,consultationRequestVO)
            it.startActivity(ChatRoomActivity.newIntent(it, consulation_chat_id))
        }
    }


}