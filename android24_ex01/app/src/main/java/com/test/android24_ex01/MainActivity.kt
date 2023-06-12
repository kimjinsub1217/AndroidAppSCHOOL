package com.test.android24_ex01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.SeekBar
import com.test.android24_ex01.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var name = ""
        var director = ""
        var amount = 0
        var age = ""
        var starRating = 0

        val movieList = mutableListOf<Movie>()

        binding.run {
            thread {
                // 500ms 쉬게 한다.
                // onCreate 메서드의 수행이 끝날 때 까지 대기한다.
                SystemClock.sleep(500)
                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(currentFocus, 0)
            }

            seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    amount = progress + 5000
                    // 계산된 값 활용
                    amountTextView.text = "${amount}\n"
                }

                override fun onStartTrackingTouch(seekBar: SeekBar) {
                    // 터치 시작 시 호출되는 메서드
                }

                override fun onStopTrackingTouch(seekBar: SeekBar) {
                    // 터치 종료 시 호출되는 메서드
                }
            })

            ratingBar.run {
                // rating: 설정된 별점 값
                // fromUser : 사용자에 의해 설정되었는가...

                setOnRatingBarChangeListener { ratingBar, rating, fromUser ->

                    starRating = rating.toInt()
                    ratingBarTextView.text = "${starRating}점\n"
                }
            }



            movieName.run {
                requestFocus()
                // 엔터키를 눌렀을때 이벤트
                setOnEditorActionListener { v, actionId, event ->

                    // true를 반환하면 엔터키누른 후에 포커스가 현재 EditText로 유지
                    // false를 반환하면 엔터키 누른 후에 다음 EditText로 포커스가 이동
                    false
                }

            }

            directorName.run {
                setOnEditorActionListener { v, actionId, event ->

                    name = movieName.text.toString()
                    director = directorName.text.toString()
                    val amountInfo = amount

                    when (chipGroup.checkedChipId) {
                        R.id.chipAll -> {
                            age = "전체"
                        }

                        R.id.chipAge12 -> {
                            age = "12세"
                        }

                        R.id.chipAge15 -> {
                            age = "15세"
                        }

                        R.id.chipAdult -> {
                            age = "성인"
                        }

                    }
                    var starRatingInfo = starRating

                    val movieInfo = Movie(name, director, amountInfo, age, starRatingInfo)
                    movieList.add(movieInfo)


                    movieName.text.clear()
                    directorName.text.clear()
                    amount = 0
                    seekBar.progress = 0
                    age = ""
                    chipAll.isChecked = true
                    starRating = 0
                    ratingBar.rating = 0F
                    movieName.requestFocus()
                }

            }

            button.setOnClickListener {
                val stringBuilder = StringBuilder()
                for (i in movieList) {
                    var name = i.name
                    var amount = i.amount
                    var age = i.age
                    var starRating = i.starRating
                    var director = i.director

                    stringBuilder.append("영화 제목 : $name\n")
                    stringBuilder.append("요금 : $amount\n")
                    stringBuilder.append("관련등급 : $age\n")
                    stringBuilder.append("별점 : $starRating\n")
                    stringBuilder.append("감독이름 : $director\n")
                    stringBuilder.append("\n")
                }

                resultTextView.text = stringBuilder.toString()


            }

        }
    }
}

data class Movie(
    val name: String,
    val director: String,
    val amount: Int,
    val age: String,
    val starRating: Int
)