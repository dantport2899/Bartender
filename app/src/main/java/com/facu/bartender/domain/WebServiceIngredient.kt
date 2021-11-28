package com.facu.bartender.domain

import com.facu.bartender.data.model.coctelesList
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServiceIngredient {
    @GET("lookup.php")
    suspend fun obtenerByName(@Query("i") BebidaName: String): coctelesList
}