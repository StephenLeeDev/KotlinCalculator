package com.example.kotlincalculator

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kotlincalculator.DAO.HistoryDAO
import com.example.kotlincalculator.Model.HistoryModel

@Database(entities = [HistoryModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun historyDao(): HistoryDAO
}