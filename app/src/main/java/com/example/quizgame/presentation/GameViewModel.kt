package com.example.quizgame.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quizgame.data.GameRepositoryImpl
import com.example.quizgame.domain.entity.GameResult
import com.example.quizgame.domain.entity.GameSettings
import com.example.quizgame.domain.entity.Level
import com.example.quizgame.domain.entity.Question
import com.example.quizgame.domain.usecases.GenerateQuestionUseCase
import com.example.quizgame.domain.usecases.GetGameSettingsUseCase

class GameViewModel : ViewModel() {

    private lateinit var gameSettings: GameSettings
    private lateinit var level: Level
    private var countOfQuestionTemp: Int = 0

    private val repository = GameRepositoryImpl

    private val generateQuestionUseCase = GenerateQuestionUseCase(repository)
    private val getGameSettingsUseCase = GetGameSettingsUseCase(repository)

    private val _question = MutableLiveData<Question>()
    val question: LiveData<Question>
        get() = _question

    private var countOfRightAnswers = 0

    private val userAnswers = mutableMapOf<Question, Int>()

    private val _percentOfRightAnswers = MutableLiveData<Int>()
    val percentOfRightAnswers: LiveData<Int>
        get() = _percentOfRightAnswers

    private val _enoughPercentOfRightAnswers = MutableLiveData<Boolean>()
    val enoughPercentOfRightAnswers: LiveData<Boolean>
        get() = _enoughPercentOfRightAnswers

    private val _minPercentSecondary = MutableLiveData<Int>()
    val minPercentSecondary: LiveData<Int>
        get() = _minPercentSecondary

    private val _gameResult = MutableLiveData<GameResult>()
    val gameResult: LiveData<GameResult>
        get() = _gameResult

    fun chooseAnswer(userAnswerId: Int) {
        val questionToGameResult = question.value ?: throw RuntimeException(
            "Question not found GameViewModel.kt")
        userAnswers += questionToGameResult to userAnswerId

        checkAnswer(userAnswerId)
        updateProgress()
        generateQuestion()
    }

    private fun updateProgress() {
        val tempPercent = ((countOfRightAnswers /
                gameSettings.countOfQuestion.toDouble()) * 100).toInt()
        _percentOfRightAnswers.value = tempPercent
        _enoughPercentOfRightAnswers.value = tempPercent >= gameSettings.minPercentOfRightAnswers
    }

    private fun checkAnswer(userAnswerId: Int) {
        val correctAnswer = question.value?.correctAnswer
        if (userAnswerId == correctAnswer) {
            countOfRightAnswers++
        }
        countOfQuestionTemp--
    }

    private fun generateQuestion() {
        if (countOfQuestionTemp == 0) {
            finishGame()
        }
        _question.value = generateQuestionUseCase()
    }

    private fun finishGame() {
        _gameResult.value = GameResult(
            enoughPercentOfRightAnswers.value == true,
            countOfRightAnswers,
            gameSettings,
            userAnswers
        )
    }

    fun startGame(level: Level) {
        this.level = level
        this.gameSettings = getGameSettingsUseCase(level)
        countOfQuestionTemp = gameSettings.countOfQuestion
        _minPercentSecondary.value = gameSettings.minPercentOfRightAnswers
        generateQuestion()
    }
}