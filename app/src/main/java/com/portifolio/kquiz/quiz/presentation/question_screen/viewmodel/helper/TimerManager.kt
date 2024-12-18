package com.portifolio.kquiz.quiz.presentation.question_screen.viewmodel.helper

import android.os.CountDownTimer

class TimerManager(
    val secondsCountdownTime: Int,
    val tickInterval: Int,
) {

    private var currentTimer: CountDownTimer? = null

    fun startNewTimer(onTick: (Int) -> Unit, onFinish: () -> Unit) {

        cancelTimer()

        currentTimer = object: CountDownTimer(secondsToMilliseconds(secondsCountdownTime), secondsToMilliseconds(tickInterval)){

            override fun onTick(milisecondsLeft: Long) = onTick(millisecondsToSeconds(milisecondsLeft))

            override fun onFinish() = onFinish()

        }.start()
    }

    fun cancelTimer() {

        currentTimer?.cancel()
        currentTimer = null
    }

    private fun secondsToMilliseconds(seconds: Int) = seconds * 1000L

    private fun millisecondsToSeconds(miliseconds: Long) = (miliseconds / 1000).toInt()

}