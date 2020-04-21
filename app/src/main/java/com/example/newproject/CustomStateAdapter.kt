package com.example.newproject

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class CustomStateAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle,
                         private val listOfFragments: ArrayList<Fragment>)
    : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun createFragment(position: Int): Fragment {
        return listOfFragments[position]
    }

    override fun getItemCount(): Int {
        return listOfFragments.size
    }
}