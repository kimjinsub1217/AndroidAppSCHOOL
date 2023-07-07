package com.example.android70_ex03.fragment

import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android70_ex03.db.DAOCategory
import com.example.android70_ex03.MainActivity
import com.example.android70_ex03.R
import com.example.android70_ex03.adapter.CategoryAdapter
import com.example.android70_ex03.databinding.DialogBinding
import com.example.android70_ex03.databinding.FragmentCategoryListBinding
import com.example.android70_ex03.db.Category.Companion.categoryList
import com.example.android70_ex03.db.CategoryClass
import kotlin.concurrent.thread


class CategoryListFragment : Fragment() {
    lateinit var fragmentCategoryListBinding: FragmentCategoryListBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentCategoryListBinding = FragmentCategoryListBinding.inflate(layoutInflater)
        mainActivity = activity as MainActivity

        fragmentCategoryListBinding.run {
            categoryListToolbar.run {
                title = "카테고리 목록"
                inflateMenu(R.menu.category_list_menu)

                // 타이틀 글자 색상
                setTitleTextColor(Color.WHITE)
                setOnMenuItemClickListener { menuItem ->
                    when (menuItem.itemId) {
                        R.id.category_list_add_button -> {
                            val dialogBinding = DialogBinding.inflate(layoutInflater)

                            val builder = AlertDialog.Builder(requireContext())
                            builder.setTitle("카테고리 이름 등록")
                            builder.setIcon(R.mipmap.ic_launcher)

                            builder.setView(dialogBinding.root)

                            thread {
                                SystemClock.sleep(500)
                                val imm =
                                    requireContext().getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
                                imm.showSoftInput(dialogBinding.editTextDialog, 0)
                            }

                            builder.setPositiveButton("확인") { dialogInterface: DialogInterface, i: Int ->

                                val str1 = dialogBinding.editTextDialog.text.toString()
                                val memoClass = CategoryClass(0, str1)
                                DAOCategory.insertData(requireContext(),memoClass)
                                categoryList.add(memoClass)
                            }
                            builder.setNegativeButton("취소", null)
                            builder.show()
                        }
                    }
                    false
                }
            }

            categoryListRecyclerView.run {
                adapter = CategoryAdapter(mainActivity)
                layoutManager = LinearLayoutManager(mainActivity).apply {
                    reverseLayout = true
                    stackFromEnd = true
                }
                registerForContextMenu(categoryListRecyclerView)
                addItemDecoration(
                    DividerItemDecoration(
                        mainActivity,
                        DividerItemDecoration.VERTICAL
                    )
                )
            }
        }

        return fragmentCategoryListBinding.root
    }
    override fun onResume() {
        super.onResume()

        // 데이터를 불러온다.
        val dataList = DAOCategory.selectAllData(requireContext())

        // 기존의 데이터를 유지한 채로 새로운 데이터를 추가한다.
        categoryList.clear() // 기존의 데이터를 비우고 새로운 데이터를 추가하기 전에 memoList를 비웁니다.
        categoryList.addAll(dataList)

        // 삭제된 이후의 모든 아이템의 인덱스 업데이트
        for (i in 0 until categoryList.size) {
            categoryList[i].idx = i + 1
        }

        // 리사이클러뷰 어댑터를 다시 생성하여 할당합니다.
        val recyclerViewAdapter = CategoryAdapter(mainActivity)
        fragmentCategoryListBinding.categoryListRecyclerView.adapter = recyclerViewAdapter
    }

}