package com.example.android47_ex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android47_ex01.MyDataList.dataList
import com.example.android47_ex01.databinding.ActivityPersonInfoBinding

class PersonInfoActivity : AppCompatActivity() {
    lateinit var binding: ActivityPersonInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {
            val position = intent.getIntExtra("position", -1)

            nameTextView.append(dataList[position].name)
            dateTextView.append(dataList[position].date)
            genderTextView.append(dataList[position].gender)
            hobbyTextView.text = dataList[position].hobby


        }
    }
}