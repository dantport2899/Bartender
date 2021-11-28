package com.facu.bartender.domain

import com.facu.bartender.data.model.Drink
import com.facu.bartender.vo.Resource

interface RepoIngredient {
    suspend fun ObtenListaDeTragosIngredient(BebidaName:String): Resource<List<Drink>>
}