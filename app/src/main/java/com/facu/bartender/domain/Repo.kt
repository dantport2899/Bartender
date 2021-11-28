package com.facu.bartender.domain

import com.facu.bartender.data.model.Drink
import com.facu.bartender.vo.Resource

interface Repo {
    suspend fun ObtenListaDeTragos(BebidaName:String):Resource<List<Drink>>
}