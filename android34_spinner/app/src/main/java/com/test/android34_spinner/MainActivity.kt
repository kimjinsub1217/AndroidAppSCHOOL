package com.test.android34_spinner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import com.google.android.material.navigation.NavigationBarView
import com.test.android34_spinner.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    val dataList = arrayOf(
        "토마토", "수박", "키위", "포도", "귤", "멜론", "딸기", "자두", "복숭아", "살구", "체리"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {
            spinner.run {
                val a1 = ArrayAdapter<String>(
                    this@MainActivity,
                    android.R.layout.simple_spinner_item,
                    dataList
                )

                // Spinner가 펼쳐져 있을 때의 항목 모양
                a1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                adapter = a1

                // spinner의 항목을 코드로 선택한다.
                // 0부터 시작하는 순서값을 넣어준다.
                setSelection(2)

                // 항목을 선택하면 동작하는 리스너
                // 3번째 : 선택한 항목의 순서값 (0부터)
                onItemSelectedListener = object : OnItemSelectedListener{
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        // Spinner에서 선택된 항목의 순서값을 가져온다.
                        val position = spinner.selectedItemPosition
                        textView1.text = "선택한 항목 : ${dataList[position]}"
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {

                    }

                }
            }
            button.run {
                setOnClickListener {
                    // Spinner에서 선택된 항목의 순서값을 가져온다.
                    val position = spinner.selectedItemPosition
                    textView2.text = "선택한 항목 : ${dataList[position]}"
                }
            }
        }
    }
}