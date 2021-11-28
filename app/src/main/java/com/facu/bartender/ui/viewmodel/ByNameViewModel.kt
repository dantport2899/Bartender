package com.facu.bartender.ui.viewmodel

import androidx.lifecycle.*
import com.facu.bartender.domain.Repo
import com.facu.bartender.vo.Resource
import kotlinx.coroutines.Dispatchers

class ByNameViewModel (private val repo:Repo):ViewModel(){

    private var bebidaData = MutableLiveData<String>()

    fun setTrago(tragoName: String){

        bebidaData.value=tragoName
    }

    init{
        setTrago("margarita")
    }

    val fetchTragosList = bebidaData.distinctUntilChanged() .switchMap {
        liveData(Dispatchers.IO){
            emit(Resource.Loading())
            try{
                emit(repo.ObtenListaDeTragos(it))
            }catch (e:Exception){
                emit(Resource.Failure(e))
            }
        }
    }
}