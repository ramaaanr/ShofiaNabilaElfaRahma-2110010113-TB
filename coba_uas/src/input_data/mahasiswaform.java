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

public class mahasiswaform {

    Connection Konek = new koneksi().configDB();

    public void insert(String npm, String nama, String jenis_kelamin, String prodi, String semester, String program) throws DuplicateEntryException {
        try {
            String SQL = "INSERT INTO `mahasiswa`(`npm`, `nama`, `jenis_kelamin`, `prodi`, `semester`, `program`)  VALUES (?, ?, ?, ?, ?, ?)";

            try (PreparedStatement ps = Konek.prepareStatement(SQL)) {
                ps.setString(1, npm);
                ps.setString(2, nama);
                ps.setString(3, jenis_kelamin);
                ps.setString(4, prodi);
                ps.setString(5, semester);
                ps.setString(6, program);
                ps.executeUpdate();
                System.out.println(npm + " Sukses");
            }
        } catch (MySQLIntegrityConstraintViolationException e) {
            throw new DuplicateEntryException("NPM " + npm + " sudah ada dalam database.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public LinkedList<String> getNPM() {
        String sql = "SELECT npm FROM mahasiswa";
        LinkedList<String> listNPM = new LinkedList<String>();
        try {
            try (Statement statement = Konek.createStatement(); ResultSet rs = statement.executeQuery(sql)) {
                while (rs.next()) {
                    listNPM.add(rs.getString(1));
                }
            }
            
        } catch (Exception e) {
            System.err.println(e);
        }
        return listNPM;
    }

    public void tabel(String sql, DefaultTableModel model) {
        try {
            try (Statement statement = Konek.createStatement(); ResultSet rs = statement.executeQuery(sql)) {
                while (rs.next()) {
                    String npm = rs.getString(1);
                    String nama = rs.getString(2);
                    String jenis_kelamin = rs.getString(3);
                    String prodi = rs.getString(4);
                    String semester = rs.getString(5);
                    String program = rs.getString(6);

                    String[] data = {npm, nama, jenis_kelamin, prodi, semester, program};
                    model.addRow(data);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(String npm, String nama, String jenis_kelamin, String prodi, String semester, String program) {
        try {
            String SQL = "UPDATE mahasiswa SET nama=?, jenis_kelamin=?, prodi=?, semester=?, program=? WHERE npm=?";

            try (PreparedStatement ps = Konek.prepareStatement(SQL)) {
                ps.setString(1, nama);
                ps.setString(2, jenis_kelamin);
                ps.setString(3, prodi);
                ps.setString(4, semester);
                ps.setString(5, program);
                ps.setString(6, npm);

                ps.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(String npm) {
        try {
            String SQL = "DELETE FROM mahasiswa WHERE npm=?";

            try (PreparedStatement ps = Konek.prepareStatement(SQL)) {
                ps.setString(1, npm);

                ps.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
