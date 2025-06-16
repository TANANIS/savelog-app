package com.example.savelogapp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.text.NumberFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onAddClick: () -> Unit,
    onSettingClick: () -> Unit,
    viewModel: ExpenseViewModel,
    settingViewModel: SettingViewModel
) {
    val expenses by viewModel.todayExpenses.collectAsState()
    val totalSpent = expenses.sumOf { it.amount }

    val setting by settingViewModel.setting.collectAsState()
    val dailyBudget = setting?.dailyBudget ?: 0
    val monthlyTarget = setting?.monthlyTarget ?: 0
    val remainingToday = dailyBudget - totalSpent

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("存兇記帳") },
                actions = {
                    IconButton(onClick = onSettingClick) {
                        Icon(Icons.Default.Settings, contentDescription = "設定")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddClick) {
                Icon(Icons.Default.Add, contentDescription = "新增")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text("本月儲蓄目標：NT$${monthlyTarget}", style = MaterialTheme.typography.titleLarge)
            Text("今日可花額度：NT$${remainingToday}", style = MaterialTheme.typography.titleMedium)

            Spacer(modifier = Modifier.height(16.dp))

            Text("今日總支出：NT$${totalSpent}", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))

            expenses.forEach {
                Text("${it.category}｜${it.item} ${formatCurrency(it.amount)}")
            }
        }
    }
}

fun formatCurrency(amount: Int): String {
    val format = NumberFormat.getCurrencyInstance(Locale.TAIWAN)
    format.maximumFractionDigits = 0
    return format.format(amount)
}
