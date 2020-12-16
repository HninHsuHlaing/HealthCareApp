package com.padcx.doctor.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.padcx.doctor.R
import com.padcx.doctor.adapters.QuestionAndAnswerAdapter
import com.padcx.doctor.mvp.presenter.Impl.PatientInfoPresenterImpl
import com.padcx.doctor.mvp.presenter.PatientInfoPresenter
import com.padcx.doctor.mvp.view.PatientInfoView
import com.padcx.shared.activities.BaseActivity
import com.padcx.shared.data.vo.ConsulationRequestVO
import com.padcx.shared.util.ImageUtil
import kotlinx.android.synthetic.main.activity_patient_info.*

/**
 * Created by Hnin Hsu Hlaing
 * on 12/11/2020
 */
class PatientInfoActivity :BaseActivity(),PatientInfoView {
    private lateinit var mPresenter: PatientInfoPresenter

    private lateinit var questionAnswerAdapter: QuestionAndAnswerAdapter

    private lateinit var mConsultationRequestVO: ConsulationRequestVO

    private lateinit var consultation_request_id: String
    companion object {
        const val PARM_CONSULTATION_Request_ID = "consultation chat id"
        fun newIntent(
            context: Context,
            consultation_chat_id : String
        ) : Intent {
            val intent = Intent(context, PatientInfoActivity::class.java)
            intent.putExtra(PARM_CONSULTATION_Request_ID, consultation_chat_id)
            return intent
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_info)

        consultation_request_id = intent.getStringExtra(PARM_CONSULTATION_Request_ID).toString()

        setUpPresenter()
        setUpRecyclerView()
        setUpActionListeners()
    }
    private fun setUpPresenter()
    {
        mPresenter = getPresenter<PatientInfoPresenterImpl, PatientInfoView>()
        mPresenter.onUiReadyConstulation(consultation_request_id,this)
    }

    private fun setUpActionListeners()
    {
        cs_btn_confirm.setOnClickListener {
            if(mConsultationRequestVO != null) {
                mPresenter.onTapStartConsultation(mConsultationRequestVO)
            }
        }

        back_patientinfo.setOnClickListener {
            onBackPressed()
        }
    }
    private fun setUpRecyclerView()
    {
        rc_question_answer?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        questionAnswerAdapter = QuestionAndAnswerAdapter(mPresenter,"")
        rc_question_answer?.adapter = questionAnswerAdapter
        rc_question_answer?.setHasFixedSize(false)
    }
    override fun displayPatientInfo(consultationRequestVO: ConsulationRequestVO) {
        ImageUtil().showImage(uerimage,consultationRequestVO.patient.photo.toString(), R.drawable.user)
        pname.text = " : " + consultationRequestVO.patient.name
        pdateofBirth.text =  " : " +consultationRequestVO.patient.dob
        pheight.text =  " : " + consultationRequestVO.patient.height
        pbloodtype.text = " : " + consultationRequestVO.patient.blood_type
        pweight.text =  " : " +consultationRequestVO.patient.weight
        pbloodpressure.text =  " : " +consultationRequestVO.patient.blood_pressure
        pcomment.text =  " : " + consultationRequestVO.patient.allergic_medicine
        mConsultationRequestVO= consultationRequestVO
        questionAnswerAdapter.setNewData(consultationRequestVO.caseSummary)
    }

    override fun nextPageToChat(consulation_chat_id: String) {
        if(consulation_chat_id.isNotEmpty()) {
            this.finish()
           // Toast.makeText(this,consulation_chat_id+"", Toast.LENGTH_LONG).show()
            startActivity(ChatRoomActivity.newIntent(this, consulation_chat_id))
        }
    }

    override fun showError(error: String) {

    }
}