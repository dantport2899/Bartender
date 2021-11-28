package com.facu.bartender.domain

import com.facu.bartender.data.model.coctelesList
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {


    @GET("search.php")
    suspend fun obtenerByName(@Query("s") BebidaName: String): coctelesList

}