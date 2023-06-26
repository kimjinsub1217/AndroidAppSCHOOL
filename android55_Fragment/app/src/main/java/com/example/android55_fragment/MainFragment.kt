package com.example.android55_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android55_fragment.databinding.FragmentMainBinding


class MainFragment : Fragment() {
    lateinit var fragmentMainBinding: FragmentMainBinding
    lateinit var mainActivity: MainActivity



    // Fragment가 보여줄 화면을 생성하는 메서드
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Fragment를 관리하는 Activity 객체를 가져온다.
        mainActivity = activity as MainActivity

        //ViewBinding
        fragmentMainBinding = FragmentMainBinding.inflate(layoutInflater)


        fragmentMainBinding.run {
            buttonMain1.setOnClickListener {
                //Fragment를 교체한다.
                mainActivity.replaceFragment(FragmentName.FRAGMENT_INPUT,
                    addToBackStack = true,
                    animate = true
                )
            }
        }

        fragmentMainBinding.run {
            recyclerView.run{
                adapter=recyclerViewAdaper()
                layoutManager=LinearLayoutManager(activity)
            }
        }

        return fragmentMainBinding.root
    }

}
