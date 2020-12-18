package com.padcx.doctor.viewPod

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.padcx.doctor.R
import com.padcx.shared.data.vo.PrescriptionVO
import com.padcx.shared.util.ImageUtil
import kotlinx.android.synthetic.main.prescription_item_for_chat_viewpod.view.*

/**
 * Created by Hnin Hsu Hlaing
 * on 12/18/2020
 */
class PrescriptionViewPod @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var mDelegate: Delegate? = null

    override fun onFinishInflate() {
        super.onFinishInflate()
        setUpListener()

    }

    fun setPrescriptionData(prescription : List<PrescriptionVO>, doctorPhoto: String) {
        ImageUtil().showImage(ddoctor_photo,doctorPhoto, R.drawable.user)
        var str : String = ""
        if(prescription.isNotEmpty())
        {
            for( item in prescription)
            {
                str += item.medicine_name +"\n"
            }
        }
        dtxt_medicineList.text = str.toString()
    }

    fun setDelegate(delegate: Delegate) {
        mDelegate = delegate
    }

    private fun setUpListener() {

    }

    interface Delegate {
        fun onTapPrescription()
    }

}