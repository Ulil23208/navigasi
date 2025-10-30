package com.example.tugasnavigasipassingdata.tampilan.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tugasnavigasipassingdata.data.Item
import com.example.tugasnavigasipassingdata.navigation.Screen
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

// Data dummy
val dummyItemList = listOf(
    Item(1, "Barang A", "Ini adalah deskripsi untuk barang A. Kualitas terbaik."),
    Item(2, "Barang B", "Ini adalah deskripsi untuk barang B. Harga terjangkau."),
    Item(3, "Barang C", "Ini adalah deskripsi untuk barang C. Paling populer.")
)

@Composable
fun HomeScreen(navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(dummyItemList) { item ->
            ItemCard(item = item) {
                // --- MEKANISME PENGIRIMAN DATA --- [cite: 6, 13]
                // 1. Ubah objek Item menjadi String JSON menggunakan @Serializable
                val itemJson = Json.encodeToString(item)

                // 2. (PENTING) Encode string JSON agar aman untuk URL
                val encodedJson = URLEncoder.encode(itemJson, StandardCharsets.UTF_8.name())

                // 3. Navigasi ke screen detail dengan membawa JSON string
                navController.navigate(Screen.createDetailRoute(encodedJson))
            }
        }
    }
}

@Composable
fun ItemCard(item: Item, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = item.name, fontWeight = FontWeight.Bold)
            Text(text = item.description.take(40) + "...") // Tampilkan ringkasan
        }
    }
}