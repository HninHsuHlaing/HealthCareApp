package com.padcx.doctor.activities

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TimePicker
import androidx.recyclerview.widget.LinearLayoutManager
import com.padcx.doctor.R
import com.padcx.doctor.adapters.ConsultationAcceptAdapter
import com.padcx.doctor.adapters.ConsultationRequestAdapter
import com.padcx.doctor.mvp.presenter.HomePresenter
import com.padcx.doctor.mvp.presenter.Impl.HomePresenterImpl
import com.padcx.doctor.mvp.view.HomView
import com.padcx.doctor.util.SessionManager
import com.padcx.shared.activities.BaseActivity
import com.padcx.shared.data.vo.ConsulationChatVO
import com.padcx.shared.data.vo.ConsulationRequestVO
import com.padcx.shared.data.vo.converters.ConsultatedPatientVO
import com.padcx.shared.util.ImageUtil
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.postpone_dialog.view.*

class MainActivity : BaseActivity() , HomView{

    lateinit var mPresenter : HomePresenter
    lateinit var mConsultationRequestAdapter :ConsultationRequestAdapter
    lateinit var mConsultationAcceptAdapter: ConsultationAcceptAdapter

    companion object{
//        fun newIntent(context: Context) : Intent{
//            return Intent(context,MainActivity::class.java)
//        }
    fun newIntent(context: Context) = Intent(context, MainActivity::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drname.text= SessionManager.doctor_name
        ImageUtil().showImage(img_doctor,SessionManager.doctor_photo.toString(),R.drawable.doctor_thumbnail)

        setUpPresenter()
        setUpActionListener()
        setUPRecyclerView()
    }

    private fun setUPRecyclerView() {

        rc_consulation_request.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        mConsultationRequestAdapter = ConsultationRequestAdapter(mPresenter)
        rc_consulation_request.adapter = mConsultationRequestAdapter

        rc_consulation_accept.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        mConsultationAcceptAdapter = ConsultationAcceptAdapter(mPresenter)
        rc_consulation_accept.adapter = mConsultationAcceptAdapter
    }

    private fun setUpActionListener() {
        img_doctor.setOnClickListener {
            startActivity(ProfileActivity.newIntent(this))
        }
    }

    private fun setUpPresenter() {
        mPresenter = getPresenter<HomePresenterImpl,HomView>()
        mPresenter.onUiReady(this,this)
    }

    override fun displayConsultationRequests(list: List<ConsulationRequestVO>) {
        mConsultationRequestAdapter.setNewData(list.toMutableList())
    }

    override fun displayConsultationAcceptList(list: List<ConsulationChatVO>) {
//        consultationlabel.visibility = View.VISIBLE
//        mConsultationAcceptAdapter.setNewData(list.toMutableList())
        mConsultationAcceptAdapter.setNewData(list.toMutableList())
        if(list?.size == 0) {
            empty_view.visibility =View.VISIBLE
            consultationlabel.visibility = View.GONE
        }else
        {
            empty_view.visibility =View.GONE
            consultationlabel.visibility = View.VISIBLE
        }
    }

    override fun displayConsultedPatient(list: List<ConsultatedPatientVO>) {
        mConsultationRequestAdapter.setConsultedPatientList(list.toMutableList())
    }

    override fun nextPageChatRoom(consultation_chat_id: String) {
        startActivity(consultation_chat_id?.let { ChatRoomActivity.newIntent(this, it) })
        this.finish()
    }

    override fun nextPagePatientInfo(consultation_request_id: String) {
        startActivity(consultation_request_id?.let { PatientInfoActivity.newIntent(this, it) })
    }

    override fun displayPostPoneChooserDialog(consultationRequestVO: ConsulationRequestVO)
    {
        val view = layoutInflater.inflate(R.layout.postpone_dialog, null)
        val dialog = this?.let { Dialog(it) }
        val timePicker = view?.findViewById<TimePicker>(R.id.timePicker)
        var msg : String =""
        timePicker?.setOnTimeChangedListener { _, hour, minute -> var hour = hour
            var am_pm = ""
            // AM_PM decider logic
            when {hour == 0 -> { hour += 12
                am_pm = "AM"
            }
                hour == 12 -> am_pm = "PM"
                hour > 12 -> { hour -= 12
                    am_pm = "PM"
                }
                else -> am_pm = "AM"
            }

            val h = if (hour < 10) "0" + hour else hour
            val min = if (minute < 10) "0" + minute else minute
            // display format of time
            msg = " $h : $min $am_pm"

        }
        dialog?.apply {
            setCancelable(true)
            setContentView(view)
            window?.setBackgroundDrawableResource(android.R.color.transparent)
        }

        view.confirm.setOnClickListener {
            msg?.let{mPresenter.onTapPostponeTime(it,consultationRequestVO)}
            dialog?.dismiss()
        }
        dialog?.show()
    }

//    override fun nextPage(data: ConsulationRequestVO) {
//        if(data.cr_id.toString().isEmpty()){
//            startActivity(data.cr_id?.let {
//                PatientInfoActivity.newIntent(this,it)
//            })
//        }else{
//            startActivity(data.cr_id?.let {
//                ChatRoomActivity.newIntent(this,it)
//            })
//        }
//    }


    override fun showError(error: String) {

    }
}