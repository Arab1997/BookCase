package com.shivamkumarjha.bookstore.ui.displaybook.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import com.shivamkumarjha.bookstore.R
import com.squareup.picasso.Picasso

class SliderAdapter(private val images: ArrayList<String>) :
    PagerAdapter() {

    private lateinit var layoutInflater: LayoutInflater

    override fun isViewFromObject(view: View, `object`: Any): Boolean =
        view == `object` as LinearLayout

    override fun getCount(): Int = images.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = LayoutInflater.from(container.context)
        val view: View = layoutInflater.inflate(R.layout.content_image_item, container, false)
        val imageView: ImageView = view.findViewById(R.id.slider_image_view)
        Picasso.get().load(images[position])
            .placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .into(imageView)
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }
}