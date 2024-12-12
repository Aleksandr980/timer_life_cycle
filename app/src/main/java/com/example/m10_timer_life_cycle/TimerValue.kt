package com.example.m10_timer_life_cycle

import android.content.Context
import android.util.AttributeSet

import android.widget.LinearLayout
import com.example.m10_timer_life_cycle.databinding.WidgetCustomBinding

class TimerValue @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val binding : WidgetCustomBinding

    init {
        val root = inflate(context, R.layout.widget_custom, this)
        binding = WidgetCustomBinding.bind(root)
    }
    fun value(text: String) {
        binding.custom2.text = text
    }

}


