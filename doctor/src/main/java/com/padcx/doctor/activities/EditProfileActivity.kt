package com.padcx.doctor.activities

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
import android.view.View
import android.widget.AdapterView
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.annotation.RequiresApi
import com.padcx.doctor.R
import com.padcx.doctor.mvp.presenter.Impl.ProfilePresenterImpl
import com.padcx.doctor.mvp.presenter.ProfilePresenter
import com.padcx.doctor.mvp.view.ProfileView
import com.padcx.doctor.util.SessionManager
import com.padcx.shared.activities.BaseActivity
import com.padcx.shared.data.vo.DoctorVO
import com.padcx.shared.util.ImageUtil
import kotlinx.android.synthetic.main.edit_profile_activity.*
import java.io.IOException

/**
 * Created by Hnin Hsu Hlaing
 * on 12/16/2020
 */
class EditProfileActivity : BaseActivity(), ProfileView {
    private lateinit var mPresenter: ProfilePresenter

    private  var bitmap : Bitmap? = null
    private var year: String? = null
    private var month: String? = null
    private var day: String? = null
    private var gender: String? = null
    private var bloodType: String? = null

    private lateinit var  mProgreessDialog : ProgressDialog

    companion object {
        const val PICK_IMAGE_REQUEST = 1111
        fun newIntent(context: Context) = Intent(context, EditProfileActivity::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_profile_activity)
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
    }

    private fun setUpClickListener() {

        eradio_group.setOnCheckedChangeListener(
                RadioGroup.OnCheckedChangeListener { group, checkedId ->
                    val radio: RadioButton = findViewById(checkedId)
                    gender = radio.text.toString()
                })

        txt_upload.setOnClickListener {
            openGallery()
        }

        tex_back.setOnClickListener {
            onBackPressed()
        }

        mProgreessDialog = ProgressDialog(this)
        mProgreessDialog.setTitle("လုပ်ဆောင်နေပါသည်")
        mProgreessDialog.setMessage("ခဏစောင့်ဆိုင်းပေးပါ.....")

        btn_save.setOnClickListener {

            mProgreessDialog.show()
            var dateofbirth ="$day  $month $year"
            bitmap?.let { it1 ->
                mPresenter?.updateUserData(it1 ,
                        SessionManager.doctor_specility.toString(),
                        ePphone?.text.toString(),
                        e_degree.text.toString(),
                        ebiography.text.toString(),
                        eaddress.text.toString(),
                        eexperience.text.toString(),
                        day+ " "+month+" "+year,
                        gender.toString()
                )
            }

        }
    }

    fun openGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
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
                        val source: ImageDecoder.Source = ImageDecoder.createSource(this?.contentResolver, filePath)
                        bitmap = ImageDecoder.decodeBitmap(source)

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



    private fun setUpPresenter() {
        this?.let{
            mPresenter = getPresenter<ProfilePresenterImpl, ProfileView>()
            this?.let { it1 -> (mPresenter as ProfilePresenterImpl).onUiReady(it1,this) }
        }
    }

    override fun displayDocotrData(vo: DoctorVO) {

            ePphone.text =    Editable.Factory.getInstance().newEditable(vo.phone)
            e_degree.text =    Editable.Factory.getInstance().newEditable(vo.degree)
            ebiography.text =    Editable.Factory.getInstance().newEditable(vo.biography)
            eaddress.text =    Editable.Factory.getInstance().newEditable(vo.address)
            eexperience.text =  Editable.Factory.getInstance().newEditable(vo.experience)

    }

    override fun hideProgressDialog() {
        mProgreessDialog.dismiss()
    }

    override fun showError(error: String) {

    }
}