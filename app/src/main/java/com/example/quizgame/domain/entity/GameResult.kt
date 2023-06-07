package com.example.quizgame.domain.entity

import android.widget.ImageView

data class GameResult (
    val isWin: Boolean,
    val countOfRightAnswers: Int,
    val gameSettings: GameSettings,
    val userAnswers: Map<Question, ImageView>
)

