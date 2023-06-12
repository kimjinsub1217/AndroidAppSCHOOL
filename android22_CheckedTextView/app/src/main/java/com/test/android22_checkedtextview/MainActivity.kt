package com.test.android22_checkedtextview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android22_checkedtextview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {
            checkedTextView1.run {
                setOnClickListener {
                    // 현재 체크 상태를 반전시킨다.
                    toggle()

                }
            }

            checkedTextView2.run {
                setOnClickListener {
                    toggle()
                }
            }

            button.run {
                setOnClickListener {
                    checkedTextView1.isChecked = true
                    checkedTextView2.isChecked = true
                }
            }

            button2.run {
                setOnClickListener {
                    checkedTextView1.isChecked = true
                    checkedTextView2.isChecked = true
                }
            }

            button3.run {
                setOnClickListener {
                    textView.text = ""
                    if (checkedTextView1.isChecked) {
                        textView.append("CheckBox1이 체크 되어 있다.\n")
                    } else {
                        textView.append("CheckBox1이 체크 되어 있지 않다.\n")
                    }

                    if (checkedTextView2.isChecked) {
                        textView.append("CheckBox2이 체크 되어 있다.\n")
                    } else {
                        textView.append("CheckBox12 체크 되어 있지 않다.\n")
                    }

                    if(checkedTextView3.isChecked){
                        textView.append("Radio1이 선택되었습니다")
                    } else if(checkedTextView4.isChecked){
                        textView.append("Radio2가 선택되었습니다")
                    } else if(checkedTextView4.isChecked){
                        textView.append("Radio3이 선택되었습니다")
                    }
                }
            }
//            라디오 버튼을 구현

//            checkedTextView3.isChecked = true
//            checkedTextView3.run{
//                setOnClickListener {
//                    checkedTextView3.isChecked = true
//                    checkedTextView4.isChecked = false
//                    checkedTextView5.isChecked = false
//                }
//            }
//            checkedTextView4.run{
//                setOnClickListener {
//                    checkedTextView3.isChecked = false
//                    checkedTextView4.isChecked = true
//                    checkedTextView5.isChecked = false
//                }
//            }
//            checkedTextView3.run{
//                setOnClickListener {
//                    checkedTextView3.isChecked = false
//                    checkedTextView4.isChecked = false
//                    checkedTextView5.isChecked = true
//                }
        }
    }
}