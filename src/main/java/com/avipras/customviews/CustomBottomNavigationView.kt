package com.avipras.customviews

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.internal.BaselineLayout
import java.lang.reflect.Field

class CustomBottomNavigationView : BottomNavigationView {


    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    private var fontFace: Typeface? = null

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        val bottomMenu = getChildAt(0) as ViewGroup
        val bottomMenuChildCount: Int = bottomMenu.childCount
        var item: BottomNavigationItemView?
        var itemTitle: View? = null
        val shiftingMode: Field

        if (fontFace == null) {
            fontFace = Typeface.createFromAsset(context.assets, "opensans_regular.ttf") // font from assets directory
        }

        /*try {
            shiftingMode = bottomMenu.javaClass.getDeclaredField("mShiftingMode")
            shiftingMode.isAccessible = true
            shiftingMode.setBoolean(bottomMenu, false)
            shiftingMode.isAccessible = false
        } catch (e: Exception){
            e.printStackTrace()
        }*/

        for (i in 0..bottomMenuChildCount) {
            try {
                if (bottomMenu.getChildAt(i) != null) {
                    item = bottomMenu.getChildAt(i) as BottomNavigationItemView
                    itemTitle = item.getChildAt(1)
                    ((itemTitle as BaselineLayout).getChildAt(0) as AppCompatTextView).typeface = fontFace
                    (itemTitle.getChildAt(0) as AppCompatTextView).textSize = 12.0F
                    (itemTitle.getChildAt(1) as AppCompatTextView).typeface = fontFace
                    (itemTitle.getChildAt(1) as AppCompatTextView).textSize = 12.0F
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

    }
}