package com.test.android03_viewbindingex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android03_viewbindingex.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // viewBinding 객체를 담을 변수
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // binding 설정
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1 ~ 100 버튼
        binding.add1to100.setOnClickListener {
            var result = 0
            for (i in 1..100) {
                result += i
            }
            binding.resultTextView.text = result.toString()
        }

        // 101 ~ 200 버튼
        binding.add101to200.setOnClickListener {
            var result = 0
            for (i in 101..200) {
                result += i
            }
            binding.resultTextView.text = result.toString()
        }
    }
}