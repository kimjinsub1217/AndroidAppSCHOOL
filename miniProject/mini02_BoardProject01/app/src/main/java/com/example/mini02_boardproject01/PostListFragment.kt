package com.example.mini02_boardproject01

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mini02_boardproject01.SearchList.Companion.ListSearch
import com.example.mini02_boardproject01.databinding.FragmentPostListBinding
import com.google.android.material.divider.MaterialDividerItemDecoration


class PostListFragment : Fragment() {

    lateinit var fragmentPostListBinding: FragmentPostListBinding
    lateinit var homeFragment: HomeFragment
    lateinit var mainActivity: MainActivity

    private lateinit var adapter: PostAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentPostListBinding = FragmentPostListBinding.inflate(inflater)
        homeFragment = requireParentFragment() as HomeFragment
        mainActivity = activity as MainActivity

        fragmentPostListBinding.run {
            searchBar.run {
                hint = "검색어를 입력하세요"
                inflateMenu(R.menu.search_bar_menu)

                setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.writing -> {
                            mainActivity.replaceFragment(MainActivity.POST_WRITE_FRAGMENT,
                                addToBackStack = true,
                                bundle = null
                            )
                        }
                    }
                    false
                }
            }


            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            val search1 = SearchListClass(0, "콜라","맛있다.")
            val search2 = SearchListClass(1, "사이다","시원하다.")
            ListSearch.add(search1)
            ListSearch.add(search2)
            adapter = PostAdapter(mainActivity)
            recyclerView.adapter = adapter
            recyclerView.addItemDecoration(MaterialDividerItemDecoration(requireContext(), MaterialDividerItemDecoration.VERTICAL))

            recyclerViewPostListResult.run {
                adapter = ResultAdapter(mainActivity)
                layoutManager = LinearLayoutManager(context)
                addItemDecoration(MaterialDividerItemDecoration(requireContext(), MaterialDividerItemDecoration.VERTICAL))
            }

        }
        return fragmentPostListBinding.root
    }
}