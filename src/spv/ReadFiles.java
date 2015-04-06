/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Javier
 */
public class ReadFiles {
    
    static String a[];
    static String server;
    static String BaseDatos;
    static String Version;
    static String LCVersion;
    static String BDContraseña;
    static String BDUsuario;
    
    static void Leer(){
        String inf = "C:\\ShiftSoft\\Punto de Venta\\inf.txt";
        
        File f = new File(inf); 
        a = new String[6];
        try {
            int i = 0;
            FileInputStream fStream = new FileInputStream(inf);
            BufferedReader in = new BufferedReader(new InputStreamReader(fStream));
            while (in.ready()) {
                a[i] = in.readLine();
                i++;
            }
            in.close();
            
            String Se[] = a[0].split(":");
            String BD[] = a[1].split(":");
            String Ver[] = a[2].split(":");
            String LCver[] = a[3].split(":");
            String BDUs[] = a[4].split(":");
            String BDCo[] = a[5].split(":");
            server = Se[1];
            BaseDatos = BD[1];
            Version = Ver[1];
            LCVersion = LCver[1];
            BDUsuario = BDUs[1];
            if(BDCo.length == 1){
                BDContraseña ="";
            }else{
                BDContraseña = BDCo[1];
            }
            
        } catch (IOException e) {
            System.out.println("File input error");
        }
    }
    
    public static String GetServer(){
        Leer();
        return server;
    }
    public static String GetBD(){
        Leer();
        return BaseDatos;
    }
    public static String GetVersion(){
        Leer();
        return Version;
    }
    public static String GetLCVersion(){
        Leer();
        return LCVersion;
    }
    public static String GetBDUsuario(){
        Leer();
        return BDUsuario;
    }
    public static String GetBDPass(){
        Leer();
        return BDContraseña;
    }

    public static void WriteServer(String ser){
        Leer();
        PrintWriter writer;
        try {
            writer = new PrintWriter("C:\\ShiftSoft\\Punto de Venta\\inf.txt", "UTF-8");
            writer.println("ServerIP:" + ser);
            writer.println("BD:" + BaseDatos);
            writer.println("Ver:" + Version);
            writer.println("LCVer:" + LCVersion);
            writer.println("BDUsuario:" + BDUsuario);
            writer.println("BDContraseña:" + BDContraseña);
            writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SerInf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(SerInf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void WriteBD(String bd){
        Leer();
        PrintWriter writer;
        try {
            writer = new PrintWriter("C:\\ShiftSoft\\Punto de Venta\\inf.txt", "UTF-8");
            writer.println("ServerIP:" + server);
            writer.println("BD:" + bd);
            writer.println("Ver:" + Version);
            writer.println("LCVer:" + LCVersion);
            writer.println("BDUsuario:" + BDUsuario);
            writer.println("BDContraseña:" + BDContraseña);
            writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SerInf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(SerInf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void WriteVersion(String ver){
        Leer();
        PrintWriter writer;
        try {
            writer = new PrintWriter("C:\\ShiftSoft\\Punto de Venta\\inf.txt", "UTF-8");
            writer.println("ServerIP:" + server);
            writer.println("BD:" + BaseDatos);
            writer.println("Ver:" + ver);
            writer.println("LCVer:" + LCVersion);
            writer.println("BDUsuario:" + BDUsuario);
            writer.println("BDContraseña:" + BDContraseña);
            writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SerInf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(SerInf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void WriteLCVer(String lcv){
        Leer();
        PrintWriter writer;
        try {
            writer = new PrintWriter("C:\\ShiftSoft\\Punto de Venta\\inf.txt", "UTF-8");
            writer.println("ServerIP:" + server);
            writer.println("BD:" + BaseDatos);
            writer.println("Ver:" + Version);
            writer.println("LCVer:" + lcv);
            writer.println("BDUsuario:" + BDUsuario);
            writer.println("BDContraseña:" + BDContraseña);
            writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SerInf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(SerInf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void WriteBDUsuario(String usu){
        Leer();
        PrintWriter writer;
        try {
            writer = new PrintWriter("C:\\ShiftSoft\\Punto de Venta\\inf.txt", "UTF-8");
            writer.println("ServerIP:" + server);
            writer.println("BD:" + BaseDatos);
            writer.println("Ver:" + Version);
            writer.println("LCVer:" + LCVersion);
            writer.println("BDUsuario:" + usu);
            writer.println("BDContraseña:" + BDContraseña);
            writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SerInf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(SerInf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void WriteBDPass(String pass){
        Leer();
        PrintWriter writer;
        try {
            writer = new PrintWriter("C:\\ShiftSoft\\Punto de Venta\\inf.txt", "UTF-8");
            writer.println("ServerIP:" + server);
            writer.println("BD:" + BaseDatos);
            writer.println("Ver:" + Version);
            writer.println("LCVer:" + LCVersion);
            writer.println("BDUsuario:" + BDUsuario);
            if(pass.equals(null)){
                writer.println("BDContraseña:");
            }else{
                writer.println("BDContraseña:" + pass);
            }
            writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SerInf.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(SerInf.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
