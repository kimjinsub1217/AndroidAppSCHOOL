package com.example.android47_ex0answer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import com.example.android47_ex0answer.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {
            val position = intent.getIntExtra("position", 0)

            textViewName.text = DataClass.humanList[position].name
            textViewDate.text = DataClass.humanList[position].date
            textViewGender.text = DataClass.humanList[position].gender
            textViewHobby.text = ""

            for (s1 in DataClass.humanList[position].hobbyList) {
                textViewHobby.append("${s1}\n")
            }
            buttonToMain.setOnClickListener {
                finish()
            }
        }
    }
}