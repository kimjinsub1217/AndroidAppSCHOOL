package com.test.android14_ex02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android14_ex02.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var num1 = binding.editText1.text
        var num2 = binding.editText2.text

        binding.run {

            plusButton.setOnClickListener {
                textView.text = "${num1.toString().toInt()} + ${num2.toString().toInt()} = ${num1.toString().toInt() + num2.toString().toInt() }"
            }
            minusButton.setOnClickListener {
                textView.text = "${num1.toString().toInt()} - ${num2.toString().toInt()} = ${num1.toString().toInt() - num2.toString().toInt() }"

            }
            multiplyButton.setOnClickListener {
                textView.text = "${num1.toString().toInt()} * ${num2.toString().toInt()} = ${num1.toString().toInt() *num2.toString().toInt() }"

            }
            divideButton.setOnClickListener {
                textView.text = "${num1.toString().toInt()} / ${num2.toString().toInt()} = ${num1.toString().toInt() / num2.toString().toInt() }"

            }


        }


    }
}