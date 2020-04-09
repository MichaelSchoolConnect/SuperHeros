package com.lebogang.superheros.database

import androidx.room.*
import java.util.ArrayList
import androidx.lifecycle.LiveData



//Our DAO which contain our data queries.
@Dao
interface SuperHeroDAO {
    @Query("SELECT * FROM character_items")
    fun getAll(): List<SuperHeroEntity>

    @Query("SELECT * FROM character_items")
    fun loadAllTasks(): LiveData<List<SuperHeroEntity>>

    @Insert
    fun insertAll(vararg arrayOfSuperHeroEntitys: SuperHeroEntity)

    @Delete
    fun delete(superHeroEntity: SuperHeroEntity)

    @Update
    fun updateTodo(vararg arrayOfSuperHeroEntitys: SuperHeroEntity)

}
