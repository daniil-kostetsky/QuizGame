package com.example.quizgame.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quizgame.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_game)
    }
}