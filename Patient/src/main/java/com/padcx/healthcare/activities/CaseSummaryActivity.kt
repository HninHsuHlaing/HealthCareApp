package com.padcx.healthcare.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.padcx.healthcare.R
import com.padcx.healthcare.adapters.PagerAdapter
import com.padcx.healthcare.delegate.CaseSummaryDelegate
import com.padcx.healthcare.util.SessionManager
import com.padcx.shared.activities.BaseActivity
import kotlinx.android.synthetic.main.case_summary.*

/**
 * Created by Hnin Hsu Hlaing
 * on 12/9/2020
 */
class CaseSummaryActivity :BaseActivity(),CaseSummaryDelegate{
    companion object {
        const val PARM_SPECIALITYID = "SPECIALITY ID"
        const val PARM_EMAIL = "EMAIL ID"
        fun newIntent(
            context: Context,
            specialityID: String
        ) : Intent {
            val intent = Intent(context, CaseSummaryActivity::class.java)
            intent.putExtra(PARM_SPECIALITYID, specialityID)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.case_summary)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
        }
        tb_casesummary.setNavigationOnClickListener {
            onBackPressed()
        }
        val speciality = intent.getStringExtra(PARM_SPECIALITYID)
        pager?.adapter =    PagerAdapter(supportFragmentManager, SessionManager.patient_email.toString(), speciality.toString(),this)
        stepper_indicator.setViewPager(pager)
    }
    override fun onGeneralQuestionCallBack() {
        pager?.setCurrentItem(1, true)
    }

    override fun onSpecitalQuestionCallBack() {

    }
}