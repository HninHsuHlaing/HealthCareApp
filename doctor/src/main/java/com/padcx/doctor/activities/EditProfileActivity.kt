package com.padcx.doctor.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.padcx.doctor.R
import com.padcx.shared.activities.BaseActivity

/**
 * Created by Hnin Hsu Hlaing
 * on 12/16/2020
 */
class EditProfileActivity : BaseActivity() {
    companion object {
        const val PICK_IMAGE_REQUEST = 1111
        fun newIntent(context: Context) = Intent(context, EditProfileActivity::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_profile_activity)
//        setUpPresenter()
//        setUpClickListener()
//        setUpItemSelectedListener()
    }
}