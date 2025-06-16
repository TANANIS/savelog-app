package com.example.savelogapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import com.example.savelogapp.ui.theme.SavelogAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SavelogAppTheme {
                // ✅ 初始化 ViewModel
                val expenseViewModel: ExpenseViewModel = viewModel(factory = object : ViewModelProvider.Factory {
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return ExpenseViewModel(application) as T
                    }
                })

                val settingViewModel: SettingViewModel = viewModel()

                // ✅ Navigation Controller
                val navController = rememberNavController()

                // ✅ 畫面導航
                NavHost(navController = navController, startDestination = "main") {
                    composable("main") {
                        MainScreen(
                            onAddClick = { navController.navigate("add") },
                            onSettingClick = { navController.navigate("settings") },
                            viewModel = expenseViewModel,
                            settingViewModel = settingViewModel
                        )
                    }

                    composable("add") {
                        AddExpenseScreen(
                            onBack = { navController.popBackStack() },
                            viewModel = expenseViewModel
                        )
                    }

                    composable("settings") {
                        SettingScreen(
                            viewModel = settingViewModel,
                            onBack = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}
