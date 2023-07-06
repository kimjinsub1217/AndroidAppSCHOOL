package com.example.android70_ex02

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.android70_ex01.student.Companion.memoList
import com.example.android70_ex02.databinding.FragmentModifyBinding


class ModifyFragment : Fragment() {
    lateinit var fragmentModifyBinding: FragmentModifyBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentModifyBinding = FragmentModifyBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        fragmentModifyBinding.run {
            toolbarModify.run {
                title = "메모수정"
                inflateMenu(R.menu.modify_menu)

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

                editTextModifyTitle.setText(memoList[mainActivity.rowPosition].titleData)
                editTextModifyDescription.setText(memoList[mainActivity.rowPosition].descriptionData)
                setOnMenuItemClickListener { menuItem ->
                    when (menuItem.itemId) {
                        R.id.modifySave -> {
                            mainActivity.removeFragment(MainActivity.MODIFY_FRAGMENT)
                            Log.i("몇번", memoList[mainActivity.rowPosition].toString())

                            memoList[mainActivity.rowPosition].titleData =
                                editTextModifyTitle.text.toString()
                            memoList[mainActivity.rowPosition].descriptionData =
                                editTextModifyDescription.text.toString()

                            val obj = DAO.selectData(requireContext(), mainActivity.rowPosition + 1)

                            if (obj != null) {
                                obj.titleData=editTextModifyTitle.text.toString()
                            }

                            if (obj != null) {
                                obj.descriptionData= editTextModifyDescription.text.toString()
                            }
                            if (obj != null) {
                                DAO.updateData(requireContext(), obj)
                            }

                        }
                    }
                    false
                }
            }
        }
        return fragmentModifyBinding.root
    }
}