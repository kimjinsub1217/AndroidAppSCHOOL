package com.test.android31_ex01

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.SimpleAdapter
import com.test.android31_ex01.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    // 이름
    var nameData = arrayOf<String>()

    // 나이
    var ageData = intArrayOf()

    // 국어점수
    var korScore = intArrayOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {

            korEditText.setOnEditorActionListener { _, actionId, event ->
                if (actionId == EditorInfo.IME_ACTION_DONE || event?.keyCode == KeyEvent.KEYCODE_ENTER) {
                    val nameInfo = nameEditText.text.toString()
                    val ageInfo = ageEditText.text.toString()
                    val korScoreInfo = korEditText.text.toString()

                    nameData += nameInfo
                    ageData += ageInfo.toInt()
                    korScore += korScoreInfo.toInt()

                    // 포커스 제거
                    korEditText.clearFocus()

                    // 키보드 숨김
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(korEditText.windowToken, 0)

                    nameEditText.text.clear()
                    ageEditText.text.clear()
                    korEditText.text.clear()

                    listViewMain.run {

                        val dataList = ArrayList<HashMap<String, Any>>()

                        for (idx in nameData.indices) {
                            // 항목 하나를 구성하기 위해 필요한 데이터를 담을
                            // Map을 생성한다.
                            val map = HashMap<String, Any>()

                            // 데이터를 담는다.
                            map["name"] = nameData[idx]
                            map["age"] = ageData[idx]
                            map["kor"] = korScore[idx]

                            // ArrayList에 담는다.
                            dataList.add(map)

                        }

                        // HashMap에 데이터를 넣을 때 사용한 이름을 가지고 있는 배열
                        val keys = arrayOf("name", "age", "kor")
                        // 데이터를 셋팅할 View의 ID 배열
                        val ids = intArrayOf(R.id.nameTextView, R.id.ageTextView, R.id.korTextView)

                        // listView의 항목 하나를 정의하는 원리
                        // 1. SimpleAdapter에 설정하는 ArrayList 내의 HashMap의 개수 만큼 항목이 만들어진다.
                        // 2. 각 항목을 개별적으로 구성하여 보여준다.
                        // 3. 항목 하나를 구성하기 위해 항목 번째의 HashMap을 추출한다.
                        // 4. HashMap에 있는 데이터를 keys 배열에 들어있는 이름의 순서대로 추출한다.
                        // 5. ids 배열에 설정되어 있는 View의 ID 순서대로 데이터를 설정한다.

                        // SimpleAdater를 생성한다.
                        adapter = SimpleAdapter(
                            this@MainActivity, dataList, R.layout.kor_score, keys, ids
                        )
                    }

                }
                true
            }
        }
    }
}