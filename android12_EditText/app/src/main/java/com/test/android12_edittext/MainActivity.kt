package com.test.android12_edittext

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import com.test.android12_edittext.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {
            editTextText.run {
                setText("코드에서 문자열 설정")
            }
            button.run {
                setOnClickListener {
                    // EditText의 문자열을 가지고 온다.
                    val str1 = editTextText.text.toString()
                    textView.text = str1

                    // 키보드를 내린다.
                    val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    // currentFocus : 현재 포커스를 가지고 있는 뷰를 지정할 수 있다.
                    if (currentFocus != null) {
                        imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)

                        // 포커스를 해제한다.
                        currentFocus!!.clearFocus()
                    }
                }
            }
        }

    }
}