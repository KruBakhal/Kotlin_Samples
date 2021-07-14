package com.example.kotlin_samples.RandomCalculatorGame.Screen.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_samples.R
import com.example.kotlin_samples.RandomCalculatorGame.Screen.Model.RoundModel

public class ResultAdapter(context: Context, list: List<RoundModel>) :
    RecyclerView.Adapter<ResultAdapter.UserViewHolder>() {
    var context: Context? = null
    var list: List<RoundModel>? = null

    init {
        this.context = context;
        this.list = list;
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.lay_result_item, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        var model = list?.get(position)
        holder.text1.setText("${model?.gameLevel}")
        holder.text2.setText("${model?.scoreBoard}")
        holder.text3.setText("${model?.answerQues}/10")


    }

    override fun getItemCount(): Int {
        if (list == null || list!!.isEmpty())
            return 0;
        else
            return list!!.size
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var text1: TextView = itemView.findViewById(R.id.text1)
        var text2: TextView = itemView.findViewById(R.id.text2)
        var text3: TextView = itemView.findViewById(R.id.text3)
    }

}