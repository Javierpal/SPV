/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spv;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 *
 * @author Javier
 */
public class SPV extends JFrame{
    
    public SPV(){
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(SPV.class.getResource("Jumbo.png")));
        this.setTitle("Sistema de Punto de Venta");
        this.setSize(new Dimension (333, 248));
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        Content(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    public void Dispose(){
        dispose();
    }
    
    public void Content(boolean cont){
        
        if(cont){
            this.setContentPane(new MainScreen(this));
            this.setVisible(true);
        }else{
            this.setContentPane(new SerInf(this));
            this.setVisible(true);
        }
    }
    
    public static void main(String[] args) {
        try { 
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"); 
        } catch (Exception ex) { 
            ex.printStackTrace(); 
        }
        SPV spv = new SPV();
    }
    
}
