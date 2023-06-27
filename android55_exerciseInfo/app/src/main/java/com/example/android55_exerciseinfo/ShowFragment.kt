package com.example.android55_exerciseinfo

import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android55_exerciseinfo.DataClass.Companion.exerciseList
import com.example.android55_exerciseinfo.databinding.FragmentShowBinding


class ShowFragment : Fragment() {

    lateinit var fragmentShowBinding: FragmentShowBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainActivity = activity as MainActivity
        fragmentShowBinding = FragmentShowBinding.inflate(layoutInflater)

        val dataClass = exerciseList[mainActivity.itemPosition]
        Log.i("확인용", dataClass.toString())
        fragmentShowBinding.run {
            when (dataClass) {
                is BaseballClass -> {
                    Log.i("확인용", "BaseballClass")
                    showSoccerLayout.visibility = View.GONE
                    showSwimmingLayout.visibility = View.GONE
                    showBaseballLayout.visibility = View.VISIBLE

                    val  (name, battingAverage, homeRunCount, safety) = exerciseList[mainActivity.itemPosition] as BaseballClass

                    baseballNameTextView.append(name)
                    battingAverageTextView.append(battingAverage.toString())
                    homeRunCountTextView.append(homeRunCount.toString() + "번")
                    safetyTextView.append(safety.toString()+"번")
                }

                is SoccerClass -> {
                    showBaseballLayout.visibility = View.GONE
                    showSwimmingLayout.visibility = View.GONE
                    showSoccerLayout.visibility = View.VISIBLE

                    val  (name, goalCount, assistCount) = exerciseList[mainActivity.itemPosition] as SoccerClass

                    soccerNameTextView.append(name)
                    goalCountTextView.append(goalCount.toString()+"골")
                    assistCountTextView.append(assistCount.toString() + "어시")

                }

                is SwimmingClass -> {
                    showBaseballLayout.visibility = View.GONE
                    showSoccerLayout.visibility = View.GONE
                    showSwimmingLayout.visibility = View.VISIBLE

                    val  (name,how_swim ) = exerciseList[mainActivity.itemPosition] as SwimmingClass

                    swimmingNameTextView.append(name)
                    swimmingTextView.append(how_swim)

                }

                else -> {
                    Log.i("확인용", "실패")
                }
            }

            modifyButton.setOnClickListener {
                mainActivity.replaceFragment(FragmentName.FRAGMENT_MODIFY, true, true)
            }


            clearButton.setOnClickListener {
                exerciseList.removeAt(mainActivity.itemPosition)
                mainActivity.removeFragment(FragmentName.FRAGMENT_SHOW)
            }
        }
        return fragmentShowBinding.root
    }
}