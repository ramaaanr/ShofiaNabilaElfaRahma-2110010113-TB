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
public class nilaiform {
    Connection Konek = new koneksi().configDB();

    public void insert(String npm, String kode_krs, String nilai, String tanggal_input, String dosen, String keterangan) throws DuplicateEntryException {
        try {
            String SQL = "INSERT INTO `nilai`( `npm`, `kode_krs`, `nilai`, `tanggal_input`, `dosen`, `keterangan`) VALUES (?, ?, ?, ?, ?, ?)";
            
            try (PreparedStatement ps = Konek.prepareStatement(SQL)) {
                ps.setString(1, npm);
                ps.setString(2, kode_krs);
                ps.setString(3, nilai);
                ps.setString(4, tanggal_input);
                ps.setString(5, dosen);
                ps.setString(6, keterangan);
                
                ps.executeUpdate();
            }
        } catch (MySQLIntegrityConstraintViolationException e) {
            throw new DuplicateEntryException("id_nilai sudah ada dalam database.");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void tabel(String sql, DefaultTableModel model) {
        try {
            try (Statement statement = Konek.createStatement(); ResultSet rs = statement.executeQuery(sql)) {
                while (rs.next()) {
                    int id_nilai = rs.getInt(1);
                    String npm = rs.getString(2);
                    String kode_krs = rs.getString(3);
                    String nilai = rs.getString(4);
                    String tanggal_input = rs.getString(5);
                    String dosen = rs.getString(6);
                    String keterangan = rs.getString(7);

                    String[] data = {String.valueOf(id_nilai), npm, kode_krs, nilai, tanggal_input, dosen, keterangan };
                    model.addRow(data);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(int id_nilai, String npm, String kode_krs, String nilai, String tanggal_input, String dosen, String keterangan) {
        try {
            String SQL = "UPDATE `nilai` SET `npm`=?,`kode_krs`=?,`nilai`=?,`tanggal_input`=?,`dosen`=?, `keterangan`=? WHERE `id_nilai` = ? ";
            
            try (PreparedStatement ps = Konek.prepareStatement(SQL)) {
                ps.setString(1, npm);
                ps.setString(2, kode_krs);
                ps.setString(3, nilai);
                ps.setString(4, tanggal_input);
                ps.setString(5, dosen);
                ps.setString(6, keterangan);
                ps.setInt(7, id_nilai);
                ps.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(int id_nilai) {
        try {
            String SQL = "DELETE FROM nilai WHERE id_nilai=?";
            
            try (PreparedStatement ps = Konek.prepareStatement(SQL)) {
                ps.setInt(1, id_nilai);
                
                ps.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
