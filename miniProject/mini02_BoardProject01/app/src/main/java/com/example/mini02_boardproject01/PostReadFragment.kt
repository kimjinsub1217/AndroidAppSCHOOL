package com.example.mini02_boardproject01

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mini02_boardproject01.databinding.FragmentPostReadBinding


class PostReadFragment : Fragment() {

    lateinit var fragmentPostReadBinding: FragmentPostReadBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentPostReadBinding = FragmentPostReadBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        fragmentPostReadBinding.run{
            toolbarPostRead.run {
                title = "글읽기"
                inflateMenu(R.menu.post_read_menu)
                setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
                setNavigationOnClickListener {
                    mainActivity.removeFragment(MainActivity.POST_READ_FRAGMENT)
                }

                setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.contractEdit -> {
//                            if(textInputEditTextPostReadTitle.isEnabled == false) {
//                                textInputEditTextPostReadTitle.isEnabled = true
//                                textInputEditTextPostReadDetail.isEnabled = true
//                            } else {
//                                textInputEditTextPostReadTitle.isEnabled = false
//                                textInputEditTextPostReadDetail.isEnabled = false
//                            }

                            mainActivity.replaceFragment(MainActivity.POST_MODIFY_FRAGMENT, true, null)
                        }
                        R.id.delete -> {

                        }
                    }
                    false
                }

            }
        }

        return fragmentPostReadBinding.root
    }

}