/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package input_data;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Lala
 */
class DuplicateEntryException extends Exception {

    public DuplicateEntryException(String message) {
        super(message);
    }
}
public class pembayaran_krsform {
    Connection Konek = new koneksi().configDB();

    public void insert(String npm, String semester, String total_biaya, String total_krs, String tanggal_bayar) throws DuplicateEntryException {
        try {
            String SQL = "INSERT INTO `pembayaran_krs`(`npm`, `semester`, `total_biaya`, `total_krs`, `tanggal_bayar`) VALUES (?, ?, ?, ?, ?)";
            
            try (PreparedStatement ps = Konek.prepareStatement(SQL)) {
                ps.setString(1, npm);
                ps.setString(2, semester);
                ps.setString(3, total_biaya);
                ps.setString(4, total_krs);
                ps.setString(5, tanggal_bayar);
                
                ps.executeUpdate();
            }
        } catch (MySQLIntegrityConstraintViolationException e) {
            throw new DuplicateEntryException("id_pembayaran sudah ada dalam database.");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void tabel(String sql, DefaultTableModel model) {
        try {
            try (Statement statement = Konek.createStatement(); ResultSet rs = statement.executeQuery(sql)) {
                while (rs.next()) {
                    int id_pembayaran = rs.getInt(1);
                    String npm = rs.getString(2);
                    String semester = rs.getString(3);
                    String total_biaya = rs.getString(4);
                    String total_krs = rs.getString(5);
                    String tanggal_bayar = rs.getString(6);
//
                    String[] data = {String.valueOf(id_pembayaran), npm, semester, total_biaya, total_krs, tanggal_bayar};
                    model.addRow(data);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(int id_pembayaran, String npm, String semester, String total_biaya, String total_krs, String tanggal_bayar) {
        try {
            String SQL = "UPDATE `pembayaran_krs` SET `npm`=?,`semester`=?,`total_biaya`=?,`total_krs`=?,`tanggal_bayar`=? WHERE `id_pembayaran` = ? ";
            
            try (PreparedStatement ps = Konek.prepareStatement(SQL)) {
                ps.setString(1, npm);
                ps.setString(2, semester);
                ps.setString(3, total_biaya);
                ps.setString(4, total_krs);
                ps.setString(5, tanggal_bayar);
                ps.setInt(6, id_pembayaran);
                ps.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(int id_pembayaran_krs) {
        try {
            String SQL = "DELETE FROM pembayaran_krs WHERE id_pembayaran=?";
            
            try (PreparedStatement ps = Konek.prepareStatement(SQL)) {
                ps.setInt(1, id_pembayaran_krs);
                
                ps.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
