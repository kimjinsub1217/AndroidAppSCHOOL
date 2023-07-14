package com.example.android83_screenlotation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android83_screenlotation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var activityManiBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityManiBinding = ActivityMainBinding.inflate(layoutInflater)

        activityManiBinding.run {

            // 복원
            if (savedInstanceState != null) {
                val str1 = savedInstanceState.getString("str1")
                textViewResult.text = str1
            }

            buttonLoginSubmit.setOnClickListener {
                editTextUserId.setText("하하하하")
                editTextUserPw.setText("aaaaa")
            }

            buttonJoin.setOnClickListener {
                textViewResult.text = "버튼을 눌렀습니다"
            }
        }

        setContentView(activityManiBinding.root)

    }

    // 화면 회전이 발생했을 때 호출되는 메서드(프르그먼트도 돌아간다.)
    // 매개변수로 돌아오는 번들 객체는 화면이 다시 만들어지고 OnCrate 메서드를 호출할 때
    // 매개 변수로 전달된다.
    // 여기에는 화면 회전 후 값이 유지되지 않는 View들에 대한 복원 작업을 할 수 있도록
    // 데이터를 저장하는 역할을 한다.
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        // 새로운 화면에서 복원해야할 뷰의 값을 저정한다
        outState.putString("str1", activityManiBinding.textViewResult.text.toString())
    }
}