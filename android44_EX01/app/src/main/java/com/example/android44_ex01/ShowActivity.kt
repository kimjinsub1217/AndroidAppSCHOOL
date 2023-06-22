package com.example.android44_ex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android44_ex01.databinding.ActivityShowBinding

class ShowActivity : AppCompatActivity() {

    lateinit var binding: ActivityShowBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityShowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {
            val position = intent.getIntExtra("position",-1)

            textViewType.text = DataClass.fluitList[position].type
            textViewVolume.text = "${DataClass.fluitList[position].volume}개"
            textViewRegion.text = DataClass.fluitList[position].region
        }
    }
}