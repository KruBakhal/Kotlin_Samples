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
import com.example.kotlin_samples.Model.api.Datum
import com.example.kotlin_samples.Model.api.SubCategory__1
import com.example.kotlin_samples.R

class MiddleAdapter(var context: Context) : RecyclerView.Adapter<MiddleAdapter.MiddleVH>() {

    var mutableList: MutableList<Datum> = ArrayList()

    fun renewItems(Datums: MutableList<Datum>) {
        mutableList = ArrayList()
        mutableList.addAll(Datums)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiddleVH {

        var inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.lay_middle_item, parent, false)
        return MiddleVH(inflater)
    }

    override fun onBindViewHolder(viewHolder: MiddleVH, position: Int) {

        val subcategory1 = mutableList.get(position)
        Glide.with(viewHolder.itemView)
            .load(subcategory1.thumbImage)
            .into(viewHolder.image)

        viewHolder.text.text = subcategory1.name

        viewHolder.download.setOnClickListener {

            rateApp(subcategory1.packageName)
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
        var download: FrameLayout

        init {
            image = itemView.findViewById(R.id.image)
            text = itemView.findViewById(R.id.text)
            download = itemView.findViewById(R.id.download)
        }

    }
}