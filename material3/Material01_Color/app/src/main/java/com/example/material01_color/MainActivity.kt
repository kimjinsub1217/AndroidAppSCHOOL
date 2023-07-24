package com.example.material01_color

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

// Material Design 적용 방법
// app 수준의 build.gradle의 material 라이브러리를
// 최신 버전으로 설정하면 된다.
// https://github.com/material-components/material-components-android/releases

// 본 예제는 색상 설정에 관련된 예제이다.
// res/values/themes.xml 파일에서 작업한다.

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}