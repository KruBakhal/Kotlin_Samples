package com.example.kotlin_samples.RandomCalculatorGame.Screen.Model

public class RoundModel {

    var gameLevel: Int = 0
    var scoreBoard: Int = 0
    var answerQues: Int = 0

    constructor(gameLevel: Int, scoreBoard: Int, answerQues: Int) {
        this.gameLevel = gameLevel
        this.scoreBoard = scoreBoard
        this.answerQues = answerQues
    }
}