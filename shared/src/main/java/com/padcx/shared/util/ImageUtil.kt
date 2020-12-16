package com.padcx.shared.util

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.padcx.shared.R

/**
 * Created by Hnin Hsu Hlaing
 * on 11/25/2020
 */
class ImageUtil {
    fun showImage( imageView: ImageView, imageUrl: String , thumbnail : Int)
    {
        Glide.with(imageView.context)
            .load( imageUrl)
            .placeholder(thumbnail)
            .apply(RequestOptions().circleCrop())
            .into(imageView)
    }

    fun showImageProfile(context: Context, imageView: ImageView, imageUrl: String?, filePath: Uri?)
    {
        Glide.with(context)
            .asBitmap()
            .load(filePath ?: imageUrl)
            .placeholder(R.drawable.user)
            .apply(RequestOptions().circleCrop())
            .into(imageView)
    }
}