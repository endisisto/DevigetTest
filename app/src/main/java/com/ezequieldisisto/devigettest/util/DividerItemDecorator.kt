package com.ezequieldisisto.devigettest.util

import android.R.color
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView


class DividerItemDecorator(context: Context) : RecyclerView.ItemDecoration() {

    private val mPaint = Paint()
    private val mAlpha: Int

    init {
        mPaint.color = ContextCompat.getColor(context, color.white)
        mPaint.strokeWidth = 2f
        mAlpha = mPaint.alpha
    }

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {


        val offset = (mPaint.strokeWidth / 2).toInt()

        // this will iterate over every visible view
        // this will iterate over every visible view
        for (i in 0 until parent.childCount) {
            val view: View = parent.getChildAt(i)
            val params = view.layoutParams as RecyclerView.LayoutParams
            // get the position
            val position = params.viewAdapterPosition
            // and finally draw the separator
            if (position < state.itemCount) { // apply alpha to support animations
                mPaint.alpha = (view.alpha * mAlpha).toInt()
                val positionY: Float = view.bottom + offset + view.translationY
                // do the drawing
                canvas.drawLine(view.left + view.translationX,
                        positionY,
                        view.right + view.translationX,
                        positionY,
                        mPaint)
            }
        }
    }
}