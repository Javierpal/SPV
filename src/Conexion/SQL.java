/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class SQL {
    
    static Conexion cc = new Conexion();
    static Connection cn = cc.Conexion();
    static ResultSet rs = null;
    static Statement st;
    static PreparedStatement pst;
    
    public static String[] getDatabases(){
        String a[] = new String[10];
        Connection cn2 = Conexion.getConection();
        try {
            st = cn2.createStatement();
            rs = st.executeQuery("show databases;");
            int i=0;
            while (rs.next()) {
               a[i] = rs.getString(1);
               i++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }
    
    public static String[] llenar(String ID, String[] a, int cuantos, String From, String columna){
        String[] model = new String[cuantos];
        try {
            st = cn.createStatement();
            String sql = "SELECT ";
            for(int i = 0; i < cuantos; i++){
                if(i == 0){
                    sql = sql+a[i];
                }else{
                    sql = sql +","+a[i];
                }
            }
            sql = sql + " FROM " + From + " WHERE `"+columna+"`="+ID+"";
            rs = st.executeQuery(sql);
            while (rs.next()) {
               for(int i = 0; i<cuantos; i++){
                    model[i] = rs.getString(a[i]);
                }
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return model;
    }
    
    public static void Insert(String table, String rows[], String values[]){
        String sql = "INSERT INTO " + table;
        for(int i = 0; i < rows.length; i++){
            if(i == 0){
                sql = sql + " (" + rows[i] + ",";
            }else if(i == rows.length-1){
                sql = sql + " " + rows[i] + ")";
            }else{
                sql = sql + " " + rows[i] + ",";
            }
        }
        sql = sql + " VALUES";
        for(int i = 0; i < values.length; i++){
            if(i == 0){
                sql = sql + " ('" + values[i] + "',";
            }else if(i == rows.length-1){
                sql = sql + " '" + values[i] + "')";
            }else{
                sql = sql + " '" + values[i] + "',";
            }
        }
        try {
            pst = cn.prepareStatement(sql);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error al Realizar Operacion");
        }
    }
    
    public static String Ultimo(String columna, String tabla){
        String a = "";
        String sql = "SELECT "+columna+" FROM "+tabla+" ORDER BY "+columna+" DESC LIMIT 1";
        try {
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                a = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Error al Realizar Operacion");
        }
        return a;
    }
    
    public static DefaultTableModel getTables(String sql, String table, String Orderby,int colums, String[] columnas){
        DefaultTableModel modelo = new DefaultTableModel(){
        @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }};
        String []datos = new String[colums];
        for(int a = 0; a < colums; a++){
            modelo.addColumn(columnas[a]);
        }
        try{
            st = cn.createStatement();
            rs = st.executeQuery(sql+" "+table+" "+Orderby);
            while(rs.next()){
                for(int i = 0; i<colums; i++){
                datos[i] = rs.getString(i+1);
                }
                modelo.addRow(datos);
            }
        }catch(SQLException ex){
            Logger.getLogger(SQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return modelo;
    }
    
}
