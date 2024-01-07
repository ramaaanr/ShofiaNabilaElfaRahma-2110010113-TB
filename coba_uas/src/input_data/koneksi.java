/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package input_data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class koneksi {
    public koneksi (){
        try {
            String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver);
            System.out.println("sukses memanggil driver");
        } catch (Exception e) {
            System.out.println("gagal memanggil driver:"+e.getMessage());
        }
    }
    public Connection configDB(){
        Connection Koneksi = null;
        try {
            String url = "jdbc:mysql://localhost/aplikasi_pengelolaankrs";
            String user = "root";
            String pass = "";
            
            Koneksi = DriverManager.getConnection(url, user, pass);
            System.out.println("koneksi database sukses");
        } catch (Exception e) {
            System.out.println("koneksi database gagal :"+e.getMessage());
        }
        return Koneksi;
    }  
}