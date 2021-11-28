package com.facu.bartender.vo

import com.facu.bartender.domain.WebServiceIngredient
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientIngredient {

    val WebServiceIngredient by lazy {

        Retrofit.Builder()
            .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(WebServiceIngredient::class.java)
    }
}