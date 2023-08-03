package com.example.mini02_boardproject01

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.mini02_boardproject01.databinding.FragmentPostReadBinding
import com.example.mini02_boardproject01.vm.PostViewModel


class PostReadFragment : Fragment() {

    lateinit var fragmentPostReadBinding: FragmentPostReadBinding
    lateinit var mainActivity: MainActivity

    lateinit var postViewModel: PostViewModel

    // 게시글 인덱스 번호를 받는다.
    var readPostIdx = 0L
    var userIdx = 0L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentPostReadBinding = FragmentPostReadBinding.inflate(inflater)
        mainActivity = activity as MainActivity

        postViewModel = ViewModelProvider(mainActivity)[PostViewModel::class.java]
        postViewModel.run {
            postSubject.observe(mainActivity) {
                fragmentPostReadBinding.textInputEditTextPostReadTitle.setText(it)
            }
            postText.observe(mainActivity) {
                fragmentPostReadBinding.textInputEditTextPostReadDetail.setText(it)
            }
            postWriteDate.observe(mainActivity) {
                fragmentPostReadBinding.textInputEditTextPostReadDate.setText(it)
            }
            postNickname.observe(mainActivity){
                fragmentPostReadBinding.textInputEditTextPostReadNickName.setText(it)
            }
            postFileName.observe(mainActivity){
                if(it == "None"){
                    fragmentPostReadBinding.imageViewPostRead.visibility = View.GONE
                }
            }
            postImage.observe(mainActivity){
                fragmentPostReadBinding.imageViewPostRead.visibility = View.VISIBLE
                fragmentPostReadBinding.imageViewPostRead.setImageBitmap(it)
            }
        }


        fragmentPostReadBinding.run {
            toolbarPostRead.run {
                title = "글읽기"
                inflateMenu(R.menu.post_read_menu)
                setNavigationIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material)
                setNavigationOnClickListener {
                    mainActivity.removeFragment(MainActivity.POST_READ_FRAGMENT)
                }

                setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.contractEdit -> {
//                            if(textInputEditTextPostReadTitle.isEnabled == false) {
//                                textInputEditTextPostReadTitle.isEnabled = true
//                                textInputEditTextPostReadDetail.isEnabled = true
//                            } else {
//                                textInputEditTextPostReadTitle.isEnabled = false
//                                textInputEditTextPostReadDetail.isEnabled = false
//                            }

                            mainActivity.replaceFragment(
                                MainActivity.POST_MODIFY_FRAGMENT,
                                true,
                                null
                            )
                        }

                        R.id.delete -> {

                        }
                    }
                    false
                }

            }
        }

//        textInputEditTextPostReadSubject.run{
//            setTextColor(Color.BLACK)
//        }
//
//        textInputEditTextPostReadText.run{
//            setTextColor(Color.BLACK)
//        }
//
//        textInputEditTextPostReadNickname.run{
//            setTextColor(Color.BLACK)
//        }
//
//        textInputEditTextPostReadWriteDate.run{
//            setTextColor(Color.BLACK)
//        }

        // 게시글 인덱스 번호를 받는다.
        readPostIdx = arguments?.getLong("readPostIdx")!!
        userIdx = arguments?.getLong("userIdx")!!
        // 게시글 정보를 가져온다.
        postViewModel.setPostReadData(readPostIdx.toDouble())
//        postViewModel.setUserPostReadData(userIdx.toDouble())

        return fragmentPostReadBinding.root
    }

}