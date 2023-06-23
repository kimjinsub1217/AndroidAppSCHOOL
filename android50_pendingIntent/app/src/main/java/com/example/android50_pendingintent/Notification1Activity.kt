package com.example.android50_pendingintent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android50_pendingintent.databinding.ActivityNotification1Binding

class Notification1Activity : AppCompatActivity() {

    lateinit var binding: ActivityNotification1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotification1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {
            val data1 = intent.getIntExtra("data1", 0)
            val data2 = intent.getStringExtra("data2")

            textViewNo1.run {
                text = "data1 : ${data1}\n"
                append("data2 : ${data2}")
            }
        }
    }
}