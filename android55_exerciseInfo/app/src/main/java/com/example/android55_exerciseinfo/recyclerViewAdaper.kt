package com.example.android55_exerciseinfo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.android55_exerciseinfo.DataClass.Companion.exerciseList
import com.example.android55_exerciseinfo.databinding.ItemInfoBinding


class recyclerViewAdaper(private val mainActivity: MainActivity, private val types: String) :
    RecyclerView.Adapter<recyclerViewAdaper.ViewHolderClass>() {
    inner class ViewHolderClass(itemBinding: ItemInfoBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        var type: TextView = itemBinding.type
        var name: TextView = itemBinding.name
        var viewLine: View = itemBinding.viewLine

        init {
            itemBinding.root.setOnClickListener {
                mainActivity.itemPosition = adapterPosition
                mainActivity.replaceFragment(FragmentName.FRAGMENT_SHOW, true, true)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val binding = ItemInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val params = RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.MATCH_PARENT,
            RecyclerView.LayoutParams.WRAP_CONTENT
        )
        binding.root.layoutParams = params
        return ViewHolderClass(binding)
    }

    override fun getItemCount() = exerciseList.size

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val dataClass = exerciseList[position]



        when (types) {
            "전체보기" -> {
                // 부서 is 연산자로 객체의 타입을 확인한다.
                val type = when (dataClass) {
                    is BaseballClass -> {
                        "야구부"
                    }

                    is SoccerClass -> {
                        "축구부"
                    }

                    is SwimmingClass -> {
                        "수영부"
                    }

                    else -> null
                }

                // 이름
                val name = when (dataClass) {

                    is BaseballClass -> dataClass.name
                    is SoccerClass -> dataClass.name
                    is SwimmingClass -> dataClass.name
                    else -> null
                }

                holder.type.append(type)
                holder.name.append(name)
            }

            "야구부" -> {
                // 야구부 데이터 클래스인 경우에만 처리
                if (dataClass is BaseballClass) {
                    // 부서
                    val type = "야구부"

                    // 이름
                    val name = dataClass.name

                    holder.type.append(type)
                    holder.name.append(name)
                } else {
                    holder.type.visibility = View.GONE
                    holder.name.visibility = View.GONE
                    holder.viewLine.visibility = View.GONE
                }
            }

            "축구부" -> {
                if (dataClass is SoccerClass) {
                    // 부서
                    val type = "축구부"

                    // 이름
                    val name = dataClass.name

                    holder.type.append(type)
                    holder.name.append(name)
                } else {
                    holder.type.visibility = View.GONE
                    holder.name.visibility = View.GONE
                    holder.viewLine.visibility = View.GONE
                }
            }

            "수영부" -> {
                // 야구부 데이터 클래스인 경우에만 처리
                if (dataClass is SwimmingClass) {
                    // 부서
                    val type = "수영부"

                    // 이름
                    val name = dataClass.name

                    holder.type.append(type)
                    holder.name.append(name)
                } else {
                    holder.type.visibility = View.GONE
                    holder.name.visibility = View.GONE
                    holder.viewLine.visibility = View.GONE

                }
            }

            else -> {

            }
        }

    }
}