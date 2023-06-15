/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import java.sql.*;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author T O M
 */
public final class formTransaksi extends javax.swing.JFrame {
    Statement st = null;
    ResultSet rs = null;
    NumberFormat nf = NumberFormat.getNumberInstance(new Locale("in","ID"));
    Connection con = koneksi.konek_db.konek();
    fungsi.hitung itung = new fungsi.hitung();
    /**
     * Creates new form formTransaksi
     */
    public formTransaksi() {
        initComponents();
        tSize();
        tSize2();
        tampilData();
        crData();
        tampilPesan();
        total();
        tampilTanggal();
        cekStok();
        valLevel();
        
    }
    public void setColor (JPanel panel) {
        panel.setBackground(new Color(97,135,110));
    }
    public void resetColor (JPanel panel) {
        panel.setBackground(new Color(60,98,85));
    }
    private void valLevel() {
       try {
           st = con.createStatement();
           String vlevel = "SELECT * FROM profil WHERE id_log = (SELECT id_log FROM user_log ORDER BY id_login DESC LIMIT 1) ";
           rs = st.executeQuery(vlevel);
           
           if (rs.next()) {
             String bg = rs.getString("bagian");
             if (bg.equals("kasir")) {
                 jPanel4.setVisible(true);
                 jPanel5.setVisible(false);
                 jPanel15.setVisible(false);
             } else {
                 jPanel4.setVisible(false);
                 jPanel5.setVisible(true);
                 jPanel15.setVisible(true);
             }
           }
           
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null, e);
       }
    }
    private void tampilData() {
        try {
            String tampil = "SELECT * FROM menu";
            st = con.createStatement();
            rs = st.executeQuery(tampil);
            
            DefaultTableModel mdl = new DefaultTableModel();
            
            mdl.addColumn("NO");
            mdl.addColumn("KODE MENU");
            mdl.addColumn("NAMA MENU");
            mdl.addColumn("HARGA");
            mdl.addColumn("STOK");
            
            mdl.getDataVector();
            mdl.fireTableDataChanged();
            mdl.setRowCount(0);
            
            int no = 1;
            while (rs.next()) {
                Object data[] = {
                    no++,
                    rs.getString("kode"),
                    rs.getString("nama_menu"),
                    rs.getString("harga"),
                    rs.getString("stok")
            };
                mdl.addRow(data);
                tblMenu.setModel(mdl);
            }
//            txtKode.setEditable(false);
            tSize();
            tSize2();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void cariData() {
        try {
            st = con.createStatement();
            
            if (cmbCari.getSelectedItem().equals("KODE")) {
                rs = st.executeQuery("SELECT * FROM menu WHERE kode LIKE '%"+ txtCari.getText() +"%'");
            } else {
                rs = st.executeQuery("SELECT * FROM menu WHERE nama_menu LIKE '%"+ txtCari.getText() +"%'");
            }
            
            DefaultTableModel mdl = new DefaultTableModel();
            
            mdl.addColumn("NO");
            mdl.addColumn("KODE MENU");
            mdl.addColumn("NAMA MENU");
            mdl.addColumn("HARGA");
            mdl.addColumn("STOK");
            
            mdl.getDataVector();
            mdl.fireTableDataChanged();
            mdl.setRowCount(0);
            
            int no = 1;
            while (rs.next()) {
                Object data [] = {
                    no++,
                    rs.getString("kode"),
                    rs.getString("nama_menu"),
                    rs.getString("harga"),
                    rs.getString("stok")
                };
                mdl.addRow(data);
                tblMenu.setModel(mdl);
            }
            tSize();
            tSize2();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void tampilPesan() {
        try {
            String tampil = "SELECT * FROM keranjang";
            st = con.createStatement();
            rs = st.executeQuery(tampil);
            
            DefaultTableModel mdl = new DefaultTableModel();
            
            mdl.addColumn("ID TRANS");
            mdl.addColumn("KODE MENU");
            mdl.addColumn("NAMA MENU");
            mdl.addColumn("HARGA");
            mdl.addColumn("JUMLAH");
            mdl.addColumn("SUBTOTAL");
            
            mdl.getDataVector();
            mdl.fireTableDataChanged();
            mdl.setRowCount(0);
            
            while (rs.next()) {
                Object data[] = {
                    rs.getString("id_trans"),
                    rs.getString("kode"),
                    rs.getString("nama_menu"),
                    rs.getString("harga"),
                    rs.getString("jumlah"),
                    rs.getString("subtotal")
                    
            };
                mdl.addRow(data);
                tblMenu1.setModel(mdl);
            }
            tSize2();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void crData() {
        txtCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent de) {
                cariData();
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                cariData();
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }
    private void tSize() {
        tblMenu.getColumnModel().getColumn(0).setPreferredWidth(5);
        tblMenu.getColumnModel().getColumn(1).setPreferredWidth(35);
        tblMenu.getColumnModel().getColumn(2).setPreferredWidth(110);
        tblMenu.getColumnModel().getColumn(3).setPreferredWidth(40);
        tblMenu.getColumnModel().getColumn(4).setPreferredWidth(20);
        
    }
    private void tSize2() {
        tblMenu1.getColumnModel().getColumn(0).setPreferredWidth(15);
        tblMenu1.getColumnModel().getColumn(1).setPreferredWidth(30);
        tblMenu1.getColumnModel().getColumn(2).setPreferredWidth(110);
        tblMenu1.getColumnModel().getColumn(3).setPreferredWidth(20);
        tblMenu1.getColumnModel().getColumn(4).setPreferredWidth(18);
        tblMenu1.getColumnModel().getColumn(5).setPreferredWidth(35);
    }
    private void bersih() {
        txtKode.setText("");
        txtMenu.setText("");
        txtHarga.setText("");
        jumlah.setValue(1);
    }
    private void total() {
        try {
            String tot = "SELECT SUM(subtotal) AS total FROM keranjang";
            st = con.createStatement();
            rs = st.executeQuery(tot);
            
            while (rs.next()) {
            int ttl = rs.getInt("total");
            txtTotal.setText(String.valueOf(nf.format(ttl)));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void aturStok () {
        try {
            st = con.createStatement();
            String updateSt = "UPDATE menu m INNER JOIN keranjang k ON m.kode = k.kode AND m.nama_menu = k.nama_menu SET m.stok = m.stok - k.jumlah";
            st.executeUpdate(updateSt);
            tampilData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void tampilTanggal () {

        Date tgl = new Date();
        
        SimpleDateFormat sdf =  new SimpleDateFormat("dd-MM-yyyy");
        String dd = sdf.format(tgl);
        
        Timer t;
        SimpleDateFormat sd ;
        
        t = new Timer(0, (ActionEvent ae) -> {
            Date dt = new Date();
            SimpleDateFormat sd1 = new SimpleDateFormat("HH:mm:ss");
            String tt = sd1.format(dt);
            
            txtTanggal.setText(dd);
            txtWaktu.setText(tt);
        }) ;
        t.start();
        
    }
    private void cekStok () {
        try {
            st = con.createStatement();
            String cs = "SELECT * FROM menu WHERE stok = 0";
            rs = st.executeQuery(cs);
            
            if (rs.next()) {
                jPanel10.setVisible(true);
                String nm = rs.getString("nama_menu");
                txtCekstok1.setText("Stok Menu "+ nm +" Habis");
                jLabel19.setText("PESANAN");
            } else {
                jPanel10.setVisible(false);
                jLabel19.setText("                 PESANAN");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtHarga = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtKode = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        txtMenu = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jumlah = new javax.swing.JSpinner();
        btnTambah = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        btnBatal1 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JLabel();
        btnTambah1 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblMenu = new javax.swing.JTable();
        cmbCari = new javax.swing.JComboBox<>();
        txtCari = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        btnBatal2 = new javax.swing.JButton();
        txtTotal1 = new javax.swing.JLabel();
        txtTanggal = new javax.swing.JLabel();
        txtWaktu = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        txtCekstok1 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblMenu1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1200, 700));

        jPanel2.setBackground(new java.awt.Color(60, 98, 85));
        jPanel2.setPreferredSize(new java.awt.Dimension(245, 617));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/sdd.png"))); // NOI18N

        jPanel3.setBackground(new java.awt.Color(60, 98, 85));
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel3MouseExited(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Bahnschrift", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("HOME");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/home.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18))
        );

        jPanel4.setBackground(new java.awt.Color(97, 135, 110));
        jPanel4.setPreferredSize(new java.awt.Dimension(156, 56));
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel4MouseExited(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Bahnschrift", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("TRANSAKSI");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/money.png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18))
        );

        jPanel5.setBackground(new java.awt.Color(60, 98, 85));
        jPanel5.setPreferredSize(new java.awt.Dimension(114, 56));
        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel5MouseExited(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Bahnschrift", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("MENU");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/chocolate.png"))); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18))
        );

        jPanel6.setBackground(new java.awt.Color(60, 98, 85));
        jPanel6.setPreferredSize(new java.awt.Dimension(225, 56));
        jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel6MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel6MouseExited(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Bahnschrift", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("RIWAYAT TRANSAKSI");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/time (3).png"))); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18))
        );

        jPanel7.setBackground(new java.awt.Color(60, 98, 85));
        jPanel7.setPreferredSize(new java.awt.Dimension(129, 56));
        jPanel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel7MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel7MouseExited(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Bahnschrift", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("LOGOUT");

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/arrow.png"))); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18))
        );

        jPanel15.setBackground(new java.awt.Color(60, 98, 85));
        jPanel15.setPreferredSize(new java.awt.Dimension(225, 56));
        jPanel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel15MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel15MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel15MouseExited(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Bahnschrift", 1, 12)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("MANAGEMENT");

        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/supply-chain.png"))); // NOI18N

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel34)
                .addGap(18, 18, 18)
                .addComponent(jLabel33)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel34)
                    .addComponent(jLabel33))
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1)
                .addContainerGap(10, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGap(111, 111, 111)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(60, 98, 85));

        jLabel12.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("x");
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Bahnschrift", 1, 36)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("TRANSAKSI");

        jLabel14.setFont(new java.awt.Font("Cascadia Code", 0, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Es Teh Kampul Indonesia");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 653, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addGap(19, 19, 19))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14)))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        txtHarga.setEditable(false);
        txtHarga.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        txtHarga.setForeground(new java.awt.Color(97, 135, 110));
        txtHarga.setBorder(null);
        txtHarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHargaActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Bahnschrift", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(97, 135, 110));
        jLabel15.setText("KODE");

        txtKode.setEditable(false);
        txtKode.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        txtKode.setForeground(new java.awt.Color(97, 135, 110));
        txtKode.setBorder(null);
        txtKode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKodeActionPerformed(evt);
            }
        });

        jSeparator1.setBackground(new java.awt.Color(60, 98, 85));

        jLabel16.setFont(new java.awt.Font("Bahnschrift", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(97, 135, 110));
        jLabel16.setText("NAMA MENU");

        jLabel17.setFont(new java.awt.Font("Bahnschrift", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(97, 135, 110));
        jLabel17.setText("HARGA");

        jSeparator3.setBackground(new java.awt.Color(60, 98, 85));

        txtMenu.setEditable(false);
        txtMenu.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        txtMenu.setForeground(new java.awt.Color(97, 135, 110));
        txtMenu.setBorder(null);
        txtMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMenuActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Bahnschrift", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(97, 135, 110));
        jLabel18.setText("JUMLAH");

        jumlah.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        jumlah.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));

        btnTambah.setBackground(new java.awt.Color(97, 135, 110));
        btnTambah.setFont(new java.awt.Font("Bahnschrift", 1, 15)); // NOI18N
        btnTambah.setForeground(new java.awt.Color(255, 255, 255));
        btnTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/check-mark.png"))); // NOI18N
        btnTambah.setText("CHECKOUT");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        btnBatal.setBackground(new java.awt.Color(97, 135, 110));
        btnBatal.setFont(new java.awt.Font("Bahnschrift", 1, 15)); // NOI18N
        btnBatal.setForeground(new java.awt.Color(255, 255, 255));
        btnBatal.setText("BATAL");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

        btnBatal1.setBackground(new java.awt.Color(97, 135, 110));
        btnBatal1.setFont(new java.awt.Font("Bahnschrift", 1, 15)); // NOI18N
        btnBatal1.setForeground(new java.awt.Color(255, 255, 255));
        btnBatal1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/trash.png"))); // NOI18N
        btnBatal1.setText("HAPUS");
        btnBatal1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatal1ActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Bahnschrift", 1, 36)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(97, 135, 110));
        jLabel19.setText("PESANAN");

        jLabel20.setFont(new java.awt.Font("Bahnschrift", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(97, 135, 110));
        jLabel20.setText("TOTAL");

        txtTotal.setFont(new java.awt.Font("Times New Roman", 1, 40)); // NOI18N
        txtTotal.setForeground(new java.awt.Color(97, 135, 110));
        txtTotal.setText("0.00");

        btnTambah1.setBackground(new java.awt.Color(97, 135, 110));
        btnTambah1.setFont(new java.awt.Font("Bahnschrift", 1, 15)); // NOI18N
        btnTambah1.setForeground(new java.awt.Color(255, 255, 255));
        btnTambah1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/checklist.png"))); // NOI18N
        btnTambah1.setText("PESAN");
        btnTambah1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambah1ActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Bahnschrift", 1, 36)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(97, 135, 110));
        jLabel21.setText("MENU");

        jLabel22.setFont(new java.awt.Font("Bahnschrift", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(97, 135, 110));
        jLabel22.setText("Rp.");

        tblMenu.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        tblMenu.setForeground(new java.awt.Color(97, 135, 110));
        tblMenu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "NO", "KODE MENU", "NAMA MENU", "HARGA", "STOK"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMenu.setRowHeight(35);
        tblMenu.setSelectionBackground(new java.awt.Color(97, 135, 110));
        tblMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMenuMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblMenu);

        cmbCari.setFont(new java.awt.Font("Bahnschrift", 1, 12)); // NOI18N
        cmbCari.setForeground(new java.awt.Color(97, 135, 110));
        cmbCari.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NAMA MENU", "KODE" }));
        cmbCari.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbCariItemStateChanged(evt);
            }
        });

        txtCari.setFont(new java.awt.Font("Bahnschrift", 0, 15)); // NOI18N
        txtCari.setForeground(new java.awt.Color(97, 135, 110));
        txtCari.setBorder(null);
        txtCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCariActionPerformed(evt);
            }
        });
        txtCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCariKeyReleased(evt);
            }
        });

        jSeparator4.setBackground(new java.awt.Color(60, 98, 85));

        btnBatal2.setBackground(new java.awt.Color(97, 135, 110));
        btnBatal2.setFont(new java.awt.Font("Bahnschrift", 1, 15)); // NOI18N
        btnBatal2.setForeground(new java.awt.Color(255, 255, 255));
        btnBatal2.setText("BATAL");
        btnBatal2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatal2ActionPerformed(evt);
            }
        });

        txtTotal1.setFont(new java.awt.Font("Times New Roman", 1, 40)); // NOI18N
        txtTotal1.setForeground(new java.awt.Color(97, 135, 110));
        txtTotal1.setText("Rp.");

        txtTanggal.setForeground(new java.awt.Color(255, 255, 255));
        txtTanggal.setText("0");

        txtWaktu.setForeground(new java.awt.Color(255, 255, 255));
        txtWaktu.setText("0");

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(60, 98, 85), 2));

        jLabel24.setFont(new java.awt.Font("HelveticaNeue", 0, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(60, 98, 85));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("WARNING !");

        txtCekstok1.setFont(new java.awt.Font("HelveticaNeue", 0, 10)); // NOI18N
        txtCekstok1.setForeground(new java.awt.Color(60, 98, 85));
        txtCekstok1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtCekstok1.setText("INFO");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
            .addComponent(txtCekstok1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCekstok1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jSeparator5.setBackground(new java.awt.Color(60, 98, 85));

        tblMenu1.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        tblMenu1.setForeground(new java.awt.Color(97, 135, 110));
        tblMenu1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID TRANS", "KODE MENU", "NAMA MENU", "HARGA", "JUMLAH", "SUBTOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblMenu1.setRowHeight(35);
        tblMenu1.setSelectionBackground(new java.awt.Color(97, 135, 110));
        tblMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblMenu1MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblMenu1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnBatal2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnTambah1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(12, 12, 12)
                                            .addComponent(cmbCari, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(14, 14, 14)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabel15)
                                                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGap(37, 37, 37))
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel22)
                                                        .addGap(18, 18, 18)))
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addComponent(jLabel18)
                                                    .addGap(88, 88, 88)))
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jumlah)
                                                .addComponent(jSeparator5, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtKode, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtMenu, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtHarga, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(92, 92, 92)
                                        .addComponent(btnBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnBatal1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtTotal1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtWaktu, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(111, 111, 111)
                            .addComponent(jLabel21)
                            .addGap(47, 47, 47)
                            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(40, 40, 40)
                            .addComponent(jLabel19))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(txtTanggal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtWaktu)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbCari, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(141, 141, 141)
                                        .addComponent(jumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtKode, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel15))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(txtMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel16))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(28, 28, 28)
                                                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(14, 14, 14)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel17)
                                            .addComponent(jLabel22)
                                            .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(15, 15, 15)
                                        .addComponent(jLabel18)))
                                .addGap(49, 49, 49))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(25, 25, 25))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtTotal1)
                                        .addComponent(txtTotal)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBatal1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTambah1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBatal2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(63, 63, 63))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseClicked
        home homi = new home();
        homi.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jPanel3MouseClicked

    private void jPanel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseEntered
        setColor(jPanel3);
    }//GEN-LAST:event_jPanel3MouseEntered

    private void jPanel3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseExited
        resetColor(jPanel3);
    }//GEN-LAST:event_jPanel3MouseExited

    private void jPanel4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseEntered
//        setColor(jPanel4);
    }//GEN-LAST:event_jPanel4MouseEntered

    private void jPanel4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseExited
//        resetColor(jPanel4);
    }//GEN-LAST:event_jPanel4MouseExited

    private void jPanel5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseEntered
        setColor(jPanel5);
    }//GEN-LAST:event_jPanel5MouseEntered

    private void jPanel5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseExited
        resetColor(jPanel5);
    }//GEN-LAST:event_jPanel5MouseExited

    private void jPanel6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseEntered
        setColor(jPanel6);
    }//GEN-LAST:event_jPanel6MouseEntered

    private void jPanel6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseExited
        resetColor(jPanel6);
    }//GEN-LAST:event_jPanel6MouseExited

    private void jPanel7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7MouseEntered
        setColor(jPanel7);
    }//GEN-LAST:event_jPanel7MouseEntered

    private void jPanel7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7MouseExited
        resetColor(jPanel7);
    }//GEN-LAST:event_jPanel7MouseExited

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel12MouseClicked

    private void jPanel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseClicked
        formMenu menu = new formMenu();
        menu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jPanel5MouseClicked

    private void jPanel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseClicked
        formRiwayat rw = new formRiwayat();
        rw.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jPanel6MouseClicked

    private void txtHargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHargaActionPerformed

    private void txtKodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKodeActionPerformed

    private void txtMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMenuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMenuActionPerformed

    private void btnBatal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatal1ActionPerformed
        try {
            
            if (tblMenu1.getRowCount() == 1) {
                st = con.createStatement();
                String clear = "DELETE FROM keranjang";
                st.executeUpdate(clear);
                ((DefaultTableModel)tblMenu1.getModel()).setNumRows(0);
                total();
                tampilPesan();
            } else {
                String id = tblMenu1.getValueAt(tblMenu1.getSelectedRow(), 0).toString();
                String del = "DELETE FROM keranjang WHERE id_trans = '"+ id +"'";
                st = con.createStatement();
                st.executeUpdate(del);
                total();
                tampilPesan();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }//GEN-LAST:event_btnBatal1ActionPerformed

    private void btnTambah1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambah1ActionPerformed
        try {
            st = con.createStatement();
            String cs = "SELECT kode, stok AS sto FROM menu WHERE kode = '"+ txtKode.getText() +"'";
            rs = st.executeQuery(cs);

            while (rs.next()) {
                int stk = rs.getInt("sto");
                int jm = Integer.parseInt(jumlah.getValue().toString());
                
                if (jm > stk) {
                    JOptionPane.showMessageDialog(null, "Jumlah beli melebihi stok !");
                    return;
                } else {
                    st = con.createStatement();
                    String cekStok= "SELECT * FROM menu WHERE kode = '"+ txtKode.getText() +"'";
                    rs = st.executeQuery(cekStok);

                    while (rs.next()) {
                        int stok = rs.getInt("stok");

                        if (stok == 3) {
                            JOptionPane.showMessageDialog(null, "Stok "+ txtMenu.getText() +" Sudah Hampir Habis");
                        } else if (stok == 0) {
                            JOptionPane.showMessageDialog(null, "Stok "+ txtMenu.getText() +" Sudah Habis !");
                            return;
                        }
                    }
                if (txtKode.getText().equals("") ||
                        txtMenu.getText().equals("") ||
                        txtHarga.equals("") ||
                        jumlah.getValue().equals(0)) {
                    JOptionPane.showMessageDialog(null, "Lengkapi Data Menu Yang Akan Dipesan");
                } else {

                String kode = txtKode.getText();
                String nama_menu = txtMenu.getText();
                int harga = Integer.parseInt(txtHarga.getText());
                int jml = (Integer)jumlah.getValue();

                itung.jml = jml;
                itung.harga = harga;

                itung.sub = itung.subtotal(itung.jml,itung.harga);

                st = con.createStatement();
                String input = "INSERT INTO keranjang (kode, nama_menu, harga, jumlah, subtotal) VALUES ('"+ kode +"','"+ nama_menu +"','"+ String.valueOf(harga) +"','"+ itung.jml +"','"+ itung.sub +"')";
                st.executeUpdate(input);

                tampilPesan();
                total();
                bersih();
                }
            }
        }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnTambah1ActionPerformed

    private void cmbCariItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbCariItemStateChanged
        cariData();
    }//GEN-LAST:event_cmbCariItemStateChanged

    private void txtCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariActionPerformed

    private void txtCariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKeyReleased
        cariData();
    }//GEN-LAST:event_txtCariKeyReleased

    private void tblMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMenuMouseClicked
        txtKode.setText(tblMenu.getValueAt(tblMenu.getSelectedRow(),1).toString());
        txtMenu.setText(tblMenu.getValueAt(tblMenu.getSelectedRow(),2).toString());
        txtHarga.setText(tblMenu.getValueAt(tblMenu.getSelectedRow(),3).toString());
        
        
    }//GEN-LAST:event_tblMenuMouseClicked

    private void btnBatal2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatal2ActionPerformed
        bersih();
    }//GEN-LAST:event_btnBatal2ActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        try {
            st = con.createStatement();
            String clear = "DELETE FROM keranjang";
            st.executeUpdate(clear);
            ((DefaultTableModel)tblMenu1.getModel()).setNumRows(0);
            total();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        
            if (tblMenu1.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Data Pesanan Belum Terisi");
            } else {
            try {
                
                String tot = "SELECT SUM(subtotal) AS total FROM keranjang";
                st = con.createStatement();
                rs = st.executeQuery(tot);
                
                while (rs.next()) {
                    int ttl = rs.getInt("total");
                    String kem = JOptionPane.showInputDialog(this, "<html> <b> TOTAL : <i>Rp. "+ String.valueOf(ttl) +"</i>, Masukkan Nominal Dibayar</b> </html>");
                    int kmb = Integer.parseInt(kem);
                    if (kmb < ttl) {
                        JOptionPane.showMessageDialog(null, "Nominal Yang Dibayarkan Kurang");
                        return;
                    } else {
                    int bayar = Integer.parseInt(kem);
                    
                    itung.total = ttl;
                    itung.bayar = bayar;
                    itung.kembali = itung.kembalian(itung.bayar, itung.total);
                    JOptionPane.showMessageDialog(null, "<html><b><i>Rp. "+ String.valueOf(nf.format(itung.kembali)) +"</i></b></html>","KEMBALIAN",JOptionPane.INFORMATION_MESSAGE);
                    }
                    DefaultTableModel mdl = (DefaultTableModel) tblMenu1.getModel();
                    
                    String Kode,Nama_menu,Harga,Id_trans,Jumlah,Subtotal,Total,Bayar,Kembalian,idkasir,idusr;
                    st = con.createStatement();
                    String ks = "SELECT * FROM user_log ORDER BY id_login DESC LIMIT 1";
                    rs = st.executeQuery(ks);
                    if (rs.next()) {
                        String ksr = rs.getString("id_login");
                        String usr = rs.getString("id_log");
                        
                                for (int i=0; i<mdl.getRowCount(); i++) {
                                    Id_trans = mdl.getValueAt(i, 0).toString();
                                    Kode = mdl.getValueAt(i, 1).toString();
                                    Nama_menu = mdl.getValueAt(i, 2).toString();
                                    Harga = mdl.getValueAt(i, 3).toString();
                                    Jumlah =  mdl.getValueAt(i, 4).toString();
                                    Subtotal =  mdl.getValueAt(i, 5).toString();
                                    Total = String.valueOf(itung.total);
                                    Bayar = String.valueOf(itung.bayar);
                                    Kembalian = String.valueOf(itung.kembali);
                                    idkasir = ksr;
                                    idusr = usr;

                                    String sql = "INSERT INTO pembelian (id_trans,id_kasir,id_user,kode,nama_menu,harga,jumlah,subtotal,total,bayar,kembalian,tanggal,waktu) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                                    PreparedStatement pst = con.prepareStatement(sql);
                                    pst.setInt(1, Integer.parseInt(Id_trans));
                                    pst.setInt(2, Integer.parseInt(idkasir));
                                    pst.setInt(3, Integer.parseInt(idusr));
                                    pst.setString(4, Kode);
                                    pst.setString(5, Nama_menu);
                                    pst.setString(6, Harga);
                                    pst.setInt(7, Integer.parseInt(Jumlah));
                                    pst.setInt(8, Integer.parseInt(Subtotal));
                                    pst.setInt(9, Integer.parseInt(Total));
                                    pst.setInt(10, Integer.parseInt(Bayar));
                                    pst.setInt(11, Integer.parseInt(Kembalian));
                                    pst.setString(12, txtTanggal.getText());
                                    pst.setString(13, txtWaktu.getText());
                                    pst.execute();

                                    }
                            
                    }
                    
                    String file ="src/cetak/struk.jasper";
                    JasperPrint jp = JasperFillManager.fillReport(file, null, koneksi.konek_db.konek());
                    JasperViewer jv = new JasperViewer(jp,false);
                    jv.setVisible(true);
                    
                    aturStok();
                    cekStok();
                    
                    st = con.createStatement();
                    String clear = "DELETE FROM keranjang";
                    st.executeUpdate(clear);
                    ((DefaultTableModel)tblMenu1.getModel()).setNumRows(0);
                    txtTotal.setText("0.00");
                }
//                    
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_btnTambahActionPerformed

    private void jPanel15MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel15MouseEntered
        setColor(jPanel15);
    }//GEN-LAST:event_jPanel15MouseEntered

    private void jPanel15MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel15MouseExited
        
        resetColor(jPanel15);
    }//GEN-LAST:event_jPanel15MouseExited

    private void jPanel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel15MouseClicked
        formManage ma = new formManage();
        ma.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jPanel15MouseClicked

    private void jPanel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7MouseClicked
        formLogin log = new formLogin();
        log.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jPanel7MouseClicked

    private void tblMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblMenu1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblMenu1MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(formTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new formTransaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnBatal1;
    private javax.swing.JButton btnBatal2;
    private javax.swing.JButton btnTambah;
    private javax.swing.JButton btnTambah1;
    private javax.swing.JComboBox<String> cmbCari;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSpinner jumlah;
    private javax.swing.JTable tblMenu;
    private javax.swing.JTable tblMenu1;
    private javax.swing.JTextField txtCari;
    private javax.swing.JLabel txtCekstok1;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtKode;
    private javax.swing.JTextField txtMenu;
    private javax.swing.JLabel txtTanggal;
    private javax.swing.JLabel txtTotal;
    private javax.swing.JLabel txtTotal1;
    private javax.swing.JLabel txtWaktu;
    // End of variables declaration//GEN-END:variables

}
