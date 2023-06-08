package com.test.android11_button

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android11_button.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        // 버튼의 문자열을 변경한다.
//        binding.button1.text = "변경된 문자열 이름"
//
//        // 이미지 버튼의 이미지를 변경한다.
//        binding.imageButton2.setImageResource(R.drawable.imgflag8)

        binding.run {
            button1.run {
                text = "버튼 입니다."
                setOnClickListener {
                    binding.textView1.text="버튼을 눌렀어요."
                }
            }
            imageButton2.run {
                setImageResource(R.drawable.imgflag8)
                setOnClickListener {
                    binding.textView1.text="이미지 버튼을 눌렀어요."
                }
            }
        }
    }
}