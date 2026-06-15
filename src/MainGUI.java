import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame {
    private JTextField txtKehadiran, txtEvaluasi, txtPenelitian, txtPengabdian;
    private JLabel lblOutputNilai, lblOutputKategori;

    public MainGUI() {
        setTitle("Sistem Penentuan Kinerja Dosen - Fuzzy Sugeno");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 2, 10, 10));

        // Komponen Input
        add(new JLabel("Kehadiran Mengajar:"));
        txtKehadiran = new JTextField();
        add(txtKehadiran);

        add(new JLabel("Evaluasi Mahasiswa:"));
        txtEvaluasi = new JTextField();
        add(txtEvaluasi);

        add(new JLabel("Jumlah Penelitian:"));
        txtPenelitian = new JTextField();
        add(txtPenelitian);

        add(new JLabel("Pengabdian Masyarakat:"));
        txtPengabdian = new JTextField();
        add(txtPengabdian);

        // Tombol Hitung
        JButton btnHitung = new JButton("Run / Hitung");
        add(new JLabel("")); // Spacer
        add(btnHitung);

        // Komponen Output
        add(new JLabel("Nilai Akhir Sugeno:"));
        lblOutputNilai = new JLabel("-");
        lblOutputNilai.setFont(new Font("Arial", Font.BOLD, 14));
        add(lblOutputNilai);

        add(new JLabel("Kategori Kinerja:"));
        lblOutputKategori = new JLabel("-");
        lblOutputKategori.setFont(new Font("Arial", Font.BOLD, 14));
        add(lblOutputKategori);

        // Event Listener Tombol
        btnHitung.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hitungKinerja();
            }
        });
    }

    private void hitungKinerja() {
        try {
            // 1. Ambil input dari GUI
            double kehadiran = Double.parseDouble(txtKehadiran.getText());
            double evaluasi = Double.parseDouble(txtEvaluasi.getText());
            double penelitian = Double.parseDouble(txtPenelitian.getText());
            double pengabdian = Double.parseDouble(txtPengabdian.getText());

            // 2. Hubungkan dengan RuleBase dari Anggota 2
            // CATATAN: Ini adalah contoh pemanggilan. Sesuaikan dengan nama method Anggota 2.
            // RuleBase ruleBase = new RuleBase();
            // double[] alpha = ruleBase.hitungAlpha(kehadiran, evaluasi, penelitian, pengabdian);
            // double[] z = ruleBase.getRuleOutput();

            // --- DATA DUMMY UNTUK TEST GUI (Hapus saat digabung dengan Anggota 2) ---
            double[] alphaDummy = {0.2, 0.5, 0.8}; 
            double[] zDummy = {40, 70, 90};
            // ------------------------------------------------------------------------

            // 3. Proses di SugenoCalculator (Punya Anggota 3)
            double nilaiAkhir = SugenoCalculator.hitungDefuzzifikasi(alphaDummy, zDummy);
            String kategori = SugenoCalculator.tentukanKategori(nilaiAkhir);

            // 4. Tampilkan di GUI
            lblOutputNilai.setText(String.format("%.2f", nilaiAkhir));
            lblOutputKategori.setText(kategori);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Mohon masukkan angka yang valid!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainGUI().setVisible(true);
        });
    }
}