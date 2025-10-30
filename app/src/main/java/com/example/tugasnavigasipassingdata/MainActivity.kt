package com.example.tugasnavigasipassingdata // Sesuaikan dengan nama package Anda

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.tugasnavigasipassingdata.navigation.AppNavigation
import com.example.tugasnavigasipassingdata.tampilan.theme.TugasNavigasiPassingDataTheme // Sesuaikan dengan nama tema Anda

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TugasNavigasiPassingDataTheme { // Sesuaikan dengan nama tema Anda
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Panggil composable navigasi utama
                    AppNavigation()
                }
            }
        }
    }
}