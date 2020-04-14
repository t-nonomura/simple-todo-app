# simple-todo-app
[![Bitrise](https://app.bitrise.io/app/2cba13180fe42440/status.svg?token=D3cf-dZmU7N0oJ2n5AGP6g&branch=master)](https://app.bitrise.io/app/2cba13180fe42440)

[<img src="https://dply.me/z7clcf/button/large" alt="Try it on your device via DeployGate">](https://dply.me/z7clcf#install)

## 動作環境
- Android Studio3.6.2
- minSdkVersion23(Android6.0〜)


## スクリーンショット
|Allタブ|Activeタブ|Completedタブ|ToDo追加|
|---|---|---|---|
|<img src="https://user-images.githubusercontent.com/19687820/79193114-f1655200-7e64-11ea-8a30-ea4028a57b93.jpg" width="320"/>|<img src="https://user-images.githubusercontent.com/19687820/79193118-f32f1580-7e64-11ea-9783-5e42e8d48ae5.jpg" width="320"/>|<img src="https://user-images.githubusercontent.com/19687820/79193119-f3c7ac00-7e64-11ea-8f40-c693c4996121.jpg" width="320"/>|<img src="https://user-images.githubusercontent.com/19687820/79193122-f4604280-7e64-11ea-8cf8-fc7dab0d30e6.jpg" width="320"/>|

## アーキテクチャ
MVVMアーキテクチャ。
Roomで保存されたデータをCoroutines Flowで取得し、LiveDataに変換した物をViewで監視する。

<img src="https://user-images.githubusercontent.com/19687820/79194503-a13bbf00-7e67-11ea-884c-817a2f5d5f95.png" width="320/">



## ライブラリ
- [Android Jetpack](https://developer.android.com/jetpack)
  - Foundation
    - Android KTX
    - AppCompat
  - Architecture
    - Data Binding
    - LiveData
    - Navigation
    - Room
    - ViewModel
  - UI
    - Fragment
    - ViewPager2
  - Widget
    - CardView
- Kotlin
  - Stdlib
  - Coroutines
- DI
  - [Koin](https://github.com/InsertKoinIO/koin)
- RecyclerView
  - [Groupie](https://github.com/lisawray/groupie)
- Unit Test
  - [MockK](https://github.com/mockk/mockk)
