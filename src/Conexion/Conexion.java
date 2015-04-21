/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import spv.ReadFiles;


public class Conexion {
    
    int Conexion = 0;
    static int Conexion2 = 0;
    
    Connection conectar = null;
    public Connection Conexion(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            //Ejemplo: conectar = DriverManager.getConnection("jdbc:mysql://192.168.1.1:3306/inventarios", "Racktor", "root");
            conectar = DriverManager.getConnection("jdbc:mysql://"+ReadFiles.GetServer()+":3306/"+ReadFiles.GetBD(), ReadFiles.GetBDUsuario(), ReadFiles.GetBDPass());
            System.out.println(conectar);
                Conexion = 2;
        }catch (Exception e){
            System.out.println(e.getMessage());
            Conexion = 1;
        }
        return conectar;
    }
    public int GetConexion(){
        return Conexion;
    }
    
    public static Connection getConection(){
        Connection cn = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            cn = DriverManager.getConnection("jdbc:mysql://"+ReadFiles.GetServer()+":3306", ReadFiles.GetBDUsuario(), ReadFiles.GetBDPass());
                Conexion2 = 2;
        }catch (Exception e){
            System.out.println(e.getMessage());
            Conexion2 = 1;
        }
        return cn;
    }
    
    public static int getBDConexion(){
        return Conexion2;
    }
}