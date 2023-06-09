package com.example.quizgame.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameSettings (
    val minPercentOfRightAnswers: Int,
    val countOfQuestion: Int
) : Parcelable