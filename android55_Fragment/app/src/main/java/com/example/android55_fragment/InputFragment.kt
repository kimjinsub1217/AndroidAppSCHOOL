package com.example.android55_fragment

import android.content.Context
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.example.android55_fragment.databinding.FragmentInputBinding
import com.example.android55_fragment.infoList.dataList
import kotlin.concurrent.thread


class InputFragment : Fragment() {
    lateinit var fragmentInputBinding: FragmentInputBinding
    lateinit var mainActivity: MainActivity


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Fragment를 관리하는 Activity 객체를 가져온다.
        mainActivity = activity as MainActivity

        //ViewBinding
        fragmentInputBinding = FragmentInputBinding.inflate(layoutInflater)


        fragmentInputBinding.run {

            thread {
                // 500ms 쉬게 한다.
                // onCreate 메서드의 수행이 끝날 때 까지 대기한다.
                SystemClock.sleep(500)

                val imm =
                    requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(
                    activity?.currentFocus?.windowToken,
                    InputMethodManager.HIDE_IMPLICIT_ONLY
                )
            }

            editTextInputUserName.run {
                requestFocus()
            }

            editTextInputUserKor.run {
                setOnEditorActionListener { _, _, _ ->
                    var name = editTextInputUserName.text.toString()
                    var age = editTextInputUserAge.text.toString().toInt()
                    var kor = editTextInputUserKor.text.toString().toInt()

                    val studentInfo = DataClass(name, age, kor)
                    dataList.add(studentInfo)
                    editTextInputUserName.text.clear()
                    editTextInputUserAge.text.clear()
                    editTextInputUserKor.text.clear()



//                    내 코드
//                    mainActivity.replaceFragment(FragmentName.FRAGMENT_MAIN,
//                        addToBackStack = true,
//                        animate = true
//                    )

//                  강사님 코드
                    mainActivity.removeFragment(FragmentName.FRAGMENT_INPUT)
                    false
                }
            }


        }

        return fragmentInputBinding.root
    }

}