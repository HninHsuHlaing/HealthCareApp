package com.padcx.healthcare.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.padcx.healthcare.delegate.CaseSummaryDelegate
import com.padcx.healthcare.fragment.GeneralQuestionFragment
import com.padcx.healthcare.fragment.SpecialQuestionFragment

/**
 * Created by Hnin Hsu Hlaing
 * on 12/9/2020
 */
class PagerAdapter (fragmentManager: FragmentManager, var email : String, var speciality: String, var listener : CaseSummaryDelegate) : FragmentStatePagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        return   when(position)
        {
            0 ->  GeneralQuestionFragment.newInstance(email ,listener)
            else -> SpecialQuestionFragment.newInstance(speciality,listener)
        }
    }

    override fun getCount(): Int {
        return 2
    }
}