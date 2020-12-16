package com.padcx.healthcare.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.padcx.healthcare.R
import com.padcx.healthcare.adapters.ChattingAdapter
import com.padcx.healthcare.adapters.QuestionAndAnswerAdapter
import com.padcx.healthcare.dialog.PatientInfoDialog
import com.padcx.healthcare.mvp.presenter.ChatRoomPresenter
import com.padcx.healthcare.mvp.presenter.Impl.ChatRoomPresenterImpl
import com.padcx.healthcare.mvp.view.ChatView
import com.padcx.healthcare.util.SessionManager
import com.padcx.shared.activities.BaseActivity
import com.padcx.shared.data.vo.ConsulationChatVO
import com.padcx.shared.data.vo.MessageVO
import com.padcx.shared.util.ImageUtil
import com.padcx.shared.util.PATIENT
import kotlinx.android.synthetic.main.activity_chat_room.*

/**
 * Created by Hnin Hsu Hlaing
 * on 12/11/2020
 */
class ChatRoomActivity:BaseActivity(), ChatView {
    private lateinit var mPresenter: ChatRoomPresenter
    private lateinit var consultation_chat_id: String
    private lateinit var questionAnswerAdapter: QuestionAndAnswerAdapter
    private lateinit var mConsultationChatVO: ConsulationChatVO
    private lateinit var adapter: ChattingAdapter
    companion object {
        const val PARM_CONSULTATION_CHAT_ID = " chat id"
        fun newIntent(
            context: Context,
            consultation_chat_id : String
        ) : Intent {
            val intent = Intent(context, ChatRoomActivity::class.java)
            intent.putExtra(PARM_CONSULTATION_CHAT_ID, consultation_chat_id)
            return intent
        }

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_room)
        consultation_chat_id = intent.getStringExtra(PARM_CONSULTATION_CHAT_ID).toString()
        setUPPresenter()
        setUPActionListener()
        setUpRecycler()
    }

    private fun setUpRecycler() {
        rc_chating?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        adapter = ChattingAdapter(mPresenter)
        rc_chating?.adapter = adapter
        rc_chating?.setHasFixedSize(false)


        rc_question_answer?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        questionAnswerAdapter = QuestionAndAnswerAdapter(mPresenter, "chat")
        rc_question_answer?.adapter = questionAnswerAdapter
        rc_question_answer?.setHasFixedSize(false)
    }

    private fun setUPActionListener() {
        patientname.setOnClickListener {
            onBackPressed()
        }
        seemore.setOnClickListener {
            var data=  Gson().toJson(mConsultationChatVO)
            val dialog: PatientInfoDialog = PatientInfoDialog.newInstance(data)
            dialog.show(supportFragmentManager, "")

        }
        btn_attachfile.setOnClickListener {
            Toast.makeText(this, this.resources.getString(R.string.image_upload_service_not_available),
                Toast.LENGTH_LONG).show()
        }

        btn_sendMessage.setOnClickListener {
            mPresenter?.addTextMessage(ed_message.text.toString(),
                    consultation_chat_id, PATIENT, SessionManager.patient_photo.toString(),
                    SessionManager.patient_name.toString(), this)
                    ed_message.hint = "ေရးသားပါ"
        }

    }

    private fun setUPPresenter() {
        mPresenter = getPresenter<ChatRoomPresenterImpl, ChatView>()
        mPresenter.onUiReadyConstulation(consultation_chat_id, this)
    }

    override fun displayPatientInfo(consultationChatVO: ConsulationChatVO) {
        scrollview.scrollTo(0, scrollview.bottom)
        mConsultationChatVO= consultationChatVO
        patientname.text = consultationChatVO.doctor?.name
        ImageUtil().showImage(userprofile, consultationChatVO.doctor?.photo.toString(), R.drawable.user)
        pname.text = " : " + consultationChatVO.patient?.name
        pdateofBirth.text =  " : " +consultationChatVO.patient?.dob
        pheight.text =  " : " + consultationChatVO.patient?.height
        pbloodtype.text = " : " + consultationChatVO.patient?.blood_type
        pweight.text =  " : " +consultationChatVO.patient?.weight
        pbloodpressure.text =  " : " +consultationChatVO.patient?.blood_pressure
        pcomment.text =  " : " + consultationChatVO.patient?.allergic_medicine
        consultationChatVO.caseSummary?.let{
            questionAnswerAdapter.setNewData(it)
        }

    }

    override fun displayChatMessageList(list: List<MessageVO>) {
        scrollview.scrollTo(0, scrollview.bottom)
        adapter.setNewData(list.toMutableList())
    }

    override fun showError(error: String) {

    }
}