package com.example.mini02_boardproject01

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mini02_boardproject01.HomeFragment.Companion.MODIFY_USER_ADDITIONAL_FRAGMENT
import com.example.mini02_boardproject01.HomeFragment.Companion.MODIFY_USER_BASIC_FRAGMENT
import com.example.mini02_boardproject01.databinding.FragmentModifyUserBinding

class ModifyUserFragment : Fragment() {

    lateinit var fragmentModifyUserBinding: FragmentModifyUserBinding
    lateinit var homeFragment: HomeFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentModifyUserBinding = FragmentModifyUserBinding.inflate(inflater)
        homeFragment = requireParentFragment() as HomeFragment

        fragmentModifyUserBinding.run {

//            existingUserInfoButton.setOnClickListener {
//                homeFragment.replaceFragment(MODIFY_USER_BASIC_FRAGMENT,true,false,null)
//            }
//
//            addUserInfoButton.setOnClickListener {
//                homeFragment.replaceFragment(MODIFY_USER_ADDITIONAL_FRAGMENT,true,false,null)
//            }

            ModifyCompleteButton.setOnClickListener {
                val password = textInputEditTextModifyUserPw.text.toString()
                val rePassword = textInputEditTextModifyUserPw2.text.toString()

                if (password != rePassword) {

                    textInputEditTextModifyUserPw.error = "두 비밀번호가 일치하지 않아요"
                    textInputEditTextModifyUserPw.error = "두 비밀번호가 일치하지 않아요"
                } else {

                    textInputEditTextModifyUserPw.error = null
                    textInputEditTextModifyUserPw.error = null


                    val userPassword = textInputEditTextModifyUserPw.text
                    val userRePassword = textInputEditTextModifyUserPw.text
                    val userNickname = textInputEditTextModifyUserNickName.text
                    val userAge = textInputEditTextModifyUserAge.text


                    val userPasswordError =
                        if (TextUtils.isEmpty(userPassword)) "비밀번호를 입력해주세요." else null
                    val userRePasswordError =
                        if (TextUtils.isEmpty(userRePassword)) "비밀번호를 입력해주세요." else null
                    val userNicknameError =
                        if (TextUtils.isEmpty(userNickname)) "이름을 입력해주세요." else null
                    val userAgeError = if (TextUtils.isEmpty(userAge)) "나이를 입력해주세요." else null

                    textInputLayoutModifyUserPw.error = userPasswordError
                    textInputLayoutModifyUserPw2.error = userRePasswordError
                    textInputLayoutModifyUserNickName.error = userNicknameError
                    textInputLayoutModifyUserLoginUserAge.error = userAgeError

                    if (userNicknameError == null && userPasswordError == null && userAgeError == null && userRePasswordError == null) {
                        homeFragment.replaceFragment(
                            HomeFragment.POST_LIST_FRAGMENT,
                            false, false, null
                        )
                    }
                }
            }
        }

        return fragmentModifyUserBinding.root
    }

}