/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fungsi;
import java.sql.*;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
/**
 *
 * @author T O M
 */
public class hitungLaporan {
    Statement st = null;
    ResultSet rs = null;
    
    Connection con = koneksi.konek_db.konek();
    
    public String item, fav, itemKs, favKs;
    public int hasil, hasilKs, dapat, masuk, keluar;
    
    java.util.Date tgl = new java.util.Date();
        
    SimpleDateFormat sdf =  new SimpleDateFormat("dd-MM-yyyy");
    String dd = sdf.format(tgl);
        
    public String hitungItem () throws SQLException {
        st = con.createStatement();
        String sql = "SELECT SUM(jumlah) AS jml FROM pembelian";
        rs = st.executeQuery(sql);
        
        while(rs.next()) {
            item = rs.getString("jml");
        }
        return this.item;
    }
    public int hitungHasil () throws SQLException {
        st = con.createStatement();
        String sql = "SELECT SUM(subtotal) AS sub FROM pembelian";
        rs = st.executeQuery(sql);
        
        while(rs.next()) {
            hasil = rs.getInt("sub");
        }
        return this.hasil;
    }
    public String favoritMenu () throws SQLException {
        st = con.createStatement();
        String sql = "SELECT nama_menu, COUNT(nama_menu) AS favorit FROM pembelian GROUP BY nama_menu ORDER BY COUNT(nama_menu) DESC LIMIT 1";
        rs = st.executeQuery(sql);
        
        while(rs.next()) {
            fav = rs.getString("nama_menu");
        }
        return this.fav;
    }
    public String itemKsr () throws SQLException {
        st = con.createStatement();
        String sql = "SELECT SUM(jumlah) AS jml FROM pembelian WHERE id_user = (SELECT id_log FROM user_log ORDER BY id_login DESC LIMIT 1) AND tanggal = '"+ dd +"'";
        rs = st.executeQuery(sql);
        
        while(rs.next()) {
            itemKs = rs.getString("jml");
        }
        return this.itemKs;
    }
    public int hitungKsr () throws SQLException {
        st = con.createStatement();
        String sql = "SELECT SUM(subtotal) AS sub FROM pembelian WHERE id_user = (SELECT id_log FROM user_log ORDER BY id_login DESC LIMIT 1) AND tanggal = '"+ dd +"'";
        rs = st.executeQuery(sql);
        
        while(rs.next()) {
            hasilKs = rs.getInt("sub");
        }
        return this.hasilKs;
    }
    public String favoritKsr () throws SQLException {
        st = con.createStatement();
        String sql = "SELECT nama_menu, SUM(jumlah) AS jml FROM pembelian WHERE id_user = (SELECT id_log FROM user_log ORDER BY id_login DESC LIMIT 1) AND tanggal = '"+ dd +"' GROUP BY nama_menu  ORDER BY jml DESC LIMIT 1";
        rs = st.executeQuery(sql);
        
        while(rs.next()) {
            favKs = rs.getString("nama_menu");
        }
        return this.favKs;
    }
    
    public int pendapatan () throws SQLException {
        st = con.createStatement();
        String sql = "SELECT (SELECT SUM(subtotal) FROM pembelian) - (SELECT SUM(total) FROM management) AS pendapatan";
        rs = st.executeQuery(sql);
        
        while (rs.next()) {
            dapat = rs.getInt("pendapatan");
        }
        return this.dapat;
    }
    public int pemasukan () throws SQLException {
        st = con.createStatement();
        String sql = "SELECT SUM(subtotal) AS m FROM pembelian";
        rs = st.executeQuery(sql);
        
        while (rs.next()) {
            masuk = rs.getInt("m");
        }
        return this.masuk;
    }
    public int pengeluaran () throws SQLException {
        st = con.createStatement();
        String sql = "SELECT SUM(total) AS k FROM management";
        rs = st.executeQuery(sql);
        
        while (rs.next()) {
            keluar = rs.getInt("k");
        }
        return this.keluar;
    }
}
