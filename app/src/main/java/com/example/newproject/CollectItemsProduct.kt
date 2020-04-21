package com.example.newproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment

class CollectItemsProduct(val function: () -> Unit) : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_collect_items_product,
            container, false)

        val hideViewButton = view.findViewById<ImageButton>(R.id.collectItemsSecondHideViewPager)

        hideViewButton.setOnClickListener {
            function()
        }

        return view
    }

}