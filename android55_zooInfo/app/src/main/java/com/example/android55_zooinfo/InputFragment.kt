package com.example.android55_zooinfo

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import com.example.android55_zooinfo.databinding.FragmentInputBinding


class InputFragment : Fragment() {

    lateinit var fragmentInputBinding: FragmentInputBinding
    lateinit var mainActivity: MainActivity

    // 스피너 구성을 위한 데이터
    var spinnerData = arrayOf("코끼리", "기린", "토끼")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainActivity = activity as MainActivity
        fragmentInputBinding = FragmentInputBinding.inflate(layoutInflater)

        fragmentInputBinding.run {
            spinner.run {
                val adapter1 = ArrayAdapter<String>(
                    mainActivity, android.R.layout.simple_spinner_item, spinnerData
                )
                adapter1.setDropDownViewResource(android.R.layout.simple_spinner_item)
                adapter = adapter1
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

            editTextInputUserWeight.doAfterTextChanged {
                val weightString = editTextInputUserWeight.text.toString().trim()
                if (weightString.toString().isEmpty()) {
                    editTextInputUserWeight.error = "몸무게를 입력하세요."
                } else {
                    if (weightString.toInt() <= 0) {
                        editTextInputUserWeight.error = "0보다 큰 수를 입력하세요."
                    } else {
                        editTextInputUserWeight.error = null // 에러 메시지 지우기
                    }
                }
            }

            editTextInputUserWeight.setOnEditorActionListener { _, _, _ ->
                var type = spinnerData[spinner.selectedItemPosition]
                var name = editTextInputUserName.text.toString()
                var age = editTextInputUserAge.text.toString()
                var weight = editTextInputUserWeight.text.toString()




                if (age.isEmpty() || weight.isEmpty()||name.isEmpty()) {
                    Toast.makeText(context, "빈칸을 채워주세요 ^^", Toast.LENGTH_SHORT).show()
                } else if (age.toInt() <= 0 || weight.toInt() <= 0) {
                    Toast.makeText(context, "나이와 몸무게는 0보다 큰 수를 입력해주세요.", Toast.LENGTH_SHORT).show()
                } else {
                    val zooClass = ZooClass(type, name, age.toInt(), weight.toInt())
                    Log.i("확인", "${age.toInt()}")
                    DataClass.animalList.add(0,zooClass)

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