package com.example.material10_navigationrail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.material10_navigationrail.databinding.ActivityMainBinding
import com.google.android.material.navigationrail.NavigationRailView

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBidning: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBidning = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBidning.root)

        activityMainBidning.run {
            navigationRailView.run {



                // 라벨 표시 설정
//                labelVisibilityMode=NavigationRailView.LABEL_VISIBILITY_UNLABELED
//                labelVisibilityMode=NavigationRailView.LABEL_VISIBILITY_LABELED
                labelVisibilityMode = NavigationRailView.LABEL_VISIBILITY_SELECTED
//                labelVisibilityMode=NavigationRailView.LABEL_VISIBILITY_AUTO

                setOnItemSelectedListener {
                    when (it.itemId) {
                        R.id.menu_item1 -> {
                            textView.text = "첫 번째 항목을 눌렀다."
                        }
                        R.id.menu_item2 -> {
                            textView.text = "두 번째 항목을 눌렀다."
                        }
                        R.id.menu_item3 -> {
                            textView.text = "세 번째 항목을 눌렀다."
                        }
                    }
                    true
                }
                // 만약 선택 메뉴를 선택하고 싶다면..
                selectedItemId = R.id.menu_item2
            }
        }
    }
}