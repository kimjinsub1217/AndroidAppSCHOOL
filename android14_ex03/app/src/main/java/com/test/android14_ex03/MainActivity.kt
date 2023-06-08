package com.test.android14_ex03

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.addTextChangedListener
import com.test.android14_ex03.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.run {


            var name = ""
            var age = 0
            var korScore = 0
            var engScore = 0
            var mathScore = 0
            val studentList = mutableListOf<Student>()

            thread {
                // 500ms 쉬게 한다.
                // onCreate 메서드의 수행이 끝날 때 까지 대기한다.
                SystemClock.sleep(500)
                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(currentFocus, 0)
            }
            nameEditText.run {
                requestFocus()
                // 엔터키를 눌렀을때 이벤트
                setOnEditorActionListener { v, actionId, event ->

                    // true를 반환하면 엔터키누른 후에 포커스가 현재 EditText로 유지
                    // false를 반환하면 엔터키 누른 후에 다음 EditText로 포커스가 이동
                    false
                }

            }
            mathEditText.setOnEditorActionListener { _, actionId, event ->
                if (actionId == EditorInfo.IME_ACTION_DONE || event?.keyCode == KeyEvent.KEYCODE_ENTER) {

                    name = nameEditText.text.toString()
                    age = ageEditText.text.toString().toInt()
                    korScore = korEditText.text.toString().toInt()
                    engScore = engEditText.text.toString().toInt()
                    mathScore = mathEditText.text.toString().toInt()

                    val student = Student(name, age, korScore, engScore, mathScore)
                    studentList.add(student)


                    nameEditText.text.clear()
                    ageEditText.text.clear()
                    korEditText.text.clear()
                    engEditText.text.clear()
                    mathEditText.text.clear()


                    textView.text = "현재까지 저장한 학생의 수는 : ${studentList.size.toString()}"
                    nameEditText.requestFocus()
                }
                true
            }

            button.setOnClickListener {
                Log.i("stduentList","$studentList\n")
            }
        }
    }
}

data class Student(
    val name: String,
    val age: Int,
    val korScore: Int,
    val engScore: Int,
    val mathScore: Int
)