package com.example.mini02_boardproject01

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mini02_boardproject01.databinding.FragmentPostModifyBinding


class PostModifyFragment : Fragment() {
    lateinit var fragmentPostModifyBinding: FragmentPostModifyBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentPostModifyBinding = FragmentPostModifyBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        fragmentPostModifyBinding.run {
            toolbarPostModify.run {
                title = "글수정"
                inflateMenu(R.menu.post_modify_menu)
                setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
                setNavigationOnClickListener {
                    mainActivity.removeFragment(MainActivity.POST_MODIFY_FRAGMENT)
                }

                setOnMenuItemClickListener {
                    when(it.itemId){
                        R.id.photoCameraModify -> {

                        }
                        R.id.photoLibraryModify -> {

                        }
                        R.id.doneModify -> {
                            val userId = textInputEditTextPostModifyTitle.text
                            val userPassword = textInputEditTextPostModifyDetail.text

                            val userIdError = if (TextUtils.isEmpty(userId)) "이름을 입력해주세요." else null
                            val userPasswordError = if (TextUtils.isEmpty(userPassword)) "비밀번호를 입력해주세요." else null

                            textInputLayoutPostModifyTitle.error = userIdError
                            textInputLayoutPostModifyDetail.error = userPasswordError

                            if (userIdError == null && userPasswordError == null) {
                                mainActivity.removeFragment(MainActivity.POST_MODIFY_FRAGMENT)
                            }
                        }
                    }
                    true
                }
            }
        }

        return fragmentPostModifyBinding.root
    }
}