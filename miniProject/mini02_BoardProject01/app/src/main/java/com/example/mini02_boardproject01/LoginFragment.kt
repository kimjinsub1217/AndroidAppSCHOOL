package com.example.mini02_boardproject01

import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mini02_boardproject01.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    lateinit var fragmentLoginBinding: FragmentLoginBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        fragmentLoginBinding = FragmentLoginBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        fragmentLoginBinding.run {
            // toolbar
            toolbarLogin.run {
                title = "로그인"

            }

            textInputEditTextLoginUserPassword.setOnKeyListener { _, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                    val userId = textInputEditTextLoginUserId.text
                    val userPassword = textInputEditTextLoginUserPassword.text

                    val userIdError = if (TextUtils.isEmpty(userId)) "이름을 입력해주세요." else null
                    val userPasswordError = if (TextUtils.isEmpty(userPassword)) "비밀번호를 입력해주세요." else null

                    textInputLayoutLoginUserId.error = userIdError
                    textInputLayoutLoginUserPassword.error = userPasswordError

                    if (userIdError == null && userPasswordError == null) {
                        mainActivity.replaceFragment(MainActivity.HOME_FRAGMENT, false, null)
                    }

                    true
                } else {
                    false
                }
            }

            loginButton.setOnClickListener {
                val userId = textInputEditTextLoginUserId.text
                val userPassword = textInputEditTextLoginUserPassword.text

                val userIdError = if (TextUtils.isEmpty(userId)) "이름을 입력해주세요." else null
                val userPasswordError = if (TextUtils.isEmpty(userPassword)) "비밀번호를 입력해주세요." else null

                textInputLayoutLoginUserId.error = userIdError
                textInputLayoutLoginUserPassword.error = userPasswordError

                if (userIdError == null && userPasswordError == null) {
                    mainActivity.replaceFragment(MainActivity.HOME_FRAGMENT, false, null)
                }
            }

            joinButton.setOnClickListener {
                mainActivity.replaceFragment(MainActivity.JOIN_FRAGMENT, true, null)
            }

        }

        return fragmentLoginBinding.root
    }

}