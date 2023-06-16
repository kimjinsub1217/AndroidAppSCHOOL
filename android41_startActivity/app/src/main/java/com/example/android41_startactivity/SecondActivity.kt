package com.example.android41_startactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android41_startactivity.databinding.ActivityMainBinding
import com.example.android41_startactivity.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {
            buttonMain.run {
                setOnClickListener {
                    finish()
                }
            }
        }
    }
}