/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fungsi;
import java.sql.*;
/**
 *
 * @author T O M
 */ 
public class hitung {
    public Statement st = null;
    public ResultSet rs = null;
    
    Connection con = koneksi.konek_db.konek();
    
    public int jml, harga, sub, bayar, total, kembali, satuan, jumlah, ttl;

    public int subtotal (int jml, int harga) {
        this.sub = harga * jml;
        return this.sub;
    }
    public int kembalian (int bayar, int total) {
        this.kembali = bayar - total;
        return this.kembali;
    }
    public int totalStok (int satuan, int jumlah) {
        this.ttl = satuan * jumlah;
        return this.ttl;
    }
}
