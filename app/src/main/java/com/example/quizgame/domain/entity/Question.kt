package com.example.quizgame.domain.entity

import android.widget.ImageView

data class Question (
    val quote: String,
    val answerOption: List<Int>
)