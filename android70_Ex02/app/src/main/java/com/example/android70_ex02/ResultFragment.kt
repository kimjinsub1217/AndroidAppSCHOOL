package com.example.android70_ex02

import android.content.DialogInterface
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.android70_ex01.MemoClass
import com.example.android70_ex01.student
import com.example.android70_ex01.student.Companion.memoList
import com.example.android70_ex02.databinding.FragmentMainBinding
import com.example.android70_ex02.databinding.FragmentResultBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ResultFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ResultFragment : Fragment() {

    private lateinit var fragmentResultBinding: FragmentResultBinding
    private lateinit var mainActivity: MainActivity

    private lateinit var recyclerViewAdapter: MainRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentResultBinding = FragmentResultBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        // 선택한 행 번째의 객체에서 데이터를 가져온다.
        val studentList = DAO.selectData(requireContext(), mainActivity.rowPosition+1)

        // 선택한 행 번째의 객체가 null이 아닌 경우에만 데이터를 출력한다.
        studentList?.let {
            fragmentResultBinding.run {
                toolbarResult.run {
                    title = "메모읽기"
                    inflateMenu(R.menu.result_menu)

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
                            R.id.menuResultModify -> {
                                mainActivity.replaceFragment(MainActivity.MODIFY_FRAGMENT,
                                    addToBackStack = true,
                                    animate = true
                                )
                            }

                            R.id.menuResultDelete -> {
                                // 다이얼로그 생성을 위한 객체를 생성한다.
                                val builder = AlertDialog.Builder(requireContext())

                                // 타이틀
                                builder.setTitle("메모 삭제")

                                // 메시지
                                builder.setMessage("메모를 삭제 하겠어요?")

                                // 아이콘
                                builder.setIcon(R.mipmap.ic_launcher)

                                builder.setPositiveButton("취소") { dialogInterface: DialogInterface, i: Int ->

                                }
                                builder.setNegativeButton("확인") { dialogInterface: DialogInterface, i: Int ->

                                    val deletedPosition = mainActivity.rowPosition // 삭제되는 항목의 위치 저장
                                    val deletedItem = memoList[deletedPosition]
                                    val deletedId = deletedItem.idx // 삭제되는 항목의 ID 저장

                                    DAO.deleteData(requireContext(), deletedId)

                                    memoList.removeAt(deletedPosition)

                                    // 삭제된 이후의 모든 아이템의 인덱스 업데이트
                                    for (i in deletedPosition until memoList.size) {
                                        memoList[i].idx = memoList[i].idx - 1
                                    }

                                    mainActivity.removeFragment(MainActivity.RESULT_FRAGMENT)

                                    // 데이터베이스에 추가한 데이터 다시 삽입
                                    DAO.deleteAllData(requireContext()) // 기존 데이터 모두 삭제
                                    for (item in memoList) {
                                        DAO.insertData(requireContext(), item)
                                    }

                                    recyclerViewAdapter.notifyDataSetChanged() // 어댑터에 변경을 알림
                                }


                                // 다이얼로그를 띄운다.
                                builder.show()

                            }
                        }
                        false
                    }
                }

                textViewTitle.append(studentList.titleData)
                textViewDate.append(studentList.dateData)
                textViewDescription.text = studentList.descriptionData
            }
        }

        // 어댑터 객체를 생성하고 recyclerViewAdapter에 할당
        recyclerViewAdapter = MainRecyclerViewAdapter(mainActivity, memoList)

        return fragmentResultBinding.root
    }
}