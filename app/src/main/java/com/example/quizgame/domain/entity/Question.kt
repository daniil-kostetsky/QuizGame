package com.example.quizgame.domain.entity

import android.widget.ImageView

data class Question (
    val quote: String,
    val correctAnswerImageView: ImageView,
    val answerOption: List<ImageView>,
    val countOfQuestions: Int
)