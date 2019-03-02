package com.twobrothers.overcooked.views.recipe

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.load.engine.DiskCacheStrategy

import com.twobrothers.overcooked.R
import com.twobrothers.overcooked.interfaces.IRecipeContract
import com.twobrothers.overcooked.models.recipe.RecipeModel
import com.twobrothers.overcooked.presenters.RecipePresenter
import com.twobrothers.overcooked.utils.GlideApp
import com.twobrothers.overcooked.views.recipelist.RecipeListViewAdapter
import kotlinx.android.synthetic.main.fragment_recipe.*

class RecipeFragment : Fragment(), IRecipeContract.View {

    private val presenter: IRecipeContract.Presenter = RecipePresenter(this)
    private lateinit var methodViewAdapter: RecyclerView.Adapter<*>
    private lateinit var methodViewManager: RecyclerView.LayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_recipe, container, false)

        methodViewManager = LinearLayoutManager(context)
        methodViewAdapter = MethodViewAdapter(presenter)

        view!!.findViewById<RecyclerView>(R.id.recycler_method).apply {
            layoutManager = methodViewManager
            adapter = methodViewAdapter
        }

        return view
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    override fun render(recipe: RecipeModel) {
        if (isDetached) {
            return
        }
        text_title.text = recipe.title
        GlideApp.with(view!!.context)
                .load(recipe.imageUrl)
                .placeholder(R.drawable.placeholder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .into(image_hero)
    }

    override fun onMethodDataSetChanged() {
        methodViewAdapter.notifyDataSetChanged()
    }

}