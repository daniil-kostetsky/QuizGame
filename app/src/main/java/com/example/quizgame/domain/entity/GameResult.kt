package com.example.quizgame.domain.entity

import android.os.Parcelable
import android.widget.ImageView
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameResult (
    val isWin: Boolean,
    val countOfRightAnswers: Int,
    val gameSettings: GameSettings,
    val userAnswers: Map<Question, Int>
) : Parcelable

