package com.padcx.healthcare.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.padcx.healthcare.R
import com.padcx.healthcare.mvp.presenter.Impl.RegisterPresenterImpl
import com.padcx.healthcare.mvp.presenter.RegisterPresenter
import com.padcx.healthcare.mvp.view.RegisterView
import com.padcx.shared.activities.BaseActivity
import com.padcx.shared.data.vo.PatientVO
import com.padcx.shared.mvp.view.BaseView

class MainActivity : BaseActivity(),RegisterView{
    private lateinit var mPresenter : RegisterPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpPresenter()
        val patientVOMap = hashMapOf(
            "id" to "002",
            "name" to "Daw Aye",
            "email" to "dawaye@gmail.com",
            "phone" to "0912345",
            "photo" to "",
            "age" to "23",
            "address" to "Pyay",
            "gender" to "Female",
            "dateOfBirth" to "12/3/1090"
        )
//        mPresenter.onTapRegister(this,patientVOMap as PatientVO,"dawaye")
    }

    private fun setUpPresenter() {
        mPresenter = getPresenter<RegisterPresenterImpl,RegisterView>()
    }

    override fun navigateToLoginScreen() {

    }

    override fun showError(error: String) {

    }
}