package com.padcx.healthcare.views.ViewPod

import android.content.Context
import android.util.AttributeSet
import androidx.cardview.widget.CardView

/**
 * Created by Hnin Hsu Hlaing
 * on 12/6/2020
 */
class ConsultationRequestViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    private var mDelegate: Delegate? = null

    override fun onFinishInflate() {
        super.onFinishInflate()
        setUpListener()
    }



    fun setDelegate(delegate: Delegate) {
        mDelegate = delegate
    }

    private fun setUpListener() {

    }

    interface Delegate {

    }

}