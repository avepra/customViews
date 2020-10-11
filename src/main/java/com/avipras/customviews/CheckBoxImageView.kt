package com.avipras.customviews

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatImageView

class CheckBoxImageView : AppCompatImageView, View.OnClickListener {
    private var isItemChecked = false
    var defImageRes = 0
    var checkedImageRes = 0
    private lateinit var onCustomCheckBoxChangeListener: OnCustomCheckBoxChangeListener

    constructor(context: Context?, attr: AttributeSet, defStyle: Int) : super(context!!, attr, defStyle) {
        init(attr, defStyle)
    }

    constructor(context: Context?, attr: AttributeSet) : super(context!!, attr) {
        init(attr, -1)
    }

    constructor(context: Context?) : super(context!!)

    fun isItemChecked(): Boolean {
        return isItemChecked
    }

    fun setItemChecked(itemChecked: Boolean) {
        isItemChecked = itemChecked
        setImageResource(if (itemChecked) defImageRes else checkedImageRes)
    }

    @SuppressLint("ResourceType")
    private fun init(attributeSet: AttributeSet, defStyle: Int) {
        var a: TypedArray? = null
        a = if (defStyle != -1) context.obtainStyledAttributes(attributeSet, R.styleable.CheckBoxImageView, defStyle, 0) else context.obtainStyledAttributes(attributeSet, R.styleable.CheckBoxImageView)
        defImageRes = a.getResourceId(0, R.drawable.check_unchecked)
        checkedImageRes = a.getResourceId(1, R.drawable.check_checked)
        isItemChecked = a.getBoolean(2, false)
        a.recycle()
        setImageResource(if (isItemChecked) checkedImageRes else defImageRes)
        setOnClickListener(this)
    }

    override fun onClick(v: View) {
        isItemChecked = !isItemChecked
        setImageResource(if (isItemChecked) checkedImageRes else defImageRes)
        onCustomCheckBoxChangeListener.onCheckedChanged(this, isItemChecked)
    }

    fun setOnCustomCheckBoxChangeListener(onCustomCheckBoxChangeListener: OnCustomCheckBoxChangeListener?) {
        if (onCustomCheckBoxChangeListener != null) {
            this.onCustomCheckBoxChangeListener = onCustomCheckBoxChangeListener
        }
    }

    interface OnCustomCheckBoxChangeListener {
        fun onCheckedChanged(buttonView: View?, isChecked: Boolean)
    }
}