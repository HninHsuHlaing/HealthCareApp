package com.padcx.healthcare.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.padcx.healthcare.R
import com.padcx.healthcare.adapters.QuestionAndAnswerAdapter
import com.padcx.healthcare.mvp.presenter.ChatRoomPresenter
import com.padcx.healthcare.mvp.presenter.Impl.ChatRoomPresenterImpl
import com.padcx.healthcare.mvp.view.ChatView
import com.padcx.shared.activities.BaseeDialogFragment
import com.padcx.shared.data.vo.ConsulationChatVO
import kotlinx.android.synthetic.main.patient_info_dialog.view.*

/**
 * Created by Hnin Hsu Hlaing
 * on 12/15/2020
 */
class PatientInfoDialog: BaseeDialogFragment()   {
    private lateinit var mPresenter: ChatRoomPresenter

    companion object {

        private const val KEY_CONSULATIONCHATVO = "cousulationChatVO"


        fun newInstance(cousulationChatVO: String): PatientInfoDialog {
            val args = Bundle()
            args.putString(KEY_CONSULATIONCHATVO, cousulationChatVO)
            val fragment = PatientInfoDialog()
            fragment.arguments = args
            return fragment
        }

    }
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.patient_info_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
        setupClickListeners(view)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT
        )
        dialog?.apply {
            setCancelable(false)
            window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
    }
    private fun setupView(view: View) {
        var data = arguments?.getString(KEY_CONSULATIONCHATVO)
        val gson = Gson()
        var consultationChatVO = gson.fromJson(data, ConsulationChatVO::class.java)

        view.pname.text = " : " + consultationChatVO.patient?.name
        view.pdateofBirth.text =  " : " +consultationChatVO.patient?.dob
        view.pheight.text =  " : " + consultationChatVO.patient?.height
        view.pbloodtype.text = " : " + consultationChatVO.patient?.blood_type
        view.pweight.text =  " : " +consultationChatVO.patient?.weight
        view.pbloodpressure.text =  " : " +consultationChatVO.patient?.blood_pressure
        view.pcomment.text =  " : " + consultationChatVO.patient?.allergic_medicine

        view.rc_question_answer?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        mPresenter = getPresenter<ChatRoomPresenterImpl, ChatView>()
        var questionAnswerAdapter = QuestionAndAnswerAdapter(mPresenter, "")
        view.rc_question_answer?.adapter = questionAnswerAdapter
        view.rc_question_answer?.setHasFixedSize(false)

        consultationChatVO.caseSummary?.let{
            questionAnswerAdapter.setNewData(it)
        }
    }
    private fun setupClickListeners(view: View) {
        view.btn_close.setOnClickListener {
            dismiss()
        }
    }
}