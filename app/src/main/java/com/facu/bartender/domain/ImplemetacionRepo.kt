package com.facu.bartender.domain

import com.facu.bartender.data.DataSource
import com.facu.bartender.data.model.Drink
import com.facu.bartender.vo.Resource

class ImplemetacionRepo(private val dataSource: DataSource): Repo {
    suspend override fun ObtenListaDeTragos(BebidaName:String): Resource<List<Drink>> {
        return dataSource.getBebibiaByName(BebidaName)
    }
}