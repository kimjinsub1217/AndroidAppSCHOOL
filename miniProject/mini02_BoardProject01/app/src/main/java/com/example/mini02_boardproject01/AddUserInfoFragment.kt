package com.example.mini02_boardproject01

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.os.SystemClock
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import com.example.mini02_boardproject01.databinding.FragmentAddUserInfoBinding
import kotlin.concurrent.thread


class AddUserInfoFragment : Fragment() {
    lateinit var fragmentAddUserInfoBinding: FragmentAddUserInfoBinding
    lateinit var mainActivity: MainActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentAddUserInfoBinding = FragmentAddUserInfoBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        fragmentAddUserInfoBinding.run {
            toolbarUserInfo.run {
                title = "회원가입"

                setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
                setNavigationOnClickListener {
                    mainActivity.removeFragment(MainActivity.JOIN_FRAGMENT)
                }

            }

            textInputEditTextNickName.requestFocus()

            thread {
                // 500ms 쉬게 한다.
                // onCreate 메서드의 수행이 끝날 때 까지 대기한다.
                SystemClock.sleep(500)
                val imm =
                    requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(textInputEditTextNickName, 0)
            }

            completeButton.setOnClickListener {
                val userId = textInputEditTextNickName.text
                val userPassword = textInputEditTextAge.text

                val userIdError = if (TextUtils.isEmpty(userId)) "이름을 입력해주세요." else null
                val userPasswordError =
                    if (TextUtils.isEmpty(userPassword)) "비밀번호를 입력해주세요." else null

                textInputLayoutNickName.error = userIdError
                textInputLayoutLoginUserAge.error = userPasswordError

                if (userIdError == null && userPasswordError == null) {
                    mainActivity.removeFragment(MainActivity.ADD_USER_INFO_FRAGMENT)
                    mainActivity.removeFragment(MainActivity.JOIN_FRAGMENT)
                }
            }

        }

        return fragmentAddUserInfoBinding.root
    }
}