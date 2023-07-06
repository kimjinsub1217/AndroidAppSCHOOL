package com.example.android70_ex02

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android70_ex01.student.Companion.memoList
import com.example.android70_ex02.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    lateinit var fragmentMainBinding: FragmentMainBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentMainBinding = FragmentMainBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        fragmentMainBinding.run {
            toolbarMain.run {
                title = "메모앱"
                inflateMenu(R.menu.main_menu)

                // 타이틀 글자 색상
                setTitleTextColor(Color.WHITE)

                setOnMenuItemClickListener {
                    mainActivity.replaceFragment(MainActivity.ADD_FRAGMENT, true, true)
                    false
                }
            }

            recyclerViewMain.run {
                adapter = MainRecyclerViewAdapter(mainActivity, memoList)
                layoutManager = LinearLayoutManager(mainActivity).apply {
                    reverseLayout = true
                    stackFromEnd = true
                }

                addItemDecoration(
                    DividerItemDecoration(
                        mainActivity,
                        DividerItemDecoration.VERTICAL
                    )
                )
            }

        }


        // Inflate the layout for this fragment
        return fragmentMainBinding.root
    }



    override fun onResume() {
        super.onResume()

        // 데이터를 불러온다.
        val dataList = DAO.selectAllData(requireContext())

        // 기존의 데이터를 유지한 채로 새로운 데이터를 추가한다.
        memoList.clear() // 기존의 데이터를 비우고 새로운 데이터를 추가하기 전에 memoList를 비웁니다.
        memoList.addAll(dataList)

        // 삭제된 이후의 모든 아이템의 인덱스 업데이트
        for (i in 0 until memoList.size) {
            memoList[i].idx = i + 1
        }

        // 리사이클러뷰 어댑터를 다시 생성하여 할당합니다.
        val recyclerViewAdapter = MainRecyclerViewAdapter(mainActivity, memoList)
        fragmentMainBinding.recyclerViewMain.adapter = recyclerViewAdapter
    }
}