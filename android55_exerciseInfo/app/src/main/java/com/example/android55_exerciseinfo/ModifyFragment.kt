package com.example.android55_exerciseinfo

import android.R
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.android55_exerciseinfo.databinding.FragmentModifyBinding


class ModifyFragment : Fragment() {
    lateinit var fragmentModifyBinding: FragmentModifyBinding
    lateinit var mainActivity: MainActivity
    // 수영법
    val dataList =
        arrayOf("자유형", "접영", "배영", "평형")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainActivity = activity as MainActivity
        fragmentModifyBinding = FragmentModifyBinding.inflate(layoutInflater)

        val dataClass = DataClass.exerciseList[mainActivity.itemPosition]
        Log.i("확인용", dataClass.toString())
        fragmentModifyBinding.run {
            when (dataClass) {
                is BaseballClass -> {
                    Log.i("확인용", "BaseballClass")
                    showSoccerLayout.visibility = View.GONE
                    showSwimmingLayout.visibility = View.GONE
                    showBaseballLayout.visibility = View.VISIBLE

                    soccerLayout.visibility=View.GONE
                    swimmingLayout.visibility=View.GONE
                    baseballLayout.visibility=View.VISIBLE

                    modifyButton.setOnClickListener {
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
                            DataClass.exerciseList[mainActivity.itemPosition]=baseballClass
                            mainActivity.removeFragment(FragmentName.FRAGMENT_MODIFY)
                        }

                    }


                    val (name, battingAverage, homeRunCount, safety) = DataClass.exerciseList[mainActivity.itemPosition] as BaseballClass

                    baseballNameTextView.append(name)
                    battingAverageTextView.append(battingAverage.toString())
                    homeRunCountTextView.append(homeRunCount.toString() + "번")
                    safetyTextView.append(safety.toString() + "번")
                }

                is SoccerClass -> {
                    showBaseballLayout.visibility = View.GONE
                    showSwimmingLayout.visibility = View.GONE
                    showSoccerLayout.visibility = View.VISIBLE

                    swimmingLayout.visibility=View.GONE
                    baseballLayout.visibility=View.GONE
                    soccerLayout.visibility=View.VISIBLE

                    modifyButton.setOnClickListener {
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
                            DataClass.exerciseList[mainActivity.itemPosition]=soccerClass

                            mainActivity.removeFragment(FragmentName.FRAGMENT_MODIFY)
                        }
                    }

                    val (name, goalCount, assistCount) = DataClass.exerciseList[mainActivity.itemPosition] as SoccerClass

                    soccerNameTextView.append(name)
                    goalCountTextView.append(goalCount.toString() + "골")
                    assistCountTextView.append(assistCount.toString() + "어시")

                }

                is SwimmingClass -> {
                    showBaseballLayout.visibility = View.GONE
                    showSoccerLayout.visibility = View.GONE
                    showSwimmingLayout.visibility = View.VISIBLE

                    baseballLayout.visibility=View.GONE
                    soccerLayout.visibility=View.GONE
                    swimmingLayout.visibility=View.VISIBLE

                    swimmingButton.setOnClickListener {
                        var swimmingName = swimmingNameEditText.text.toString()
                        var swimming: String? = null

                        val adapter = ArrayAdapter<String>(
                            requireContext(),
                            R.layout.simple_list_item_1,
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

                                modifyButton.setOnClickListener {
                                    val swimmingClass = SwimmingClass(swimmingName, swimming!!)
                                    Log.i("확인", "${swimmingClass.how_swim}")
                                    DataClass.exerciseList[mainActivity.itemPosition] = swimmingClass
                                    mainActivity.removeFragment(FragmentName.FRAGMENT_MODIFY)
                                }
                            }
                        }

                        builder.setNegativeButton("취소", null)
                        builder.show()
                    }


                    val (name, how_swim) = DataClass.exerciseList[mainActivity.itemPosition] as SwimmingClass

                    swimmingNameTextView.append(name)
                    swimmingTextView.append(how_swim)

                }

                else -> {
                    Log.i("확인용", "실패")
                }
            }
            clearButton.setOnClickListener {
                mainActivity.removeFragment(FragmentName.FRAGMENT_MODIFY)
            }
        }
        return fragmentModifyBinding.root
    }
}