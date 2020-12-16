package com.padcx.healthcare.fragment

import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.padcx.healthcare.R
import com.padcx.healthcare.activities.LoginActivity
import com.padcx.healthcare.activities.ProfileActivity
import com.padcx.healthcare.mvp.presenter.Impl.ProfilePresenterImpl
import com.padcx.healthcare.mvp.presenter.ProfilePresenter
import com.padcx.healthcare.mvp.view.ProfileView
import com.padcx.healthcare.util.SessionManager
import com.padcx.shared.activities.Basefragment
import com.padcx.shared.data.vo.PatientVO
import com.padcx.shared.util.ImageUtil
import kotlinx.android.synthetic.main.fragment_account.*
import kotlinx.android.synthetic.main.profile_data_dialog.view.*

/**
 * Created by Hnin Hsu Hlaing
 * on 12/9/2020
 */
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

class AccountFragment: Basefragment() , ProfileView {
    private lateinit var mPresenter: ProfilePresenter
//    private var param1: String? = null
//    private var param2: String? = null
//    companion object {
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//                AccountFragment().apply {
//                    arguments = Bundle().apply {
//                        putString(ARG_PARAM1, param1)
//                        putString(ARG_PARAM2, param2)
//                    }
//                }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        setUpActionListener()
        setUpPresenter()

        imgedit.setOnClickListener {
            startActivity(activity?.let { it1 -> ProfileActivity.newIntent(it1) })
        }

    }

    private fun setUpPresenter() {
        this?.let{
            mPresenter = getPresenter<ProfilePresenterImpl, ProfileView>()
            activity?.let { it1 -> mPresenter.onUiReadyForAccountFragment(it1,this) }
        }
    }

    private fun checkPatientInfoDialog()
    {

        if(SessionManager.patient_bloodType.toString().isEmpty())
        {
            val view = layoutInflater.inflate(R.layout.profile_data_dialog, null)
            val dialog = context?.let { Dialog(it) }
            dialog?.window?.setLayout(
                    WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.MATCH_PARENT
            )
            dialog?.apply {
                setCancelable(false)
                setContentView(view)
                window?.setBackgroundDrawableResource(android.R.color.transparent)
            }

            view.android_cancel.setOnClickListener {
                dialog?.dismiss()
            }

            view.btn_fill_patient.setOnClickListener {
                startActivity(  activity?.applicationContext?.let{ ProfileActivity.newIntent(it)})
                dialog?.dismiss()
            }
            dialog?.show()
        }
    }


    private fun setUpActionListener()
    {
        btnLogout.setOnClickListener {
            SessionManager.login_status=false
            startActivity(activity?.let { it -> LoginActivity.newIntent(it) })
            activity?.finish()
        }
    }

    override fun displayPatientData(patientVO: PatientVO) {

        patientVO?.let {
            SessionManager.addPatientInfo(patientVO)
            checkPatientInfoDialog()
        }

        ImageUtil().showImage(img_profile, patientVO.photo.toString(),R.drawable.user)

        etUserName.text = Editable.Factory.getInstance().newEditable( SessionManager.patient_name)
        etEmail.text = Editable.Factory.getInstance().newEditable(SessionManager.patient_email)
        //etphone.text = Editable.Factory.getInstance().newEditable(SessionManager.pa)
        et_dateofbirth.text = Editable.Factory.getInstance().newEditable(SessionManager.patient_dob)
        et_bloodtype.text = Editable.Factory.getInstance().newEditable(SessionManager.patient_bloodType)
        et_height.text = Editable.Factory.getInstance().newEditable(SessionManager.patient_height)
        et_comment.text = Editable.Factory.getInstance().newEditable(SessionManager.patient_allergicMedicine)

    }

    override fun hideProgressDialog() {}


}