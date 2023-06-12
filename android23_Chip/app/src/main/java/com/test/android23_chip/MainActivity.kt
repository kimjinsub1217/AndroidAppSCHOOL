package com.test.android23_chip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android23_chip.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {
            button1.run {
                setOnClickListener {
                    if (chip2.isChecked) {
                        textView1.text = "선택 되어 있습니다\n"
                    } else {
                        textView1.text = "선택되어 있지 않습니다\n"
                    }

                    when (chipGroup.checkedChipId) {
                        R.id.chip5 -> {
                            chip5.isChecked = true
                            textView1.append("라디오1 선택\n")
                        }

                        R.id.chip6 -> {
                            chip6.isChecked = true
                            textView1.append("라디오2 선택\n")
                        }

                        R.id.chip7 -> {
                            chip7.isChecked = true
                            textView1.append("라이도3 선택\n")
                        }
                    }
                }
            }
        }
    }
}