package com.shivamkumarjha.bookstore.common

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.shivamkumarjha.bookstore.R

object BadgeConverter {
    fun convertLayoutToImage(
        mContext: Context,
        count: Int,
        drawableId: Int
    ): Drawable {
        val inflater = LayoutInflater.from(mContext)
        val view: View = inflater.inflate(R.layout.badge_icon_layout, null)
        (view.findViewById<View>(R.id.icon_badge) as ImageView).setImageResource(
            drawableId
        )
        if (count == 0) {
            val counterTextPanel =
                view.findViewById<View>(R.id.counterValuePanel)
            counterTextPanel.visibility = View.GONE
        } else {
            val textView = view.findViewById<View>(R.id.count) as TextView
            textView.text = count.toString()
        }
        view.measure(
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        )
        view.layout(0, 0, view.measuredWidth, view.measuredHeight)
        return BitmapDrawable(mContext.resources, getBitmapFromView(view))
    }

    private fun getBitmapFromView(view: View): Bitmap? {
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }
}