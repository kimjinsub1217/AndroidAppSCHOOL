package com.example.android67_applicationclass

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android67_applicationclass.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    lateinit var activitySecondBinding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activitySecondBinding = ActivitySecondBinding.inflate(layoutInflater)

        activitySecondBinding.run{
            val appClass = application as AppClass

            textViewSecond.text = "${appClass.value1}\n"
            textViewSecond.append("${appClass.value2}\n")
            textViewSecond.append("${appClass.value3}")
        }

        setContentView(activitySecondBinding.root)
    }
}