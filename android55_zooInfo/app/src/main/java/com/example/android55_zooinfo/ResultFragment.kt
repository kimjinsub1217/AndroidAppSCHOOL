package com.example.android55_zooinfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android55_zooinfo.DataClass.Companion.animalList
import com.example.android55_zooinfo.databinding.FragmentResultBinding


class ResultFragment : Fragment() {

    lateinit var fragmentResultBinding: FragmentResultBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainActivity = activity as MainActivity
        fragmentResultBinding = FragmentResultBinding.inflate(layoutInflater)

        fragmentResultBinding.run {
            // 데이터를 추출한다.
            val (type, name, age, weight) = animalList[mainActivity.rowPosition]

            typeTextView.append(type)
            nameTextView.append(name)
            ageTextView.append(age.toString()+"살")
            weightTextView.append(weight.toString()+"Kg")


            mainButton.setOnClickListener {
                mainActivity.removeFragment(FragmentName.FRAGMENT_RESULT)
            }

            clearButton.setOnClickListener {
                animalList.removeAt(mainActivity.rowPosition)
                mainActivity.removeFragment(FragmentName.FRAGMENT_RESULT)
            }
        }

        return fragmentResultBinding.root
    }

}