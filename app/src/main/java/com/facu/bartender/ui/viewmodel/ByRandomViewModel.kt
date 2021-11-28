package com.facu.bartender.ui.viewmodel

import androidx.lifecycle.*
import com.facu.bartender.domain.RepoRandom
import com.facu.bartender.vo.Resource
import kotlinx.coroutines.Dispatchers

class ByRandomViewModel (private val repo: RepoRandom): ViewModel(){

    private var RandomData = MutableLiveData<String>()

    fun setTragoRandom(tragoRandom: String){

        RandomData.value=tragoRandom
    }

    init{
        setTragoRandom("")
    }

    val fetchRandomList = RandomData.distinctUntilChanged() .switchMap {
        liveData(Dispatchers.IO){
            emit(Resource.Loading())
            try{
                emit(repo.ObtenListaDeTragosRandom(it))
            }catch (e:Exception){
                emit(Resource.Failure(e))
            }
        }
    }
}