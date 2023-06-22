package com.example.android45_app2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.android45_app2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val c1 = ActivityResultContracts.StartActivityForResult()
        val activityLauncher = registerForActivityResult(c1){
            val value1 = it.data?.getIntExtra("value1", 0)
            val value2 = it.data?.getStringExtra("value2")

            binding.run{
                textView.run{
                    text = "value1 : ${value1}\n"
                    append("value2 : ${value2}")
                }
            }

        }
        binding.run {
            button.run {
                setOnClickListener {
                    // 다른 애플리케이션의 Activity에 붙혀준 이름ㅇ르 지정하여 Intent를 생성한다.
                    val newIntent = Intent("com.test.android45.third_activity")
                    newIntent.putExtra("data1", 100)
                    newIntent.putExtra("data2", "안녕하세요")

//                    startActivity(newIntent)
                    activityLauncher.launch(newIntent)
                }
            }
        }
    }
}