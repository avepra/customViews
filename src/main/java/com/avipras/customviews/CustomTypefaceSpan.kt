package com.avipras.customviews

import android.graphics.Paint
import android.graphics.Typeface
import android.text.TextPaint
import android.text.style.TypefaceSpan

/**
 * Created by skumar on 5/30/2017.
 */
class CustomTypefaceSpan(family: String?, private val newType: Typeface, private val mColor: Int) : TypefaceSpan(family) {
    override fun updateDrawState(ds: TextPaint) {
        applyCustomTypeFace(ds, newType, mColor)
    }

    override fun updateMeasureState(paint: TextPaint) {
        applyCustomTypeFace(paint, newType, mColor)
    }

    companion object {
        private fun applyCustomTypeFace(paint: Paint, tf: Typeface, color: Int) {
            val oldStyle: Int
            paint.color = color
            val old = paint.typeface
            oldStyle = old?.style ?: 0
            val fake = oldStyle and tf.style.inv()
            if (fake and Typeface.BOLD != 0) {
                paint.isFakeBoldText = true
            }
            if (fake and Typeface.ITALIC != 0) {
                paint.textSkewX = -0.25f
            }
            paint.typeface = tf
        }
    }

}