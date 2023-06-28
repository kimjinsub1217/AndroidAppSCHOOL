package com.example.android60_ex01

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android60_ex01.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    lateinit var fragmentMainBinding: FragmentMainBinding
    lateinit var mainActivity: MainActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainActivity = activity as MainActivity

        //ViewBinding
        fragmentMainBinding = FragmentMainBinding.inflate(layoutInflater)


        fragmentMainBinding.run {
            toolbar.run {
                title = "학생 정보 관리"

                // 타이틀 글자 색상
                setTitleTextColor(Color.WHITE)

                // 옵션 메뉴를 구성한다.
                inflateMenu(R.menu.main_menu)

                // 상단 메뉴를 눌러주면 동작하는 리스너
                setOnMenuItemClickListener {
                    // 사용자가 누른 메뉴의 id로 분기한다.
                    // 사용자가 누른 메뉴의 id로 분기한다.
                    when (it?.itemId) {
                        R.id.item1 -> {
                            //Fragment를 교체한다.
                            mainActivity.replaceFragment(FragmentName.FRAGMENT_INPUT,
                                addToBackStack = true,
                                animate = true
                            )
                        }
                    }
                    false
                }
            }

            recyclerView.run{
                adapter=recyclerViewAdaper(mainActivity)
                layoutManager= LinearLayoutManager(activity)
                addItemDecoration(DividerItemDecoration(mainActivity, DividerItemDecoration.VERTICAL))
            }

            return fragmentMainBinding.root
        }
    }
}