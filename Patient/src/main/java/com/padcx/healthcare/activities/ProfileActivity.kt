package com.padcx.healthcare.activities

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.annotation.RequiresApi
import com.padcx.healthcare.R
import com.padcx.healthcare.mvp.presenter.Impl.ProfilePresenterImpl
import com.padcx.healthcare.mvp.presenter.ProfilePresenter
import com.padcx.healthcare.mvp.view.ProfileView
import com.padcx.shared.activities.BaseActivity
import com.padcx.shared.data.vo.PatientVO
import com.padcx.shared.util.ImageUtil
import kotlinx.android.synthetic.main.activity_profile.*
import java.io.IOException

/**
 * Created by Hnin Hsu Hlaing
 * on 12/16/2020
 */
class ProfileActivity : BaseActivity(),ProfileView{
    private lateinit var mPresenter: ProfilePresenter

    private  var bitmap : Bitmap? = null

    private var year: String? = null
    private var month: String? = null
    private var day: String? = null
    private var bloodType: String? = null
    private lateinit var  mProgreessDialog : ProgressDialog

    companion object {
        const val PICK_IMAGE_REQUEST = 1111
        fun newIntent(context: Context) = Intent(context, ProfileActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        setUpPresenter()
        setUpClickListener()
        setUpItemSelectedListener()
    }
    private fun setUpItemSelectedListener() {
        year_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
            ) {
                year = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        month_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
            ) {
                month = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        day_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
            ) {
                day = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        bloodtype_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
            ) {
                bloodType = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }
    private  fun setUpClickListener()
    {
        txt_upload.setOnClickListener {
            openGallery()
        }

        tex_back.setOnClickListener {
            onBackPressed()
        }

        mProgreessDialog = ProgressDialog(this@ProfileActivity)
        mProgreessDialog.setTitle("လုပ်ဆောင်နေပါသည်")
        mProgreessDialog.setMessage("ခဏစောင့်ဆိုင်းပေးပါ.....")

        btn_save.setOnClickListener {


            mProgreessDialog.show()
            var dateofbirth ="$day  $month $year"
            bitmap?.let { it1 -> mPresenter?.updateUserData(it1,
                    bloodType.toString(), dateofbirth,
                    pt_height.text.toString(), pt_comment.text.toString(), ptphone.text.toString(),
                    et_address.text.toString()
            ) }

        }
    }
    fun openGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
    }

    private fun setUpPresenter() {
        this?.let{
            mPresenter = getPresenter<ProfilePresenterImpl, ProfileView>()
            this?.let { it1 -> mPresenter.onUiReady(it1,this) }
        }
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            if (data == null || data.data == null) {
                return
            }
            val filePath = data.data
            try {
                filePath?.let {
                    if (Build.VERSION.SDK_INT >= 29) {
                        val source: ImageDecoder.Source =
                                ImageDecoder.createSource(this.contentResolver, filePath)

                         bitmap = ImageDecoder.decodeBitmap(source)
                        Log.d("Bitmap" , bitmap.toString())
                      //  mPresenter
                        ImageUtil().showImageProfile(img_profile.context,img_profile,null,filePath)
                    } else {
                         bitmap = MediaStore.Images.Media.getBitmap(this?.contentResolver, filePath)
                        ImageUtil().showImageProfile(img_profile.context,img_profile,null,filePath)

                    }
                }

            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    override fun displayPatientData(patientVO: PatientVO) {
        ImageUtil().showImage(img_profile, patientVO.photo.toString(),R.drawable.user)
        ptphone.text =    Editable.Factory.getInstance().newEditable(patientVO.email)
        pt_height.text =    Editable.Factory.getInstance().newEditable(patientVO.height)
        pt_comment.text =    Editable.Factory.getInstance().newEditable(patientVO.allergic_medicine)
        et_address.text =    Editable.Factory.getInstance().newEditable(patientVO.perment_address)
    }

    override fun hideProgressDialog() {
        mProgreessDialog.dismiss()
    }

    override fun showError(error: String) {

    }

}