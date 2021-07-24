package com.example.kotlin_samples.DayDreamSoft.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_samples.DayDreamSoft.Intermediate.BaseInterface
import com.example.kotlin_samples.DayDreamSoft.Model.ParentChildModel
import com.example.kotlin_samples.R


class MovieAdapter(
    var type: Int,
    private val moviesList: ArrayList<ParentChildModel>?, private var list: ArrayList<String>?,
    var baseInterface: BaseInterface,
) :
    RecyclerView.Adapter<MovieAdapter.MyViewHolder>() {
    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView

        init {
            title = view.findViewById<View>(R.id.title) as TextView
        }
    }

    public fun setList_New() {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_list_row, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        if (type == 1) {
            val movie: ParentChildModel?
            movie = moviesList?.get(position)
            holder.title.setText(movie?.parentName)
            holder.title.setOnClickListener {
                baseInterface.onCLickItems(position)
            }
        } else {
            var movie = list?.get(position)
            holder.title.setText(movie)
            holder.title.setOnClickListener {
                baseInterface.onCLickItems(position)
            }
        }


    }

    override fun getItemCount(): Int {
        if (type == 1) {
            if (moviesList == null || moviesList.size == 0)
                return 0
            else
                return moviesList.size

        } else {
            if (list == null || list?.size == 0)
                return 0
            else
                return list?.size!!

        }

    }

    fun notifyListChild(childList: ArrayList<String>?) {
        list = null
        list = ArrayList<String>()
        list.let {
            childList?.let { it1 -> it?.addAll(it1) }
        }
        notifyDataSetChanged()
//        childList?.let { (list as ArrayList<String>).toMutableList().addAll(it) }
    }
}