package com.example.savelogapp

import android.app.Application
import androidx.lifecycle.*
import androidx.room.Room
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDate

class ExpenseViewModel(application: Application) : AndroidViewModel(application) {

    private val db = Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        "expense-db"
    ).build()

    private val repo = ExpenseRepository(db.expenseDao())

    private val _todayExpenses = MutableStateFlow<List<Expense>>(emptyList())
    val todayExpenses: StateFlow<List<Expense>> = _todayExpenses.asStateFlow()

    init {
        loadToday()
    }

    private fun loadToday() {
        val today = LocalDate.now().toString()
        viewModelScope.launch {
            repo.getTodayExpenses(today).collect {
                _todayExpenses.value = it
            }
        }
    }

    fun addExpense(category: String, item: String, amount: Int) {
        val today = LocalDate.now().toString()
        viewModelScope.launch {
            repo.insertExpense(Expense(category = category, item = item, amount = amount, date = today))
        }
    }
}
