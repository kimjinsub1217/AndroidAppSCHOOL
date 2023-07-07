package com.example.android70_ex03.fragment

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.android70_ex03.MainActivity
import com.example.android70_ex03.R
import com.example.android70_ex03.databinding.FragmentPasswordSettingBinding
import com.example.android70_ex03.db.DAOPassword
import com.example.android70_ex03.db.PasswordClass


class PasswordSettingFragment : Fragment() {

    lateinit var fragmentPasswordSettingBinding:
            FragmentPasswordSettingBinding
    lateinit var mainActivity: MainActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentPasswordSettingBinding = FragmentPasswordSettingBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        fragmentPasswordSettingBinding.run {
            passwordSettingToolbar.run {
                title = "비밀번호설정"

                // 타이틀 글자 색상
                setTitleTextColor(Color.WHITE)

                val savaPassword = DAOPassword.selectAllData(requireContext())
                if (savaPassword.isNotEmpty()){
                    mainActivity.replaceFragment(MainActivity.LOGIN_FRAGMENT,
                        addToBackStack = true,
                        animate = true
                    )
                }

                settingCompleteButton.setOnClickListener {
                    val password = settingPasswordEditText.text.toString()
                    val checkPassword = settingCheckPasswordEditText.text.toString()

                    if (password == checkPassword) {
                        Toast.makeText(requireContext(), "비밀번호 생성 완료", Toast.LENGTH_SHORT)
                            .show()
                        val obj1 = PasswordClass(0,password )
                        DAOPassword.insertData(requireContext(), obj1)
                        mainActivity.replaceFragment(MainActivity.LOGIN_FRAGMENT,
                            addToBackStack = true,
                            animate = true
                        )
                    }else{
                        Toast.makeText(requireContext(), "비밀번호가 일치하지 않네요", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }

        return fragmentPasswordSettingBinding.root
    }

}