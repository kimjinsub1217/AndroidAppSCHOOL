package com.test.android18_ex01

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.RadioButton
import com.test.android18_ex01.databinding.ActivityMainBinding
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
            var gender = ""
            var hobby = ""
            var korScore = 0

            val studentList = mutableListOf<Student>()

            textNameInputEditText.run {
                requestFocus()
                // 엔터키를 눌렀을때 이벤트
                setOnEditorActionListener { v, actionId, event ->

                    // true를 반환하면 엔터키누른 후에 포커스가 현재 EditText로 유지
                    // false를 반환하면 엔터키 누른 후에 다음 EditText로 포커스가 이동
                    false
                }

            }
            radioGroup1.setOnCheckedChangeListener { _, checkedId ->
                val selectedRadioButton = binding.root.findViewById<RadioButton>(checkedId)
                gender = selectedRadioButton.text.toString()
                Log.d("MainActivity", "Selected Gender: $gender")
            }
            korInputEditText.setOnEditorActionListener { _, actionId, event ->
                if (actionId == EditorInfo.IME_ACTION_DONE || event?.keyCode == KeyEvent.KEYCODE_ENTER) {

                    name = textNameInputEditText.text.toString()
                    age = textAgeInputEditText.text.toString().toInt()

                    if (gameCheckBox.isChecked) {
                        hobby += gameCheckBox.text.toString()+" ,"
                    }

                    if (soccerCheckBox.isChecked) {
                        hobby += soccerCheckBox.text.toString()+" ,"
                    }

                    if (watchVideoCheckBox.isChecked) {
                        hobby += watchVideoCheckBox.text.toString()+" ,"
                    }

                    korScore = korInputEditText.text.toString().toInt()

                    val student = Student(name, age, gender, hobby, korScore)
                    studentList.add(student)
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(korInputEditText.windowToken, 0)

                }
                true
            }

            button.setOnClickListener {
                textNameInputEditText.text?.clear()
                textAgeInputEditText.text?.clear()
                radioGroup1.check(maleButton.id)
                gameCheckBox.isChecked = false
                soccerCheckBox.isChecked = false
                watchVideoCheckBox.isChecked = false

                Log.i("MainActivity", "$studentList")

                for (i in studentList) {
                    resultTextView.text =
                        "이름 : ${i.name}\n나이 : ${i.age}\n성별 : ${i.gender}\n취미:${i.hobby.substring(0,hobby.length-2)}\n국어점수:${i.korScore} "
                }


            }
        }
    }
}

data class Student(
    val name: String,
    val age: Int,
    val gender: String,
    val hobby: String,
    val korScore: Int
)

