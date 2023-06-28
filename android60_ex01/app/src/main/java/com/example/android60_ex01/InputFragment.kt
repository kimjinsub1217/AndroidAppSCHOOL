package com.example.android60_ex01

import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import com.example.android60_ex01.DataClass.Companion.studentList
import com.example.android60_ex01.databinding.FragmentInputBinding


class InputFragment : Fragment() {
    lateinit var fragmentInputBinding: FragmentInputBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainActivity = activity as MainActivity
        fragmentInputBinding = FragmentInputBinding.inflate(layoutInflater)

        fragmentInputBinding.run {
            inputToolbar.run {
                title = "학생 정보 입력 화면"
                setTitleTextColor(Color.WHITE)

                // back 버튼 아이콘을 표시한다.
                setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)

                // back 버튼의 아이콘 색상을 변경한다.
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    navigationIcon?.colorFilter =
                        BlendModeColorFilter(Color.WHITE, BlendMode.SRC_ATOP)
                } else {
                    navigationIcon?.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP)
                }
                // 백 버튼을 누르면 동작하는 리스너
                setNavigationOnClickListener {
                    mainActivity.removeFragment(FragmentName.FRAGMENT_INPUT)
                }
            }

            editTextInputUserAge.doAfterTextChanged {
                val ageString = editTextInputUserAge.text.toString().trim()
                if (ageString.isEmpty()) {
                    editTextInputUserAge.error = "나이를 입력하세요."
                } else {
                    if (ageString.toInt() <= 0) {
                        editTextInputUserAge.error = "0보다 큰 수를 입력하세요."
                    } else {
                        editTextInputUserAge.error = null // 에러 메시지 지우기
                    }
                }
            }

            editTextInputUserKor.doAfterTextChanged {
                val weightString = editTextInputUserKor.text.toString().trim()
                if (weightString.isEmpty()) {
                    editTextInputUserKor.error = "국어 점수를 입력하세요."
                } else {
                    if (weightString.toInt() < 0) {
                        editTextInputUserKor.error = "0이상의 수를 입력하세요."
                    } else {
                        editTextInputUserKor.error = null // 에러 메시지 지우기
                    }
                }
            }

            editTextInputUserKor.setOnEditorActionListener { _, _, _ ->
                var name = editTextInputUserName.text.toString()
                var age = editTextInputUserAge.text.toString()
                var kor = editTextInputUserKor.text.toString()




                if (age.isEmpty() || kor.isEmpty() || name.isEmpty()) {
                    Toast.makeText(context, "빈칸을 채워주세요 ^^", Toast.LENGTH_SHORT).show()
                } else if (age.toInt() <= 0 || kor.toInt() <= 0) {
                    Toast.makeText(context, "나이와 몸무게는 0이상의 수를 입력하세요.", Toast.LENGTH_SHORT).show()
                } else {
                    val studentClass = StudentClass(name, age.toInt(), kor.toInt())
                    Log.i("확인", "${age.toInt()}")
                    studentList.add(0, studentClass)

//                    spinner.setSelection(1)
//                    editTextInputUserName.text.clear()
//                    editTextInputUserAge.text.clear()
//                    editTextInputUserWeight.text.clear()

                    mainActivity.removeFragment(FragmentName.FRAGMENT_INPUT)
                }
                false
            }

        }


        return fragmentInputBinding.root
    }

}