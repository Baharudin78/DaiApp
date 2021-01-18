package com.baharudin.daiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.baharudin.daiapp.adapter.ViewPagerAdapter
import com.baharudin.daiapp.databinding.ActivityDaiListBinding
import com.google.android.material.tabs.TabLayoutMediator

class DaiListAct : AppCompatActivity() {
    lateinit var binding : ActivityDaiListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDaiListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ViewPagerAdapter(supportFragmentManager,lifecycle)
        binding.viewPager.adapter = adapter
        TabLayoutMediator(binding.tabLayout,binding.viewPager){tab,position ->
            when(position){
                0 -> {
                    tab.text ="Daftar Dai"
                }
                1 -> {
                    tab.text = "Cari Dai"
                }
            }
        }.attach()
    }
}