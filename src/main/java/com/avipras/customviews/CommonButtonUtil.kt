package com.avipras.customviews

import android.content.Context
import android.content.res.Resources
import android.content.res.TypedArray
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import java.util.*

class CommonButtonUtil : LinearLayout {
    var mInflater: LayoutInflater
    var btnType = 0
    private lateinit var tvText: CustomTextViewSemiBold
    private var ivImage: ImageView? = null
    private var llParent: LinearLayout? = null
    private var btnText: String? = null
    private var btnTextSize: Float? = null

    constructor(context: Context) : super(context) {
        mInflater = LayoutInflater.from(context)
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
        mInflater = LayoutInflater.from(context)
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        mInflater = LayoutInflater.from(context)
        init(context, attrs)
    }

    fun init(context: Context, attrs: AttributeSet?) {
        var typedArray: TypedArray? = null
        if (attrs != null) typedArray = context.obtainStyledAttributes(attrs, R.styleable.CommonButtonUtil)
        btnType = Objects.requireNonNull(typedArray)!!.getInteger(R.styleable.CommonButtonUtil_cbBtnType, 4)
        btnText = typedArray!!.getString(R.styleable.CommonButtonUtil_cbBtnText)
        btnTextSize = typedArray.getFloat(R.styleable.CommonButtonUtil_cbBtnTextSize, 12.0F)
        val v: View = mInflater.inflate(R.layout.common_button, this, true)
        llParent = v.findViewById(R.id.llParentView)
        tvText = v.findViewById(R.id.tvText)
        ivImage = v.findViewById(R.id.ivImage)
        tvText.setTextColor(resources.getColor(R.color.whiteColor))
        tvText.text = btnText
        tvText.textSize = btnTextSize as Float
        getButton(btnType)
    }

    private fun getButton(btnType: Int) {
        isClickable = true
        tvText.setTextColor(resources.getColor(R.color.whiteColor))
        when (btnType) {
            0 -> {
                ivImage!!.visibility = View.GONE
                tvText.setText(R.string.ok)
                llParent!!.setBackgroundResource(R.drawable.rounded_button_lemon_green)
            }
            1 -> {
                ivImage!!.visibility = View.GONE
                tvText.setText(R.string.cancel)
                llParent!!.setBackgroundResource(R.drawable.rounded_button_app_blue)
            }
            2 -> {
                ivImage!!.visibility = View.GONE
                tvText.setText(R.string.yes)
                llParent!!.setBackgroundResource(R.drawable.rounded_button_app_blue)
            }
            3 -> {
                ivImage!!.visibility = View.GONE
                tvText.setText(R.string.no)
                llParent!!.setBackgroundResource(R.drawable.rounded_button_dark_gray)
            }
            4 -> {
                ivImage!!.visibility = View.GONE
                tvText.setText(R.string.btnContinue)
                llParent!!.setBackgroundResource(R.drawable.rounded_button_lemon_green)
            }
            5 -> {
                ivImage!!.visibility = View.VISIBLE
                tvText.setText(R.string.retry)
                llParent!!.setBackgroundResource(R.drawable.rounded_corner_button_white_semi)
            }
            6 -> {
                ivImage!!.visibility = View.GONE
                tvText.setText(R.string.contact_support)
                tvText.setTextColor(resources.getColor(R.color.whiteColor))
                llParent!!.setBackgroundResource(R.drawable.rounded_button_app_blue)
            }
            7 -> {
                ivImage!!.visibility = View.GONE
                tvText.setText(R.string.do_it_later)
                tvText.setTextColor(resources.getColor(R.color.textDarkGray))
                llParent!!.setBackgroundResource(R.drawable.rounded_button_white)
            }
            8 -> {
                ivImage!!.visibility = View.GONE
                tvText.setText(R.string.register)
                tvText.setTextColor(resources.getColor(R.color.whiteColor))
                llParent!!.setBackgroundResource(R.drawable.rounded_button_lemon_green)
            }
            9 -> {
                isClickable = false
                ivImage!!.visibility = View.GONE
                tvText.setText(R.string.btnContinue)
                tvText.setTextColor(resources.getColor(R.color.textWhiteOpacityLow))
                llParent!!.setBackgroundResource(R.drawable.rounded_button_translucent)
            }
            10 -> {
                ivImage!!.visibility = View.GONE
                tvText.setText(R.string.settings)
                llParent!!.setBackgroundResource(R.drawable.rounded_corner_button_white_semi)
            }
            11 -> {
                ivImage!!.visibility = View.GONE
                tvText.setText(R.string.cancel)
                tvText.setTextColor(resources.getColor(R.color.dark_grey))
                llParent!!.setBackgroundResource(R.drawable.corner_bottom_rounded_white_background)
            }
        }
        if (btnText!!.length > 0) {
            tvText.text = btnText
        }
    }

    fun setEnableState(enabled: Boolean) {
        if (enabled) {
            isEnabled = true
            getButton(8)
        } else {
            isEnabled = false
            getButton(9)
        }
    }

    fun setText(text: String?) {
        btnText = text
        tvText.text = btnText
    }

    fun updateButton(btnType: Int) {
        getButton(btnType)
    }

    companion object {
        private fun dp2px(dp: Float): Float {
            val r = Resources.getSystem()
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.displayMetrics)
        }

        private fun dp2pxInt(dp: Float): Int {
            return dp2px(dp).toInt()
        }
    }
}