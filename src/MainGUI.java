import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame {
    private JTextField txtKehadiran, txtEvaluasi, txtPenelitian, txtPengabdian;
    private JLabel lblOutputNilai, lblOutputKategori;

    private static final double KH_RENDAH_MAX = 60.0; 
    private static final double KH_SEDANG_MIN = 50.0;  
    private static final double KH_SEDANG_MID = 75.0; 
    private static final double KH_SEDANG_MAX = 90.0; 
    private static final double KH_TINGGI_MIN = 75.0; 
    private static final double KH_TINGGI_MAX = 100.0;

    private static final double EV_RENDAH_MAX = 60.0;
    private static final double EV_SEDANG_MIN = 50.0;
    private static final double EV_SEDANG_MID = 75.0;
    private static final double EV_SEDANG_MAX = 90.0;
    private static final double EV_TINGGI_MIN = 75.0;
    private static final double EV_TINGGI_MAX = 100.0;

    private static final double PN_RENDAH_MAX = 3.0;
    private static final double PN_TINGGI_MIN = 1.0;
    private static final double PN_TINGGI_MAX = 4.0;

    private static final double PM_RENDAH_MAX = 3.0;
    private static final double PM_TINGGI_MIN = 1.0;
    private static final double PM_TINGGI_MAX = 4.0;

    public MainGUI() {
        setTitle("Sistem Penentuan Kinerja Dosen - Fuzzy Sugeno");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 2, 10, 10));

        add(new JLabel("  Kehadiran Mengajar:"));
        txtKehadiran = new JTextField(); add(txtKehadiran);

        add(new JLabel("  Evaluasi Mahasiswa:"));
        txtEvaluasi = new JTextField(); add(txtEvaluasi);

        add(new JLabel("  Jumlah Penelitian:"));
        txtPenelitian = new JTextField(); add(txtPenelitian);

        add(new JLabel("  Pengabdian Masyarakat:"));
        txtPengabdian = new JTextField(); add(txtPengabdian);

        JButton btnHitung = new JButton("Hitung");
        add(new JLabel("")); add(btnHitung);

        add(new JLabel("  Nilai Akhir Sugeno:"));
        lblOutputNilai = new JLabel("-");
        lblOutputNilai.setFont(new Font("Arial", Font.BOLD, 14)); add(lblOutputNilai);

        add(new JLabel("  Kategori Kinerja:"));
        lblOutputKategori = new JLabel("-");
        lblOutputKategori.setFont(new Font("Arial", Font.BOLD, 14)); add(lblOutputKategori);

        btnHitung.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prosesFuzzySistem();
            }
        });
    }

    private void prosesFuzzySistem() {
        try {
            double valKehadiran = Double.parseDouble(txtKehadiran.getText());
            double valEvaluasi = Double.parseDouble(txtEvaluasi.getText());
            double valPenelitian = Double.parseDouble(txtPenelitian.getText());
            double valPengabdian = Double.parseDouble(txtPengabdian.getText());

            double khRendah = MembershipFunction.rendah(valKehadiran, 0.0, KH_RENDAH_MAX); 
            double khSedang = MembershipFunction.sedang(valKehadiran, KH_SEDANG_MIN, KH_SEDANG_MID, KH_SEDANG_MAX);
            double khTinggi  = MembershipFunction.tinggi(valKehadiran, KH_TINGGI_MIN, KH_TINGGI_MAX);

            double evRendah = MembershipFunction.rendah(valEvaluasi, 0.0, EV_RENDAH_MAX);
            double evSedang = MembershipFunction.sedang(valEvaluasi, EV_SEDANG_MIN, EV_SEDANG_MID, EV_SEDANG_MAX);
            double evTinggi  = MembershipFunction.tinggi(valEvaluasi, EV_TINGGI_MIN, EV_TINGGI_MAX);

            double pnRendah = MembershipFunction.rendah(valPenelitian, 0.0, PN_RENDAH_MAX);
            double pnTinggi  = MembershipFunction.tinggi(valPenelitian, PN_TINGGI_MIN, PN_TINGGI_MAX);

            double pmRendah = MembershipFunction.rendah(valPengabdian, 0.0, PM_RENDAH_MAX);
            double pmTinggi  = MembershipFunction.tinggi(valPengabdian, PM_TINGGI_MIN, PM_TINGGI_MAX);

            double[] alpha = new double[9];
            alpha[0] = RuleBase.rule1(khRendah);
            alpha[1] = RuleBase.rule2(evRendah);
            alpha[2] = RuleBase.rule3(pnRendah, pmRendah);
            alpha[3] = RuleBase.rule4(khSedang, evSedang);
            alpha[4] = RuleBase.rule5(khTinggi, evSedang);
            alpha[5] = RuleBase.rule6(khSedang, evTinggi);
            alpha[6] = RuleBase.rule7(khTinggi, evTinggi);
            alpha[7] = RuleBase.rule8(evTinggi, pnTinggi);
            alpha[8] = RuleBase.rule9(khTinggi, evTinggi, pnTinggi, pmTinggi);
      
            double nilaiAkhir = SugenoCalculator.hitungNilaiAkhir(alpha);
            String kategoriAkhir = SugenoCalculator.tentukanKategori(nilaiAkhir);

            lblOutputNilai.setText(String.format("%.2f", nilaiAkhir));
            lblOutputKategori.setText(kategoriAkhir);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Masukkan data angka yang valid!", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainGUI().setVisible(true);
        });
    }
}