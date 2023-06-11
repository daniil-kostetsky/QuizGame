package com.example.quizgame.presentation

import android.content.Context
import android.content.res.ColorStateList
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.quizgame.R

interface OnAnswerOptionClickListener {
    fun onAnswerOptionClick(imageId: Int)
}

@BindingAdapter("isWin")
fun bindIsWin(textView: TextView, isWin: Boolean) {

        val colorResId = if (isWin) {
            android.R.color.holo_green_light
        } else {
            android.R.color.holo_red_light
        }
        val color = ContextCompat.getColor(textView.context, colorResId)
        textView.setTextColor(color)

        val isWinStr = if (isWin) {
            textView.context.getString(R.string.user_win)
        } else {
            textView.context.getString(R.string.user_lose)
        }
        textView.text = isWinStr
}

@BindingAdapter("setQuote")
fun bindSetQuote(textView: TextView, quote: String) {
    textView.text = String.format(
        textView.context.getString(R.string.quote_text),
        quote
    )
}

@BindingAdapter("setProgressInBar")
fun bindSetProgressInBar(progressBar: ProgressBar, percentOfRightAnswer: Int) {
    progressBar.setProgress(percentOfRightAnswer, true)
}

@BindingAdapter("progressBarTintList")
fun bindProgressBarTintList(progressBar: ProgressBar, enoughPercentOfRightAnswers: Boolean) {
    val color = getColorByState(progressBar.context, enoughPercentOfRightAnswers)
    progressBar.progressTintList = ColorStateList.valueOf(color)
}

@BindingAdapter("setAnswerOptions")
fun bindSetAnswerOptions(imageView: ImageView, resId: Int) {
    val drawable = ContextCompat.getDrawable(imageView.context, resId)
    imageView.setImageDrawable(drawable)
    imageView.tag = resId
}

@BindingAdapter("onAnswerOptionClickListener")
fun bindOnAnswerOptionCLickListener(imageView: ImageView, clickListener: OnAnswerOptionClickListener) {
    imageView.setOnClickListener {
        val imageId = imageView.tag.toString().toInt()
        clickListener.onAnswerOptionClick(imageId)
    }
}

private fun getColorByState(context: Context, state: Boolean): Int {
    val colorResId = if (state) {
        android.R.color.holo_green_light
    } else {
        android.R.color.holo_red_light
    }
    return ContextCompat.getColor(context, colorResId)
}