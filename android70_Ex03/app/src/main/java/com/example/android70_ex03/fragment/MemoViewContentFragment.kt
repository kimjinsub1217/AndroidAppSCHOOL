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
import com.example.android70_ex03.MainActivity
import com.example.android70_ex03.R
import com.example.android70_ex03.adapter.MemoAdapter
import com.example.android70_ex03.databinding.FragmentMemoViewContentBinding
import com.example.android70_ex03.db.DAOMemo
import com.example.android70_ex03.db.Memo.Companion.memoList


class MemoViewContentFragment : Fragment() {
    lateinit var fragmentMemoViewContentBinding: FragmentMemoViewContentBinding
    lateinit var mainActivity: MainActivity

    lateinit var memoAdapter: MemoAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentMemoViewContentBinding = FragmentMemoViewContentBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        val dbMemoList = DAOMemo.selectData(requireContext(), mainActivity.memoPosition + 1)

        fragmentMemoViewContentBinding.run {
            memoViewContentToolbar.run {
                title = "메모 보기"
                inflateMenu(R.menu.memo_view_content_menu)

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
                        R.id.category_list_modify_button -> {
                            mainActivity.replaceFragment(
                                MainActivity.MEMO_MODIFY_FRAGMENT,
                                addToBackStack = true,
                                animate = true
                            )
                        }

                        R.id.category_list_delete_button -> {
                            Log.i("메모 포지션",mainActivity.memoPosition.toString())
                            Log.i("메모 포지션2", memoList.toString())

                            val deletedPosition = mainActivity.memoPosition // 삭제되는 항목의 위치 저장
                            val deletedItem = memoList[deletedPosition]
                            val deletedId = deletedItem.idx // 삭제되는 항목의 ID 저장

                            DAOMemo.deleteData(requireContext(), deletedId)

                            memoList.removeAt(deletedPosition)

                            // 삭제된 이후의 모든 아이템의 인덱스 업데이트
                            for (i in deletedPosition until memoList.size) {
                                memoList[i].idx = memoList[i].idx - 1
                            }

                            mainActivity.removeFragment(MainActivity.MEMO_VIEW_CONTENT_FRAGMENT)

                            // 데이터베이스에 추가한 데이터 다시 삽입
                            DAOMemo.deleteAllData(requireContext()) // 기존 데이터 모두 삭제
                            for (item in memoList) {
                                DAOMemo.insertData(requireContext(), item)
                            }

                            memoAdapter.notifyDataSetChanged() // 어댑터에 변경을 알림
                        }
                    }
                    false
                }
            }
            // 어댑터 객체를 생성하고 recyclerViewAdapter에 할당
            memoAdapter = MemoAdapter(mainActivity)
            memoViewContentTitleTextView.text = dbMemoList!!.memoTitleData
            memoViewContentDateTextView.text = dbMemoList!!.dateData
            memoViewContentContentTextView.text = dbMemoList!!.descriptionData
        }
        return fragmentMemoViewContentBinding.root
    }


}