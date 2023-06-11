package com.example.quizgame.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameResult (
    val isWin: Boolean,
    val userAnswers: Map<Question, Int>
) : Parcelable

