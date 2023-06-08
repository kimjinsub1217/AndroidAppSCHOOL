package com.test.android10_textview

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android10_textview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 문자열 출력
        binding.textView.text= "안녕하세요"

        binding.run {
            textView3.run{
                text = "안녕하세요"
                //배경 색상
                //setBackgroundColor(Color.RED)
                //setBackgroundColor(Color.rgb(222.222.222)
//                setBackgroundColor(Color.GREEN)

                // 글자색 설정
                setTextColor(Color.RED)

                // 새로운 문자열 설정
                text = "문자열1"

                //문자열 추가
                append("문자열 추가")
                append("문자열 추가2")

            }
        }

    }
}