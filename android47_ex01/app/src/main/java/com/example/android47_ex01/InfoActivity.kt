package com.example.android47_ex01

import android.app.DatePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.android47_ex01.databinding.ActivityInfoBinding
import java.util.Calendar


class InfoActivity : AppCompatActivity() {

    lateinit var binding: ActivityInfoBinding
    val genderList = arrayOf("남자", "여자")
    val hobbyList = arrayOf("영화감상", "독서", "헬스", "골프", "낚시")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var datainfo = ""
        var gender = ""
        var hobby = ""
        binding.run {

            // 날짜
            dateButton.setOnClickListener {
                val calendar = Calendar.getInstance()

                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH)
                val day = calendar.get(Calendar.DAY_OF_MONTH)

                // 날짜 선택하면 동작할 리스너
                val datePickerListener =
                    DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                        datainfo = "${year}년 ${month + 1}월 ${dayOfMonth}일"
                        dateTextView.append(datainfo)
                    }

                val pickerDialog =
                    DatePickerDialog(this@InfoActivity, datePickerListener, year, month, day)
                pickerDialog.show()

            }
            // 성별
            genderButton.setOnClickListener {
                val adapter = ArrayAdapter<String>(
                    this@InfoActivity,
                    android.R.layout.simple_list_item_1,
                    genderList
                )

                val builder = AlertDialog.Builder(this@InfoActivity)
                builder.setTitle("성별 선택")
                builder.setIcon(R.mipmap.ic_launcher)

                builder.setAdapter(adapter) { dialogInterface: DialogInterface, i: Int ->
                    gender = genderList[i]
                    genderTextView.append(genderList[i])
                }

                builder.setNegativeButton("취소", null)
                builder.show()
            }
            //취미 버튼 방법 1 (다중 선택)
            val boolArray = BooleanArray(hobbyList.size) { _ -> false }
            hobbyButton.setOnClickListener {
                val builder = AlertDialog.Builder(this@InfoActivity)
                builder.setTitle("취미를 선택해주세요")


                Log.i("어떤 값이 들어 온걸까?", "${boolArray.contentToString()}")

                builder.setMultiChoiceItems(hobbyList, boolArray, null)
                builder.setNegativeButton("취소", null)
                builder.setPositiveButton("확인") { dialogInterface: DialogInterface, i: Int ->
                    hobbyTextView.text = ""
                    hobbyTextView.text = "취미 : "
                    // 다이얼로그에서 ListView 추출
                    val alertDialog = dialogInterface as AlertDialog

                    // 현재 체크되어 있는 상태 정보 추출
                    val position = alertDialog.listView.checkedItemPositions

                    for (idx in 0 until position.size()) {
                        val pos1 = position.keyAt(idx)
                        boolArray[pos1] = position.get(pos1)
                    }

                    for (idx in boolArray.indices) {
                        if (boolArray[idx]) {
                            hobbyTextView.append("${hobbyList[idx]}, ")
                        }
                    }
                    hobbyTextView.text.substring(2,hobbyTextView.text.length)
                    hobby = hobbyTextView.text.toString()
                    if (hobby.length >= 2) {
                        val newText = hobby.substring(0,hobby.length-2)
                        hobbyTextView.text = newText
                    } else {
                        hobbyTextView.text = ""
                    }
                }

                builder.show()
            }

            // 추가 버튼
            addButton.setOnClickListener {
                var name = nameEditText.text.toString()

                val intent = Intent()

                intent.putExtra("name", name) //이름
                intent.putExtra("date", datainfo) //날짜
                intent.putExtra("gender", gender)// 성별
                intent.putExtra("hobby",hobby.substring(0,hobby.length-2))
                setResult(RESULT_OK, intent)
                finish()

            }
            // 취소 버튼
            cencleButton.setOnClickListener {
                finish()
            }
        }
    }
}