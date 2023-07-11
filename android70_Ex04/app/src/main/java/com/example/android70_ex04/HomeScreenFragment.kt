package com.example.android70_ex04

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.android70_ex04.databinding.ActivityMainBinding
import com.example.android70_ex04.databinding.FragmentHomeScreanBinding
import com.example.android70_ex04.databinding.FragmentLoginBinding
import com.example.android70_ex04.databinding.NavHeaderMainBinding
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar


class HomeScreenFragment : Fragment() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var fragmentHomeScreenBinding: FragmentHomeScreanBinding
    private lateinit var mainActivity: MainActivity


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentHomeScreenBinding = FragmentHomeScreanBinding.inflate(inflater, container, false)
        mainActivity = activity as MainActivity

        fragmentHomeScreenBinding.run {
            appBarMain.run {
                toolbar.run {
                    title="맥주"
                    inflateMenu(R.menu.fragment_home_screen_drawer)
                    setTitleTextColor(Color.WHITE)
                }
            }
        }

        return fragmentHomeScreenBinding.root
    }
}

