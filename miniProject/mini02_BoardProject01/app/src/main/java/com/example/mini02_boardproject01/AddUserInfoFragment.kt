package com.example.mini02_boardproject01

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.mini02_boardproject01.databinding.FragmentAddUserInfoBinding


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
                title = "유저정보"
                setTitleTextColor(Color.WHITE)
                setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
                setNavigationOnClickListener {
                    mainActivity.removeFragment(MainActivity.JOIN_FRAGMENT)
                }

            }

            completeButton.setOnClickListener {
                mainActivity.replaceFragment(MainActivity.LOGIN_FRAGMENT, true, null)
            }
            return fragmentAddUserInfoBinding.root
        }
    }
}