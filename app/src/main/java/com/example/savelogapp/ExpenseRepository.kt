package com.example.savelogapp

import kotlinx.coroutines.flow.Flow

class ExpenseRepository(private val dao: ExpenseDao) {
    suspend fun insertExpense(expense: Expense) = dao.insertExpense(expense)

    fun getTodayExpenses(today: String): Flow<List<Expense>> =
        dao.getExpensesByDate(today)
}
