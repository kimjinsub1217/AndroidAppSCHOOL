package com.example.mini02_boardproject01

import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mini02_boardproject01.databinding.FragmentLoginBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.FirebaseDatabase

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

                val userIdError = if (TextUtils.isEmpty(userId)) "아이디를 입력해주세요." else null
                val userPasswordError = if (TextUtils.isEmpty(userPassword)) "비밀번호를 입력해주세요." else null

                textInputLayoutLoginUserId.error = userIdError
                textInputLayoutLoginUserPassword.error = userPasswordError


                if(userId.toString().isEmpty()) {
                    val builder = MaterialAlertDialogBuilder(mainActivity)
                    builder.setTitle("로그인 오류")
                    builder.setMessage("아이디를 입력해주세요")
                    builder.setPositiveButton("확인") { dialogInterface: DialogInterface, i: Int ->
                        mainActivity.showSoftInput(textInputEditTextLoginUserId)
                    }
                    builder.show()
                }

                if(userPassword.toString().isEmpty()) {
                    val builder = MaterialAlertDialogBuilder(mainActivity)
                    builder.setTitle("로그인 오류")
                    builder.setMessage("아이디를 입력해주세요")
                    builder.setPositiveButton("확인") { dialogInterface: DialogInterface, i: Int ->
                        mainActivity.showSoftInput(textInputEditTextLoginUserPassword)
                    }
                    builder.show()
                }

                val database = FirebaseDatabase.getInstance()
                val userDataRef = database.getReference("UserData")

                // userId가 사용자가 입력한 아이디와 같은 데이터를 가져온다.
                userDataRef.orderByChild("userId").equalTo(userId.toString()).get().addOnCompleteListener {
                    // 가져온 데이터가 없다면
                    if(!it.result.exists()){
                        val builder = MaterialAlertDialogBuilder(mainActivity)
                        builder.setTitle("로그인 오류")
                        builder.setMessage("존재하지 않는 아이디 입니다")
                        builder.setPositiveButton("확인"){ dialogInterface: DialogInterface, i: Int ->
                            textInputEditTextLoginUserId.setText("")
                            textInputEditTextLoginUserPassword.setText("")
                            mainActivity.showSoftInput(textInputEditTextLoginUserId)
                        }
                        builder.show()
                    }
                    // 가져온 데이터가 있다면
                    else {

                        for(c1 in it.result.children){
                            // 가져온 데이터에서 비밀번호를 가져온다.
                            val userPw = c1.child("userPw").value as String

                            // 입력한 비밀번호와 현재 계정의 비밀번호가 다르다면
                            if(userPassword.toString() != userPw){
                                val builder = MaterialAlertDialogBuilder(mainActivity)
                                builder.setTitle("로그인 오류")
                                builder.setMessage("잘못된 비밀번호 입니다")
                                builder.setPositiveButton("확인"){ dialogInterface: DialogInterface, i: Int ->
                                    textInputEditTextLoginUserPassword.setText("")
                                    mainActivity.showSoftInput(textInputEditTextLoginUserPassword)
                                }
                                builder.show()
                            }
                            // 입력한 비밀번호와 현재 계정의 비밀번호가 같다면
                            else {

                                // 로그인한 사용자 정보를 가져온다.
                                val userIdx =c1.child("userIdx").value as Long
                                val userId =c1.child("userId").value as String
                                val userPw =c1.child("userPw").value as String
                                val userNickname =c1.child("userNickname").value as String
                                val userAge =c1.child("userAge").value as Long
                                val hobby1 =c1.child("hobby1").value as Boolean
                                val hobby2 =c1.child("hobby2").value as Boolean
                                val hobby3 =c1.child("hobby3").value as Boolean
                                val hobby4 =c1.child("hobby4").value as Boolean
                                val hobby5 =c1.child("hobby5").value as Boolean
                                val hobby6 =c1.child("hobby6").value as Boolean

                                mainActivity.loginUserClass = UserClass(userIdx, userId, userPw, userNickname, userAge, hobby1, hobby2, hobby3, hobby4, hobby5, hobby6)
                                Snackbar.make(fragmentLoginBinding.root, "로그인 되었습니다", Snackbar.LENGTH_SHORT).show()

                                if (userIdError == null && userPasswordError == null) {
                                    mainActivity.replaceFragment(MainActivity.HOME_FRAGMENT, false, null)
                                }
                            }
                        }
                    }
                }

            }

            joinButton.setOnClickListener {
                mainActivity.replaceFragment(MainActivity.JOIN_FRAGMENT, true, null)
            }

        }

        return fragmentLoginBinding.root
    }

}