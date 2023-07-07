package com.example.android70_ex03.fragment

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android70_ex03.MainActivity
import com.example.android70_ex03.R
import com.example.android70_ex03.adapter.MemoAdapter
import com.example.android70_ex03.databinding.FragmentMemoListBinding
import com.example.android70_ex03.db.Category.Companion.categoryList
import com.example.android70_ex03.db.DAOCategory
import com.example.android70_ex03.db.DAOMemo
import com.example.android70_ex03.db.Memo.Companion.memoList


class MemoListFragment : Fragment() {
    lateinit var fragmentMemoListBinding: FragmentMemoListBinding
    lateinit var mainActivity: MainActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentMemoListBinding = FragmentMemoListBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        fragmentMemoListBinding.run {
            memoListToolbar.run {
                val obj = DAOCategory.selectData(requireContext(), mainActivity.rowPosition + 1)
                Log.i("memoListFragment 값", obj.toString())
                title = "${obj?.titleData}"
                inflateMenu(R.menu.memo_list_menu)

                // 타이틀 글자 색상
                setTitleTextColor(Color.WHITE)

                setNavigationIcon(R.drawable.baseline_arrow_back)
                navigationIcon?.setColorFilter(
                    ContextCompat.getColor(context, R.color.white),
                    PorterDuff.Mode.SRC_IN
                )
                setNavigationOnClickListener {
                    mainActivity.onBackPressed()
                }

                setOnMenuItemClickListener { menuItem ->
                    when (menuItem.itemId) {
                        R.id.memo_list_add_button -> {
                            mainActivity.replaceFragment(
                                MainActivity.MEMO_ADD_FRAGMENT,
                                addToBackStack = true,
                                animate = true
                            )
                        }
                    }
                    false
                }
            }
            memoListRecyclerView.run {
                adapter = MemoAdapter(mainActivity)
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
        return fragmentMemoListBinding.root
    }

    override fun onResume() {
        super.onResume()
        // 데이터를 불러온다.
        val dataList = DAOMemo.selectAllData(requireContext())
        // 기존의 데이터를 유지한 채로 새로운 데이터를 추가한다.
        memoList.clear()
        memoList.addAll(dataList)
        // 삭제된 이후의 모든 아이템의 인덱스 업데이트
        for (i in 0 until memoList.size) {
            memoList[i].idx = i + 1
        }

        // 리사이클러뷰 어댑터를 다시 생성하여 할당합니다.
        val recyclerViewAdapter = MemoAdapter(mainActivity)
        fragmentMemoListBinding.memoListRecyclerView.adapter = recyclerViewAdapter
    }
}