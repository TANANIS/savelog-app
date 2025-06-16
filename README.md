
# 💰 記帳 App

一款從零打造的極簡 Android 記帳 App，強調視覺清晰、可擴充、長期使用，支援本地儲存、自動分類與每日支出統計。

---

## 📱 畫面預覽（v1.0）

### 🏠 MainScreen（首頁總覽）
- 顯示「本月儲蓄目標」「今日可花額度」「今日總支出」
- 自動列出今日所有支出記錄
- 根據每日預算與當日支出自動計算「可花額度」
- 點擊「＋新增」進入新增畫面
- 點擊「⚙ 設定」可修改預算與目標

### ➕ AddExpenseScreen（新增支出）
- 選擇分類（早餐／午餐／晚餐／娛樂／雜費）
- 輸入品項名稱與金額
- 儲存後自動寫入資料庫並返回首頁

### ⚙ SettingScreen（設定）
- 自訂每日預算與月儲蓄目標
- 修改後即時反映於主畫面

---

## 🧱 專案技術堆疊

| 層級 | 技術 |
|------|------|
| 語言 | Kotlin |
| UI 框架 | Jetpack Compose |
| 狀態管理 | ViewModel + StateFlow |
| 資料儲存 | Room Database |
| 畫面切換 | Navigation Compose |
| 最低支援 SDK | API 24（Android 7.0） |

---

## 📂 架構檔案與說明

### 📄 Expense.kt（資料 Entity）
```kotlin
@Entity
data class Expense(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val category: String,
    val item: String,
    val amount: Int,
    val date: String // YYYY-MM-DD
)
```

### 📄 Setting.kt（設定 Entity）
```kotlin
@Entity
data class Setting(
    @PrimaryKey val id: Int = 1,
    val dailyBudget: Int,
    val monthlyTarget: Int
)
```

### 📄 ExpenseDao.kt / SettingDao.kt（資料存取 DAO）
- 提供每日支出查詢、資料寫入與設定讀取功能

### 📄 AppDatabase.kt（Room Database）
- 整合 Expense 與 Setting 表
- 使用 `.fallbackToDestructiveMigration()` 管理升級

### 📄 ExpenseViewModel.kt / SettingViewModel.kt
- 封裝 UI 與資料互動邏輯，採用 StateFlow 即時更新

---

## 🔄 畫面流程圖

```text
MainActivity.kt
├─ MainScreen.kt（首頁）
│   └─ 顯示支出、跳轉新增與設定
├─ AddExpenseScreen.kt（新增）
│   └─ 輸入內容 → 儲存 → 回首頁
└─ SettingScreen.kt（設定）
    └─ 自訂每日預算與儲蓄 → 回首頁
```

---

## 🚀 開發進度與下一步

- ✅ 完成 v1.0（記帳、儲存、每日預算、自訂目標）
- 🔜 支援「歷史紀錄查詢」與日期選擇
- 🔜 支出類別統計圖（圓餅圖）
- 🔜 匯出功能與月結報表

---

開發者自製自用，專為長期記帳與節流設計 ✨
