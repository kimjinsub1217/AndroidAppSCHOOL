package com.test.android20_zoo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.RadioButton
import com.test.android20_zoo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val elephantList = mutableListOf<Animal>()
    private val catList = mutableListOf<Animal>()
    private val dogList = mutableListOf<Animal>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {
            zooGroup.setOnCheckedChangeListener { group, checkedId ->
                val selectedRadioButton = findViewById<RadioButton>(checkedId)
                val selectedAnimal = selectedRadioButton.text.toString()
                when (selectedAnimal) {
                    "코끼리" -> {
                        radioButtonName.text = selectedAnimal
                        type.text = "동물 종류 : $selectedAnimal"
                        food.text = "먹이 : 나뭇잎"
                        skill.text = "코의 길이 : "
                    }

                    "고양이" -> {
                        radioButtonName.text = selectedAnimal
                        type.text = "동물 종류 : $selectedAnimal"
                        food.text = "먹이 : 고양이 사료"
                        skill.text = "냥편치 속도 : "
                    }

                    "강아지" -> {
                        radioButtonName.text = selectedAnimal
                        type.text = "동물 종류 : $selectedAnimal"
                        food.text = "먹이 : 강아지 사료"
                        skill.text = "개인기 갯수 : "
                    }

                    else -> {
                        radioButtonName.text = ""
                        type.text = ""
                        food.text = ""
                        skill.text = ""
                    }
                }
            }

            button.setOnClickListener {
                val selectedAnimal = zooGroup.checkedRadioButtonId
                val radioButton = when (selectedAnimal) {
                    R.id.elephantButton -> "코끼리"
                    R.id.catButton -> "고양이"
                    R.id.dogButton -> "강아지"
                    else -> ""
                }

                val food = when (selectedAnimal) {
                    R.id.elephantButton -> "나뭇잎"
                    R.id.catButton -> "고양이 사료"
                    R.id.dogButton -> "강아지 사료"
                    else -> ""
                }

                val name = nameInfo.text.toString()
                val skill = skillInfo.text.toString()

                val animal = Animal(radioButton, food, name, skill)

                when (selectedAnimal) {
                    R.id.elephantButton -> elephantList.add(animal)
                    R.id.catButton -> catList.add(animal)
                    R.id.dogButton -> dogList.add(animal)
                }

                val elephantCount = elephantList.size
                val catCount = catList.size
                val dogCount = dogList.size

                animalsTextView.text = "코끼리: $elephantCount\n고양이: $catCount\n강아지: $dogCount\n"
                val stringBuilder = StringBuilder()


                        for (i in elephantList) {
                            var type = i.type
                            var food = i.food
                            var name = i.name
                            var skill = i.skill

                            stringBuilder.append("동물 타입 : $type\n")
                            stringBuilder.append("먹이 : $food\n")
                            stringBuilder.append("이름 : $name\n")
                            stringBuilder.append("코의 길이 : $skill\n")

                            stringBuilder.append("\n")
                        }



                        for (i in catList) {
                            var type = i.type
                            var food = i.food
                            var name = i.name
                            var skill = i.skill

                            stringBuilder.append("동물 타입 : $type\n")
                            stringBuilder.append("먹이 : $food\n")
                            stringBuilder.append("이름 : $name\n")
                            stringBuilder.append("냥편치 속도 : ${skill}km\n")

                            stringBuilder.append("\n")
                        }




                        for (i in dogList) {
                            var type = i.type
                            var food = i.food
                            var name = i.name
                            var skill = i.skill

                            stringBuilder.append("동물 타입 : $type\n")
                            stringBuilder.append("먹이 : $food\n")
                            stringBuilder.append("이름 : $name\n")
                            stringBuilder.append("개인기 갯수 : ${skill}km\n")

                            stringBuilder.append("\n")
                        }





                infoTextView.text = stringBuilder.toString()

                nameInfo.setText("")
                skillInfo.setText("")
            }
        }
    }
}

data class Animal(var type: String, var food: String, var name: String, var skill: String)