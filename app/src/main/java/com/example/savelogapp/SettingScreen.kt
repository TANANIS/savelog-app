package com.example.savelogapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.KeyboardType




@Composable
fun SettingScreen(viewModel: SettingViewModel, onBack: () -> Unit) {
    val setting by viewModel.setting.collectAsState()

    var dailyBudgetInput by remember { mutableStateOf(setting?.dailyBudget?.toString() ?: "") }
    var monthlyTargetInput by remember { mutableStateOf(setting?.monthlyTarget?.toString() ?: "") }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Text("每日預算", style = MaterialTheme.typography.titleMedium)
        TextField(
            value = dailyBudgetInput,
            onValueChange = { dailyBudgetInput = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("月儲蓄目標", style = MaterialTheme.typography.titleMedium)
        TextField(
            value = monthlyTargetInput,
            onValueChange = { monthlyTargetInput = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = {
            val daily = dailyBudgetInput.toIntOrNull() ?: 0
            val monthly = monthlyTargetInput.toIntOrNull() ?: 0
            viewModel.saveSetting(daily, monthly)
            onBack()
        }) {
            Text("儲存設定")
        }
    }
}
