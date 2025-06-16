
package com.example.savelogapp

class SettingRepository(private val dao: SettingDao) {
    suspend fun saveSetting(setting: Setting) = dao.saveSetting(setting)
    fun getSetting() = dao.getSetting()
}
