package com.example.android70_ex03.adapter


import android.content.DialogInterface
import android.os.SystemClock
import android.util.Log
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.android70_ex03.db.DAOCategory
import com.example.android70_ex03.MainActivity
import com.example.android70_ex03.R
import com.example.android70_ex03.databinding.DialogBinding
import com.example.android70_ex03.databinding.RowCategoryBinding
import com.example.android70_ex03.db.Category.Companion.categoryList
import com.example.android70_ex03.db.DAOMemo
import com.example.android70_ex03.db.Memo.Companion.memoList
import kotlin.concurrent.thread

class CategoryAdapter(private val mainActivity: MainActivity) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolderClass>() {
    inner class CategoryViewHolderClass(rowCategoryBinding: RowCategoryBinding) :
        RecyclerView.ViewHolder(rowCategoryBinding.root), View.OnCreateContextMenuListener {
        var categoryName: TextView = rowCategoryBinding.categoryName

        init {
            itemView.setOnCreateContextMenuListener(this)
            rowCategoryBinding.root.setOnClickListener {
                mainActivity.categoryPosition = adapterPosition
                mainActivity.replaceFragment(MainActivity.MEMO_LIST_FRAGMENT, true, true)
            }
        }

        override fun onCreateContextMenu(
            menu: ContextMenu?,
            v: View?,
            menuInfo: ContextMenu.ContextMenuInfo?
        ) {
            menu?.setHeaderTitle(categoryList[adapterPosition].titleData)
            mainActivity.menuInflater.inflate(R.menu.category_list_context_menu, menu)

            menu?.findItem(R.id.category_list_context_modify_button)?.setOnMenuItemClickListener {

                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val dialogBinding = DialogBinding.inflate(mainActivity.layoutInflater)

                    val builder = AlertDialog.Builder(mainActivity)
                    builder.setTitle("카테고리 이름 수정")
                    builder.setIcon(R.mipmap.ic_launcher)

                    builder.setView(dialogBinding.root)

                    thread {
                        SystemClock.sleep(500)
                        val imm =
                            mainActivity.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.showSoftInput(dialogBinding.editTextDialog, 0)
                    }

                    builder.setPositiveButton("확인") { dialogInterface: DialogInterface, i: Int ->
                        val str1 = dialogBinding.editTextDialog.text.toString()
                        val obj = DAOCategory.selectData(mainActivity, position + 1)

                        categoryList[position].titleData = str1
                        obj!!.titleData = str1

                        DAOCategory.updateData(mainActivity, obj)

                        notifyItemChanged(position)
                    }
                    builder.setNegativeButton("취소", null)
                    builder.show()
                }
                true
            }


            menu?.findItem(R.id.category_list_context_delete_button)?.setOnMenuItemClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {

                    // 메모
                    val removedMemos = memoList.filter { it.categoryData == position + 1 }
                    memoList.removeAll(removedMemos)

                    for (removedMemo in removedMemos) {
                        DAOMemo.deleteData(mainActivity, removedMemo.idx)
                    }

                    for (i in position until memoList.size) {
                        val memo = memoList[i]
                        memo.categoryData = memo.categoryData - 1
                        DAOMemo.updateData(mainActivity, memo)
                    }

                    memoList = DAOMemo.selectAllData(mainActivity)

                    val obj = DAOMemo.selectAllData(mainActivity)
                    Log.i("오늘도 확인1", memoList.toString())
                    Log.i("오늘도 확인2", obj.toString())

                    // 카테고리
                    val deletedItem = categoryList[position]
                    val deletedId = deletedItem.idx

                    categoryList.removeAt(position)
                    DAOCategory.deleteData(mainActivity, deletedId)

                    for (i in position until categoryList.size) {
                        categoryList[i].idx = categoryList[i].idx - 1
                    }

                    DAOCategory.deleteAllData(mainActivity)
                    for (item in categoryList) {
                        DAOCategory.insertData(mainActivity, item)
                    }
                    notifyItemRemoved(position)
                }
                true
            }

        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryAdapter.CategoryViewHolderClass {
        val rowCategoryBinding =
            RowCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val categoryViewHolderClass = CategoryViewHolderClass(rowCategoryBinding)

        rowCategoryBinding.root.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        return categoryViewHolderClass
    }

    override fun getItemCount() = categoryList.size

    override fun onBindViewHolder(holder: CategoryAdapter.CategoryViewHolderClass, position: Int) {
        val currentItem = categoryList[position]

        holder.categoryName.text = currentItem.titleData
    }
}