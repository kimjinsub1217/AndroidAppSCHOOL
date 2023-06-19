package com.example.android43_ex02

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Selection.setSelection
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.RadioButton
import com.example.android43_ex02.databinding.ActivityItemBinding

class ItemActivity : AppCompatActivity() {
    lateinit var binding: ActivityItemBinding
    var fruitList = arrayOf(
        "수박", "오렌지", "딸기"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {
            spinner.run {
                val a1 = ArrayAdapter<String>(
                    this@ItemActivity,
                    R.layout.simple_spinner_item,
                    fruitList
                )

                // Spinner가 펼쳐져 있을 때의 항목 모양
                a1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                adapter = a1
                setSelection(0)
                onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                    }
                    override fun onNothingSelected(parent: AdapterView<*>?) {}
                }
            }

           button.run {
               setOnClickListener {
                   var position = spinner.selectedItemPosition // spinner 위치 값
                   var nationality = fruitList[position] // spinner 위치에 있는 과일
                   val quantity = quantityEditText.text.toString().toInt() //수량
                   val origin = originEditText.text.toString() //원산지

                   val resultIntent = Intent()
                   resultIntent.putExtra("fruit", nationality)
                   resultIntent.putExtra("quantity", quantity)
                   resultIntent.putExtra("origin", origin)
                   setResult(RESULT_OK, resultIntent)
                   finish()

               }
           }


        }
    }
}