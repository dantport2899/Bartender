package com.facu.bartender.data

import com.facu.bartender.data.model.Drink
import com.facu.bartender.vo.Resource
import com.facu.bartender.vo.RetrofitClientRandom

class DataSourceRandom {
    suspend fun getBebibiaByRandom(coctelName: String): Resource<List<Drink>> {

        val resultRequest=RetrofitClientRandom.WebServiceRandom.obtenerByName(coctelName).coctelesList
        resultRequest?.let{
            return Resource.Success(it)
        }
        var e= Exception("No existe cocteles con ese nombre!!")
        return Resource.Failure(e)


    //return Resource.Success(RetrofitClientRandom.WebServiceRandom.obtenerByName(coctelName).coctelesList)
    }
}