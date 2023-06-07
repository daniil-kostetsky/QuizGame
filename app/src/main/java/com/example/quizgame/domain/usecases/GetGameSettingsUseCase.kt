package com.example.quizgame.domain.usecases

import com.example.quizgame.domain.entity.GameSettings
import com.example.quizgame.domain.entity.Level
import com.example.quizgame.domain.repository.GameRepository

class GetGameSettingsUseCase(
    private val repository: GameRepository
) {

    operator fun invoke(level: Level): GameSettings {
        return repository.getGameSettings(level)
    }
}