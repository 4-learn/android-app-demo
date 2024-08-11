# Android APP demo projects

## 重建專案

#### 創建新專案:

1. **創建新專案**：
   - 在 Android Studio 中，選擇「Start a new Android Studio project」。
   - 為專案命名，選擇專案的儲存位置，並選擇一個空的 Activity 或其他合適的模板。
   - 記住你的 package name，可能用的到
   - 點擊「Finish」，讓 Android Studio 創建一個新的專案。

![image](https://github.com/user-attachments/assets/cdca0fec-b153-4d6d-abed-e0357841c711)

---

![image](https://github.com/user-attachments/assets/dd6d06a9-c63a-4b79-b3e4-fa33a491558b)


---

![image](https://github.com/user-attachments/assets/333d70f1-5ef4-46c1-93a4-5189c6e8f41a)



#### 載入專案:

1. **替換 `src` 資料夾**：
   - 在新的專案中，找到剛剛創建的專案資料夾（一般在你選擇的儲存位置下）。
   - 將原本的 `src` 資料夾複製並粘貼到新的專案中，替換掉新專案中的 `src` 資料夾。

2. **檢查和更新 `build.gradle` 文件**：
   - 檢查 `build.gradle` 文件，確保它包含你的專案需要的所有依賴庫和設定。
   - 如果 `src` 資料夾中的程式碼依賴某些庫或特定的 SDK，請確保在 `build.gradle` 文件中正確配置這些依賴。


![image](https://github.com/user-attachments/assets/706ae863-d026-4925-8658-60774734c009)

#### 同步專案:

1. **同步專案**：
   - 在 Android Studio 中，點擊「Sync Project with Gradle Files」以確保所有的依賴和設定都正確配置。
   - 如果遇到錯誤或缺少依賴，根據提示進行修正。

2. **編譯與運行**：
   - 成功同步後，嘗試編譯和運行專案。如果一切順利，你的專案應該能夠正常執行。
  
## Known Issue

#### error: package R does not exist:
- 在 Android Studio 抓到錯誤的地方，置換你的 package name
  - 範例： package my.project; -> package com.example.bmicaculator
- 重新執行你的專案
