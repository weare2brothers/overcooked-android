package com.twobrothers.overcooked.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.twobrothers.overcooked.R
import com.twobrothers.overcooked.app.Navigation
import com.twobrothers.overcooked.views.recipe.RecipeFragment
import com.twobrothers.overcooked.views.recipelist.RecipeListFragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy

class MainFragment : Fragment() {

    private val disposable = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, state: Bundle?): View? {
        fragmentManager
                ?.beginTransaction()
                ?.replace(R.id.layout_main, RecipeListFragment())
                ?.addToBackStack(null)
                ?.commitAllowingStateLoss()

        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onStart() {
        super.onStart()
        disposable.add(
                Navigation
                        .getInstance()
                        .subscribeBy(
                                onNext = {
                                    var newView:Fragment = RecipeListFragment()
                                    if (it.tag == "RECIPE_VIEW") {
                                        newView = RecipeFragment()
                                    }
                                    newView.arguments = it.args
                                    fragmentManager
                                            ?.beginTransaction()
                                            ?.replace(R.id.layout_main, newView)
                                            ?.addToBackStack(null)
                                            ?.commitAllowingStateLoss()
                                }
                        )
        )
    }

    override fun onStop() {
        super.onStop()
        disposable.clear()
    }
}
