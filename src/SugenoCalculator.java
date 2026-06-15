public class SugenoCalculator {

    public static double hitungNilaiAkhir(double[] alpha) {
        // 1. Definisikan nilai konstan (z) sesuai tabel output kelompokmu (Rentang 0-100)
        double zKurang = 40.0;     // Kategori Kurang
        double zBaik = 70.0;       // Kategori Baik
        double zSangatBaik = 90.0;  // Kategori Sangat Baik

        // 2. Petakan nilai Z ke masing-masing 9 Rule sesuai struktur MainGUI
        double[] z = new double[9];
        z[0] = zKurang;       // Rule 1
        z[1] = zKurang;       // Rule 2
        z[2] = zKurang;       // Rule 3
        z[3] = zBaik;         // Rule 4
        z[4] = zBaik;         // Rule 5
        z[5] = zBaik;         // Rule 6
        z[6] = zSangatBaik;   // Rule 7
        z[7] = zSangatBaik;   // Rule 8
        z[8] = zSangatBaik;   // Rule 9

        double totalAlphaZ = 0.0;
        double totalAlpha = 0.0;

        // 3. Lakukan looping perkalian matriks Sugeno (Alpha * Z)
        for (int i = 0; i < 9; i++) {
            totalAlphaZ += (alpha[i] * z[i]);
            totalAlpha += alpha[i];
        }

        // 4. KUNCI SKALA & PENGAMAN: Jika pembagian 0/0 terjadi (Semua rule mati)
        if (totalAlpha == 0.0) {
            return 0.0; // Mengunci batas bawah agar tidak menghasilkan NaN / 0.00 eror
        }

        // 5. Hitung nilai akhir real
        double hasilAkhir = totalAlphaZ / totalAlpha;

        // 6. Validasi batas atas mutlak skala 100
        if (hasilAkhir > 100.0) {
            return 100.0;
        }

        return hasilAkhir;
    }

    /**
     * Method pembantu untuk mengonversi hasil angka menjadi Kategori String di GUI
     */
    public static String tentukanKategori(double nilai) {
        if (nilai < 50.0) {
            return "Kurang";
        } else if (nilai >= 50.0 && nilai < 80.0) {
            return "Baik";
        } else {
            return "Sangat Baik";
        }
    }
}