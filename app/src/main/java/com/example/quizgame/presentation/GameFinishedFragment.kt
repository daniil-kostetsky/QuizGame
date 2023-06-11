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
import com.example.quizgame.domain.entity.Question

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
        binding.gameResult = args.gameResult
        showResultList()
    }

    private fun showResultList() {

        val result = args.gameResult.userAnswers
        for (res in result) {
            val itemView = getItemView(res)
            binding.linearLayoutResult.addView(itemView)
        }
    }

    private fun getItemView(res: Map.Entry<Question, Int>): View {
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
        return itemView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun retryGame() {
        findNavController().popBackStack()
    }
}