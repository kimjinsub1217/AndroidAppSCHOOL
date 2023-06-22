package com.example.android44_ex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import com.example.android44_ex01.databinding.ActivityInputBinding
import kotlin.concurrent.thread

class InputActivity : AppCompatActivity() {
    lateinit var binding: ActivityInputBinding

    // 스피너 구성을 위한 데이터
    var spinnerData = arrayOf("수박", "사과", "귤")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {
            spinnerType.run {
                val adapter1 = ArrayAdapter<String>(
                    this@InputActivity, android.R.layout.simple_spinner_item, spinnerData
                )
                adapter1.setDropDownViewResource(android.R.layout.simple_spinner_item)
                adapter = adapter1
            }

            editTextVolume.requestFocus()
            thread {
                SystemClock.sleep(500)
                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(editTextVolume, 0)
            }

            buttonToMain.run {
                setOnClickListener {
                    finish()
                }
            }

            editTextRegion.run {
                setOnEditorActionListener { v, actionId, event ->
                    val type = spinnerData[spinnerType.selectedItemPosition]
                    val volume = editTextVolume.text.toString().toInt()
                    val region = editTextRegion.text.toString()

                    val fluitClass = FluitClass(type, volume,region)
                    DataClass.fluitList.add(fluitClass)

                    finish()
                    false
                }
            }
        }
    }
}
