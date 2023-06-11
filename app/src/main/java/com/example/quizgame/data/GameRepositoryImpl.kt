package com.example.quizgame.data

import android.util.Log
import com.example.quizgame.R
import com.example.quizgame.domain.entity.GameSettings
import com.example.quizgame.domain.entity.Level
import com.example.quizgame.domain.entity.Question
import com.example.quizgame.domain.repository.GameRepository

object GameRepositoryImpl : GameRepository {

    private val quotesMap = mapOf<String, Int>(
        "Call Me Ishmael." to R.drawable.moby_dick,
        "It was the best of times, it was the worst of times ..."  to R.drawable.a_tale_of_two_cities,
        "All children, except one, grow up." to R.drawable.peter_pan,
        "It was a pleasure to burn." to R.drawable.fahrenheit_451,
        "All happy families are alike; every unhappy family is unhappy in its own way." to
                R.drawable.anna_karenina,
        "It was a bright cold day in April, and the clocks were striking thirteen." to
                R.drawable.book1984,
        "Far out in the uncharted backwaters of the unfashionable end of the western spiral arm " +
                "of the Galaxy lies a small unregarded yellow sun." to
                R.drawable.the_ultimate_hitchhiker_s_guide_to_the_galaxy,
        "Mr. and Mrs. Dursley of number four, Privet Drive, were proud to say that they were " +
                "perfectly normal, thank you very much." to R.drawable.harry_potter_and_the_sorcer,
        "Ships at a distance have every man’s wish on board." to R.drawable.their_eyes_were_watching_god,
        "In a hole in the ground there lived a hobbit." to R.drawable.the_hobbit,
        "When he was nearly thirteen, my brother Jem got his arm badly broken at the elbow." to R.drawable.to_kill_a_mockingbird,
        "This is my favorite book in all the world, though I have never read it." to R.drawable.the_princess_bride,
        "The boy with fair hair lowered himself down the last few feet of rock and began to pick his way towards the lagoon." to R.drawable.lord_of_the_flies,
        "I've watched through his eyes, I've listened through his ears, and I tell you he's the one." to R.drawable.enders_game,
        "When shall we three meet again? In thunder, lightning, or rain?" to R.drawable.macbeth_shakespeare
    )
    private val quotes = listOf<String>(
        "Call Me Ishmael.",
        "It was the best of times, it was the worst of times ...",
        "All children, except one, grow up.",
        "It was a pleasure to burn.",
        "All happy families are alike; every unhappy family is unhappy in its own way.",
        "It was a bright cold day in April, and the clocks were striking thirteen.",
        "Far out in the uncharted backwaters of the unfashionable end of the western spiral arm " +
                "of the Galaxy lies a small unregarded yellow sun.",
        "Mr. and Mrs. Dursley of number four, Privet Drive, were proud to say that they were " +
                "perfectly normal, thank you very much.",
        "Ships at a distance have every man’s wish on board.",
        "In a hole in the ground there lived a hobbit.",
        "When he was nearly thirteen, my brother Jem got his arm badly broken at the elbow.",
        "This is my favorite book in all the world, though I have never read it.",
        "The boy with fair hair lowered himself down the last few feet of rock and began to pick his way towards the lagoon.",
        "I've watched through his eyes, I've listened through his ears, and I tell you he's the one.",
        "When shall we three meet again? In thunder, lightning, or rain?"
    )
    private val bookName = listOf<Int>(
        R.drawable.moby_dick,
        R.drawable.a_tale_of_two_cities,
        R.drawable.peter_pan,
        R.drawable.fahrenheit_451,
        R.drawable.anna_karenina,
        R.drawable.book1984,
        R.drawable.the_ultimate_hitchhiker_s_guide_to_the_galaxy,
        R.drawable.harry_potter_and_the_sorcer,
        R.drawable.their_eyes_were_watching_god,
        R.drawable.the_hobbit,
        R.drawable.to_kill_a_mockingbird,
        R.drawable.the_princess_bride,
        R.drawable.lord_of_the_flies,
        R.drawable.enders_game,
        R.drawable.macbeth_shakespeare
    )

    override fun generateQuestion(countOfAnswerOptions: Int): Question {
        val currentQuote = quotes.random()
        val correctAnswer = quotesMap[currentQuote]
        val answerOptions = mutableSetOf<Int>()
        answerOptions.add(correctAnswer ?:
            throw RuntimeException("Not found element in quotesMap [GameRepositoryImpl.kt]"))
        while (answerOptions.size < countOfAnswerOptions) {
            answerOptions.add(bookName.random())
        }
        Log.d("Repo", "Repo: $currentQuote  = $correctAnswer")
        return Question(currentQuote, answerOptions.shuffled().toList(), correctAnswer)
    }

    override fun getGameSettings(level: Level): GameSettings {
        return when (level) {
            Level.EASY -> {
                GameSettings(
                    60,
                    5
                )
            }
            Level.MEDIUM -> {
                GameSettings(
                    80,
                    10
                )
            }
            Level.HARD -> {
                GameSettings(
                    95,
                    15
                )
            }
        }
    }
}