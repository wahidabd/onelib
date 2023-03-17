package com.wahidabd.library.decoration.cutomview

import android.content.Context
import android.graphics.Bitmap.Config
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView


/**
 * Created by Wahid on 3/17/2023.
 * Github wahidabd.
 */

class CircleImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatImageView(context, attrs) {


    companion object {
        private val BITMAP_CONFIG = Config.ARGB_8888
        private val COLORDRAWABLE_DIMENSION = 2
        private val DEFAULT_BORDER_COLOR = -16777216
        private val SCALE_TYPE = ScaleType.CENTER_CROP
    }

}