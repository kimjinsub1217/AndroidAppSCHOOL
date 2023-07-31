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
import com.example.mini02_boardproject01.databinding.FragmentPostWriteBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlin.concurrent.thread

class PostWriteFragment : Fragment() {

    lateinit var fragmentPostWriteBinding: FragmentPostWriteBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentPostWriteBinding = FragmentPostWriteBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        fragmentPostWriteBinding.run{
            toolbarPostWrite.run {
                title = "글작성"
                inflateMenu(R.menu.post_write_menu)
                setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
                setNavigationOnClickListener {
                    mainActivity.removeFragment(MainActivity.POST_WRITE_FRAGMENT)
                }

                setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.done -> {

                            val subject = textInputEditTextPostWriteTitle.text.toString()
                            val text= textInputEditTextPostWriteDetail.text.toString()

                            if(subject.isEmpty()){
                                val builder = MaterialAlertDialogBuilder(mainActivity)
                                builder.setTitle("제목 입력 오류")
                                builder.setMessage("제목을 입력해주세요")
                                builder.setPositiveButton("확인"){ dialogInterface: DialogInterface, i: Int ->
                                    mainActivity.showSoftInput(textInputEditTextPostWriteTitle)
                                }
                                builder.show()
                                return@setOnMenuItemClickListener true
                            }

                            if(text.isEmpty()){
                                val builder = MaterialAlertDialogBuilder(mainActivity)
                                builder.setTitle("내용 입력 오류")
                                builder.setMessage("내용를 입력해주세요")
                                builder.setPositiveButton("확인"){ dialogInterface: DialogInterface, i: Int ->
                                    mainActivity.showSoftInput(textInputEditTextPostWriteDetail)
                                }
                                builder.show()
                                return@setOnMenuItemClickListener true
                            }

                            val userName = textInputEditTextPostWriteTitle.text
                            val userDetail = textInputEditTextPostWriteDetail.text

                            val userIdError = if (TextUtils.isEmpty(userName)) "이름을 입력해주세요." else null
                            val userPasswordError = if (TextUtils.isEmpty(userDetail)) "내용을 입력해주세요." else null

                            textInputLayoutPostWriteTitle.error = userIdError
                            textInputLayoutPostWriteDetail.error = userPasswordError

                            if (userIdError == null && userPasswordError == null) {
                                mainActivity.removeFragment(MainActivity.POST_WRITE_FRAGMENT)
                            }
                        }

                    }
                    false
                }

            }

            textInputEditTextPostWriteTitle.requestFocus()

            thread {
                // 500ms 쉬게 한다.
                // onCreate 메서드의 수행이 끝날 때 까지 대기한다.
                SystemClock.sleep(500)
                val imm =
                    requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(textInputEditTextPostWriteTitle, 0)
            }



        }

        return fragmentPostWriteBinding.root
    }
}