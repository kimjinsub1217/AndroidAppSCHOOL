package com.test.android21_compountcomponent2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android21_compountcomponent2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {
            button2.run {
                // Toggle Button 의 ON/OFF 상태를 가져온다.
                if (toggleButton.isChecked) {
                    textView.text = "ON 상태입니다"
                } else {
                    textView.text = "OFF 상태입니다"
                }

                // Switch의 ON/OFF 상태를 가져온다.
                if (switch1.isChecked) {
                    textView.append("Switch : ON 상태입니다\n")
                } else {
                    textView.append("Switch : OFF 상태입니다\n")
                }
            }

            button3.run {
                setOnClickListener {
                    // Toggle Button을 ON 상태로 설정한다.
                    toggleButton.isChecked = true

                    // switch On 상태로 설정
                    switch1.isChecked = false
                }
            }

            button4.run {
                setOnClickListener {
                    // Toggle Button을 OFF 상태로 설정한다.
                    toggleButton.isChecked = false
                    // switch를 OFF 상태로 설정한다.
                    switch1.isChecked = false
                }
            }

            button5.run {
                setOnClickListener {
                    // Toggle Button을 반전시킨다.
                    toggleButton.toggle()

                    // Switch를 반전시킨다.
                    switch1.isChecked = false
                }
            }

            // Toggle button의 ON/OFF 상태가 변경될 때의 리스너
            toggleButton.run {
                setOnCheckedChangeListener { buttonView, isChecked ->
                    if (isChecked == true) {
                        textView2.text = "Toggle 버튼이 ON 상태입니다"
                    } else {
                        textView2.text = "Toggle 버튼이 OFF 상태입니다"
                    }
                }
            }

            /*
            [Switch]
                text : 좌측에 나타나는 문자열
                textOn : ON 상태일 때 표시할 문자열
                textOff : OFF 상태일 때 표시할 문자열
                showText : textOn, TextOff 에 설정할 문자열을 보여줄 것인가
                thumb : 버튼 이미지
                track : 트랙 이미지
             */

            switch1.run {
                setOnCheckedChangeListener { buttonView, isChecked ->
                    if(isChecked){
                        textView.append("switch가 on 상태이다.")
                    }else{
                        textView2.append("switch가 off 상태이다.")
                    }
                }
            }

        }


    }


}