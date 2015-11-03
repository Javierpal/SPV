/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Javier
 */
public class XMLWriter {
    
    String FileName;
    
    Path current = Paths.get("");
    String inf ="";
    String inf2="";
    
    public XMLWriter(String FileName){
        this.FileName = FileName;
        inf = current.toAbsolutePath().toString()+"\\Ventas\\"+FileName+".xml";
        inf2 = current.toAbsolutePath().toString()+"\\Ventas";
    }
    
    public void Write(String items[][]){
        PrintWriter writer;
        File file = new File(inf2);
        if(!file.exists()){
            file.mkdir();
        }
        try {
            writer = new PrintWriter(inf, "UTF-8");
            writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            writer.println("<Venta>");
            for (int i = 0; i < items.length ; i++) {
                writer.println("    <Producto>");
                for(int o = 0; o < items[i].length; o++){
                    if(o == 0){
                        writer.println("    <nombre>" + items[i][o] + "</nombre>");
                    }
                    if(o == 1){
                        writer.println("    <cantidad>" + items[i][o] + "</cantidad>");
                    }
                    if(o == 2){
                        writer.println("    <precio>" + items[i][o] + "</precio>");
                    }
                    if(o == 3){
                        writer.println("    <total>" + items[i][o] + "</total>");
                    }
                }
                writer.println("    </Producto>");
            }
            writer.println("</Venta>");
            writer.flush();
            writer.close();
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(XMLWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
