package com.example.mini02_boardproject01

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mini02_boardproject01.databinding.FragmentPostListBinding
import com.example.mini02_boardproject01.vm.ViewModelPostList
import com.google.android.material.divider.MaterialDividerItemDecoration


class PostListFragment : Fragment() {

    lateinit var fragmentPostListBinding: FragmentPostListBinding
    lateinit var homeFragment: HomeFragment
    lateinit var mainActivity: MainActivity

    private lateinit var viewModel: ViewModelPostList
    private lateinit var adapter: PostAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentPostListBinding = FragmentPostListBinding.inflate(inflater)
        homeFragment = requireParentFragment() as HomeFragment
        mainActivity = activity as MainActivity

        viewModel = ViewModelProvider(this)[ViewModelPostList::class.java]
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




            recyclerViewPostListResult.run {

                adapter = PostAdapter(viewModel)
                layoutManager = LinearLayoutManager(context)
                addItemDecoration(MaterialDividerItemDecoration(requireContext(), MaterialDividerItemDecoration.VERTICAL))
            }

            viewModel.run {
                dataList.observe(viewLifecycleOwner) {
                    recyclerViewPostListResult.adapter?.notifyDataSetChanged()
                }
            }

        }
        return fragmentPostListBinding.root
    }
    override fun onResume() {
        super.onResume()
        // ViewModel 에 있는 모든 데이터를 가져오는 메서드를 호출한다.
        viewModel.getAll()
    }
}

