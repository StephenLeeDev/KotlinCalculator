package com.example.kotlincalculator.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HistoryModel(
        @PrimaryKey
        val uid: Int?,
        @ColumnInfo(name = "expression")
        val expression: String?,
        @ColumnInfo(name = "result")
        val result: String?
)