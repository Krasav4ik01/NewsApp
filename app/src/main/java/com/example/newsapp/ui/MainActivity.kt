package com.example.newsapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.newsapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.newsapp.databinding.ActivityMainBinding
import com.example.newsapp.ui.db.ArticleDatabase
import com.example.newsapp.ui.fragments.ArticleFragment
import com.example.newsapp.ui.fragments.BreakingNewsFragment
import com.example.newsapp.ui.fragments.SavedNewsFragment
import com.example.newsapp.ui.fragments.SearchNewsFragment
import com.example.newsapp.ui.repository.NewsRepository


class MainActivity : AppCompatActivity() {
    lateinit var bottomNavigationView: BottomNavigationView
    lateinit var newsNavHostFragment: NavHostFragment
    private lateinit var binding: ActivityMainBinding
    lateinit var newsViewModel: NewsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(BreakingNewsFragment())

        val repository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(repository)
        newsViewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)

        binding.bottomNavigationView.setOnItemSelectedListener{

            when(it.itemId){
                R.id.breakingNewsFragment -> replaceFragment(BreakingNewsFragment())
                R.id.savedNewsFragment -> replaceFragment(SavedNewsFragment())
                R.id.searchNewsFragment -> replaceFragment(SearchNewsFragment())
                else ->{

                }
            }
            true

        }

    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayoutFragment, fragment)
        fragmentTransaction.commit()
    }
}