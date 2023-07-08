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
import com.example.android70_ex03.databinding.FragmentMemoAddBinding
import com.example.android70_ex03.db.Category.Companion.categoryList
import com.example.android70_ex03.db.DAOCategory
import com.example.android70_ex03.db.DAOMemo
import com.example.android70_ex03.db.MemoClass
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class MemoAddFragment : Fragment() {

    lateinit var fragmentMemoAddBinding: FragmentMemoAddBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentMemoAddBinding = FragmentMemoAddBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        fragmentMemoAddBinding.run {
            memoAddToolbar.run {
                title = "메모 추가"
                inflateMenu(R.menu.memo_add_menu)

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
                        R.id.memo_add_save_button -> {
                            // 현재 시간을 구해 년-월-일 양식의 문자열을 만들어준다.
                            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                            val now = sdf.format(Date())

                            val memoTitle = memoTitleAddEditText.text.toString()
                            val memoContent = editContentAddEditText.text.toString()

                            Log.i("메모 추가", memoTitle)
                            Log.i("메모 추가", memoContent)


                            val obj =
                                DAOCategory.selectData(mainActivity, mainActivity.categoryPosition + 1)

                            val memoClass = MemoClass(0, memoTitle, memoContent, now,mainActivity.categoryPosition + 1)
                            DAOMemo.insertData(requireContext(), memoClass)
                            categoryList[mainActivity.categoryPosition].memoList.add(memoClass)
                            obj!!.memoList.add(memoClass)
                            DAOCategory.updateData(mainActivity, obj)

                            mainActivity.removeFragment(MainActivity.MEMO_ADD_FRAGMENT)
                        }
                    }
                    false
                }
            }
        }
        return fragmentMemoAddBinding.root
    }
}