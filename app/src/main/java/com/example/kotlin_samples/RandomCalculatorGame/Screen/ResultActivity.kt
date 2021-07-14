package com.example.kotlin_samples.RandomCalculatorGame.Screen

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.kotlin_samples.R
import com.example.kotlin_samples.RandomCalculatorGame.Screen.Adapter.ResultAdapter
import com.example.kotlin_samples.RandomCalculatorGame.Screen.Model.RoundModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_result.*
import java.lang.reflect.Type

class ResultActivity : AppCompatActivity() {

    var context: Context = this;
    var adapter: ResultAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        text1.setText("Game Level ");
        text2.setText("ScoreBoard");
        text3.setText("Ans/Ques");

        val value = intent.getStringExtra("model")
        val type: Type = object : TypeToken<List<RoundModel>>() {}.type
        val gson = Gson()
        var list: List<RoundModel> = gson.fromJson(value, type)
        adapter = ResultAdapter(context, list)

        recyclerView.adapter = adapter
        imgBack.setOnClickListener {
            onBackPressed()
        }
        if (list.size >= 3) {
            imgRound.visibility = View.GONE
        }

    }

    fun onClickHome(view: View) {
        setResult(RESULT_CANCELED)
        finish()
    }

    fun onClickNext(view: View) {
        setResult(RESULT_OK)
        finish()
    }

    override fun onBackPressed() {
        setResult(RESULT_OK)
        finish()
    }
}