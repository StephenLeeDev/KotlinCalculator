package com.example.kotlincalculator.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.kotlincalculator.Model.HistoryModel

@Dao
interface HistoryDAO {

    @Query("SELECT * FROM HistoryModel")
    fun getAll(): List<HistoryModel>

    @Insert
    fun insertHistory(historyModel: HistoryModel)

    @Query("DELETE FROM HistoryModel")
    fun deleteAll()

    @Delete
    fun delete(historyModel: HistoryModel)

    @Query("SELECT * FROM HistoryModel WHERE result LIKE :result LIMIT 1")
    fun findByResult(result: String): HistoryModel

}