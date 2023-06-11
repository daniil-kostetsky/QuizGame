package com.example.quizgame.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.quizgame.R
import com.example.quizgame.databinding.FragmentGameFinishedBinding
import com.example.quizgame.domain.entity.GameResult

class GameFinishedFragment : Fragment() {

    private val args by navArgs<GameFinishedFragmentArgs>()

    private var _binding: FragmentGameFinishedBinding? = null
    private val binding get() = _binding ?: throw RuntimeException(
        "FragmentGameFinishedBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonRetryGame.setOnClickListener {
            retryGame()
        }

        isWin()
        showResultList()
    }

    private fun isWin() {
        val textView = binding.linearLayoutResult.findViewById<TextView>(R.id.tv_win)
        val colorResId = if (args.gameResult.isWin) {
            android.R.color.holo_green_light
        } else {
            android.R.color.holo_red_light
        }
        val color = ContextCompat.getColor(requireContext(), colorResId)
        textView.setTextColor(color)

        val isWinStr = if (args.gameResult.isWin) {
            R.string.user_win
        } else {
            R.string.user_lose
        }
        textView.setText(isWinStr)
    }

    private fun showResultList() {

        val result = args.gameResult.userAnswers
        for (res in result) {
            val quote = res.key.quote
            val userAnswer = res.value
            val correctAnswer = res.key.correctAnswer

            val itemView = if (userAnswer == correctAnswer) {
                layoutInflater.inflate(
                    R.layout.item_true_answer,
                    binding.linearLayoutResult,
                    false)
            } else {
                layoutInflater.inflate(
                    R.layout.item_false_answer,
                    binding.linearLayoutResult,
                    false)
            }
            val tvQuote = itemView.findViewById<TextView>(R.id.tv_quote)
            val ivUserAnswer = itemView.findViewById<ImageView>(R.id.iv_user_answer)
            val ivCorrectAnswer = itemView.findViewById<ImageView>(R.id.iv_correct_answer)

            tvQuote.text = String.format(getString(R.string.quote_text, quote))
            ivUserAnswer.setImageResource(userAnswer)
            ivCorrectAnswer.setImageResource(correctAnswer)
            binding.linearLayoutResult.addView(itemView)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun retryGame() {
        findNavController().popBackStack()
    }

}