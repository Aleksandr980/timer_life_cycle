package com.example.m10_timer_life_cycle

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.SeekBar
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import com.example.m10_timer_life_cycle.databinding.ActivityMainBinding

private const val KEY = "key"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var timer1: CountDownTimer? = null
    private var timer2: CountDownTimer? = null


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @SuppressLint("MissingInflatedId", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var currentProgress = 0
        val DECREMENT = 1000

        val circleProgress = binding.progressBarCircular
        val buttonStart = binding.start
        val buttonStop = binding.stop
        val valueSeekBar = binding.custom2
        val seekBarDiscrete = binding.seekbarDiscrete
        val updateProgress = {
            circleProgress.progress
        }

        valueSeekBar.value("10")

        seekBarDiscrete?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar?, progress: Int,
                fromUser: Boolean,
            ) {
                Toast.makeText(
                    applicationContext, "discrete seekbar progress: $progress",
                    Toast.LENGTH_SHORT

                ).show()
                when (progress) {
                    0 -> valueSeekBar.value("10")
                    1 -> valueSeekBar.value("20")
                    2 -> valueSeekBar.value("30")
                    3 -> valueSeekBar.value("40")
                    4 -> valueSeekBar.value("50")
                    else -> valueSeekBar.value("60")
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                Toast.makeText(
                    applicationContext, "discrete seekbar touch started",
                    Toast.LENGTH_SHORT
                ).show()

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                Toast.makeText(
                    applicationContext, "discrete seekbar touch stopped",
                    Toast.LENGTH_SHORT
                ).show()

            }
        })


        fun timeValueTextStart(
            millisInFuture: Long = 0,
            millisUntilFinished: Long = 0,

            ) {
            timer1?.cancel()
            timer1 = object : CountDownTimer(millisInFuture, 1000) {

                override fun onTick(millisUntilFinished: Long) {
                    valueSeekBar.value("${millisUntilFinished / 1000}")
                    currentProgress = (millisUntilFinished).toInt()
                    currentProgress -= DECREMENT
                    updateProgress()
                }

                override fun onFinish() {
                    valueSeekBar.value("${millisUntilFinished / 1000}")
                }
            }.start()
        }


        fun timeValueProgressBarStart(
            millisInFuture: Long = 0,
            millisUntilFinished: Long = 0,
            countDownInterval: Long = 0,

            ) {
            timer2?.cancel()
            timer2 = object : CountDownTimer(millisInFuture, countDownInterval) {

                override fun onTick(millisUntilFinished: Long) {
                    circleProgress.progress =
                        (millisUntilFinished / countDownInterval).toInt()
                    seekBarDiscrete.isEnabled = false
                    buttonStop.isInvisible = false
                    buttonStart.isInvisible = true


                }

                override fun onFinish() {
                    circleProgress.progress = 100
                    seekBarDiscrete.isEnabled = true
                    buttonStop.isInvisible = true
                    buttonStart.isInvisible = false

                }
            }.start()
        }


        fun progressBar(startTimer: Boolean) {
            when (seekBarDiscrete.progress) {
                0 -> {
                    if (startTimer == true) {
                        timeValueTextStart(10000, 10000)
                        timeValueProgressBarStart(10000, 10000, 100)
                    }
                }
                1 -> {
                    if (startTimer == true) {
                        timeValueTextStart(20000, 20000)
                        timeValueProgressBarStart(20000, 20000, 200)
                    }
                }
                2 -> {
                    if (startTimer == true) {
                        timeValueTextStart(30000, 30000)
                        timeValueProgressBarStart(30000, 30000, 300)
                    }
                }
                3 -> {
                    if (startTimer == true) {
                        timeValueTextStart(40000, 40000)
                        timeValueProgressBarStart(40000, 40000, 400)
                    }
                }
                4 -> {
                    if (startTimer == true) {
                        timeValueTextStart(50000, 50000)
                        timeValueProgressBarStart(50000, 50000, 500)
                    }
                }
                else -> {
                    if (startTimer == true) {
                        timeValueTextStart(60000, 60000)
                        timeValueProgressBarStart(60000, 60000, 600)
                    }
                }
            }
        }

        binding.apply {
            buttonStart.setOnClickListener {
                progressBar(true)
            }

            buttonStop.setOnClickListener {
                timer1?.cancel()
                timer2?.cancel()
                circleProgress.progress = 100
                seekBarDiscrete.isEnabled = true
                buttonStop.isInvisible = true
                buttonStart.isInvisible = false
                when (binding.seekbarDiscrete.progress) {
                    0 -> valueSeekBar.value("10")
                    1 -> valueSeekBar.value("20")
                    2 -> valueSeekBar.value("30")
                    3 -> valueSeekBar.value("40")
                    4 -> valueSeekBar.value("50")
                    else -> valueSeekBar.value("60")
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putString(KEY, timer1.toString())
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.apply {
            getBundle(KEY)
        }
        super.onRestoreInstanceState(savedInstanceState)
    }
}












