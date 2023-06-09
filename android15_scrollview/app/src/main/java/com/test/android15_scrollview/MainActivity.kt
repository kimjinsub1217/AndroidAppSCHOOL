package com.test.android15_scrollview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.Touch.scrollTo
import android.view.View
import android.view.View.OnScrollChangeListener
import com.test.android15_scrollview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {
            scrollView.run {
                // ScrollView의 Scroll 이벤트
                // 이벤트가 발생한 뷰, 스크롤된 X 좌표, 스크롤된 Y 좌표, 스크롤 되기전 X 좌표, 스크롤 되기전 Y 좌표
                setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                    textView.text = "Y : $oldScrollY -> $scrollY"
                }
            }

            scrollView2.run {
                setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                    textView.text = "X : $oldScrollX -> $scrollX"
                }
            }

            button.setOnClickListener {
                // ScrollView의 Y 좌표를 가져온다.
                textView.text = "X : ${scrollView2.scrollX}"
                textView2.text = "Y : ${scrollView.scrollY}"
            }

            button2.setOnClickListener {
//                // 지정된 위치로 이동한다.
//                scrollView.scrollTo(0, 1000)
//                scrollView2.scrollTo(1000, 0)

                // 현재 위치에서 지정된 만큼 이동한다.
//                scrollView.scrollBy(0, 100)
//                scrollView2.scrollBy(100, 0)

                // 지정된 위치로 이동한다(애니메이션)
                scrollView.smoothScrollTo(0, 1000)
                scrollView2.smoothScrollTo(1000, 0)

                // 지정된 만큼 이동한다.(애니메이션)
                scrollView.smoothScrollBy(0, 100)
                scrollView2.smoothScrollBy(100, 0)

            }
        }
    }
}

