/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SI;

import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author T O M
 */
public class formRiwayat extends javax.swing.JFrame {
    Statement st = null;
    ResultSet rs = null;
    Connection con = koneksi.konek_db.konek();
    /**
     * Creates new form formRiwayat
     */
    public formRiwayat() {
        initComponents();
        valLevel();

//        tampilData();
        tSize();
    }
    private void valLevel() {
       try {
           st = con.createStatement();
           String vlevel = "SELECT * FROM profil WHERE id_log = (SELECT id_log FROM user_log ORDER BY id_login DESC LIMIT 1) ";
           rs = st.executeQuery(vlevel);
           
           if (rs.next()) {
             String bg = rs.getString("bagian");
             if (bg.equals("kasir")) {
                 jPanel7.setVisible(true);
                 jPanel8.setVisible(false);
                 jPanel15.setVisible(false);
                 tampilDataKsr();
//                 cariKsr();
             } else {
                 jPanel7.setVisible(false);
                 jPanel8.setVisible(true);
                 jPanel15.setVisible(true);
                 tampilData();
//                 cari();
             }
           }
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null, e);
       }
    }
    public void setColor (JPanel panel) {
        panel.setBackground(new Color(97,135,110));
    }
    public void resetColor (JPanel panel) {
        panel.setBackground(new Color(60,98,85));
    }
    private void tampilData() {
        try {
            String tampil = "SELECT * FROM pembelian";
            st = con.createStatement();
            rs = st.executeQuery(tampil);
            
            DefaultTableModel mdl = new DefaultTableModel();
            
            mdl.addColumn("NO");
            mdl.addColumn("ID TRANS");
            mdl.addColumn("KODE MENU");
            mdl.addColumn("NAMA MENU");
            mdl.addColumn("HARGA");
            mdl.addColumn("JUMLAH");
            mdl.addColumn("SUBTOTAL");
            mdl.addColumn("TANGGAL");
            mdl.addColumn("WAKTU");
            
            mdl.getDataVector();
            mdl.fireTableDataChanged();
            mdl.setRowCount(0);
            
            int no = 1;
            while (rs.next()) {
                Object data[] = {
                    no++,
                    rs.getString("id_trans"),
                    rs.getString("kode"),
                    rs.getString("nama_menu"),
                    rs.getString("harga"),
                    rs.getString("jumlah"),
                    rs.getString("subtotal"),
                    rs.getString("tanggal"),
                    rs.getString("waktu")
            };
                mdl.addRow(data);
                tblRiwayat.setModel(mdl);
            }
//            txtKode.setEditable(false);
            tSize();
//            tSize2();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void tampilDataKsr() {
        try {
            String tampil = "SELECT * FROM pembelian WHERE id_user = (SELECT id_log FROM user_log ORDER BY id_login DESC LIMIT 1)";
            st = con.createStatement();
            rs = st.executeQuery(tampil);
            
            DefaultTableModel mdl = new DefaultTableModel();
            
            mdl.addColumn("NO");
            mdl.addColumn("ID TRANS");
            mdl.addColumn("KODE MENU");
            mdl.addColumn("NAMA MENU");
            mdl.addColumn("HARGA");
            mdl.addColumn("JUMLAH");
            mdl.addColumn("SUBTOTAL");
            mdl.addColumn("TANGGAL");
            mdl.addColumn("WAKTU");
            
            mdl.getDataVector();
            mdl.fireTableDataChanged();
            mdl.setRowCount(0);
            
            int no = 1;
            while (rs.next()) {
                Object data[] = {
                    no++,
                    rs.getString("id_trans"),
                    rs.getString("kode"),
                    rs.getString("nama_menu"),
                    rs.getString("harga"),
                    rs.getString("jumlah"),
                    rs.getString("subtotal"),
                    rs.getString("tanggal"),
                    rs.getString("waktu")
            };
                mdl.addRow(data);
                tblRiwayat.setModel(mdl);
            }
//            txtKode.setEditable(false);
            tSize();
//            tSize2();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void tSize() {
        tblRiwayat.getColumnModel().getColumn(0).setPreferredWidth(5);
        tblRiwayat.getColumnModel().getColumn(1).setPreferredWidth(10);
        tblRiwayat.getColumnModel().getColumn(2).setPreferredWidth(30);
        tblRiwayat.getColumnModel().getColumn(3).setPreferredWidth(150);
        tblRiwayat.getColumnModel().getColumn(4).setPreferredWidth(20);
        tblRiwayat.getColumnModel().getColumn(5).setPreferredWidth(20);
        tblRiwayat.getColumnModel().getColumn(6).setPreferredWidth(20);
        tblRiwayat.getColumnModel().getColumn(7).setPreferredWidth(40);
        tblRiwayat.getColumnModel().getColumn(8).setPreferredWidth(30);
        
    }
    private void cari() {
        try {
            String tampil = "SELECT * FROM pembelian WHERE tanggal BETWEEN '"+ txtTanggal1.getText() +"' AND '"+ txtTanggal2.getText() +"'";
            st = con.createStatement();
            rs = st.executeQuery(tampil);
            
            
                DefaultTableModel mdl = new DefaultTableModel();

                mdl.addColumn("NO");
                mdl.addColumn("ID TRANS");
                mdl.addColumn("KODE MENU");
                mdl.addColumn("NAMA MENU");
                mdl.addColumn("HARGA");
                mdl.addColumn("JUMLAH");
                mdl.addColumn("SUBTOTAL");
                mdl.addColumn("TANGGAL");
                mdl.addColumn("WAKTU");

                mdl.getDataVector();
                mdl.fireTableDataChanged();
                mdl.setRowCount(0);

                int no = 1;
                while (rs.next()) {
                    Object data[] = {
                        no++,
                        rs.getString("id_trans"),
                        rs.getString("kode"),
                        rs.getString("nama_menu"),
                        rs.getString("harga"),
                        rs.getString("jumlah"),
                        rs.getString("subtotal"),
                        rs.getString("tanggal"),
                        rs.getString("waktu")
                };
                    mdl.addRow(data);
                    tblRiwayat.setModel(mdl);
                }
//            txtKode.setEditable(false);
            tSize();
//            tSize2();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void cariKsr() {
        try {
            String tampil = "SELECT * FROM pembelian WHERE id_user = (SELECT id_log FROM user_log ORDER BY id_login DESC LIMIT 1) AND tanggal BETWEEN '"+ txtTanggal1.getText() +"' AND '"+ txtTanggal2.getText() +"'";
            st = con.createStatement();
            rs = st.executeQuery(tampil);
            
            
                DefaultTableModel mdl = new DefaultTableModel();

                mdl.addColumn("NO");
                mdl.addColumn("ID TRANS");
                mdl.addColumn("KODE MENU");
                mdl.addColumn("NAMA MENU");
                mdl.addColumn("HARGA");
                mdl.addColumn("JUMLAH");
                mdl.addColumn("SUBTOTAL");
                mdl.addColumn("TANGGAL");
                mdl.addColumn("WAKTU");

                mdl.getDataVector();
                mdl.fireTableDataChanged();
                mdl.setRowCount(0);

                int no = 1;
                while (rs.next()) {
                    Object data[] = {
                        no++,
                        rs.getString("id_trans"),
                        rs.getString("kode"),
                        rs.getString("nama_menu"),
                        rs.getString("harga"),
                        rs.getString("jumlah"),
                        rs.getString("subtotal"),
                        rs.getString("tanggal"),
                        rs.getString("waktu")
                };
                    mdl.addRow(data);
                    tblRiwayat.setModel(mdl);
                }
//            txtKode.setEditable(false);
            tSize();
//            tSize2();
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

        dateChooser1 = new cambodia.raven.DateChooser();
        dateChooser2 = new cambodia.raven.DateChooser();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblRiwayat = new javax.swing.JTable();
        jLabel19 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTanggal1 = new javax.swing.JTextField();
        cal1 = new javax.swing.JButton();
        btnCari = new javax.swing.JButton();
        txtTanggal2 = new javax.swing.JTextField();
        cal2 = new javax.swing.JButton();
        btnTambah = new javax.swing.JButton();

        dateChooser1.setButton(cal1);
        dateChooser1.setTextRefernce(txtTanggal1);

        dateChooser2.setButton(cal2);
        dateChooser2.setTextRefernce(txtTanggal2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setUndecorated(true);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(1348, 788));

        jPanel5.setBackground(new java.awt.Color(60, 98, 85));
        jPanel5.setPreferredSize(new java.awt.Dimension(245, 617));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/sdd.png"))); // NOI18N

        jPanel6.setBackground(new java.awt.Color(60, 98, 85));
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

        jLabel5.setFont(new java.awt.Font("Bahnschrift", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("HOME");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/home.png"))); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18))
        );

        jPanel7.setBackground(new java.awt.Color(60, 98, 85));
        jPanel7.setPreferredSize(new java.awt.Dimension(156, 56));
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

        jLabel7.setFont(new java.awt.Font("Bahnschrift", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("TRANSAKSI");

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/money.png"))); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18))
        );

        jPanel8.setBackground(new java.awt.Color(60, 98, 85));
        jPanel8.setPreferredSize(new java.awt.Dimension(114, 56));
        jPanel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel8MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel8MouseExited(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Bahnschrift", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("MENU");

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/chocolate.png"))); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18))
        );

        jPanel9.setBackground(new java.awt.Color(97, 135, 110));
        jPanel9.setPreferredSize(new java.awt.Dimension(225, 56));
        jPanel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel9MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel9MouseExited(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Bahnschrift", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("RIWAYAT TRANSAKSI");

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/time (3).png"))); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18))
        );

        jPanel10.setBackground(new java.awt.Color(60, 98, 85));
        jPanel10.setPreferredSize(new java.awt.Dimension(129, 56));
        jPanel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel10MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel10MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel10MouseExited(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Bahnschrift", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("LOGOUT");

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/arrow.png"))); // NOI18N

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel13))
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

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel4)
                .addContainerGap(10, Short.MAX_VALUE))
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel4)
                .addGap(111, 111, 111)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel11.setBackground(new java.awt.Color(60, 98, 85));

        jLabel15.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("x");
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Bahnschrift", 1, 36)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("RIWAYAT TRANSAKSI");

        jLabel17.setFont(new java.awt.Font("Cascadia Code", 0, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Es Teh Kampul Indonesia");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 461, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addGap(19, 19, 19))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17)))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        tblRiwayat.setFont(new java.awt.Font("Bahnschrift", 0, 12)); // NOI18N
        tblRiwayat.setForeground(new java.awt.Color(97, 135, 110));
        tblRiwayat.setModel(new javax.swing.table.DefaultTableModel(
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
        tblRiwayat.setRowHeight(35);
        tblRiwayat.setSelectionBackground(new java.awt.Color(97, 135, 110));
        jScrollPane2.setViewportView(tblRiwayat);

        jLabel19.setFont(new java.awt.Font("Bahnschrift", 1, 36)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(97, 135, 110));
        jLabel19.setText("RIWAYAT TRANSAKSI");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(97, 135, 110));
        jLabel2.setText("SAMPAI");

        txtTanggal1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtTanggal1.setForeground(new java.awt.Color(97, 135, 110));
        txtTanggal1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTanggal1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(97, 135, 110)));

        cal1.setBackground(new java.awt.Color(97, 135, 110));
        cal1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/calendar (1).png"))); // NOI18N
        cal1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cal1ActionPerformed(evt);
            }
        });

        btnCari.setBackground(new java.awt.Color(97, 135, 110));
        btnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search.png"))); // NOI18N
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });

        txtTanggal2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtTanggal2.setForeground(new java.awt.Color(97, 135, 110));
        txtTanggal2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTanggal2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(97, 135, 110)));

        cal2.setBackground(new java.awt.Color(97, 135, 110));
        cal2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/calendar (1).png"))); // NOI18N
        cal2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cal2ActionPerformed(evt);
            }
        });

        btnTambah.setBackground(new java.awt.Color(97, 135, 110));
        btnTambah.setFont(new java.awt.Font("Bahnschrift", 1, 15)); // NOI18N
        btnTambah.setForeground(new java.awt.Color(255, 255, 255));
        btnTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/printer.png"))); // NOI18N
        btnTambah.setText(" CETAK");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel19)
                        .addGap(285, 285, 285))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(87, 87, 87)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 790, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(txtTanggal1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cal1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel2)
                                        .addGap(8, 8, 8)
                                        .addComponent(txtTanggal2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cal2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnCari, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(25, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTanggal2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cal2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCari, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTanggal1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(cal1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 1200, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseClicked
        home homi = new home();
        homi.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jPanel6MouseClicked

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

    private void jPanel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseClicked
        formMenu menu = new formMenu();
        menu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jPanel8MouseClicked

    private void jPanel8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseEntered
        setColor(jPanel8);
    }//GEN-LAST:event_jPanel8MouseEntered

    private void jPanel8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseExited
        resetColor(jPanel8);
    }//GEN-LAST:event_jPanel8MouseExited

    private void jPanel9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MouseEntered
//        setColor(jPanel6);
    }//GEN-LAST:event_jPanel9MouseEntered

    private void jPanel9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MouseExited
//        resetColor(jPanel6);
    }//GEN-LAST:event_jPanel9MouseExited

    private void jPanel10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel10MouseEntered
        setColor(jPanel10);
    }//GEN-LAST:event_jPanel10MouseEntered

    private void jPanel10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel10MouseExited
        resetColor(jPanel10);
    }//GEN-LAST:event_jPanel10MouseExited

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel15MouseClicked

    private void jPanel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7MouseClicked
        formTransaksi trans = new formTransaksi();
        trans.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jPanel7MouseClicked

    private void cal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cal1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cal1ActionPerformed

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        try {
           st = con.createStatement();
           String vlevel = "SELECT * FROM profil WHERE id_log = (SELECT id_log FROM user_log ORDER BY id_login DESC LIMIT 1) ";
           rs = st.executeQuery(vlevel);
           
           if (rs.next()) {
             String bg = rs.getString("bagian");
             if (bg.equals("kasir")) {
                 cariKsr();
             } else {
                 cari();
             }
           }
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null, e);
       }
    }//GEN-LAST:event_btnCariActionPerformed

    private void cal2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cal2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cal2ActionPerformed

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        try {
            String delete = ("DELETE FROM cetak");
            st.executeUpdate(delete);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        DefaultTableModel mdl = (DefaultTableModel) tblRiwayat.getModel();
        if (mdl.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null,"Tabel Kosong !");
        } else {
            String id,kode,nama,harga,jumlah,subtotal,tanggal,waktu;
            try {
                for (int i=0; i<mdl.getRowCount(); i++) {
                    id = mdl.getValueAt(i, 1).toString();
                    kode = mdl.getValueAt(i, 2).toString();
                    nama = mdl.getValueAt(i, 3).toString();
                    harga = mdl.getValueAt(i, 4).toString();
                    jumlah = mdl.getValueAt(i, 5).toString();
                    subtotal = mdl.getValueAt(i, 6).toString();
                    tanggal = mdl.getValueAt(i, 7).toString();
                    waktu = mdl.getValueAt(i, 8).toString();
                    
                    
                    String sql = "INSERT INTO cetak (id_trans,kode,nama_menu,harga,jumlah,subtotal,tanggal,waktu) VALUES (?, ?, ?, ?, ?, ?, ?,?)";

                    PreparedStatement pst = con.prepareStatement(sql);
                    pst.setString(1, id);
                    pst.setString(2, kode);
                    pst.setString(3, nama);
                    pst.setString(4, harga);
                    pst.setString(5, jumlah);
                    pst.setString(6, subtotal);
                    pst.setString(7, tanggal);
                    pst.setString(8, waktu);

                    pst.execute();
                }
                String file ="src/cetak/riwayat.jasper";
                JasperPrint jp = JasperFillManager.fillReport(file, null, koneksi.konek_db.konek());
                JasperViewer jv = new JasperViewer(jp,false);
                jv.setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,e);
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

    private void jPanel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel10MouseClicked
        formLogin log = new formLogin();
        log.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jPanel10MouseClicked

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
            java.util.logging.Logger.getLogger(formRiwayat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(formRiwayat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(formRiwayat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(formRiwayat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new formRiwayat().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnTambah;
    private javax.swing.JButton cal1;
    private javax.swing.JButton cal2;
    private cambodia.raven.DateChooser dateChooser1;
    private cambodia.raven.DateChooser dateChooser2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblRiwayat;
    private javax.swing.JTextField txtTanggal1;
    private javax.swing.JTextField txtTanggal2;
    // End of variables declaration//GEN-END:variables
}
