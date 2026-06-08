public class MembershipFunction {

    // Fungsi keanggotaan bahu kiri = Rendah
    public static double rendah(double x, double a, double b) {
        if (x <= a) {
            return 1.0;
        } else if (x >= b) {
            return 0.0;
        } else {
            return (b - x) / (b - a);
        }
    }

    // Fungsi keanggotaan segitiga = Sedang
    public static double sedang(double x, double a, double b, double c) {
        if (x <= a || x >= c) {
            return 0.0;
        } else if (x == b) {
            return 1.0;
        } else if (x > a && x < b) {
            return (x - a) / (b - a);
        } else {
            return (c - x) / (c - b);
        }
    }

    // Fungsi keanggotaan bahu kanan = Tinggi
    public static double tinggi(double x, double a, double b) {
        if (x <= a) {
            return 0.0;
        } else if (x >= b) {
            return 1.0;
        } else {
            return (x - a) / (b - a);
        }
    }
}
