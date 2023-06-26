package com.example.android55_fragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // mainFragment를 보여준다.
        replaceFragment(FragmentName.FRAGMENT_MAIN, false, false)
    }

    // 지정한 FRAGMENT를 보여주는 메서드
    fun replaceFragment(name: FragmentName, addToBackStack: Boolean, animate: Boolean) {
        // FRagment 교체 상태로 설정한다.

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        // 새로운 Fragment를 담을 변수
        var newFragment: Fragment? = null
        // 이름으로 분기한다.
        when (name) {
            FragmentName.FRAGMENT_MAIN -> {
                // Fragment 객체를 생성한다.
                newFragment = MainFragment()
            }

            FragmentName.FRAGMENT_INPUT -> {
                newFragment = InputFragment()
            }

            FragmentName.FRAGMENT_RESULT -> {

            }
        }
        if (newFragment != null) {
            fragmentTransaction.replace(R.id.mainContainer, newFragment!!)

            if (animate == true) {
                // 애니메이션을 설정한다.
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            }


            if (addToBackStack == true) {
                // Fragment를 Backstack에 넣어 이전으로 돌아가는 기능이 동작할 수 있도록 한다.
                fragmentTransaction.addToBackStack(name.str1)
            }
            // 교체 명령이 동작하도록 한다.
            fragmentTransaction.commit()
        }
    }

    //Fragment를 BackStack에서 제거한다.
    fun removeFragment(name: FragmentName) {
        supportFragmentManager.popBackStack(name.str1, 0)
    }
}

enum class FragmentName(val str1: String) {
    FRAGMENT_MAIN("MainFragment"),
    FRAGMENT_INPUT("InputFragment"),
    FRAGMENT_RESULT("ResultFragment")
}