package com.test.android17_imageview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.android17_imageview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {
            button.run {
                setOnClickListener {
                    // drawable , mipmap 폴더에 있는 이미지를 저장한다.
                    imageView2.setImageResource(R.drawable.img_android)
                }
            }
        }
    }
}