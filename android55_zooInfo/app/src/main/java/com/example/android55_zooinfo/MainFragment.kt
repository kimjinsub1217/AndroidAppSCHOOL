package com.example.android55_zooinfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android55_zooinfo.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    lateinit var fragmentMainBinding: FragmentMainBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Fragment를 관리하는 Activity 객체를 가져온다.
        mainActivity = activity as MainActivity

        //ViewBinding
        fragmentMainBinding = FragmentMainBinding.inflate(layoutInflater)

        fragmentMainBinding.run {
            inputButton.setOnClickListener {
                //Fragment를 교체한다.
                mainActivity.replaceFragment(FragmentName.FRAGMENT_INPUT,
                    addToBackStack = true,
                    animate = true
                )
            }
        }

        fragmentMainBinding.run {
            recyclerView.run{
                adapter=recyclerViewAdaper(mainActivity)
                layoutManager= LinearLayoutManager(activity)
            }
        }


        return fragmentMainBinding.root
    }


}