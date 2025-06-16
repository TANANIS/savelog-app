
package com.example.savelogapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SettingViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = AppDatabase.getDatabase(application).settingDao()
    private val repository = SettingRepository(dao)

    private val _setting = MutableStateFlow<Setting?>(null)
    val setting: StateFlow<Setting?> = _setting

    init {
        viewModelScope.launch {
            repository.getSetting().collect { _setting.value = it }
        }
    }

    fun saveSetting(dailyBudget: Int, monthlyTarget: Int) {
        viewModelScope.launch {
            val newSetting = Setting(dailyBudget = dailyBudget, monthlyTarget = monthlyTarget)
            repository.saveSetting(newSetting)
        }
    }
}
