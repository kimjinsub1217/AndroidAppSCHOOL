package com.example.android55_exerciseinfo

import android.R
import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isGone
import com.example.android55_exerciseinfo.databinding.FragmentInputBinding

class InputFragment : Fragment() {
    lateinit var fragmentInputBinding: FragmentInputBinding
    lateinit var mainActivity: MainActivity

    // 스피너 구성을 위한 데이터
    var spinnerData = arrayOf("야구부", "축구부", "수영부")

    // 수영법
    val dataList =
        arrayOf("자유형", "접영", "배영", "평형")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainActivity = activity as MainActivity
        fragmentInputBinding = FragmentInputBinding.inflate(layoutInflater)


        fragmentInputBinding.run {
            inputSpinner.run {
                val adapter1 = ArrayAdapter<String>(
                    mainActivity, R.layout.simple_spinner_item, spinnerData
                )
                adapter1.setDropDownViewResource(android.R.layout.simple_spinner_item)
                adapter = adapter1


                onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        val selectedSpinnerItem = spinnerData[position]

                        when (selectedSpinnerItem) {
                            "야구부" -> {
                                baseballLayout.visibility = View.VISIBLE
                                soccerLayout.visibility = View.GONE
                                swimmingLayout.visibility = View.GONE

                                safetyEditText.setOnEditorActionListener { _, _, _ ->
                                    var name = baseballNameEditText.text.toString()
                                    var battingAverage =
                                        battingAverageEditText.text.toString().toInt()
                                    var homeRun = homeRunCountEditText.text.toString().toInt()
                                    var safety = safetyEditText.text.toString().toInt()


                                    if (name.isEmpty() || battingAverage.toString()
                                            .isEmpty() || homeRun.toString()
                                            .isEmpty() || safety.toString().isEmpty()
                                    ) {
                                        Toast.makeText(context, "빈칸을 채워주세요 ^^", Toast.LENGTH_SHORT)
                                            .show()
                                    } else {
                                        val baseballClass =
                                            BaseballClass(name, battingAverage, homeRun, safety)
                                        Log.i("확인", "${baseballClass.battingAverage}")
                                        DataClass.exerciseList.add(baseballClass)

                                        mainActivity.removeFragment(FragmentName.FRAGMENT_INPUT)
                                    }
                                    false
                                }

                            }

                            "축구부" -> {
                                baseballLayout.visibility = View.GONE
                                soccerLayout.visibility = View.VISIBLE
                                swimmingLayout.visibility = View.GONE

                                assistCountEditText.setOnEditorActionListener { _, _, _ ->
                                    var name = soccerNameEditText.text.toString()
                                    var goalCount = goalCountEditTExt.text.toString().toInt()
                                    var assistCount = assistCountEditText.text.toString().toInt()



                                    if (name.isEmpty() ||
                                        goalCount.toString().isEmpty() ||
                                        assistCount.toString().isEmpty()
                                    ) {
                                        Toast.makeText(context, "빈칸을 채워주세요 ^^", Toast.LENGTH_SHORT)
                                            .show()
                                    } else {
                                        val soccerClass = SoccerClass(name, goalCount, assistCount)
                                        Log.i("확인", "${soccerClass.assistCount}")
                                        DataClass.exerciseList.add(soccerClass)

                                        mainActivity.removeFragment(FragmentName.FRAGMENT_INPUT)
                                    }
                                    false
                                }

                            }

                            "수영부" -> {
                                baseballLayout.visibility = View.GONE
                                soccerLayout.visibility = View.GONE
                                swimmingLayout.visibility = View.VISIBLE

                                swimmingButton.setOnClickListener {
                                    var swimmingName = swimmingNameEditText.text.toString()
                                    var swimming: String? = null

                                    val adapter = ArrayAdapter<String>(
                                        requireContext(),
                                        android.R.layout.simple_list_item_1,
                                        dataList
                                    )

                                    val builder = AlertDialog.Builder(requireContext())
                                    builder.setTitle("리스트 다이얼로그")
                                    builder.setIcon(R.mipmap.sym_def_app_icon)

                                    // 어뎁터를 설정한다.
                                    // 두 번째 매개변수에는 사용자가 선택한 항목의 순서값이 들어온다.
                                    builder.setAdapter(adapter) { dialogInterface: DialogInterface, i: Int ->
                                        swimmingTextView.text = "제가 잘하는 수영법은 : ${dataList[i]}"
                                        swimming = dataList[i]
                                        Log.i("swimming", swimming!!)

                                        if (swimmingName.isEmpty() || swimming.isNullOrEmpty()) {
                                            Toast.makeText(context, "빈칸을 채워주세요 ^^", Toast.LENGTH_SHORT).show()
                                        } else {
                                            val swimmingClass = SwimmingClass(swimmingName, swimming!!)
                                            Log.i("확인", "${swimmingClass.how_swim}")
                                            DataClass.exerciseList.add(swimmingClass)

                                            mainActivity.removeFragment(FragmentName.FRAGMENT_INPUT)
                                        }
                                    }

                                    builder.setNegativeButton("취소", null)
                                    builder.show()
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

        return fragmentInputBinding.root
    }

}
