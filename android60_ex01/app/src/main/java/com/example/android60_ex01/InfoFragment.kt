package com.example.android60_ex01

import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android60_ex01.DataClass.Companion.studentList
import com.example.android60_ex01.databinding.FragmentInfoBinding
import com.example.android60_ex01.databinding.ItemInfoBinding


class InfoFragment : Fragment() {
    lateinit var fragmentInfoBinding: FragmentInfoBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainActivity = activity as MainActivity

        fragmentInfoBinding = FragmentInfoBinding.inflate(layoutInflater)

        fragmentInfoBinding.run {
            infoToolbar.run {
                title = "${studentList[mainActivity.rowPosition].name}님의 정보"
                setTitleTextColor(Color.WHITE)

                // back 버튼 아이콘을 표시한다.
                setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)

                // back 버튼의 아이콘 색상을 변경한다.
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    navigationIcon?.colorFilter =
                        BlendModeColorFilter(Color.WHITE, BlendMode.SRC_ATOP)
                } else {
                    navigationIcon?.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP)
                }
                // 백 버튼을 누르면 동작하는 리스너
                setNavigationOnClickListener {
                    mainActivity.removeFragment(FragmentName.FRAGMENT_INFO)
                }
            }

            // 데이터를 추출한다.
            val (name, age, kor) = studentList[mainActivity.rowPosition]


            nameTextView.append(name)
            ageTextView.append(age.toString()+"살")
            korTextView.append(kor.toString()+"점")
        }

        return fragmentInfoBinding.root
    }

}