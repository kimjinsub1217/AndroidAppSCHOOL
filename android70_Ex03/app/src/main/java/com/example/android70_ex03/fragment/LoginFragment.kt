package com.example.android70_ex03.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.android70_ex03.MainActivity
import com.example.android70_ex03.R
import com.example.android70_ex03.databinding.FragmentLoginBinding
import com.example.android70_ex03.db.DAOPassword


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
            settingCompleteButton.setOnClickListener {
                val loginPassword = loginPasswordEditText.text.toString()
                val savaPassword = DAOPassword.selectAllData(requireContext())

                if (loginPassword == savaPassword[0].passwordData) {
                    mainActivity.replaceFragment(
                        MainActivity.CATEGORY_LIST_FRAGMENT,
                        addToBackStack = true,
                        animate = true
                    )
                } else {
                    Toast.makeText(requireContext(), "비밀번호가 틀렸어요!", Toast.LENGTH_SHORT).show()
                }

            }
        }

        return fragmentLoginBinding.root
    }

}