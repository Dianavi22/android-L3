package com.example.scalim.food

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.scalim.Food
import com.example.scalim.IPaperWrapper
import com.example.scalim.PaperWrapper
import io.paperdb.Paper

class FoodListViewModel : ViewModel() {
    var paper : IPaperWrapper = PaperWrapper()
    private val foodsLiveData = MutableLiveData<List<Food>>()
    fun getFoodLiveData(): LiveData<List<Food>> = foodsLiveData

    private var foods: List<Food> = paper.read("foods")

    sealed class AddState{
        object Success : AddState()
        data class Failure(val errorMessage: String): AddState()
    }

    fun loadFood() {
        foodsLiveData.value = foods
    }

    val stateLiveData = MutableLiveData<AddState>()

    fun addFood(food: Food, context: MainActivity) {
        checkIfAlreadyInLIst(food)
    }

    fun deleteFood(id: Food, context: MainActivity) {
        foods -= id
        paper.write("foods", foods)
    }

    fun checkIfAlreadyInLIst(food: Food) {
        if (foods.contains(food)) {
            stateLiveData.value = AddState.Failure("Nope")

        } else {
            stateLiveData.value = AddState.Success
            foods += food
            paper.write("foods", foods)
        }
    }
}