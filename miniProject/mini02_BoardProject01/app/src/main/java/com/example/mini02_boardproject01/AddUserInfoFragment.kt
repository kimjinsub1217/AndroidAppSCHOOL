package com.example.mini02_boardproject01

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.os.SystemClock
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.view.children
import com.example.mini02_boardproject01.databinding.FragmentAddUserInfoBinding
import com.example.mini02_boardproject01.repository.UserRepository
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.FirebaseDatabase
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
                val userNickName = textInputEditTextNickName.text
                val joinUserAge = textInputEditTextAge.text

                val userIdError = if (TextUtils.isEmpty(userNickName)) "이름을 입력해주세요." else null
                val userPasswordError =
                    if (TextUtils.isEmpty(joinUserAge)) "나이를 입력해주세요." else null

                textInputLayoutNickName.error = userIdError
                textInputLayoutLoginUserAge.error = userPasswordError

                if (userNickName.toString().isEmpty()) {
                    val builder = MaterialAlertDialogBuilder(mainActivity)
                    builder.setTitle("닉네임 입력 오류")
                    builder.setMessage("닉네임을 입력해주세요")
                    builder.setPositiveButton("확인") { dialogInterface: DialogInterface, i: Int ->
                        mainActivity.showSoftInput(textInputEditTextNickName)
                    }
                    builder.show()
                    return@setOnClickListener
                }

                if (userNickName.toString().isEmpty()) {
                    val builder = MaterialAlertDialogBuilder(mainActivity)
                    builder.setTitle("닉네임 입력 오류")
                    builder.setMessage("닉네임을 입력해주세요")
                    builder.setPositiveButton("확인") { dialogInterface: DialogInterface, i: Int ->
                        mainActivity.showSoftInput(textInputEditTextNickName)
                    }
                    builder.show()
                    return@setOnClickListener
                }

                if (joinUserAge.toString().isEmpty()) {
                    val builder = MaterialAlertDialogBuilder(mainActivity)
                    builder.setTitle("나이 입력 오류")
                    builder.setMessage("나이를 입력해주세요")
                    builder.setPositiveButton("확인") { dialogInterface: DialogInterface, i: Int ->
                        mainActivity.showSoftInput(textInputEditTextNickName)
                    }
                    builder.show()
                    return@setOnClickListener
                }

                // 사용자 인덱스 값을 가져온다.
                val database = FirebaseDatabase.getInstance()
                val userIdxRef = database.getReference("UserIdx")

                userIdxRef.get().addOnCompleteListener {

                    UserRepository.getUserIdx {
                        // 현재의 사용자 순서값을 가져온다.
                        var userIdx = it.result.value as Long

                        // 저장할 데이터들을 담는다.
                        val joinUserId = arguments?.getString("joinUserId")!!
                        val joinUserPw = arguments?.getString("joinUserPw")!!

                        // 사용자 인덱스 번호 1 증가
                        userIdx++

                        val userClass = UserClass(
                            userIdx,
                            joinUserId,
                            joinUserPw,
                            userNickName.toString(),
                            joinUserAge.toString().toLong(),
                            materialCheckBoxAddUserInfoHobby1.isChecked,
                            materialCheckBoxAddUserInfoHobby2.isChecked,
                            materialCheckBoxAddUserInfoHobby3.isChecked,
                            materialCheckBoxAddUserInfoHobby4.isChecked,
                            materialCheckBoxAddUserInfoHobby5.isChecked,
                            materialCheckBoxAddUserInfoHobby6.isChecked
                        )


                        // 저장한다.
                        UserRepository.addUserInfo(userClass){
                            UserRepository.setUserIdx(userIdx) {
                                Snackbar.make(fragmentAddUserInfoBinding.root, "가입이 완료되었습니다", Snackbar.LENGTH_SHORT).show()

                                mainActivity.removeFragment(MainActivity.ADD_USER_INFO_FRAGMENT)
                                mainActivity.removeFragment(MainActivity.JOIN_FRAGMENT)
                            }
                        }
                    }
                }
            }
//            materialCheckBoxAddUSerInfoAll.checkedState=MaterialCheckBox.STATE_INDETERMINATE
            // 취미 전체 체크박스
            materialCheckBoxAddUserInfoAll.run {
                setOnCheckedChangeListener { compoundButton, b ->
                    // 각 체크박스를 가지고 있는 레이아웃을 통해 그 안에 있는 View들의 체크상태를 변경한다.
                    for (v1 in materialCheckBoxGroupUserInfo1.children) {
                        // 형변환
                        v1 as MaterialCheckBox
                        // 취미 전체가 체크 되어 있다면
                        if (b) {
                            v1.checkedState = MaterialCheckBox.STATE_CHECKED
                        } else {
                            v1.checkedState = MaterialCheckBox.STATE_UNCHECKED
                        }
                    }

                    for (v1 in materialCheckBoxGroupUserInfo2.children) {
                        // 형변환
                        v1 as MaterialCheckBox
                        // 취미 전체가 체크 되어 있다면
                        if (b) {
                            v1.checkedState = MaterialCheckBox.STATE_CHECKED
                        } else {
                            v1.checkedState = MaterialCheckBox.STATE_UNCHECKED
                        }
                    }
                }
            }


            // 다른 체크박스 들...
            // 체크 박스의 개수를 구한다.
//            val checkboxCount = materialCheckBoxGroupUserInfo1.childCount + materialCheckBoxGroupUserInfo2.childCount
            // 반복문
            for (v1 in materialCheckBoxGroupUserInfo1.children) {
                v1 as MaterialCheckBox
                v1.setOnCheckedChangeListener { compoundButton, b ->
                    setParentCheckBoxState()
                }
            }
            for (v1 in materialCheckBoxGroupUserInfo2.children) {
                v1 as MaterialCheckBox
                v1.setOnCheckedChangeListener { compoundButton, b ->
                    setParentCheckBoxState()
                }
            }
        }

        return fragmentAddUserInfoBinding.root
    }

    // 하위의 체크박스들의 상태를 보고 상위 체크 박스 상태를 셋팅한다
    fun setParentCheckBoxState() {
        fragmentAddUserInfoBinding.run {
            // 체크박스의 개수를 구한다.
            val checkBoxCount =
                materialCheckBoxGroupUserInfo1.childCount + materialCheckBoxGroupUserInfo2.childCount

            // 체크되어 있는 체크박스의 개수
            var checkedCount = 0

            // 체크 되어 있는 체크박스의 개수를 구한다.
            for (v1 in materialCheckBoxGroupUserInfo1.children) {
                v1 as MaterialCheckBox
                if (v1.checkedState == MaterialCheckBox.STATE_CHECKED) {
                    checkedCount++
                }
            }
            for (v1 in materialCheckBoxGroupUserInfo2.children) {
                v1 as MaterialCheckBox
                if (v1.checkedState == MaterialCheckBox.STATE_CHECKED) {
                    checkedCount++
                }
            }
            // 만약 체크되어 있는 것이 없다면
            if (checkedCount == 0) {
                materialCheckBoxAddUserInfoAll.checkedState = MaterialCheckBox.STATE_UNCHECKED
            }
            // 모두 체크되어 있다면
            else if (checkedCount == checkBoxCount) {
                materialCheckBoxAddUserInfoAll.checkedState = MaterialCheckBox.STATE_CHECKED
            }
            // 일부만 체크되어 있다면
            else {
                materialCheckBoxAddUserInfoAll.checkedState = MaterialCheckBox.STATE_INDETERMINATE
            }
        }
    }
}