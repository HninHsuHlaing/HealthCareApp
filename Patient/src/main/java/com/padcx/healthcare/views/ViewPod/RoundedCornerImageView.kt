package com.padcx.healthcare.views.ViewPod

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.withStyledAttributes
import com.padcx.healthcare.R

/**
 * Created by Hnin Hsu Hlaing
 * on 12/6/2020
 */
class RoundedCornerImageView  @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    private var cornerRadius = 0f

    private val path = Path()

    init {

        context.withStyledAttributes(attrs, R.styleable.RoundedCornerImageView) {
            cornerRadius = getDimension(R.styleable.RoundedCornerImageView_cornerRadius, 0f)
        }
    }

    override fun onDraw(canvas: Canvas?) {

        val rectangle = RectF(0f, 0f, width.toFloat(), height.toFloat())

        path.addRoundRect(rectangle, cornerRadius, cornerRadius, Path.Direction.CCW)

        canvas?.clipPath(path)

        super.onDraw(canvas)
    }

}