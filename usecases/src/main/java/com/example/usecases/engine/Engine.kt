package com.example.usecases.engine

import android.os.CountDownTimer
private const val Finish_After_Time = 10 * 60000L
private const val Tick_Time = 1000L

class  Ticker( private val onTicking :(Long )->Unit):CountDownTimer(Finish_After_Time, Tick_Time) {
    override fun onFinish() {
    }

    override fun onTick(millisUntilFinished: Long) {
        onTicking( millisUntilFinished )
    }
}


