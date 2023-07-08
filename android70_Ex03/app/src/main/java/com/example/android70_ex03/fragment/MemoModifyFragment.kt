package com.example.android70_ex03.fragment

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.android70_ex03.MainActivity
import com.example.android70_ex03.R
import com.example.android70_ex03.databinding.FragmentMemoModifyBinding
import com.example.android70_ex03.db.DAOMemo
import com.example.android70_ex03.db.Memo.Companion.memoList


class MemoModifyFragment : Fragment() {

    lateinit var fragmentMemoModifyBinding: FragmentMemoModifyBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentMemoModifyBinding = FragmentMemoModifyBinding.inflate(inflater)
        mainActivity = activity as MainActivity
        val saveMemoList = DAOMemo.selectData(requireContext(), mainActivity.memoPosition + 1)

        fragmentMemoModifyBinding.run {
            memoModifyToolbar.run {
                title = "메모 수정"
                inflateMenu(R.menu.memo_modify_menu)

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
                        R.id.memo_modify_save_button -> {

                            memoList[mainActivity.memoPosition].memoTitleData =
                                memoTitleModifyEditText.text.toString()

                            memoList[mainActivity.memoPosition].descriptionData =
                                editContentModifyEditText.text.toString()

                            saveMemoList!!.memoTitleData=memoTitleModifyEditText.text.toString()
                            saveMemoList.descriptionData=editContentModifyEditText.text.toString()
                            DAOMemo.updateData(requireContext(), saveMemoList)

                            mainActivity.removeFragment(MainActivity.MEMO_MODIFY_FRAGMENT)
                        }
                    }
                    false
                }

            }
            memoTitleModifyEditText.setText(saveMemoList!!.memoTitleData)
            editContentModifyEditText.setText(saveMemoList.descriptionData)

        }

        return fragmentMemoModifyBinding.root
    }
}