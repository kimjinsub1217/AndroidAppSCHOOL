package com.example.android70_ex02

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.android70_ex01.MemoClass
import com.example.android70_ex01.student.Companion.memoList
import com.example.android70_ex02.databinding.FragmentAddBinding
import com.example.android70_ex03.DB.DAO
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AddFragment : Fragment() {

    lateinit var fragmentAddBinding: FragmentAddBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentAddBinding = FragmentAddBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        fragmentAddBinding.run {

            toolbarAdd.run {
                title = "메모추가"
                inflateMenu(R.menu.add_menu)

                // 타이틀 글자 색상
                setTitleTextColor(Color.WHITE)

                setNavigationIcon(R.drawable.baseline_arrow_back)
                navigationIcon?.setColorFilter(ContextCompat.getColor(context, R.color.white), PorterDuff.Mode.SRC_IN)
                setNavigationOnClickListener {
                    mainActivity.onBackPressed()
                }
                setOnMenuItemClickListener {
                    mainActivity.replaceFragment(MainActivity.ADD_FRAGMENT, true, true)
                    false
                }

                setOnMenuItemClickListener {
                    // 사용자가 누른 메뉴의 id로 분기한다.
                    // 사용자가 누른 메뉴의 id로 분기한다.
                    when (it?.itemId) {
                        R.id.menuSave -> {

                            val title = editTextAddTitle.text.toString()
                            val description = editTextDescription.text.toString()
                            // 현재 시간을 구해 년-월-일 양식의 문자열을 만들어준다.
                            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                            val now = sdf.format(Date())

                            if (title.isNotBlank() && description.isNotBlank()) {
                                val studentClass = MemoClass(0, title, description,now)
                                DAO.insertData(requireContext(), studentClass)
                                memoList.add(studentClass)

                                mainActivity.removeFragment(MainActivity.ADD_FRAGMENT)
                            } else {
                                Toast.makeText(
                                    requireContext(),
                                    "제목,내용을 입력해주세요",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                    false
                }
            }
        }
        return fragmentAddBinding.root
    }
}