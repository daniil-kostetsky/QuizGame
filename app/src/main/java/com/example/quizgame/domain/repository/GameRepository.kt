package com.example.quizgame.domain.repository

import com.example.quizgame.domain.entity.GameSettings
import com.example.quizgame.domain.entity.Level
import com.example.quizgame.domain.entity.Question

interface GameRepository {

    fun generateQuestion(countOfAnswerOptions: Int): Question

    fun getGameSettings(level: Level): GameSettings
}