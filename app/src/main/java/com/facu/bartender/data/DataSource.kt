package com.facu.bartender.data

import com.facu.bartender.data.model.Drink
import com.facu.bartender.vo.Resource
import com.facu.bartender.vo.RetrofitClient

class DataSource {

    suspend fun getBebibiaByName(coctelName: String): Resource<List<Drink>>{
        val resultRequest = RetrofitClient.webservice.obtenerByName(coctelName).coctelesList
        resultRequest?.let{
            return Resource.Success(it)
        }
        var e= Exception("No existe cocteles con ese nombre!!")
        return Resource.Failure(e)

        //return Resource.Success(RetrofitClient.webservice.obtenerByName(coctelName).coctelesList)
    }
}
/*
suspend fun getDrinksByName(drinkName: String): Resource<List<Drink>>{
        val resultRequest= RetrofitClient.retrofitService.getDrinksByName(drinkName).drinks
        resultRequest?.let{
            return Resource.Success(it)
        }
        var e= Exception("Result Not Found!!")
        return Resource.Failure(e)

    }
 */