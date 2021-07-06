package com.example.kotlin_samples.Adapter

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.kotlin_samples.Model.api.Datum
import com.example.kotlin_samples.Model.api.SubCategory
import com.example.kotlin_samples.R
import com.smarteist.autoimageslider.SliderViewAdapter
import java.util.*
import kotlin.collections.ArrayList


class SliderAdapterExample(private val context: Context) :
    SliderViewAdapter<SliderAdapterExample.SliderAdapterVH>() {
    private var mSliderItems: MutableList<SubCategory> = ArrayList()
    fun renewItems(Datums: MutableList<SubCategory>) {
        mSliderItems = ArrayList()
        for (app in Datums) {
            val bannerImg = app.bannerImage
            if (!bannerImg.isNullOrEmpty()) {
                mSliderItems.add(app)
            }
        }
        notifyDataSetChanged()
    }

    fun deleteItem(position: Int) {
        mSliderItems.removeAt(position)
        notifyDataSetChanged()
    }

    fun addItem(SubCategory: SubCategory) {
        mSliderItems.add(SubCategory)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup): SliderAdapterVH {
        val inflate: View =
            LayoutInflater.from(parent.context).inflate(R.layout.lay_auto_slider_item, null)
        return SliderAdapterVH(inflate)
    }

    override fun onBindViewHolder(viewHolder: SliderAdapterVH, position: Int) {
        val SubCategory = mSliderItems[position]
        Glide.with(viewHolder.itemView)
            .load(SubCategory.bannerImage)
            .into(viewHolder.imageGifContainer)

        viewHolder.itemView.setOnClickListener {
            Toast.makeText(
                context,
                "This is item in position $position",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun getCount(): Int {
        //slider view count could be dynamic size
        return mSliderItems.size
    }

    public inner class SliderAdapterVH(itemView: View) :
        ViewHolder(itemView) {
        var itemViews: View? = null
        var imageGifContainer: ImageView

        init {
            imageGifContainer = itemView.findViewById(R.id.imageGifContainer)
            this.itemViews = itemView
        }
    }
}
