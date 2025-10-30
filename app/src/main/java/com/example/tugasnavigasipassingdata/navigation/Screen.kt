package com.example.tugasnavigasipassingdata.navigation

object Screen {
    const val HOME = "home"
    const val PROFILE = "profile"
    // {itemJson} adalah argumen yang akan kita kirim
    const val DETAIL = "detail/{itemJson}"

    // Fungsi helper untuk membuat rute detail dengan data
    fun createDetailRoute(itemJson: String) = "detail/$itemJson"
}