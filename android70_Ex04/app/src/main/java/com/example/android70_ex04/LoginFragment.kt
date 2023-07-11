package com.example.android70_ex04

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android70_ex04.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {
    lateinit var fragmentLoginBinding: FragmentLoginBinding
    lateinit var mainActivity: MainActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentLoginBinding = FragmentLoginBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        fragmentLoginBinding.run {
            completeButton.setOnClickListener {
                mainActivity.replaceFragment(
                    MainActivity.HOME_SCREAN_FRAGMENT,
                    addToBackStack = true,
                    animate = true
                )
            }
        }

        return fragmentLoginBinding.root
    }

}