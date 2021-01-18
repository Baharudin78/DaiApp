package com.baharudin.daiapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.baharudin.daiapp.fragment.tab.SearchFragment

class ViewPagerAdapter(fragmentManager : FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager,lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> {
                com.baharudin.daiapp.fragment.tab.ListFragment()
            }else -> {
                SearchFragment()
            }
        }
    }
}