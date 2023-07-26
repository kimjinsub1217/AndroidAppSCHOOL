package com.example.mini02_boardproject01

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mini02_boardproject01.HomeFragment.Companion.MODIFY_USER_ADDITIONAL_FRAGMENT
import com.example.mini02_boardproject01.HomeFragment.Companion.MODIFY_USER_BASIC_FRAGMENT
import com.example.mini02_boardproject01.databinding.FragmentModifyUserBinding

class ModifyUserFragment : Fragment() {

    lateinit var fragmentModifyUserBinding:FragmentModifyUserBinding
    lateinit var homeFragment:HomeFragment

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentModifyUserBinding = FragmentModifyUserBinding.inflate(inflater)
        homeFragment =  requireParentFragment() as HomeFragment

        fragmentModifyUserBinding.run {

            existingUserInfoButton.setOnClickListener {
                homeFragment.replaceFragment(MODIFY_USER_BASIC_FRAGMENT,true,null)
            }

            addUserInfoButton.setOnClickListener {
                homeFragment.replaceFragment(MODIFY_USER_ADDITIONAL_FRAGMENT,true,null)
            }
        }

        return fragmentModifyUserBinding.root
    }

}