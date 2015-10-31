/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spv;

import Conexion.Conexion;
import Ventanas.Crear;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Javier
 */
public class MainScreen extends javax.swing.JPanel {

    SPV spv;
    public boolean acces=false;
    public boolean inicio=true;
    protected String p;
    protected String u;
    int in =0;
    
    public MainScreen(SPV spv) {
        this.spv = spv;
        initComponents();
    }
    
    public void Autenticacion(){
        Conexion cc = new Conexion();
        Connection cn = cc.Conexion();
        ResultSet rs = null;
        
        if(cc.GetConexion()==1){
                Notificar.setText("No hay Conexion con Base de Datos");
        }
        
        u = Usuario.getText();
        p = Contraseña.getText();
        
        PreparedStatement pst;
        
        try {
            pst = cn.prepareStatement("SELECT * FROM usuarios WHERE usuario=? AND contraseña=?");
            pst.setString(1, u);
            pst.setString(2, p);
            rs = pst.executeQuery();
            
        if(!acces){
            if(rs.next()){
                    acces=true;
                    in=4;
                    MFrame fr = new MFrame();
                    setVisible(true);
                    spv.Dispose();
                    intento.setText("¡Acceso Consedido!");
            }else{
                    if(in<3 && !rs.next()){
                    in++;
                    intento.setText("¡Acceso DENEGADO!");
                    Notificar.setForeground(Color.red);
                        if(in==1){
                            Notificar.setText("Intento 1");
                        }else if (in == 2){
                            Notificar.setText("Intento 2");
                            intento.setText("¡Acceso DENEGADO! Queda 1 Intento");
                        }else if(in == 3){
                            Notificar.setText("Intento 3");
                            Notificar.setForeground(Color.red);
                        }


                    }
                    if(in>=3 && !rs.next()){
                    intento.setText("Numero de Intentos Superados, Cerrando");
                        try {


                            intento.setText("Numero de Intentos Superados, Cerrando");
                            TimeUnit.SECONDS.sleep(2);
                            System.exit(0);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(MainScreen.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
            }
        }
        } catch (Exception e) {}
    }


    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        Contraseña = new javax.swing.JPasswordField();
        Usuario = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Notificar = new javax.swing.JLabel();
        intento = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(378, 206));
        setMinimumSize(new java.awt.Dimension(378, 206));
        setPreferredSize(new java.awt.Dimension(378, 206));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Serverbutton.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("Entrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        Contraseña.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Contraseña.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ContraseñaKeyReleased(evt);
            }
        });

        Usuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Usuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                UsuarioKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Autenticacion");

        jLabel2.setText("Usuario");

        jLabel3.setText("Contraseña");

        Notificar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Notificar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        intento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jButton3.setText("Crear");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(51, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Contraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addGap(61, 61, 61)
                        .addComponent(jButton1)))
                .addGap(40, 40, 40)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Notificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(intento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Contraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(jButton3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(intento, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Notificar, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        spv.Content(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Autenticacion();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void UsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_UsuarioKeyReleased
        int key = evt.getKeyCode();
        if(key == KeyEvent.VK_ENTER){
            Autenticacion();
        }
    }//GEN-LAST:event_UsuarioKeyReleased

    private void ContraseñaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ContraseñaKeyReleased
        int key = evt.getKeyCode();
        if(key == KeyEvent.VK_ENTER){
            Autenticacion();
        }
    }//GEN-LAST:event_ContraseñaKeyReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Crear c = new Crear();
        c.setSize(800, 600);
        c.setTitle("Herramienta de Creado de Base de Datos");
        c.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        c.setLocationRelativeTo(null);
        c.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField Contraseña;
    private javax.swing.JLabel Notificar;
    private javax.swing.JTextField Usuario;
    private javax.swing.JLabel intento;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
