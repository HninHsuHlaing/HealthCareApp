package com.padcx.doctor.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.padcx.doctor.R
import com.padcx.doctor.mvp.presenter.Impl.LoginPresenterImpl
import com.padcx.doctor.mvp.presenter.LoginPresenter
import com.padcx.doctor.mvp.view.LoginView
import com.padcx.doctor.util.SessionManager
import com.padcx.shared.activities.BaseActivity
import com.padcx.shared.data.vo.DoctorVO
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.btnRegister

/**
 * Created by Hnin Hsu Hlaing
 * on 12/9/2020
 */
class LoginActivity :BaseActivity(), LoginView {
    lateinit var mPresenter : LoginPresenter

    companion object {
        fun newIntent(context: Context) : Intent {
            return Intent(context, LoginActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login1)
        setUpPresenter()
        setUPActionListener()
    }

    private fun setUPActionListener() {
       btnRegister.setOnClickListener {
        mPresenter.onTapRegister()
       }
        btnLogin.setOnClickListener {
            mPresenter.onTapLogin(this,
                ed_email.text.toString(),
                ed_password.text.toString(),
                this)
        }
    }

    private fun setUpPresenter() {
        mPresenter  = getPresenter<LoginPresenterImpl,LoginView>()
        mPresenter.onUiReady(this,this)
    }

    override fun navigateToMainScreen(doctorVO: DoctorVO) {
        SessionManager.login_status =true
        SessionManager.doctor_name = doctorVO.name
        SessionManager.doctor_id = doctorVO.id
        SessionManager.doctor_deviceId = doctorVO.deviceId
        SessionManager.doctor_email = doctorVO.email.toString()
        SessionManager.doctor_photo = doctorVO.photo.toString()
        SessionManager.doctor_specility = doctorVO.speciality.toString()
        SessionManager.doctor_experience = doctorVO.experience.toString()
        SessionManager.doctor_phone = doctorVO.phone
        SessionManager.doctor_degree = doctorVO.degree
        SessionManager.doctor_biography = doctorVO.biography
        SessionManager.doctor_address = doctorVO.address
        startActivity(MainActivity.newIntent(this))
        this.finish()
    }

    override fun navigateToRegisterScreen() {
        startActivity(RegisterActivity.newIntent(this))
    }

    override fun showError(error: String) {

    }
}