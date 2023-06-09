package com.example.quizgame.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Question (
    val quote: String,
    val answerOption: List<Int>,
    val correctAnswer: Int
) : Parcelable