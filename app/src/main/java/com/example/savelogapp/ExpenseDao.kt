package com.example.savelogapp

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {
    @Insert
    suspend fun insertExpense(expense: Expense)

    @Query("SELECT * FROM Expense WHERE date = :today")
    fun getExpensesByDate(today: String): Flow<List<Expense>>
}
