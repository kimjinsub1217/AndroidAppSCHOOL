package com.test.android27_floatingactionbutton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android27_floatingactionbutton.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {
            floatingActionButton.run {
                setOnClickListener {
                    textView.text = "버튼을 눌렀습니다"
                }
            }
        }
    }
}