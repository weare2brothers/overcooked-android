package com.twobrothers.overcooked.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.twobrothers.overcooked.R
import com.twobrothers.overcooked.views.recipelist.RecipeListFragment

class MainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, state: Bundle?): View? {
        fragmentManager!!
                .beginTransaction()
                .replace(R.id.layout_main, RecipeListFragment())
                .commitAllowingStateLoss()
        return inflater.inflate(R.layout.fragment_main, container, false)
    }
}
