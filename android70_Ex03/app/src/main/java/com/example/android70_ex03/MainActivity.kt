package com.example.android70_ex03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.android70_ex03.databinding.ActivityMainBinding
import com.example.android70_ex03.fragment.CategoryListFragment
import com.example.android70_ex03.fragment.LoginFragment
import com.example.android70_ex03.fragment.MemoAddFragment
import com.example.android70_ex03.fragment.MemoListFragment
import com.example.android70_ex03.fragment.MemoModifyFragment
import com.example.android70_ex03.fragment.MemoViewContentFragment
import com.example.android70_ex03.fragment.PasswordSettingFragment

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding

    // 사용자가 누른 항목 번호
    var categoryPosition = 0
    var memoPosition = 0

    // Activit가 관리할 프래그먼트들의 이름
    companion object {
        // Activity가 관리할 프래그먼트들의 이름
        val PASS_SETTING_FRAGMENT = "PasswordSettingFragment";
        val LOGIN_FRAGMENT = "LoginFragment"
        val CATEGORY_LIST_FRAGMENT = "CategoryListFragment"
        var MEMO_LIST_FRAGMENT = "MemoListFragment"
        val MEMO_ADD_FRAGMENT = "MemoAddFragment"
        val MEMO_VIEW_CONTENT_FRAGMENT = "MemoViewContentFragment"
        val MEMO_MODIFY_FRAGMENT = "MemoModifyFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        replaceFragment(PASS_SETTING_FRAGMENT, false, false)
    }

    // 지정한 Fragment를 보여주는 메서드
    fun replaceFragment(name: String, addToBackStack: Boolean, animate: Boolean) {
        // Fragment 교체 상태로 설정한다.
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        // 새로운 Fragment를 담을 변수
        var newFragment = when (name) {
            PASS_SETTING_FRAGMENT -> {
                PasswordSettingFragment()
            }

            LOGIN_FRAGMENT -> {
                LoginFragment()
            }

            CATEGORY_LIST_FRAGMENT -> {
                CategoryListFragment()
            }

            MEMO_LIST_FRAGMENT -> {
                MemoListFragment()
            }

            MEMO_ADD_FRAGMENT -> {
                MemoAddFragment()
            }

            MEMO_VIEW_CONTENT_FRAGMENT -> {
                MemoViewContentFragment()
            }

            MEMO_MODIFY_FRAGMENT -> {
                MemoModifyFragment()
            }

            else -> {
                Fragment()
            }
        }

        if (newFragment != null) {
            // Fragment를 교체한다.
            fragmentTransaction.replace(R.id.mainContainer, newFragment)

            if (animate == true) {
                // 애니메이션을 설정한다.
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            }

            if (addToBackStack == true) {
                // Fragment를 Backstack에 넣어 이전으로 돌아가는 기능이 동작할 수 있도록 한다.
                fragmentTransaction.addToBackStack(name)
            }

            // 교체 명령이 동작하도록 한다.
            fragmentTransaction.commit()
        }
    }

    // Fragment를 BackStack에서 제거한다.
    fun removeFragment(name: String) {
        supportFragmentManager.popBackStack(name, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

}


