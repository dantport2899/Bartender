package com.facu.bartender.domain

import com.facu.bartender.data.model.Drink
import com.facu.bartender.vo.Resource

interface RepoRandom {
    suspend fun ObtenListaDeTragosRandom(BebidaName:String): Resource<List<Drink>>
}