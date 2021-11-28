package com.facu.bartender.ui.viewmodel

import androidx.lifecycle.*
import com.facu.bartender.domain.RepoIngredient
import com.facu.bartender.vo.Resource
import kotlinx.coroutines.Dispatchers

class ByIngredientViewModel (private val repo: RepoIngredient): ViewModel(){

    private var IngredientData = MutableLiveData<String>()

    fun setTragoIngredient(tragoIngredient: String){

        IngredientData.value=tragoIngredient
    }

    init{
        setTragoIngredient("11001")
    }

    val fetchIngredientList = IngredientData.distinctUntilChanged() .switchMap {
        liveData(Dispatchers.IO){
            emit(Resource.Loading())
            try{
                emit(repo.ObtenListaDeTragosIngredient(it))
            }catch (e:Exception){
                emit(Resource.Failure(e))
            }
        }
    }
}