package com.facu.bartender.data

import com.facu.bartender.data.model.Drink
import com.facu.bartender.vo.Resource
import com.facu.bartender.vo.RetrofitClientIngredient

class DataSourceIngredient {

    suspend fun getBebibiaByIngredient(coctelName: String): Resource<List<Drink>> {
        val resultRequest = RetrofitClientIngredient.WebServiceIngredient.obtenerByName(coctelName).coctelesList
        resultRequest?.let{
            return Resource.Success(it)
        }
        var e= Exception("No existe cocteles con ese ID!!")
        return Resource.Failure(e)

        //return Resource.Success(RetrofitClientIngredient.WebServiceIngredient.obtenerByName(coctelName).coctelesList)
    }
}