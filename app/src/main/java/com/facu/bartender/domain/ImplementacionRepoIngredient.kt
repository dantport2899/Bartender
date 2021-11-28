package com.facu.bartender.domain

import com.facu.bartender.data.DataSourceIngredient
import com.facu.bartender.data.model.Drink
import com.facu.bartender.vo.Resource

class ImplementacionRepoIngredient(private val dataSource: DataSourceIngredient): RepoIngredient {
    suspend override fun ObtenListaDeTragosIngredient(BebidaName:String): Resource<List<Drink>> {
        return dataSource.getBebibiaByIngredient(BebidaName)
    }
}