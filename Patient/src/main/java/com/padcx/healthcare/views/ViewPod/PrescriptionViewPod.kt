package com.padcx.healthcare.views.ViewPod

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.padcx.healthcare.R
import com.padcx.shared.data.vo.PrescriptionVO
import com.padcx.shared.util.ImageUtil
import kotlinx.android.synthetic.main.prescription_item_for_chat.view.*

/**
 * Created by Hnin Hsu Hlaing
 * on 12/18/2020
 */
class PrescriptionViewPod@JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var mDelegate: Delegate? = null

    override fun onFinishInflate() {
        super.onFinishInflate()

    }

    fun setPrescriptionData(prescription : List<PrescriptionVO>, doctorPhoto: String, consulationChatId : String) {
        ImageUtil().showImage(pdoctor_photo,doctorPhoto, R.drawable.user)
        var str : String = ""
        if(prescription.isNotEmpty())
        {
            for( item in prescription)
            {
                str += item.medicine_name +"\n"
            }
        }
        txt_medicineList.text = str.toString()

        btn_prescription.setOnClickListener {
            mDelegate?.onTapPrescriptionViewPod(consulationChatId)
        }
    }

    fun setDelegate(delegate: Delegate) {
        mDelegate = delegate
    }



    interface Delegate {
        fun onTapPrescriptionViewPod(chatid: String)
    }

}