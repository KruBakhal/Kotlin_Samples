package com.example.kotlin_samples.Adapter

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chaek.android.RatingBar
import com.example.kotlin_samples.Model.api.SubCategory
import com.example.kotlin_samples.Model.api.SubCategory__1
import com.example.kotlin_samples.R

class BottomAdapter(var context: Context) : RecyclerView.Adapter<BottomAdapter.MiddleVH>() {

    var mutableList: MutableList<SubCategory__1> = ArrayList()

    fun renewItems(Datums: MutableList<SubCategory__1>) {
        mutableList = Datums
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiddleVH {

        var inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.lay_bottom_item, parent, false)
        return MiddleVH(inflater)
    }

    override fun onBindViewHolder(viewHolder: MiddleVH, position: Int) {

        val subcategory1 = mutableList.get(position)
        Glide.with(viewHolder.itemView)
            .load(subcategory1.icon)
            .into(viewHolder.image)

        viewHolder.text.text = subcategory1.name
        viewHolder.text1.text = subcategory1.installedRange

        viewHolder.download.setOnClickListener {


        }

    }

    fun rateApp(pkgName: String?) {
        try {
            val marketUri = Uri.parse("market://details?id=$pkgName")
            val marketIntent = Intent(Intent.ACTION_VIEW, marketUri)
            context.startActivity(marketIntent)
        } catch (e: ActivityNotFoundException) {
            val marketUri = Uri.parse("https://play.google.com/store/apps/details?id=$pkgName")
            val marketIntent = Intent(Intent.ACTION_VIEW, marketUri)
            context.startActivity(marketIntent)
        }
    }

    override fun getItemCount(): Int {
        return mutableList.size;
    }

    class MiddleVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView
        var text: TextView
        var text1: TextView
        var rating: RatingBar
        var download: FrameLayout

        init {
            image = itemView.findViewById(R.id.image)
            text = itemView.findViewById(R.id.text)
            download = itemView.findViewById(R.id.download)
            text1 = itemView.findViewById(R.id.text1)
            rating = itemView.findViewById(R.id.rating)
        }

    }
}