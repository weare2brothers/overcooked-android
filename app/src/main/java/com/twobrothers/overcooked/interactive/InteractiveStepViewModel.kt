package com.twobrothers.overcooked.interactive

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.twobrothers.overcooked.recipedetails.models.Ingredient
import com.twobrothers.overcooked.recipedetails.models.InteractiveStep
import org.threeten.bp.Duration
import java.util.*

class InteractiveStepViewModel(step: InteractiveStep, serves: Int) : ViewModel() {

    //region state properties

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> = _title

    private val _body = MutableLiveData<String>()
    val body: LiveData<String> = _body

    private val _ingredients = MutableLiveData<Pair<List<Ingredient>?, Int>>()
    val ingredients: LiveData<Pair<List<Ingredient>?, Int>> = _ingredients

    private val _footnote = MutableLiveData<String>()
    val footnote: LiveData<String> = _footnote

    private val _timerDisplay = MutableLiveData<String>()
    val timerDisplay: LiveData<String> = _timerDisplay

    //endregion

    private var timer = Timer()
    private var handler = Handler()
    private var timerDurationMs: Duration = Duration.ZERO
    private var timeRemaining: Duration = Duration.ZERO

    init {
        _title.value = step.title
        _body.value = step.body
        _ingredients.value = Pair(step.ingredients, serves)
        _footnote.value = step.footnote

        if (step.timer != null) {
            timerDurationMs = Duration.ofMillis(5000) // step.timer
            timeRemaining = timerDurationMs
        }
    }

    // region private

    // endregion

    //region public

    fun startTimer() {
        timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                handler.post {
                    timeRemaining = timeRemaining.minusMillis(1000)
                }
            }
        }, 0, 1000)
    }

    //endregion

}