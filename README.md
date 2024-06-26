﻿# LP7 DPBO 2024 C1

## Janji
Saya Nabil Hanif Achmaddiredja mengerjakan Latihan Praktikum 7 dalam mata kuliah
Desain dan Pemrograman Berorientasi Objek untuk keberkahanNya maka saya tidak melakukan kecurangan
seperti yang telah dispesifikasikan. Aamiin.

## Desain Program
- FlappyBird.java: Kelas utama yang menginisialisasi game dan mengatur logika permainan.
- Player.java: Kelas untuk pemain Flappy Bird.
- Pipe.java: Kelas untuk pipa yang muncul di layar.
- assets/: Direktori berisi gambar latar belakang, karakter Flappy Bird, dan pipa.

## Alur Program
1. Inisialisasi Game:
    - Program dimulai dengan menginisialisasi JFrame sebagai wadah permainan.
    - Setelah itu, inisialisasi pemain, pipa, dan label skor.
    - Mulai dua timer: satu untuk pergerakan pemain dan pipa, dan yang lain untuk menempatkan pipa baru secara berkala.
2. Pergerakan Pemain dan Pipa:
    - Pemain diberi gravitasi, yang membuatnya bergerak turun secara konstan.
    - Pipa-pipa bergerak dari kanan ke kiri secara konstan untuk memberikan efek pergerakan maju.
3. Penanganan Tabrakan:
    - Setiap iterasi, program memeriksa apakah pemain bertabrakan dengan pipa atau menyentuh batas bawah layar.
    - Jika tabrakan terdeteksi, permainan berakhir dan timer dihentikan.
4. Penambahan Skor:
    - Setiap kali pemain melewati sepasang pipa (bagian atas dan bawah), skornya bertambah.
    - Skor ditampilkan dalam label skor di layar.
5. Restart Permainan:
    - Saat permainan berakhir, pemain dapat menekan tombol "R" untuk memulai kembali permainan.
    - Semua pipa dihapus, pemain ditempatkan kembali ke posisi awal, dan skor direset.

## Dokumentasi 
![Screenshot 2024-04-27 144431](https://github.com/NabilHanifA/LP7DPBO2024C1/assets/133948088/cebbc4ac-1a57-49e9-b4ec-5c26af4842b6)
![Screenshot 2024-04-27 144447](https://github.com/NabilHanifA/LP7DPBO2024C1/assets/133948088/c24260b4-6fda-482e-9bbb-a0f532d96715)
![Screenshot 2024-04-27 144415](https://github.com/NabilHanifA/LP7DPBO2024C1/assets/133948088/6e7b7743-1c63-454b-aa44-5e0609e0b96b)
