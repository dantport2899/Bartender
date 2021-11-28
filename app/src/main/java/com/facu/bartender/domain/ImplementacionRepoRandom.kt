package com.facu.bartender.domain

import com.facu.bartender.data.DataSourceRandom
import com.facu.bartender.data.model.Drink
import com.facu.bartender.vo.Resource

class ImplementacionRepoRandom(private val dataSource: DataSourceRandom): RepoRandom {
    suspend override fun ObtenListaDeTragosRandom(BebidaName:String): Resource<List<Drink>> {
        return dataSource.getBebibiaByRandom(BebidaName)
    }
}