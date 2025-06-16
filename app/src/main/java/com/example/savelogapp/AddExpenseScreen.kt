package com.example.savelogapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun AddExpenseScreen(
    onBack: () -> Unit,
    viewModel: ExpenseViewModel
) {
    var itemName by remember { mutableStateOf("") }
    var amountText by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("早餐") }

    val categoryOptions = listOf("早餐", "午餐", "晚餐", "娛樂", "雜費")

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Text("新增支出", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(24.dp))

        // 品項名稱
        OutlinedTextField(
            value = itemName,
            onValueChange = { itemName = it },
            label = { Text("品項名稱") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 金額
        OutlinedTextField(
            value = amountText,
            onValueChange = { amountText = it },
            label = { Text("金額") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 分類選單
        Text("分類：")
        Row(modifier = Modifier.fillMaxWidth()) {
            categoryOptions.forEach { option ->
                Row(
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    RadioButton(
                        selected = category == option,
                        onClick = { category = option }
                    )
                    Text(option)
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // 儲存按鈕
        Button(
            onClick = {
                if (itemName.isNotBlank() && amountText.isNotBlank()) {
                    viewModel.addExpense(
                        category = category,
                        item = itemName,
                        amount = amountText.toIntOrNull() ?: 0
                    )
                    onBack() // 儲存後返回主畫面
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("儲存")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("取消")
        }
    }
}
