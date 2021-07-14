package com.example.kotlin_samples.RandomCalculatorGame.Screen

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.text.method.TextKeyListener.clear
import android.view.View
import com.example.kotlin_samples.R
import com.example.kotlin_samples.RandomCalculatorGame.Screen.Model.RoundModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_game_round.*
import kotlinx.android.synthetic.main.activity_more_app.*
import java.util.*
import java.util.Collections.addAll
import kotlin.collections.ArrayList

class GameRoundActivity : AppCompatActivity() {

    var countDownBegin: CountDownTimer? = null
    var value1: Int = 0
    var value2: Int = 0
    var value3: Int = 0
    var value4: Int = 0
    var countDownRound: CountDownTimer? = null
    val context: Context = this
    val roundTimeout: Long = 45000
    val roundTimeInterval: Long = 1000
    val rightAnswer = 30
    val wrongAnswer = 10
    var totalScore = 0
    var gameLevel = 0
    var answeredQuesCount = 0
    var listGameRound: ArrayList<RoundModel> = ArrayList()
    var listOptions: List<Int> = mutableListOf()
    var isFirstTime = true
    var questionCount = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_round)

        click()
        if (isFirstTime) {
            isFirstTime = false

        }
        beginProcess()


    }

    private fun beginProcess() {
        gameLevel++
        totalScore = 0;
        questionCount = 0;
        answeredQuesCount = 0
        tvScoreCard.setText("$totalScore")
        tvgame_level.setText("$gameLevel")
        tvClock.setText("0")
        tvQuesAns.setText("0/10")
        tvOptions1.setText("0")
        tvOptions2.setText("0")
        tvOptions3.setText("0")
        tvOptions4.setText("0")
        tvQues.setText("")
        tvCountDown.visibility = View.VISIBLE

        countDownBegin = object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                tvCountDown.visibility = View.VISIBLE
                var millisUntilFinished1 = (millisUntilFinished / 1000).toInt()
                tvCountDown.setText("$millisUntilFinished1")
            }

            override fun onFinish() {

                tvCountDown.visibility = View.GONE
                startRoundTimer()


            }
        }.start()
    }

    private fun startRoundTimer() {

        clickOptions()
        getNextQuestionsData()

        countDownRound =
            object : CountDownTimer(roundTimeout, roundTimeInterval) {
                override fun onTick(millisUntilFinished: Long) {

                    var millisUntilFinished1 = (millisUntilFinished / 1000).toInt()
                    tvClock.setText("$millisUntilFinished1")
                }

                override fun onFinish() {
                    var model = RoundModel(gameLevel, totalScore, answeredQuesCount)
                    listGameRound.add(model)
                    startActivityForResult(Intent(context,
                        ResultActivity::class.java).putExtra("model",
                        Gson().toJson(listGameRound)), 123)
                }
            }.start()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 123) {
            if (resultCode == RESULT_OK) {
                beginProcess()
            } else {
                onBackPressed()
            }
        }
    }

    private fun clickOptions() {

        tvOptions1.setOnClickListener {
            if (value1 + value2 == listOptions[0]) {
                totalScore = totalScore + rightAnswer
                tvScoreCard.setText("$totalScore")
                answeredQuesCount++
                getNextQuestionsData()
            } else {
                totalScore = totalScore - wrongAnswer
                if (totalScore < 0) {
                    totalScore = 0
                }
                tvScoreCard.setText("$totalScore")
            }
            onAvoidMutipleClick(it)
        }
        tvOptions2.setOnClickListener {
            if (value1 + value2 == listOptions[1]) {
                totalScore = totalScore + rightAnswer
                tvScoreCard.setText("$totalScore")
                answeredQuesCount++
                getNextQuestionsData()
            } else {
                totalScore = totalScore - wrongAnswer
                if (totalScore < 0) {
                    totalScore = 0
                }
                tvScoreCard.setText("$totalScore")
            }
            onAvoidMutipleClick(it)
        }
        tvOptions3.setOnClickListener {
            if (value1 + value2 == listOptions[2]) {
                totalScore = totalScore + rightAnswer
                tvScoreCard.setText("$totalScore")
                answeredQuesCount++
                getNextQuestionsData()
            } else {
                totalScore = totalScore - wrongAnswer
                if (totalScore < 0) {
                    totalScore = 0
                }
                tvScoreCard.setText("$totalScore")
            }
            onAvoidMutipleClick(it)
        }
        tvOptions4.setOnClickListener {
            if (value1 + value2 == listOptions[3]) {
                totalScore = totalScore + rightAnswer
                tvScoreCard.setText("$totalScore")
                answeredQuesCount++
                getNextQuestionsData()
            } else {
                totalScore = totalScore - wrongAnswer
                if (totalScore < 0) {
                    totalScore = 0
                }
                tvScoreCard.setText("$totalScore")
            }
            onAvoidMutipleClick(it)
        }
    }

    private fun onAvoidMutipleClick(it: View) {
        it.setEnabled(false)
        it.postDelayed(Runnable { it.setEnabled(true) }, 1000)

    }

    private fun getNextQuestionsData() {
        if (questionCount >= 10) {
            countDownRound?.onFinish()
            return
        }

        getRandomQuestions()
        listOptions.toMutableList().clear()
        listOptions =
            listOf(getRandomValue(), getRandomValue(), getRandomValue(), (value1 + value2))
        Collections.shuffle(listOptions)

        tvQues.setText(" $value1  + $value2  ")
        tvOptions1.setText("" + listOptions[0])
        tvOptions2.setText("" + listOptions[1])
        tvOptions3.setText("" + listOptions[2])
        tvOptions4.setText("" + listOptions[3])
        questionCount++
        tvQuesAns.setText("$questionCount/10")
    }

    private fun getRandomQuestions() {
        value1 = getRandomValue()
        value2 = getRandomValue()
        if (value1 == value3 && value2 == value4) {
            return getRandomQuestions()
        }
        value3 = value1
        value4 = value2
    }

    private fun getRandomValue(): Int {
        var min = 1
        var max = 10

        if (gameLevel == 2) {
            min = 10
            max = 20
        } else if (gameLevel == 3) {
            min = 21
            max = 30

        }
        var random: java.util.Random = java.util.Random()
        var value = (random.nextInt(max - min + 1) + min).toInt()
        if (listOptions.contains(value) || (value1 + value2) == value) {
            value = getRandomValue()
        }
        return value
    }

    private fun click() {
        imgBack.setOnClickListener {
            onBackPressed()
        }
        tvCountDown.setOnTouchListener { v, event -> return@setOnTouchListener true }


    }

    override fun onBackPressed() {
        if (countDownRound != null)
            countDownRound?.cancel()
        if (countDownBegin != null)
            countDownBegin?.cancel()
        finish()
    }
}