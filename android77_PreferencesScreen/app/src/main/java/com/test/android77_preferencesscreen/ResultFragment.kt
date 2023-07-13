package com.test.android77_preferencesscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.PreferenceManager
import com.test.android77_preferencesscreen.databinding.FragmentResultBinding


class ResultFragment : Fragment() {

    lateinit var fragmentResultBinding: FragmentResultBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        fragmentResultBinding = FragmentResultBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        // Preferences를 가져온다.
        val pref = PreferenceManager.getDefaultSharedPreferences(mainActivity)

        // 저장된 데이터를 가져온다.
        // EditTextPreference
        val data1 = pref.getString("data1", null)
        // CheckBoxPreference
        val data2 = pref.getBoolean("data2", false)
        // SwitchPreference
        val data3 = pref.getBoolean("data3", false)
        // ListPreference
        val data4 = pref.getString("data4", null)
        // MultiListPreference
        val data5 = pref.getStringSet("data5", null)

        fragmentResultBinding.run{
            textViewResult.text = "data1 : ${data1}\n"
            textViewResult.append("data2 : ${data2}\n")
            textViewResult.append("data3 : ${data3}\n")
            textViewResult.append("data4 : ${data4}\n")

            for(a1 in data5!!){
                textViewResult.append("data5 : ${a1}\n")
            }
        }

        return fragmentResultBinding.root
    }

}