package com.padcx.doctor.activities

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.padcx.doctor.R
import com.padcx.doctor.mvp.presenter.Impl.MedicalRecordPresenterImpl
import com.padcx.doctor.mvp.presenter.MedicalRecordPresenter
import com.padcx.doctor.mvp.view.MedicalRecordView
import com.padcx.shared.activities.BaseActivity
import com.padcx.shared.data.vo.ConsulationChatVO
import kotlinx.android.synthetic.main.activity_medical_record.*

/**
 * Created by Hnin Hsu Hlaing
 * on 12/18/2020
 */
class MedicalCommentAcitivity : BaseActivity() , MedicalRecordView
{

    private lateinit var mPresenter: MedicalRecordPresenter
    private lateinit var mConsultationChatVO: ConsulationChatVO

    companion object {

        private const val ConsultationCHAT = "ConsultationCHAT"

        fun newIntent(context: Context, consultationChatVO: String) : Intent {
            val intent = Intent(context, MedicalCommentAcitivity::class.java)
            intent.putExtra(ConsultationCHAT, consultationChatVO)
            return intent
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medical_record)
        setUpPresenter()
        setUpActionListeners()

        var data = intent?.getStringExtra(ConsultationCHAT)
        mConsultationChatVO = Gson().fromJson(data, ConsulationChatVO::class.java)

        mConsultationChatVO?.let {
            pname.text = mConsultationChatVO.patient?.name
            pdateofBirth.text = mConsultationChatVO.patient?.dob
            p_start_date.text = mConsultationChatVO.start_consultation_date

        }
    }

    private fun setUpActionListeners() {
        btn_save.setOnClickListener {
            mConsultationChatVO?.let {
                mConsultationChatVO.medical_record = medical_comment.text.toString()
                mPresenter.onTapSaveMedicalRecord(mConsultationChatVO ,this)
            }
        }

        tuex_back.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finish()
    }

    private fun setUpPresenter() {
        mPresenter = getPresenter<MedicalRecordPresenterImpl, MedicalRecordView>()
        mPresenter.onUiReady(this,this)
    }

    override fun showSnackBar(message: String) {
        val snackBar = Snackbar.make(medical_record_layout, message, Snackbar.LENGTH_SHORT
        ).setAction(resources.getString(R.string.know), null)
        snackBar.setActionTextColor(Color.BLACK)
        val snackBarView = snackBar.view
        snackBarView.setBackgroundColor(Color.WHITE)
        val textView = snackBarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
        textView.setTextColor(Color.BLACK)
        snackBar.show()
    }

    override fun showError(error: String) {

    }


}