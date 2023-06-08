package com.test.android09_includeotherlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android09_includeotherlayout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // include를 통해 View에 접근한다.
        binding.textView3.text = "첫 번째 문자"
        binding.include1.textView.text = "두 번째 문자"
        binding.include2.textView2.text = "세 번째 문자"


    }
}