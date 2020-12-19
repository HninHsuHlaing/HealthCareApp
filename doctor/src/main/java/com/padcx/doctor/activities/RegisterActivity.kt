package com.padcx.doctor.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import com.google.firebase.iid.FirebaseInstanceId
import com.padcx.doctor.R
import com.padcx.doctor.mvp.presenter.Impl.RegisterPresenterImpl
import com.padcx.doctor.mvp.presenter.RegisterPresenter
import com.padcx.doctor.mvp.view.RegisterView
import com.padcx.shared.activities.BaseActivity
import kotlinx.android.synthetic.main.activity_register.*

/**
 * Created by Hnin Hsu Hlaing
 * on 12/9/2020
 */
class RegisterActivity :BaseActivity(),RegisterView {

    lateinit var mPresenter: RegisterPresenter
    lateinit var token : String
    lateinit var specilities : String
    lateinit var specilities_name :String
    val specialityTypeList = mutableListOf(
        "cardiologist",
        "children",
        "dentist",
        "ear,nose",
        "eye",
        "general physician",
        "internal",
        "neurologist",
        "nutritionsts",
        "obstetricians",
        "reproduction",
        "skin specialist"
    )
    companion object {
        fun newIntent(context: Context) : Intent {
            return Intent(context, RegisterActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setUpPresenter()
        setUpActionListener()
        setUpItemSelectedListener()
    }

    private fun setUpItemSelectedListener() {
        specialname_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                // specilities = parent.getItemAtPosition(position).toString()
                specilities_name = specialityTypeList[position]
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
    }

    private fun setUpActionListener() {
        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener {
            Log.d("fbToken", it.token)
            token =it.token
        }
        btnRegister.setOnClickListener {
            mPresenter.onTapRegister(this,
                etUserName.text.toString(),
                etEmail.text.toString(),
                etPassword.text.toString(),
                token,
                specilities_name.toString(),
                ed_phone.text.toString(),
                ed_degree.text.toString(),
                ed_biography.text.toString()
            )
        }
        back.setOnClickListener {
        onBackPressed()
        }
    }

    private fun setUpPresenter() {
        mPresenter = getPresenter<RegisterPresenterImpl,RegisterView>()
        mPresenter.onUiReady(this,this)
    }

    override fun navigateToToLoginScreen() {
        startActivity(LoginActivity.newIntent(this))
        this.finish()
    }

    override fun showError(error: String) {

    }
}