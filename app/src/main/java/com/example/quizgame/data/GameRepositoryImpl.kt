package com.example.quizgame.data

import com.example.quizgame.R
import com.example.quizgame.domain.entity.GameSettings
import com.example.quizgame.domain.entity.Level
import com.example.quizgame.domain.entity.Question
import com.example.quizgame.domain.repository.GameRepository

object GameRepositoryImpl : GameRepository {
    private val answeredQuestion = mutableListOf<String>()

    private val quotesMap = mapOf<String, Int>(
        "Call Me Ishmael." to R.drawable.moby_dick,
        "It was the best of times, it was the worst of times ..."  to R.drawable.a_tale_of_two_cities,
        "All children, except one, grow up." to R.drawable.peter_pan,
        "It was a pleasure to burn." to R.drawable.fahrenheit_451,
        "All happy families are alike; every unhappy family is unhappy in its own way." to
                R.drawable.anna_karenina
    )
    private val quotes = listOf<String>(
        "Call Me Ishmael.",
        "It was the best of times, it was the worst of times ...",
        "All children, except one, grow up.",
        "It was a pleasure to burn.",
        "All happy families are alike; every unhappy family is unhappy in its own way."
    )
    private val bookName = listOf<Int>(
        R.drawable.moby_dick,
        R.drawable.a_tale_of_two_cities,
        R.drawable.peter_pan,
        R.drawable.fahrenheit_451,
        R.drawable.anna_karenina
    )

    override fun generateQuestion(countOfQuestion: Int, countOfAnswerOptions: Int): Question {
        val currentQuote = getCurrentQuote()
        val correctAnswer = quotesMap[currentQuote]
        val answerOptions = mutableSetOf<Int>()
        answerOptions.add(correctAnswer ?:
            throw RuntimeException("Not found element in quotesMap [GameRepositoryImpl.kt]"))
        while (answerOptions.size < countOfAnswerOptions) {
            answerOptions.add(bookName.random())
        }
        return Question(currentQuote, answerOptions.toList())
    }

    override fun getGameSettings(level: Level): GameSettings {
        return when (level) {
            Level.TEST -> {
                GameSettings(50)
            }
            Level.EASY -> {
                GameSettings(60)
            }
            Level.MEDIUM -> {
                GameSettings(80)
            }
            Level.HARD -> {
                GameSettings(95)
            }
        }
    }

    private fun getCurrentQuote(): String {
        var currentQuote: String
        do {
            currentQuote = quotes.random()
        } while (currentQuote in answeredQuestion)

        answeredQuestion += currentQuote
        return currentQuote
    }
}