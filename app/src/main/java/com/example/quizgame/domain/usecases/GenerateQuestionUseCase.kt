package com.example.quizgame.domain.usecases

import com.example.quizgame.domain.entity.Question
import com.example.quizgame.domain.repository.GameRepository

class GenerateQuestionUseCase (
    private val repository: GameRepository
    ) {

    operator fun invoke(): Question {
        return repository.generateQuestion(COUNT_OF_ANSWERS_OPTIONS)
    }

    private companion object {
        private const val COUNT_OF_ANSWERS_OPTIONS = 3
    }
}