package com.example.mini02_boardproject01

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnticipateInterpolator
import android.widget.Toast
import android.widget.Toolbar
import androidx.core.splashscreen.SplashScreen
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentManager
import com.example.mini02_boardproject01.databinding.FragmentHomeBinding
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.transition.MaterialSharedAxis


class HomeFragment : Fragment() {

    lateinit var fragmentHomeBinding: FragmentHomeBinding
    lateinit var mainActivity: MainActivity

    var newFragment: Fragment? = null
    var oldFragment: Fragment? = null
    lateinit var homeToolbar: MaterialToolbar

    companion object {
        val POST_LIST_FRAGMENT = "PostListFragment"
        val MODIFY_USER_FRAGMENT = "ModifyUserFragment"
        val LOGINFRAGMENT = "LoginFragment"
        val MODIFY_USER_BASIC_FRAGMENT = "ModifyUserBasicFramgent"
        val MODIFY_USER_ADDITIONAL_FRAGMENT = "ModifyUserAdditionalFragment"
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentHomeBinding = FragmentHomeBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        fragmentHomeBinding.run {
            homeToolbar = toolbarHome
            toolbarHome.run {
                title = "게시판"
                setNavigationIcon(R.drawable.menu)
                setNavigationOnClickListener {
                    drawerLayout.open()
                }
            }

            navigationView.setNavigationItemSelectedListener { menuItem ->
                menuItem.isChecked = true
                // 사용자가 누르 메뉴의 id로 분기한다.
                when (menuItem.itemId) {
                    // 전체 게시판
                    R.id.allBoard -> {
                        replaceFragment(POST_LIST_FRAGMENT, false, false, null)
                    }
                    // 자유 게시판
                    R.id.freeBoard -> {
                        replaceFragment(POST_LIST_FRAGMENT, false, false, null)
                    }
                    // 유머 게시판
                    R.id.humorBoard -> {
                        replaceFragment(POST_LIST_FRAGMENT, false, false, null)
                    }
                    // 질문 게시판
                    R.id.questionBoard -> {
                        replaceFragment(POST_LIST_FRAGMENT, false, false, null)
                    }
                    // 스포츠 게시판
                    R.id.sportsBoard -> {
                        replaceFragment(POST_LIST_FRAGMENT, false, false, null)
                    }
                    // 사용자 정보 수정
                    R.id.item_board_main_user_info -> {
                        replaceFragment(MODIFY_USER_FRAGMENT, false, false, null)
                    }
                    // 로그아웃
                    R.id.logout -> {
                        mainActivity.replaceFragment(MainActivity.LOGIN_FRAGMENT, false, null)
                    }
                    // 회원탈퇴
                    R.id.Withdrawal -> {
                        mainActivity.replaceFragment(MainActivity.LOGIN_FRAGMENT, false, null)
                    }
                }

                drawerLayout.close()
                true
            }
        }

        replaceFragment(POST_LIST_FRAGMENT, true, false, null)
        return fragmentHomeBinding.root
    }

    fun splashScreenCustomizing(splashScreen: SplashScreen) {
        // SplashScreen이 사라질 때 동작하는 리스너를 설정한다.
        splashScreen.setOnExitAnimationListener {

            // 가로 비율 애니메이션
            val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1f, 2f, 1f, 0f)

            // 세로 비율 애니메이션
            val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1f, 2f, 1f, 0f)

            // 투명도
            val alpha = PropertyValuesHolder.ofFloat(View.ALPHA, 1f, 1f, 0.5f, 0f)

            // 애니메이션 관리 객체를 생성한다.
            // 첫 번째 : 애니메이션을 적용할 뷰
            // 나머지는 적용한 애니메이션 종류
            val objectAnimator =
                ObjectAnimator.ofPropertyValuesHolder(it.iconView, scaleX, scaleY, alpha)

            // 애니메이션 적용을 위한 에이징
            objectAnimator.interpolator = AnticipateInterpolator()

            // 애니메이션 동작 시간
            objectAnimator.duration = 5000

            // 애니메이션이 끝났을 때 동작할 리스너
            objectAnimator.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)

                    // SplashScreen을 제거한다.
                    it.remove()
                }
            })
            objectAnimator.start()
        }
    }

    // 지정한 Fragment를 보여주는 메서드
    fun replaceFragment(name: String, addToBackStack: Boolean, animate: Boolean, bundle: Bundle?) {
        // Fragment 교체 상태로 설정한다.
        val fragmentTransaction = childFragmentManager.beginTransaction()

        // newFragment 에 Fragment가 들어있으면 oldFragment에 넣어준다.
        if (newFragment != null) {
            oldFragment = newFragment
        }

        // 새로운 Fragment를 담을 변수
        newFragment = when (name) {
            POST_LIST_FRAGMENT -> PostListFragment()
            MODIFY_USER_FRAGMENT -> ModifyUserFragment()
            LOGINFRAGMENT -> LoginFragment()
            MODIFY_USER_BASIC_FRAGMENT -> ModifyUserBasicFramgent()
            MODIFY_USER_ADDITIONAL_FRAGMENT -> ModifyUserAdditionalFragment()
            else -> Fragment()
        }

        newFragment?.arguments = bundle

        if (newFragment != null) {


            if (animate == true) {
                // 애니메이션 설정
                if (oldFragment != null) {
                    oldFragment?.exitTransition = MaterialSharedAxis(MaterialSharedAxis.X, true)
                    oldFragment?.reenterTransition = MaterialSharedAxis(MaterialSharedAxis.X, false)
                    oldFragment?.enterTransition = null
                    oldFragment?.returnTransition = null
                }

                newFragment?.exitTransition = null
                newFragment?.reenterTransition = null
                newFragment?.enterTransition = MaterialSharedAxis(MaterialSharedAxis.X, true)
                newFragment?.returnTransition = MaterialSharedAxis(MaterialSharedAxis.X, false)
            } else {
                if (oldFragment != null) {
                    oldFragment?.exitTransition = null
                    oldFragment?.reenterTransition = null
                    oldFragment?.enterTransition = null
                    oldFragment?.returnTransition = null
                }

                newFragment?.exitTransition = null
                newFragment?.reenterTransition = null
                newFragment?.enterTransition = null
                newFragment?.returnTransition = null
            }

            // Fragment를 교채한다.
            fragmentTransaction.replace(R.id.homeContainer, newFragment!!)

            if (addToBackStack) {
                // Fragment를 Backstack에 넣어 이전으로 돌아가는 기능이 동작할 수 있도록 한다.
                fragmentTransaction.addToBackStack(name)
            }

            // 교체 명령이 동작하도록 한다.
            fragmentTransaction.commit()
        }
    }

    // Fragment를 BackStack에서 제거한다.
    fun removeFragment(name: String) {
        childFragmentManager.popBackStack(name, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}