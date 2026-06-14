public class Main {

    //TESTING AJA
    public static void main(String[] args) {

        double kehadiran = 85;
        double evaluasi = 80;
        double penelitian = 4;
        double pengabdian = 3;

        // FUZZIFIKASI

        double kRendah =
                MembershipFunction.rendah(
                        kehadiran,
                        50,
                        75
                );

        double kSedang =
                MembershipFunction.sedang(
                        kehadiran,
                        50,
                        75,
                        100
                );

        double kTinggi =
                MembershipFunction.tinggi(
                        kehadiran,
                        75,
                        100
                );

        double eRendah =
                MembershipFunction.rendah(
                        evaluasi,
                        50,
                        75
                );

        double eSedang =
                MembershipFunction.sedang(
                        evaluasi,
                        50,
                        75,
                        100
                );

        double eTinggi =
                MembershipFunction.tinggi(
                        evaluasi,
                        75,
                        100
                );

        double pRendah =
                MembershipFunction.rendah(
                        penelitian,
                        1,
                        3
                );

        double pTinggi =
                MembershipFunction.tinggi(
                        penelitian,
                        3,
                        5
                );

        double pmRendah =
                MembershipFunction.rendah(
                        pengabdian,
                        1,
                        4
                );

        double pmTinggi =
                MembershipFunction.tinggi(
                        pengabdian,
                        2.5,
                        4
                );

        // RULE

        double alpha1 = RuleBase.rule1(kRendah);
        double alpha2 = RuleBase.rule2(eRendah);
        double alpha3 = RuleBase.rule3(pRendah, pmRendah);
        double alpha4 = RuleBase.rule4(kSedang, eSedang);
        double alpha5 = RuleBase.rule5(kTinggi, eSedang);
        double alpha6 = RuleBase.rule6(kSedang, eTinggi);
        double alpha7 = RuleBase.rule7(kTinggi, eTinggi);
        double alpha8 = RuleBase.rule8(eTinggi, pTinggi);
        double alpha9 = RuleBase.rule9(
                kTinggi,
                eTinggi,
                pTinggi,
                pmTinggi
        );

        System.out.println("===== ALPHA PREDIKAT =====");

        System.out.println("Rule 1 = " + alpha1);
        System.out.println("Rule 2 = " + alpha2);
        System.out.println("Rule 3 = " + alpha3);
        System.out.println("Rule 4 = " + alpha4);
        System.out.println("Rule 5 = " + alpha5);
        System.out.println("Rule 6 = " + alpha6);
        System.out.println("Rule 7 = " + alpha7);
        System.out.println("Rule 8 = " + alpha8);
        System.out.println("Rule 9 = " + alpha9);
    }
}