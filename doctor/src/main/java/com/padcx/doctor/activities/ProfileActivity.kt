package com.padcx.doctor.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import com.padcx.doctor.R
import com.padcx.doctor.mvp.presenter.Impl.ProfilePresenterImpl
import com.padcx.doctor.mvp.presenter.ProfilePresenter
import com.padcx.doctor.mvp.view.ProfileView
import com.padcx.doctor.util.SessionManager
import com.padcx.shared.activities.BaseActivity
import com.padcx.shared.data.vo.DoctorVO
import com.padcx.shared.util.ImageUtil
import kotlinx.android.synthetic.main.acitvity_profile.*

/**
 * Created by Hnin Hsu Hlaing
 * on 12/10/2020
 */
class ProfileActivity : BaseActivity() , ProfileView {

    private lateinit var mPresenter: ProfilePresenter

    companion object {
        fun newIntent(context: Context) : Intent {
            return Intent(context, ProfileActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acitvity_profile)
        setUpPresenter()
        setUpActionListeners()
    }

    private fun setUpActionListeners() {
        imgedit.setOnClickListener {
            startActivity(this?.let { it1 -> EditProfileActivity.newIntent(it1) })
        }

        stxt_back.setOnClickListener {
            onBackPressed()
        }

        btnLogout.setOnClickListener {
            SessionManager.login_status=false
            startActivity(this?.let { it -> LoginActivity.newIntent(it) })
            this?.finish()
        }
    }
    private fun setUpPresenter() {
        mPresenter = getPresenter<ProfilePresenterImpl, ProfileView>()
        (mPresenter as ProfilePresenterImpl).onUiReady(this,this)
        mPresenter.onUiReadyForProfile(this,this)
    }

    override fun displayDocotrData(doctorVo: DoctorVO) {
        doctorVo?.let {
            SessionManager.addDoctorInfo(doctorVo)
        }

        ImageUtil().showImage(img_profile, doctorVo.photo.toString(),R.drawable.user)

        doctorname.text = Editable.Factory.getInstance().newEditable( SessionManager.doctor_name)
        doctorphone.text = Editable.Factory.getInstance().newEditable(SessionManager.doctor_phone)
        doctorspeciality.text = Editable.Factory.getInstance().newEditable(SessionManager.doctor_specility)
        doctor_dateofbirth.text =  " : " + Editable.Factory.getInstance().newEditable(doctorVo.dob)
        doctor_gender.text = " : " + Editable.Factory.getInstance().newEditable(doctorVo.gender)
        doctor_address.text = Editable.Factory.getInstance().newEditable(SessionManager.doctor_address)
        doctor_degree.text = Editable.Factory.getInstance().newEditable(SessionManager.doctor_degree)
        doctor_biography.text = Editable.Factory.getInstance().newEditable(doctorVo.biography)
        doctor_experience.text = " : " + Editable.Factory.getInstance().newEditable(doctorVo.experience)
    }

    override fun hideProgressDialog() {}
    override fun showError(error: String) {

    }


}