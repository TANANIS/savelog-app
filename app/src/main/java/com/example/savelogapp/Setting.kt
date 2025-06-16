
package com.example.savelogapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Setting(
    @PrimaryKey val id: Int = 1,
    val dailyBudget: Int,
    val monthlyTarget: Int
)
