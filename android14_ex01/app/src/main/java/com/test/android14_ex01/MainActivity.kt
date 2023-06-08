package com.test.android14_ex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android14_ex01.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var dan = binding.editText.text

        binding.button.setOnClickListener {
            var gugudan =""
            for (i in 1..9){
                gugudan += "${dan.toString().toInt()} * $i = ${dan.toString().toInt() * i}\n"
            }
            binding.textView.text = gugudan

        }

    }
}