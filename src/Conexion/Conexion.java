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
    
    Connection conectar = null;
    public Connection Conexion(){
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            //conectar = DriverManager.getConnection("jdbc:mysql://192.168.1.1:3306/inventarios", "Racktor", "root");
            conectar = DriverManager.getConnection("jdbc:mysql://"+ReadFiles.GetServer()+"/"+ReadFiles.GetBD(), ReadFiles.GetBDUsuario(), ReadFiles.GetBDPass());
            System.out.println(conectar);
                Conexion = 2;
        }catch (Exception e){
            System.out.println(e.getMessage());
            Conexion = 1;
        }
        return conectar;
    }
    int GetConexion(){
        return Conexion;
    }
    
}