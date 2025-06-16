package com.example.savelogapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Expense(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val category: String,
    val item: String,
    val amount: Int,
    val date: String // 存成 "2025-06-13"
)
