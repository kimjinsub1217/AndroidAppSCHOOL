package com.example.android45_startactivityexport

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android45_startactivityexport.databinding.ActivityMainBinding
import com.example.android45_startactivityexport.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {
    lateinit var binding: ActivityThirdBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {
            val data1 = intent.getIntExtra("data1", 0)
            val data2 = intent.getStringExtra("data2")

            textViewResult1.text = "data1 : ${data1}\n"
            textViewResult1.append("data2: $data2")

            button.run {
                setOnClickListener {
                    val resultIntent = Intent()
                    resultIntent.putExtra("value1", 200)
                    resultIntent.putExtra("value2", "감사합니다")

                    setResult(RESULT_OK, resultIntent)
                    finish()
                }
            }

            buttonShowMap.run{
                setOnClickListener {
                    // 위도와 경도를 문자열로 만들어준다.
                    // val address = "geo:37.243243,131.861601"
                    // val uri = Uri.parse(address)
                    // val newIntent = Intent(Intent.ACTION_VIEW, uri)

                    // 웹사이트
                    val address = "http://developer.android.com"
                    val uri = Uri.parse(address)
                    val newIntent = Intent(Intent.ACTION_VIEW, uri)

                    startActivity(newIntent)
                }
            }
        }
    }
}