package com.example.mini02_boardproject01

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.mini02_boardproject01.databinding.FragmentJoinBinding


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
                setTitleTextColor(Color.WHITE)
                setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
                setNavigationOnClickListener {
                    mainActivity.removeFragment(MainActivity.JOIN_FRAGMENT)
                }
            }

            nextButton.setOnClickListener {
                mainActivity.replaceFragment(MainActivity.ADD_USER_INFO_FRAGMENT, true, null)
            }

        }
        return fragmentJoinBinding.root
    }
}