/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spv;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author Javier
 */
public class SPV extends JFrame{

    public SPV(){
        this.setTitle("Sistema de Punto de Venta");
        this.setSize(new Dimension (1024, 768));
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setContentPane(new MainScreen());
        this.setVisible(true);
    }
    
    
    public static void main(String[] args) {
        SPV spv = new SPV();
    }
    
}
