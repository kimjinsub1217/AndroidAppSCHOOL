package com.example.mini02_boardproject01

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mini02_boardproject01.databinding.FragmentPostListBinding


class PostListFragment : Fragment() {

    lateinit var fragmentPostListBinding: FragmentPostListBinding
    lateinit var homeFragment: HomeFragment

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PostAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentPostListBinding = FragmentPostListBinding.inflate(inflater)
        homeFragment = requireParentFragment() as HomeFragment

        fragmentPostListBinding.run {
            recyclerView.layoutManager= LinearLayoutManager(requireContext())
            val postList = listOf("테스트1", "테스트2", "테스트3")
            adapter = PostAdapter(postList)
            recyclerView.adapter = adapter

            homeFragment.homeToolbar.visibility=View.GONE
        }

        return fragmentPostListBinding.root
    }


}