# Tugas: Implementasi Pola Navigasi & Passing Data (Jetpack Compose)

Aplikasi ini dibuat untuk memenuhi tugas mata kuliah [Nama Mata Kuliah Anda]. Aplikasi ini mendemonstrasikan implementasi pola navigasi modern dan mekanisme pengiriman data antar layar menggunakan Jetpack Compose.


[cite_start](Tugas ini dikerjakan secara berkelompok [cite: 21])

## Implementasi Teknis

Berikut adalah penjelasan mengenai dua fitur utama yang diimplementasikan dalam proyek ini sesuai dengan poin penilaian tugas.

### 1. Pola Navigasi (Poin Penilaian: 30)

* [cite_start]**Pola yang Dipilih:** Kami memilih untuk mengimplementasikan **Bottom Navigation**[cite: 10].
* **Struktur:** Kami menggunakan `Scaffold` sebagai kerangka utama aplikasi. Komponen `NavigationBar` (Bottom Bar) ditempatkan di dalam `Scaffold`.
* **Navigasi:** Proses navigasi ditangani oleh `NavController` dan `NavHost`.
* [cite_start]**Indikator Aktif:** Untuk menampilkan menu mana yang sedang aktif[cite: 19], kami menggunakan `navController.currentBackStackEntryAsState()` untuk mendapatkan rute saat ini. Rute ini kemudian dibandingkan dengan rute setiap `NavigationBarItem` untuk menentukan status `selected`.

### 2. Mekanisme Pengiriman Data (Poin Penilaian: 30)

[cite_start]Aplikasi ini mengirimkan data dari `HomeScreen` (Screen A - Sumber) [cite: 16] [cite_start]ke `DetailScreen` (Screen B - Tujuan)[cite: 17].

* [cite_start]**Metode:** Sesuai ketentuan tugas, data dikirim menggunakan objek yang ditandai dengan **`@Serializable`**[cite: 18, 29].
* **Proses Pengiriman:**
    1.  Kami membuat sebuah `data class Item` yang diberi anotasi `@Serializable`.
    2.  Di `HomeScreen`, ketika sebuah item diklik, objek `Item` tersebut diubah (di-encode) menjadi sebuah **String JSON** menggunakan `kotlinx.serialization`.
    3.  String JSON tersebut kemudian di-encode ke format URL (`URLEncoder.encode`) agar aman untuk dilewatkan sebagai argumen navigasi.
* **Proses Penerimaan:**
    1.  Di `AppNavigation`, `NavHost` kami dikonfigurasi untuk menerima argumen `itemJson`.
    2.  String JSON yang diterima kemudian di-decode dari format URL (`URLDecoder.decode`) untuk memperbaiki *bug* yang mengubah spasi menjadi tanda `+`.
    3.  Di `DetailScreen`, String JSON yang sudah bersih ini di-parse kembali (`Json.decodeFromString`) menjadi objek `Item` dan datanya ditampilkan ke pengguna.

## Tangkapan Layar (Screenshots)

[cite_start](Anda **HARUS** mengganti bagian di bawah ini dengan screenshot Anda yang sebenarnya [cite: 26])

**1. Halaman Utama (Home Screen - Menampilkan Daftar)**
![Home Screen](path/to/your/screenshot_home.png)

**2. Halaman Detail (Detail Screen - Menerima Data)**
![Detail Screen](path/to/your/screenshot_detail.png)

**3. Halaman Profile (Menampilkan Indikator Aktif)**
![Profile Screen](path/to/your/screenshot_profile.png)
