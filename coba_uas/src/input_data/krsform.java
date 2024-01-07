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
import java.util.LinkedList;
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

public class krsform {

    Connection Konek = new koneksi().configDB();

    public void insert(String kode, String nama, String sks, String dosen, String kelas, String ruang) throws DuplicateEntryException {
        try {
            String SQL = "INSERT INTO `krs` VALUES (?, ?, ?, ?, ?, ?)";

            try (PreparedStatement ps = Konek.prepareStatement(SQL)) {
                ps.setString(1, kode);
                ps.setString(2, nama);
                ps.setString(3, sks);
                ps.setString(4, dosen);
                ps.setString(5, kelas);
                ps.setString(6, ruang);

                ps.executeUpdate();
            }
        } catch (MySQLIntegrityConstraintViolationException e) {
            throw new DuplicateEntryException("Kode KRS " + kode + " sudah ada dalam database.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public LinkedList<String> getKode() {
        String sql = "SELECT kode FROM krs";
        LinkedList<String> listKode = new LinkedList<String>();
        try {
            try (Statement statement = Konek.createStatement(); ResultSet rs = statement.executeQuery(sql)) {
                while (rs.next()) {
                    listKode.add(rs.getString(1));
                }
            }
            
        } catch (Exception e) {
            System.err.println(e);
        }
        return listKode;
    }

    public void tabel(String sql, DefaultTableModel model) {
        try {
            try (Statement statement = Konek.createStatement(); ResultSet rs = statement.executeQuery(sql)) {
                while (rs.next()) {
                    String kode = rs.getString(1);
                    String nama = rs.getString(2);
                    String sks = rs.getString(3);
                    String dosen = rs.getString(4);
                    String kelas = rs.getString(5);
                    String ruang = rs.getString(6);

                    String[] data = {kode, nama, sks, dosen, kelas, ruang};
                    model.addRow(data);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(String kode, String nama, String sks, String dosen, String kelas, String ruang) {
        try {
            String SQL = "UPDATE `krs` SET `nama`=?,`sks`=?,`dosen`=?,`kelas`=?,`ruang`=? WHERE `kode` = ? ";

            try (PreparedStatement ps = Konek.prepareStatement(SQL)) {
                ps.setString(1, nama);
                ps.setString(2, sks);
                ps.setString(3, dosen);
                ps.setString(4, kelas);
                ps.setString(5, ruang);
                ps.setString(6, kode);
                ps.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(String kode) {
        try {
            String SQL = "DELETE FROM krs WHERE kode=?";

            try (PreparedStatement ps = Konek.prepareStatement(SQL)) {
                ps.setString(1, kode);

                ps.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
