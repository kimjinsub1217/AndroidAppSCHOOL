package com.example.android70_ex04

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android70_ex04.databinding.FragmentMainBinding


class MainFragment : Fragment() {
    lateinit var fragmentMainBinding: FragmentMainBinding
    lateinit var mainActivity: MainActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentMainBinding = FragmentMainBinding.inflate(inflater)
        mainActivity=activity as MainActivity

        fragmentMainBinding.run {
            loginButton.setOnClickListener {

            }

            loginButton.setOnClickListener {
                mainActivity.replaceFragment(
                    MainActivity.LOGIN_FRAGMENT,
                    addToBackStack = true,
                    animate = true
                )
            }


            signUpButton.setOnClickListener {
                mainActivity.replaceFragment(
                    MainActivity.SIGN_UP_FRAGMENT,
                    addToBackStack = true,
                    animate = true
                )
            }
        }

        return fragmentMainBinding.root
    }

}