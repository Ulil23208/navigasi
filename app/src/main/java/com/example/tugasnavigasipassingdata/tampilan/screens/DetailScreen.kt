// Pastikan baris package ini sesuai dengan proyek Anda
package com.example.tugasnavigasipassingdata.tampilan.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack // Import icon baru
import androidx.compose.material3.ExperimentalMaterial3Api // Import baru
import androidx.compose.material3.Icon // Import baru
import androidx.compose.material3.IconButton // Import baru
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold // Import baru
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar // Import baru
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tugasnavigasipassingdata.data.Item // Pastikan import data ini benar
import kotlinx.serialization.json.Json

@OptIn(ExperimentalMaterial3Api::class) // Perlu untuk TopAppBar
@Composable
fun DetailScreen(
    itemJson: String?,
    onNavigateBack: () -> Unit // Parameter baru untuk tombol back
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detail Barang") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) { // Aksi tombol back
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Kembali"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding) // Gunakan padding dari Scaffold
                .padding(16.dp), // Tambahkan padding kita sendiri
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (itemJson != null) {
                // Objek Item akan ditampilkan dengan benar di sini
                val item = Json.decodeFromString<Item>(itemJson)

                // Tampilkan data
                Text(text = item.name, style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = item.description, style = MaterialTheme.typography.bodyLarge)

            } else {
                Text(text = "Data tidak ditemukan.")
            }
        }
    }
}