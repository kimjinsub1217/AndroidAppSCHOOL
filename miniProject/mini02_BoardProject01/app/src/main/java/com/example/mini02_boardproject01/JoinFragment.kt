package com.example.mini02_boardproject01

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.os.SystemClock
import android.text.TextUtils
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import com.example.mini02_boardproject01.databinding.FragmentJoinBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.FirebaseDatabase
import kotlin.concurrent.thread


class JoinFragment : Fragment() {
    lateinit var fragmentJoinBinding: FragmentJoinBinding
    lateinit var mainActivity: MainActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentJoinBinding = FragmentJoinBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        fragmentJoinBinding.run {


            toolbarJoin.run {
                title = "회원가입"

                setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
                setNavigationOnClickListener {
                    mainActivity.removeFragment(MainActivity.JOIN_FRAGMENT)
                }
            }

            textInputEditTextJoinUserId.requestFocus()

            thread {
                // 500ms 쉬게 한다.
                // onCreate 메서드의 수행이 끝날 때 까지 대기한다.
                SystemClock.sleep(500)
                val imm =
                    requireActivity().getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(textInputEditTextJoinUserId, 0)
            }

            nextButton.setOnClickListener {
                val password = textInputEditTextJoinUserPassword.text.toString()
                val rePassword = textInputEditTextJoinUserRePassword.text.toString()

                if (password != rePassword) {

                    textInputEditTextJoinUserPassword.error = "두 비밀번호가 일치하지 않아요"
                    textInputEditTextJoinUserRePassword.error = "두 비밀번호가 일치하지 않아요"
                } else {

                    textInputEditTextJoinUserPassword.error = null
                    textInputEditTextJoinUserRePassword.error = null

                    val userId = textInputEditTextJoinUserId.text
                    val userPassword = textInputEditTextJoinUserPassword.text
                    val userRePassword = textInputEditTextJoinUserRePassword.text

                    val userIdError = if (TextUtils.isEmpty(userId)) "이름을 입력해주세요." else null
                    val userPasswordError =
                        if (TextUtils.isEmpty(userPassword)) "비밀번호를 입력해주세요." else null
                    val userRePasswordError =
                        if (TextUtils.isEmpty(userRePassword)) "비밀번호를 입력해주세요." else null

                    textInputEditTextJoinUserId.error = userIdError
                    textInputEditTextJoinUserPassword.error = userPasswordError
                    textInputEditTextJoinUserRePassword.error = userRePasswordError

                    if (userId.toString().isEmpty()) {
                        val builder = MaterialAlertDialogBuilder(mainActivity)
                        builder.setTitle("로그인 오류")
                        builder.setMessage("아이디를 입력해주세요")
                        builder.setPositiveButton("확인") { dialogInterface: DialogInterface, i: Int ->
                            mainActivity.showSoftInput(textInputEditTextJoinUserId)
                        }
                        builder.show()
                    }

                    if (userPassword.toString().isEmpty()) {
                        val builder = MaterialAlertDialogBuilder(mainActivity)
                        builder.setTitle("비밀번호 오류")
                        builder.setMessage("비빈번호를 입력해주세요")
                        builder.setPositiveButton("확인") { dialogInterface: DialogInterface, i: Int ->
                            mainActivity.showSoftInput(textInputEditTextJoinUserPassword)
                        }
                        builder.show()
                    }

                    if (userRePassword.toString().isEmpty()) {
                        val builder = MaterialAlertDialogBuilder(mainActivity)
                        builder.setTitle("비밀번호 오류")
                        builder.setMessage("비빈번호를 입력해주세요")
                        builder.setPositiveButton("확인") { dialogInterface: DialogInterface, i: Int ->
                            mainActivity.showSoftInput(textInputEditTextJoinUserRePassword)
                        }
                        builder.show()
                    }

                    if (userPassword.toString() != userRePassword.toString()) {
                        val builder = MaterialAlertDialogBuilder(mainActivity)
                        builder.setTitle("비빌번호 오류")
                        builder.setMessage("비밀번호가 일치하지 않습니다.")
                        builder.setPositiveButton("확인") { dialogInterface: DialogInterface, i: Int ->
                            textInputEditTextJoinUserPassword.setText("")
                            textInputEditTextJoinUserRePassword.setText("")
                            mainActivity.showSoftInput(textInputEditTextJoinUserPassword)
                        }
                        builder.show()
                    }


                    if (userIdError == null && userPasswordError == null) {
                        val newBundle = Bundle()
                        newBundle.putString("joinUserId", userId.toString())
                        newBundle.putString("joinUserPw", userPassword.toString())

                        mainActivity.replaceFragment(
                            MainActivity.ADD_USER_INFO_FRAGMENT,
                            true,
                            newBundle
                        )
                    }
                }
            }

            textInputEditTextJoinUserRePassword.setOnKeyListener { _, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                    val password = textInputEditTextJoinUserPassword.text.toString()
                    val rePassword = textInputEditTextJoinUserRePassword.text.toString()

                    if (password != rePassword) {

                        textInputEditTextJoinUserPassword.error = "두 비밀번호가 일치하지 않아요"
                        textInputEditTextJoinUserRePassword.error = "두 비밀번호가 일치하지 않아요"
                    } else {

                        textInputEditTextJoinUserPassword.error = null
                        textInputEditTextJoinUserRePassword.error = null

                        val userId = textInputEditTextJoinUserId.text
                        val userPassword = textInputEditTextJoinUserPassword.text
                        val userRePassword = textInputEditTextJoinUserRePassword.text

                        val userIdError = if (TextUtils.isEmpty(userId)) "이름을 입력해주세요." else null
                        val userPasswordError =
                            if (TextUtils.isEmpty(userPassword)) "비밀번호를 입력해주세요." else null
                        val userRePasswordError =
                            if (TextUtils.isEmpty(userRePassword)) "비밀번호를 입력해주세요." else null

                        textInputEditTextJoinUserId.error = userIdError
                        textInputEditTextJoinUserPassword.error = userPasswordError
                        textInputEditTextJoinUserRePassword.error = userRePasswordError

                        if (userIdError == null && userPasswordError == null) {


                            mainActivity.replaceFragment(
                                MainActivity.ADD_USER_INFO_FRAGMENT,
                                true,
                                null
                            )
                        }
                    }
                    true
                } else {
                    false
                }
            }

        }
        return fragmentJoinBinding.root
    }
}