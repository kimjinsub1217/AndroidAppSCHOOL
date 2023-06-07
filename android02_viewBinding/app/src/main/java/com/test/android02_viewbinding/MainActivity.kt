package com.test.android02_viewbinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.test.android02_viewbinding.databinding.ActivityMainBinding
import com.test.android02_viewbinding.databinding.ActivityTestBinding

class MainActivity : AppCompatActivity() {
    // ViewBinding
    // res / layout 폴더에 있는 xml  파일 하나당 하나의 클래스가 만들어진다.
    // 이 클래스에는 xml 파일이 가지고 있는 View들을 관리하는 기능이 들어가 있다.
    // 이를 통하면 개발자가 View를 직접 추출해주고 사용할 수 있다.
    // 안드로이드 OS가 알아서 View를 추출하여 변수에 담아준다.
    // 자동으로 생성되는 클래스의 이름은 xml 파일의 이름으로 생성된다.
    // activity_main

    //ViewBinding 객체를 담을 변수
    private lateinit var binding: ActivityMainBinding
    private lateinit var activityTestBinding: ActivityTestBinding

    //View를 담을 변수
//    lateinit var textView: TextView
//    lateinit var button: Button
//    lateinit var button2: Button
//    lateinit var button3: Button
//    lateinit var button4: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
//        activityTestBinding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        ViewBinding 객체가 관리하는 View 중에 최상위 View를
//        지정하여 화면에 나타난게 한다.
//        layoutInflater : XML 파일을 통해 객체를 생성하는 도구
//        setContentView(activityTestBinding.root)
        // 생성된 ViewBinding 객체에는 View의 ID와 동일한 이름의 변수가
        // 만들어지고 그 변수에는 View 객체가 들어가 있다.
        // View 객체를 가져온다.
//        textView = findViewById(R.id.textView)
//        button = findViewById(R.id.addButton)
//        button2 = findViewById(R.id.minusButton)
//        button3 = findViewById(R.id.multiplyButton)
//        button4 = findViewById(R.id.divideButton)

        // 리스너를 설정해준다
        val button1ClickListener = Button1ClickListener()
        val button2ClickListener = Button2ClickListener()
        val button3ClickListener = Button3ClickListener()
        val button4ClickListener = Button4ClickListener()

//        button.setOnClickListener(button1ClickListener)
//        button2.setOnClickListener(button2ClickListener)
//        button3.setOnClickListener(button3ClickListener)
//        button4.setOnClickListener(button4ClickListener)

//        binding.addButton.setOnClickListener (button1ClickListener)
//        binding.minusButton.setOnClickListener ( button2ClickListener )
//        binding.multiplyButton.setOnClickListener ( button3ClickListener )
//        binding.divideButton.setOnClickListener ( button4ClickListener )

//        binding.addButton.setOnClickListener {
//            binding.textView.text = "10 + 10 = ${10 + 10}"
//        }
//
//        binding.minusButton.setOnClickListener {
//            binding.textView.text = "10 - 10 = ${10 - 10}"
//        }
//
//        binding.multiplyButton.setOnClickListener {
//            binding.textView.text = "10 * 10 = ${10 * 10}"
//        }
//
//        binding.divideButton.setOnClickListener {
//            binding.textView.text = "10 / 10 = ${10 / 10}"
//        }

        binding.run {
            addButton.run{
                setOnClickListener {
                    binding.textView.text = "10 + 10 = ${10 + 10}"
                }
            }

            minusButton.run{
                setOnClickListener {
                    binding.textView.text = "10 - 10 = ${10 - 10}"
                }
            }

            multiplyButton.run{
                setOnClickListener {
                    binding.textView.text = "10 * 10 = ${10 * 10}"
                }
            }

            divideButton.run{
                setOnClickListener {
                    binding.textView.text = "10 / 10 = ${10 / 10}"
                }
            }
        }
    }

    //각 버튼별 리스너
    inner class Button1ClickListener : View.OnClickListener {
        override fun onClick(v: View?) {
            //textView.text = "10 + 10 = ${10 + 10}"
            binding.textView.text = "10 + 10 = ${10 + 10}"
        }
    }

    inner class Button2ClickListener : View.OnClickListener {
        override fun onClick(v: View?) {
            //textView.text = "10 - 10 = ${10 - 10}"
            binding.textView.text = "10 - 10 = ${10 - 10}"
        }
    }

    inner class Button3ClickListener : View.OnClickListener {
        override fun onClick(v: View?) {
            //textView.text = "10 * 10 = ${10 * 10}"
            binding.textView.text = "10 * 10 = ${10 * 10}"
        }
    }

    inner class Button4ClickListener : View.OnClickListener {
        override fun onClick(v: View?) {
            //textView.text = "10 / 10 = ${10 / 10}"
            binding.textView.text = "10 / 10 = ${10 / 10}"
        }
    }
}