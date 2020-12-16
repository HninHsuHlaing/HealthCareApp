package com.padcx.healthcare.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.padcx.healthcare.R
import com.padcx.healthcare.mvp.presenter.Impl.LoginPresenterImpl
import com.padcx.healthcare.mvp.presenter.LoginPresenter
import com.padcx.healthcare.mvp.view.LoginView
import com.padcx.healthcare.util.SessionManager
import com.padcx.shared.activities.BaseActivity
import com.padcx.shared.data.vo.PatientVO
import kotlinx.android.synthetic.main.activity_login.*

/**
 * Created by Hnin Hsu Hlaing
 * on 12/9/2020
 */
class LoginActivity :BaseActivity(), LoginView{

    lateinit var mPresenter :LoginPresenter
    companion object{
        fun newIntent(context: Context) : Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.patient_login_page)
        setUpPresenter()
        setUpActionListener()
    }

    private fun setUpActionListener() {
        btnLogin.setOnClickListener {
            mPresenter.onTapLogin(this,ed_email.text.toString(), ed_password.text.toString(),this)
        }
        btnRegister.setOnClickListener {
            mPresenter.onTapRegister()
        }
    }

    private fun setUpPresenter() {
        mPresenter = getPresenter<LoginPresenterImpl,LoginView>()
        mPresenter.onUiReady(this,this)
    }

    override fun navigateToHomeScreen(patientVO: PatientVO) {

        SessionManager.login_status =true
        SessionManager.patient_name = patientVO.name
        SessionManager.patient_id = patientVO.id
        SessionManager.patient_deviceId = patientVO.deviceId
        SessionManager.patient_email = patientVO.email
        SessionManager.patient_photo = patientVO.photo.toString()
        SessionManager.patient_dob =patientVO.dob
        SessionManager.patient_height = patientVO.height
        SessionManager.patient_bloodType = patientVO.blood_type
        SessionManager.patient_allergicMedicine = patientVO.allergic_medicine
        SessionManager.patient_weight = patientVO.weight
        SessionManager.patient_bloodPressure = patientVO.blood_pressure
        //SessionManager.patient_createDate = patientVO.created_date
        startActivity(MainActivity.newIntent(this))
        this.finish()

    }

    override fun navigateToRegisterScreen() {
        startActivity(RegisterActivity.newIntent(this))
    }

    override fun showError(error: String) {

    }
}