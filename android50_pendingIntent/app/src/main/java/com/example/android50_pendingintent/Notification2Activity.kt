package com.example.android50_pendingintent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android50_pendingintent.databinding.ActivityNotification2Binding

class Notification2Activity : AppCompatActivity() {

    lateinit var binding: ActivityNotification2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotification2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {
            textViewNo2.run {
                val data3 = intent.getIntExtra("data3", 0)
                val data4 = intent.getStringExtra("data4")

                text = "data3 : ${data3}\n"
                append("data4 : ${data4}")
            }
        }
    }
}