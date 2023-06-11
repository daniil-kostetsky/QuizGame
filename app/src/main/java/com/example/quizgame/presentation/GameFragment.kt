package com.example.quizgame.presentation

import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.quizgame.R
import com.example.quizgame.databinding.FragmentGameBinding
import com.example.quizgame.domain.entity.GameResult
import com.example.quizgame.domain.entity.Level

class GameFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[GameViewModel::class.java]
    }

    private val ivOptions by lazy {
        mutableListOf<ImageView>().apply {
            add(binding.ivOption1)
            add(binding.ivOption2)
            add(binding.ivOption3)
        }
    }

    private val args by navArgs<GameFragmentArgs>()

    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("FragmentGameBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
        viewModel.startGame(args.level)
        setAnswersClickListeners()
    }

    private fun setAnswersClickListeners() {
        for (ivOption in ivOptions) {
            ivOption.setOnClickListener {
                val imageId = ivOption.tag.toString().toInt()
                viewModel.chooseAnswer(imageId)
            }
        }
    }

    private fun observeViewModel() {

        viewModel.question.observe(viewLifecycleOwner) {
            binding.tvQuote.text = String.format(
                getString(R.string.quote_text),
                it.quote
            )
            for (i in ivOptions.indices) {
                val drawable = ContextCompat.getDrawable(requireContext(), it.answerOption[i])
                ivOptions[i].setImageDrawable(drawable)
                ivOptions[i].tag = it.answerOption[i]
            }
        }

        viewModel.percentOfRightAnswers.observe(viewLifecycleOwner) {
            binding.progressBar.setProgress(it, true)
        }

        viewModel.enoughPercentOfRightAnswers.observe(viewLifecycleOwner) {
            val color = getColorByState(it)
            binding.progressBar.progressTintList = ColorStateList.valueOf(color)
        }

        viewModel.minPercentSecondary.observe(viewLifecycleOwner) {
            binding.progressBar.secondaryProgress = it
        }

        viewModel.gameResult.observe(viewLifecycleOwner) {
            launchGameFinishedFragment(it)
        }
    }

    private fun getColorByState(state: Boolean): Int {
        val colorResId = if (state) {
            android.R.color.holo_green_light
        } else {
            android.R.color.holo_red_light
        }
        return ContextCompat.getColor(requireContext(), colorResId)
    }

    private fun launchGameFinishedFragment(gameResult: GameResult) {
        val action = GameFragmentDirections.actionGameFragmentToGameFinishedFragment(gameResult)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}