public class RuleBase {

    // Operator AND menggunakan MIN
    public static double hitungAlpha(double... nilai) {

        if (nilai.length == 0) {
            return 0;
        }

        double min = nilai[0];

        for (double n : nilai) {
            if (n < min) {
                min = n;
            }
        }

        return min;
    }

    // Konstanta output Sugeno
    public static double getRuleOutput(String kategori) {

        return switch (kategori) {
            case "Kurang" -> 40;
            case "Baik" -> 70;
            case "SangatBaik" -> 90;
            default -> 0;
        };
    }

    // R1
    // IF Kehadiran Rendah THEN Kurang
    public static double rule1(double kRendah) {
        return kRendah;
    }

    // R2
    // IF Evaluasi Rendah THEN Kurang
    public static double rule2(double eRendah) {
        return eRendah;
    }

    // R3
    // IF Penelitian Rendah AND Pengabdian Rendah THEN Kurang
    public static double rule3(
            double pRendah,
            double pmRendah) {

        return hitungAlpha(
                pRendah,
                pmRendah
        );
    }

    // R4
    // IF Kehadiran Sedang AND Evaluasi Sedang THEN Baik
    public static double rule4(
            double kSedang,
            double eSedang) {

        return hitungAlpha(
                kSedang,
                eSedang
        );
    }

    // R5
    // IF Kehadiran Tinggi AND Evaluasi Sedang THEN Baik
    public static double rule5(
            double kTinggi,
            double eSedang) {

        return hitungAlpha(
                kTinggi,
                eSedang
        );
    }

    // R6
    // IF Kehadiran Sedang AND Evaluasi Tinggi THEN Baik
    public static double rule6(
            double kSedang,
            double eTinggi) {

        return hitungAlpha(
                kSedang,
                eTinggi
        );
    }

    // R7
    // IF Kehadiran Tinggi AND Evaluasi Tinggi THEN Sangat Baik
    public static double rule7(
            double kTinggi,
            double eTinggi) {

        return hitungAlpha(
                kTinggi,
                eTinggi
        );
    }

    // R8
    // IF Evaluasi Tinggi AND Penelitian Tinggi THEN Sangat Baik
    public static double rule8(
            double eTinggi,
            double pTinggi) {

        return hitungAlpha(
                eTinggi,
                pTinggi
        );
    }

    // R9
    // IF Kehadiran Tinggi AND Evaluasi Tinggi
    // AND Penelitian Tinggi AND Pengabdian Tinggi
    // THEN Sangat Baik
    public static double rule9(
            double kTinggi,
            double eTinggi,
            double pTinggi,
            double pmTinggi) {

        return hitungAlpha(
                kTinggi,
                eTinggi,
                pTinggi,
                pmTinggi
        );
    }
}