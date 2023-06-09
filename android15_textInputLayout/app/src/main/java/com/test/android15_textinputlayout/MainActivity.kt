package com.test.android15_textinputlayout

import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import com.test.android15_textinputlayout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {

            textInputLayout.run {
                // 오류 문구 설정
//                error = "입력 오류가 발생하였다."
                editText?.run{
                    addTextChangedListener {
                        if(it!!.length > 10){
                            // error = "10 글자 이하로 입력해주세요"
                            textInputLayout.error = "10 글자 이하로 입력해주세요"
                        } else {
                            // error = null
                            textInputLayout.error = null
                        }
                    }
                }
            }

            button1.run {
                setOnClickListener {
//                    val str1 = textInputText.text.toString()
//                    textView.text = str1

                    //TextInputLayout으로 부터 EditText를 추출한다.
                    if (textInputLayout.editText != null) {
                        val str1 = textInputLayout.editText!!.text.toString()
                        textView.text = str1
                    }
                }
            }
        }
    }
}