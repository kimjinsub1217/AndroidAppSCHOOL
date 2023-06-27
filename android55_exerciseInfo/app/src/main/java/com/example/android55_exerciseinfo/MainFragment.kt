package com.example.android55_exerciseinfo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android55_exerciseinfo.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    lateinit var fragmentMainBinding: FragmentMainBinding
    lateinit var mainActivity: MainActivity

    // 스피너 구성을 위한 데이터
    var spinnerData = arrayOf("전체보기", "야구부", "축구부", "수영부")

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
                mainActivity.replaceFragment(
                    FragmentName.FRAGMENT_INPUT,
                    addToBackStack = true,
                    animate = true
                )
            }

            spinner.run {
                val adapter1 = ArrayAdapter<String>(
                    mainActivity, android.R.layout.simple_spinner_item, spinnerData
                )
                adapter1.setDropDownViewResource(android.R.layout.simple_spinner_item)
                adapter = adapter1

                onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        val selectedSpinnerItem = spinnerData[position]

                        when (selectedSpinnerItem) {
                            "전체보기" -> {
                                val type = "전체보기"
                                recyclerView.run {
                                    adapter = recyclerViewAdaper(mainActivity, type)
                                    layoutManager = LinearLayoutManager(activity)
                                }
                            }

                            "야구부" -> {
                                val type = "야구부"
                                recyclerView.run {
                                    adapter = recyclerViewAdaper(mainActivity, type)
                                    layoutManager = LinearLayoutManager(activity)

                                }
                            }

                            "축구부" -> {
                                val type = "축구부"
                                recyclerView.run {
                                    adapter = recyclerViewAdaper(mainActivity, type)
                                    layoutManager = LinearLayoutManager(activity)

                                }
                            }

                            "수영부" -> {
                                val type = "수영부"
                                recyclerView.run {
                                    adapter = recyclerViewAdaper(mainActivity, type)
                                    layoutManager = LinearLayoutManager(activity)
                                }
                            }

                        }
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        Toast.makeText(context, "선택해주세요!", Toast.LENGTH_SHORT).show()
                    }

                }
            }


        }


        return fragmentMainBinding.root
    }


}