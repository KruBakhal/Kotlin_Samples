package com.example.kotlin_samples.TicToc.TicTacTo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_samples.R
import com.example.kotlin_samples.TicToc.TicTacTo.Model.MatrixModel
import com.example.kotlin_samples.TicToc.TicTacTo.TicTacAdapter.UserViewHolder

class TicTacAdapter(list: List<MatrixModel>, value: Int, var ticInterface: TicInterface) :
    RecyclerView.Adapter<UserViewHolder>() {
    var list: kotlin.collections.List<MatrixModel>?
    var listSelected: kotlin.collections.MutableList<MatrixModel>? = null
    var value: Int

    public interface TicInterface {
        fun onCLickBox(position: Int, matrixModel: MatrixModel)
    }

    public override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view: android.view.View = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_tic_tac, parent, false)
        return UserViewHolder(view)
    }

    public override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val matrixModel: MatrixModel = list!!.get(position)
        holder.button.setText(matrixModel.getRow().toString() + "" + matrixModel.getColumn())
        //checkInSelectedList(matrixModel!!)
        if (matrixModel.user == 0) {
            holder.button.setBackgroundColor(android.graphics.Color.DKGRAY)
        } else if (matrixModel.user == 1) {
            holder.button.setBackgroundColor(android.graphics.Color.BLUE)
        } else {
            holder.button.setBackgroundColor(android.graphics.Color.GREEN)
        }


        holder.button.setOnClickListener(object : android.view.View.OnClickListener {
            public override fun onClick(v: android.view.View) {

                ticInterface.onCLickBox(position, matrixModel)
                notifyDataSetChanged()
            }
        })
    }

    private fun checkInSelectedList(matrixModel: MatrixModel): kotlin.Boolean {
        var status: kotlin.Boolean = false
        if (listSelected != null && listSelected!!.size > 0) for (i in listSelected!!.indices) {
            val model: MatrixModel = listSelected!!.get(i)
            if (matrixModel.getRow() === model.getRow() && matrixModel.getColumn() === model.getColumn()) {
                status = true
                break
            }
        }
        return status
    }

    public override fun getItemCount(): Int {
        if (list != null) return list!!.size else return 0
    }

    inner class UserViewHolder constructor(itemView: android.view.View) :
        RecyclerView.ViewHolder(itemView) {
        var button: android.widget.Button

        init {
            button = itemView.findViewById<android.widget.Button>(R.id.btn)
        }
    }

    init {
        this.list = list
        this.value = value
    }
}