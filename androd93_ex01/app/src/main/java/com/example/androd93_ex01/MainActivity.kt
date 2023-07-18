package com.example.androd93_ex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androd93_ex01.databinding.ActivityMainBinding
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    lateinit var activityMainBinding: ActivityMainBinding
    val serverAddress = "https://a.4cdn.org/boards.json"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        activityMainBinding.run {
            button.setOnClickListener {
                thread {
                    // URL 객체 생성
                    val url = URL(serverAddress)
                    // 접속 후 스트림 추출
                    val httpURLConnection = url.openConnection() as HttpURLConnection

                    val inputStreamReader =
                        InputStreamReader(httpURLConnection.inputStream, "UTF-8")
                    val bufferedReader = BufferedReader(inputStreamReader)

                    var str: String? = null
                    val stringBuffer = StringBuffer()
                    // 문서의 마지막까지 읽어온다.
                    do {
                        str = bufferedReader.readLine()
                        if (str != null) {
                            stringBuffer.append(str)
                        }
                    } while (str != null)

                    val data = stringBuffer.toString()
//                    runOnUiThread {
//                        textView.text = data
//                    }

                    runOnUiThread {
                        textView.text = ""
                    }

                    // JSON 데이터 분석
                    // { } : JSONObject, 이름 - 값 형태
                    // [ ] :JSONArray, 0부터 1씩 증가하는 순서값을 가지고 관리
                    // 100 : 정수
                    // 11.11 : 실수
                    // "문자열" : 문자열
                    // true, false : 논리형

                    // 최 상위가 { } 이므로 JSONObject를 생성한다.
                    val root = JSONObject(data)

                    val countryArray = root.getJSONArray("boards")

                    for(idx in 0 until countryArray.length()){

                        // idx 번째 JSONObject를 추출한다.
                        val countryObject = countryArray.getJSONObject(idx)

                        val board = countryObject.getString("board")
                        val title = countryObject.getString("title")
                        val pages = countryObject.getInt("pages")
                        val image_limit = countryObject.getInt("image_limit")
                        val cooldowns = countryObject.getJSONObject("cooldowns")
                        val threads = cooldowns.getInt("threads")
                        val replies = cooldowns.getInt("replies")
                        val images = cooldowns.getInt("images")
                        runOnUiThread {
                            textView.append("board : ${board}\n")
                            textView.append("title : ${title}\n")
                            textView.append("pages : ${pages}\n")
                            textView.append("image_limit : ${image_limit}\n")
                            textView.append("threads : ${threads}\n")
                            textView.append("replies : ${replies}\n")
                            textView.append("images : ${images}\n\n")
                        }
                    }
                }
            }
        }
    }
}