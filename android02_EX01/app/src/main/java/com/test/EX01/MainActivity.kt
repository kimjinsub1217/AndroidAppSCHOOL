package com.test.EX01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var addButton: Button
    lateinit var minusbutton: Button
    lateinit var multiplyButton: Button
    lateinit var divideButton: Button
    lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addButton = findViewById(R.id.addButton)
        minusbutton = findViewById(R.id.minusButton)
        multiplyButton = findViewById(R.id.multiplyButton)
        divideButton = findViewById(R.id.divideButton)

        textView = findViewById(R.id.resultTextView)

        val addButtonClickListener = AddButtonClickListener()
        addButton.setOnClickListener(addButtonClickListener)

        val minusButton2ClickListener = MinusButtonClickListener()
        minusbutton.setOnClickListener(minusButton2ClickListener)

        val multiplayButtonClickListener = MultiplayButtonClickListener()
        multiplyButton.setOnClickListener(multiplayButtonClickListener)

        val divideButton2ClickListener = DivideButtonClickListener()
        divideButton.setOnClickListener(divideButton2ClickListener)
    }

    // 버튼을 누르면 동작할 리스너
    inner class AddButtonClickListener : View.OnClickListener {
        override fun onClick(v: View?) {
            textView.text = "10 + 10 = ${10+10}"
        }
    }

    inner class MinusButtonClickListener : View.OnClickListener {
        override fun onClick(v: View?) {
            textView.text = "10 - 10 = ${10-10}"
        }
    }
    inner class MultiplayButtonClickListener : View.OnClickListener {
        override fun onClick(v: View?) {
            textView.text = "10 * 10 = ${10*10}"
        }
    }

    inner class DivideButtonClickListener : View.OnClickListener {
        override fun onClick(v: View?) {
            textView.text = "10 / 10 = ${10/10}"
        }
    }
}