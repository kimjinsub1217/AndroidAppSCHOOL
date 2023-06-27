package com.example.android55_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android55_fragment.databinding.FragmentResultBinding
import com.example.android55_fragment.infoList.dataList

class ResultFragment : Fragment() {

    lateinit var fragmentResultBinding:FragmentResultBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mainActivity = activity as MainActivity
        fragmentResultBinding = FragmentResultBinding.inflate(layoutInflater)

        fragmentResultBinding.run{
            // 데이터를 추출한다.
            val (name, age, korean) = dataList[mainActivity.rowPosition]

            nameResultTextView.text = "이름 : $name"
            ageResultTextView.text = "나이 : $age"
            korResultTextView.text = "국어점수 : $korean"
        }

        return fragmentResultBinding.root
    }


}