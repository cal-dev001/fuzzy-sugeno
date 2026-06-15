public class SugenoCalculator {

    // 1. Menghitung nilai akhir Sugeno (Defuzzifikasi)
    // alphaPredikat didapat dari Anggota 2, zOutput adalah konstanta (40, 70, 90)
    public static double hitungDefuzzifikasi(double[] alphaPredikat, double[] zOutput) {
        double pembilang = 0.0;
        double penyebut = 0.0;

        for (int i = 0; i < alphaPredikat.length; i++) {
            pembilang += (alphaPredikat[i] * zOutput[i]);
            penyebut += alphaPredikat[i];
        }

        // Mencegah pembagian dengan nol jika semua rule menghasilkan alpha 0
        if (penyebut == 0) {
            return 0.0; 
        }

        return pembilang / penyebut;
    }

    // 2. Menentukan kategori berdasarkan nilai akhir
    public static String tentukanKategori(double nilaiAkhir) {
        // Pembulatan ke integer terdekat untuk pengecekan range
        int nilai = (int) Math.round(nilaiAkhir); 

        if (nilai >= 0 && nilai <= 59) {
            return "Kurang";
        } else if (nilai >= 60 && nilai <= 79) {
            return "Baik";
        } else if (nilai >= 80 && nilai <= 100) {
            return "Sangat Baik";
        } else {
            return "Nilai di luar jangkauan";
        }
    }
}