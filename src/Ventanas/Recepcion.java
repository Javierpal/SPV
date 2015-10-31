/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

/**
 *
 * @author Javier
 */
public class Recepcion extends javax.swing.JPanel {

    /**
     * Creates new form Recepcion
     */
    public Recepcion() {
        initComponents();
    }

    void init(){
        bImporte.setEnabled(false);
        bTotal.setEditable(false);
    }
    
    private void Limpiar(){
        bCodigo.setText("");
        bNombre.setText("");
        bCantidad.setText("");
        bPrecio.setText("");
        bDescuento.setText("");
        bIVA.setText("");
        bTotal.setText("");
        bImporte.setText("");
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        bCodigo = new javax.swing.JTextField();
        bNombre = new javax.swing.JTextField();
        bCantidad = new javax.swing.JTextField();
        bPrecio = new javax.swing.JTextField();
        bDescuento = new javax.swing.JTextField();
        bIVA = new javax.swing.JTextField();
        bImporte = new javax.swing.JTextField();
        bTotal = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel2.add(jScrollPane1);

        jPanel3.add(jPanel2);

        jPanel1.setMaximumSize(new java.awt.Dimension(98301, 130));
        jPanel1.setMinimumSize(new java.awt.Dimension(634, 130));
        jPanel1.setPreferredSize(new java.awt.Dimension(683, 130));
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel9.setLayout(new javax.swing.BoxLayout(jPanel9, javax.swing.BoxLayout.LINE_AXIS));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Codigo");
        jLabel2.setMaximumSize(new java.awt.Dimension(240, 14));
        jLabel2.setMinimumSize(new java.awt.Dimension(240, 14));
        jLabel2.setPreferredSize(new java.awt.Dimension(240, 14));
        jPanel9.add(jLabel2);

        jLabel3.setText("Nombre");
        jLabel3.setMaximumSize(new java.awt.Dimension(65465456, 14));
        jLabel3.setMinimumSize(new java.awt.Dimension(50, 14));
        jLabel3.setPreferredSize(new java.awt.Dimension(175, 14));
        jPanel9.add(jLabel3);

        jLabel4.setText("Cantidad");
        jLabel4.setMaximumSize(new java.awt.Dimension(80, 14));
        jLabel4.setMinimumSize(new java.awt.Dimension(80, 14));
        jLabel4.setPreferredSize(new java.awt.Dimension(80, 14));
        jPanel9.add(jLabel4);

        jLabel5.setText("Precio");
        jLabel5.setMaximumSize(new java.awt.Dimension(55, 14));
        jLabel5.setMinimumSize(new java.awt.Dimension(55, 14));
        jLabel5.setPreferredSize(new java.awt.Dimension(55, 14));
        jPanel9.add(jLabel5);

        jLabel6.setText("Descuento");
        jLabel6.setMaximumSize(new java.awt.Dimension(65, 14));
        jLabel6.setMinimumSize(new java.awt.Dimension(65, 14));
        jLabel6.setPreferredSize(new java.awt.Dimension(65, 14));
        jPanel9.add(jLabel6);

        jLabel7.setText("I.V.A");
        jLabel7.setMaximumSize(new java.awt.Dimension(60, 14));
        jLabel7.setMinimumSize(new java.awt.Dimension(60, 14));
        jLabel7.setPreferredSize(new java.awt.Dimension(60, 14));
        jPanel9.add(jLabel7);

        jLabel8.setText("Importe");
        jLabel8.setMaximumSize(new java.awt.Dimension(80, 14));
        jLabel8.setMinimumSize(new java.awt.Dimension(80, 14));
        jLabel8.setPreferredSize(new java.awt.Dimension(80, 14));
        jPanel9.add(jLabel8);

        jLabel9.setText("Total");
        jLabel9.setMaximumSize(new java.awt.Dimension(50, 14));
        jLabel9.setMinimumSize(new java.awt.Dimension(50, 14));
        jLabel9.setPreferredSize(new java.awt.Dimension(50, 14));
        jPanel9.add(jLabel9);

        jPanel1.add(jPanel9);

        jPanel5.setMaximumSize(new java.awt.Dimension(2147483647, 27));
        jPanel5.setMinimumSize(new java.awt.Dimension(634, 27));
        jPanel5.setPreferredSize(new java.awt.Dimension(683, 27));
        jPanel5.setLayout(new javax.swing.BoxLayout(jPanel5, javax.swing.BoxLayout.LINE_AXIS));

        jLabel10.setText("Producto");
        jPanel5.add(jLabel10);

        jButton1.setText("...");
        jPanel5.add(jButton1);

        bCodigo.setMaximumSize(new java.awt.Dimension(150, 2147483647));
        bCodigo.setMinimumSize(new java.awt.Dimension(150, 20));
        bCodigo.setPreferredSize(new java.awt.Dimension(150, 20));
        jPanel5.add(bCodigo);
        jPanel5.add(bNombre);

        bCantidad.setMaximumSize(new java.awt.Dimension(65, 2147483647));
        bCantidad.setMinimumSize(new java.awt.Dimension(65, 20));
        bCantidad.setPreferredSize(new java.awt.Dimension(65, 20));
        jPanel5.add(bCantidad);

        bPrecio.setMaximumSize(new java.awt.Dimension(65, 2147483647));
        bPrecio.setMinimumSize(new java.awt.Dimension(65, 20));
        bPrecio.setPreferredSize(new java.awt.Dimension(65, 20));
        jPanel5.add(bPrecio);

        bDescuento.setMaximumSize(new java.awt.Dimension(65, 2147483647));
        bDescuento.setMinimumSize(new java.awt.Dimension(65, 20));
        bDescuento.setPreferredSize(new java.awt.Dimension(65, 20));
        jPanel5.add(bDescuento);

        bIVA.setMaximumSize(new java.awt.Dimension(65, 2147483647));
        bIVA.setMinimumSize(new java.awt.Dimension(65, 20));
        bIVA.setPreferredSize(new java.awt.Dimension(65, 20));
        jPanel5.add(bIVA);

        bImporte.setMaximumSize(new java.awt.Dimension(65, 2147483647));
        bImporte.setMinimumSize(new java.awt.Dimension(65, 20));
        bImporte.setPreferredSize(new java.awt.Dimension(65, 20));
        jPanel5.add(bImporte);

        bTotal.setMaximumSize(new java.awt.Dimension(65, 2147483647));
        bTotal.setMinimumSize(new java.awt.Dimension(65, 20));
        bTotal.setPreferredSize(new java.awt.Dimension(65, 20));
        jPanel5.add(bTotal);

        jPanel1.add(jPanel5);

        jPanel10.setMaximumSize(new java.awt.Dimension(32767, 10));
        jPanel10.setMinimumSize(new java.awt.Dimension(0, 10));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 704, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel10);

        jPanel6.setMaximumSize(new java.awt.Dimension(2147483647, 80));
        jPanel6.setMinimumSize(new java.awt.Dimension(137, 80));
        jPanel6.setPreferredSize(new java.awt.Dimension(683, 80));
        jPanel6.setLayout(new javax.swing.BoxLayout(jPanel6, javax.swing.BoxLayout.LINE_AXIS));

        jPanel7.setPreferredSize(new java.awt.Dimension(90, 80));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
        );

        jPanel6.add(jPanel7);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel1.setText("TOTAL:");
        jPanel6.add(jLabel1);

        jTextField2.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jPanel6.add(jTextField2);

        jPanel8.setPreferredSize(new java.awt.Dimension(90, 80));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
        );

        jPanel6.add(jPanel8);

        jPanel1.add(jPanel6);

        jPanel3.add(jPanel1);

        add(jPanel3);

        jPanel4.setMaximumSize(new java.awt.Dimension(150, 32767));
        jPanel4.setMinimumSize(new java.awt.Dimension(150, 0));
        jPanel4.setPreferredSize(new java.awt.Dimension(150, 501));
        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.PAGE_AXIS));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 427, Short.MAX_VALUE)
        );

        jPanel4.add(jPanel11);

        jPanel12.setMaximumSize(new java.awt.Dimension(2147483647, 130));
        jPanel12.setMinimumSize(new java.awt.Dimension(76, 130));
        jPanel12.setPreferredSize(new java.awt.Dimension(150, 130));
        jPanel12.setLayout(new javax.swing.BoxLayout(jPanel12, javax.swing.BoxLayout.PAGE_AXIS));

        jButton2.setText("Importar a Excel");
        jButton2.setMaximumSize(new java.awt.Dimension(545632485, 23));
        jPanel12.add(jButton2);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 27, Short.MAX_VALUE)
        );

        jPanel12.add(jPanel13);

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setText("Recepcion");
        jButton3.setMaximumSize(new java.awt.Dimension(5642198, 80));
        jButton3.setMinimumSize(new java.awt.Dimension(100, 80));
        jButton3.setPreferredSize(new java.awt.Dimension(90, 80));
        jPanel12.add(jButton3);

        jPanel4.add(jPanel12);

        add(jPanel4);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField bCantidad;
    private javax.swing.JTextField bCodigo;
    private javax.swing.JTextField bDescuento;
    private javax.swing.JTextField bIVA;
    private javax.swing.JTextField bImporte;
    private javax.swing.JTextField bNombre;
    private javax.swing.JTextField bPrecio;
    private javax.swing.JTextField bTotal;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
