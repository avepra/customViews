package com.avipras.customviews.checkableRadioGroup

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.use
import androidx.core.view.children
import com.avipras.customviews.R
import kotlinx.android.synthetic.main.indicator_button.view.*

@Suppress("LeakingThis")
open class IndicatorButton @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.indicator_button, this, true)

        context.theme.obtainStyledAttributes(attrs, R.styleable.IndicatorButton, 0, 0).use {
            isEnabled = it.getBoolean(R.styleable.IndicatorButton_android_enabled, isEnabled)
            icon.setImageDrawable(it.getDrawable(R.styleable.IndicatorButton_icon))
            label.text = it.getText(R.styleable.IndicatorButton_android_text)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            background = context.getDrawable(R.drawable.background_selector)
            icon.imageTintList = ContextCompat.getColorStateList(context, R.color.icon_tint_selector)
            indicator.imageTintList = ContextCompat.getColorStateList(context, R.color.icon_tint_selector)
        }

        label.setTextColor(ContextCompat.getColorStateList(context, R.color.text_color_selector))

        isClickable = true
        isFocusable = true
    }

    override fun setEnabled(enabled: Boolean) {
        super.setEnabled(enabled)
        children.forEach { it.isEnabled = enabled }
    }
}