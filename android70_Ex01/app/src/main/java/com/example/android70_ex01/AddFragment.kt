package com.example.android70_ex01

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android70_ex01.databinding.FragmentAddBinding
import com.example.android70_ex01.student.Companion.studentList

class AddFragment : Fragment() {

    lateinit var fragmentAddBinding: FragmentAddBinding
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentAddBinding = FragmentAddBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        fragmentAddBinding.run {
            editTextKorean.setOnEditorActionListener { textView, i, keyEvent ->
                val name = editTextAddName.text.toString()
                val age = editTextAddAge.text.toString().toInt()
                val korean = editTextKorean.text.toString().toInt()

                val studentClass = StudentClass(0, name, age, korean)
                DAO.insertData(requireContext(), studentClass)
                studentList.add(studentClass)


                mainActivity.removeFragment(MainActivity.ADD_FRAGMENT)
                false
            }
        }
        return fragmentAddBinding.root
    }


}