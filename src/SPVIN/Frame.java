/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SPVIN;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author Javier
 */
public class Frame extends JFrame{
    public Frame(){
        this.setTitle("Sistema de Punto de Venta");
        this.setSize(new Dimension (800, 600));
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
    
    public void Dispose(){
        dispose();
    }
}
