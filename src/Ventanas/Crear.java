/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Javier
 */
public class Crear extends javax.swing.JFrame {

    int BDNumber = 0;
    int TableNumber = 0;
    boolean first = false;
    JFrame jf;
    JPanel jp;
    
    public Crear() {
        initComponents();
    }
    
    void newBD(){
        jp = new JPanel();
        String name = "tab"+BDNumber;
        jp.setName(name);
        jTabbedPane1.addTab(jTextField1.getText(), jp);
        System.out.println("Hello");
        System.out.println("Name "+jp.getName()+"ID "+jp);
        jTabbedPane1.updateUI();
    }
    
    void newTable(JPanel a){
            JPanel b = new JPanel();
            a.removeAll();
            JTextField[] tables = new JTextField[TableNumber];
            Dimension dm = new Dimension(300,25);
            for(int i = 0; i < tables.length;i++){
                tables[i] = new JTextField("Hola " + i);
                tables[i].setMaximumSize(dm);
                
                a.add(tables[i]);
                a.updateUI();
            }
            
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        NBaseDatos = new javax.swing.JMenuItem();
        NTabla = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        Texto = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        NBaseDatos.setText("Nueva Base de Datos");
        NBaseDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NBaseDatosActionPerformed(evt);
            }
        });
        jPopupMenu1.add(NBaseDatos);

        NTabla.setText("Nueva Tabla");
        NTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NTablaActionPerformed(evt);
            }
        });
        jPopupMenu1.add(NTabla);

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));

        jLabel1.setText("Nombre");
        jPanel2.add(jLabel1);

        jTextField1.setMaximumSize(new java.awt.Dimension(2147483647, 25));
        jTextField1.setMinimumSize(new java.awt.Dimension(6, 25));
        jTextField1.setPreferredSize(new java.awt.Dimension(59, 25));
        jPanel2.add(jTextField1);

        jButton1.setText("Aceptar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);

        jPanel1.add(jPanel2);

        jPanel4.setMaximumSize(new java.awt.Dimension(45655556, 456456456));
        jPanel4.setMinimumSize(new java.awt.Dimension(699, 25));
        jPanel4.setPreferredSize(new java.awt.Dimension(699, 25));
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel4MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanel4MouseReleased(evt);
            }
        });
        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.PAGE_AXIS));

        Texto.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Texto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Texto.setText("Haga Clic Derecho y Escoja Crear Base de Datos, Posteriormente, Cree Tablas");
        Texto.setMaximumSize(new java.awt.Dimension(45655556, 22));
        jPanel4.add(Texto);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jTabbedPane1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTabbedPane1PropertyChange(evt);
            }
        });
        getContentPane().add(jTabbedPane1);

        jMenu1.setText("Archivo");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Nuevo");

        jMenuItem1.setText("Base de Datos");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuItem2.setText("Tabla");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NBaseDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NBaseDatosActionPerformed
        if(first){
            jPopupMenu1.setVisible(false);
            Texto.setVisible(false);
        }
        jf = new JFrame();
        jf.setSize(379, 60);
        jf.setTitle("Escoja el Nombre de la Base de datos");
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jf.setResizable(false);
        jf.setContentPane(jPanel1);
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
    }//GEN-LAST:event_NBaseDatosActionPerformed

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        if(evt.isPopupTrigger()){
            jPopupMenu1.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_formMouseReleased

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        if(evt.getButton() == MouseEvent.BUTTON1){
            jPopupMenu1.setVisible(false);
        }
    }//GEN-LAST:event_formMousePressed

    private void NTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NTablaActionPerformed
        jPopupMenu1.setVisible(false);
        Texto.setVisible(false);
        TableNumber = TableNumber+1;
        newTable(jp);
    }//GEN-LAST:event_NTablaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        BDNumber = BDNumber+1;
        newBD();
        jf.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jPanel4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MousePressed
        if(evt.getButton() == MouseEvent.BUTTON1){
            jPopupMenu1.setVisible(false);
        }
    }//GEN-LAST:event_jPanel4MousePressed

    private void jPanel4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseReleased
        if(evt.isPopupTrigger()){
            jPopupMenu1.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_jPanel4MouseReleased

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        if(first){
            jPopupMenu1.setVisible(false);
            Texto.setVisible(false);
        }
        jf = new JFrame();
        jf.setSize(379, 60);
        jf.setTitle("Escoja el Nombre de la Base de datos");
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jf.setResizable(false);
        jf.setContentPane(jPanel1);
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        jPopupMenu1.setVisible(false);
        Texto.setVisible(false);
        TableNumber = TableNumber+1;
        newTable(jp);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jTabbedPane1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1PropertyChange
        
    }//GEN-LAST:event_jTabbedPane1PropertyChange



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem NBaseDatos;
    private javax.swing.JMenuItem NTabla;
    private javax.swing.JLabel Texto;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
