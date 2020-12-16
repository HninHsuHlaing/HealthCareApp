package com.padcx.healthcare.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.padcx.healthcare.R
import com.padcx.healthcare.mvp.presenter.Impl.RegisterPresenterImpl
import com.padcx.healthcare.mvp.presenter.RegisterPresenter
import com.padcx.healthcare.mvp.view.RegisterView
import com.padcx.shared.activities.BaseActivity
import kotlinx.android.synthetic.main.activity_register.*

/**
 * Created by Hnin Hsu Hlaing
 * on 12/9/2020
 */
class RegisterActivity:BaseActivity(), RegisterView {

    private lateinit var mPresenter: RegisterPresenter
    private lateinit var token : String
    companion object{
        fun newIntent(context: Context) : Intent {
            return Intent(context, RegisterActivity::class.java)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setUpPresenter()
        setUpActionListener()
    }

    private fun setUpActionListener() {
        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener {
            Log.d("fbToken", it.token)
            token =it.token
        }

        back.setOnClickListener {
        onBackPressed()
        }

        btnRegister.setOnClickListener {
            mPresenter.onTapRegister(
                this,
                etUserName.text.toString(),
                etEmail.text.toString(),
                etPassword.text.toString(),
                token
                )
        }
    }

    private fun setUpPresenter() {
        mPresenter = getPresenter<RegisterPresenterImpl,RegisterView>()
        mPresenter.onUiReady(this, this)
    }

    override fun navigateToLoginScreen() {
        startActivity(LoginActivity.newIntent(this))
        this.finish()

    }

    override fun showError(error: String) {

    }

}