package com.avipras.customviews.customSnackBar

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.OvershootInterpolator
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import com.avipras.customviews.R
import com.google.android.material.snackbar.ContentViewCallback
import kotlinx.android.synthetic.main.custom_snack_bar.view.*

class CustomSnackBarView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), ContentViewCallback {

    var tvItemCount: TextView
    var tvItemAmount: TextView
    var imLeft: ImageView
    var layRoot: CardView

    init {
        View.inflate(context, R.layout.custom_snack_bar, this)
        clipToPadding = false
        this.tvItemCount = findViewById(R.id.tv_item_count)
        this.tvItemAmount = findViewById(R.id.tv_item_amount)
        this.imLeft = findViewById(R.id.im_action_left)
        this.layRoot = findViewById(R.id.rlCartSnackBar)
    }

    override fun animateContentIn(delay: Int, duration: Int) {
        val scaleX = ObjectAnimator.ofFloat(im_action_left, View.SCALE_X, 0f, 1f)
        val scaleY = ObjectAnimator.ofFloat(im_action_left, View.SCALE_Y, 0f, 1f)
        val animatorSet = AnimatorSet().apply {
            interpolator = OvershootInterpolator()
            setDuration(500)
            playTogether(scaleX, scaleY)
        }
        animatorSet.start()
    }

    override fun animateContentOut(delay: Int, duration: Int) {
    }
}