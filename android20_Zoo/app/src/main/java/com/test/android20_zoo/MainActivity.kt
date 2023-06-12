package com.test.android20_zoo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.RadioButton
import com.test.android20_zoo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val animal = mutableListOf<Animal>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.run {
            binding.zooGroup.setOnCheckedChangeListener { group, checkedId ->
                val selectedRadioButton = findViewById<RadioButton>(checkedId)
                val selectedAnimal = selectedRadioButton.text.toString()

                when (selectedAnimal) {
                    "코끼리" -> {
                        elephant()

                    }

                    "고양이" -> {

                        var radioButton = "고양이"
                        var catFood = "고양이 사료"
                        radioButtonName.text = radioButton
                        type.text = "동물 종류 : $radioButton"
                        food.text = "먹이 : $catFood"
                        var catName = name.text.toString()
                        skill.text = "냥편치 속도 : "
                        var nyangpyeonchi = skillInfo.text.toString()

                        var cat = Animal(radioButton, catFood, catName, nyangpyeonchi)
                        animal.add(cat)
                    }

                    "강아지" -> {
                        var radioButton = "강아지"
                        var dogFood = "강아지 사료"
                        radioButtonName.text = radioButton
                        type.text = "동물 종류 : $radioButton"
                        food.text = "먹이 : $dogFood"
                        var dogName = name.text.toString()
                        skill.text = "개인기 갯수 : "
                        var skills = skillInfo.text.toString()

                        var dog = Animal(radioButton, dogFood, dogName, skills)
                        animal.add(dog)
                    }

                    else -> "알 수 없음"
                }
            }


            button.setOnClickListener {
                var elephantCount = 0
                var catCount = 0
                var dogCount = 0
                elephant()


                for (i in animal) {
                    if (i.type == "코끼리") {
                        elephantCount++
                    }
                    if (i.type == "고양이"){
                        catCount++
                    }
                    if(i.type =="강아지"){
                        dogCount++
                    }
                }
                animalsTextView.text="코끼리 : $elephantCount\n 고양이 : $catCount\n 강아지 : $dogCount"
            }
        }
    }

    private fun ActivityMainBinding.elephant() {
        var radioButton = "코끼리"
        var elephantFood = "나뭇잎"
        radioButtonName.text = radioButton
        type.text = "동물 종류 : $radioButton"
        food.text = "먹이 : $elephantFood"
        var elephantName = name.text.toString()
        skill.text = "코의길이 : "
        var noseLength = skillInfo.text.toString()

        var elephant = Animal(radioButton, elephantFood, elephantName, noseLength)
        animal.add(elephant)
    }
}


data class Animal(var type: String, var food: String, var name: String, var skill: String)

