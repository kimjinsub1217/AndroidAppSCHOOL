package com.example.android70_ex04

import android.animation.Animator
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.android70_ex04.databinding.FragmentSignUpBinding
import com.romainpiel.shimmer.Shimmer
import com.romainpiel.shimmer.ShimmerTextView


class SignUpFragment : Fragment() {

    lateinit var fragmentSignUpBinding: FragmentSignUpBinding
    lateinit var mainActivity: MainActivity

    private var tv: ShimmerTextView? = null
    private var shimmer: Shimmer? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentSignUpBinding = FragmentSignUpBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        fragmentSignUpBinding.run {
            tv = shimmerTitleTextView

            nameTextInputLayout.run {
                addOnEditTextAttachedListener {
                    if (it.counterMaxLength > 10) {
                        // error = "10 글자 이하로 입력해주세요"
                        nameTextInputText.error = "10글자 이하로 입력해주세요"
                    } else {
                        nameTextInputText.error = null
                    }
                }
            }

            completeButton.setOnClickListener {
                mainActivity.removeFragment(MainActivity.SIGN_UP_FRAGMENT)
            }

        }
        toggleAnimation()
        return fragmentSignUpBinding.root
    }

    private fun toggleAnimation() {
        if (shimmer?.isAnimating == true) {
            shimmer?.cancel()
        } else {
            shimmer = Shimmer()
            shimmer!!.start(tv)
            shimmer!!.repeatCount = 10
        }
    }

}

