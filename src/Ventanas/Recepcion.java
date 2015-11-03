/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import Conexion.SQL;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import spv.SPV;

/**
 *
 * @author Javier
 */
public class Recepcion extends javax.swing.JPanel {

    DefaultTableModel t;
    JFrame producto;

    public Recepcion() {
        initComponents();
        init();
    }
    
    private void tabla(String a[]){
        String cabecera[]={"Clave","Articulo","Cantidad","Precio","Descuento","IVA","Importe", "Total"};
        String datos[][] = {};
        
        t = new DefaultTableModel(datos,cabecera){
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        TablaProductos.getTableHeader().setReorderingAllowed(false);

        TablaProductos.setModel(t);
    }
    
    public boolean norepeticion(){
        String cod="";
        int fila = TablaProductos.getRowCount();
        if(fila != 0){
            for(int i = fila-1; i >= 0; i--){
                System.out.println(fila);
                cod=TablaProductos.getValueAt(i, 0).toString();
                if(cod.equals(bCodigo.getText())){
                    int a = Integer.parseInt(TablaProductos.getValueAt(i, 2).toString())+1;
                    TablaProductos.setValueAt(a, i, 2);
                    TablaProductos.setValueAt(tableImporte(i), i, 6);
                    TablaProductos.setValueAt(tableTotal(i), i, 7);
                    Limpiar();
                    SumaTotal();
                    i = 0;
                    return true;
                }
            }
        }else{
            return false;
        }
        return false;
    }
    
    void SumaTotal(){
        Float cod = 0f;
        int fila = TablaProductos.getRowCount();
        if(fila != 0){
            for(int i = fila-1; i >= 0; i--){
                cod = cod + Float.parseFloat(TablaProductos.getValueAt(i, 7).toString());
            }
            textTotal.setText("$"+String.valueOf(cod));
        }
    }
    
    void agregar(){
        if(!norepeticion()){
            if(ComprobarValoresNoVacios()){
                String ba = String.valueOf(TablaProductos.getRowCount()+1);
                String datos[]={bCodigo.getText(),bNombre.getText(), bCantidad.getText(),bPrecio.getText(),bDescuento.getText(), bIVA.getText(), String.valueOf(importe()), String.valueOf(total())};
                t.addRow(datos);
                Limpiar();
                SumaTotal();
            }else{
                JOptionPane.showMessageDialog(null, "Asegurese de que Todos los Campos esten llenos");
            }
        }
    }
    
    float tableImporte(int row){
        Float iva = Float.parseFloat(TablaProductos.getValueAt(row, 5).toString());
        Float precio = Float.parseFloat(TablaProductos.getValueAt(row, 3).toString());
        Float descuento = Float.parseFloat(TablaProductos.getValueAt(row, 4).toString());
        return ((((iva/100) * precio) + precio)-((descuento/100)*precio));
    }
    
    float tableTotal(int row){
        int cantidad = Integer.parseInt(TablaProductos.getValueAt(row, 2).toString());
        float importe = Float.parseFloat(TablaProductos.getValueAt(row, 6).toString());
        return cantidad * importe;
    }
    
    float importe(){
        Float iva = Float.parseFloat(bIVA.getText());
        Float precio = Float.parseFloat(bPrecio.getText());
        Float descuento = Float.parseFloat(bDescuento.getText());
        
        float r = ((((iva/100) * precio) + precio)-((descuento/100)*precio));
        return r;
    }
    
    float total(){
        Float cantidad = Float.parseFloat(bCantidad.getText());
        return cantidad*importe();
    }
    
    boolean ComprobarValoresNoVacios(){
        if(!bCodigo.getText().equals("") && !bNombre.getText().equals("") &&  !bCantidad.getText().equals("") && !bPrecio.getText().equals("") && !bDescuento.getText().equals("") && !bIVA.getText().equals("") && !bImporte.getText().equals("") && !bTotal.getText().equals("")){
            if(!bCodigo.getText().equals(" ") && !bNombre.getText().equals(" ") &&  !bCantidad.getText().equals(" ") && !bPrecio.getText().equals(" ") && !bDescuento.getText().equals(" ") && !bIVA.getText().equals(" ") && !bImporte.getText().equals(" ") && !bTotal.getText().equals(" ")){
                if(bCodigo.getText() != null && bNombre.getText() != null &&  bCantidad.getText() != null && bPrecio.getText() != null && bDescuento.getText() != null && bIVA.getText() != null && bImporte != null && bTotal != null){
                    return true;
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    void init(){
        tabla(null);
        bImporte.setEnabled(false);
        bTotal.setEnabled(false);
        textTotal.setEnabled(false);
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
    
    public void traer(){
        int row = TablaPro.getSelectedRow();
        String a = String.valueOf(TablaPro.getValueAt(row, 0));
        bCodigo.setText(a);
        rellenar(a);
        producto.dispose();
    }
    
    public void rellenar(String ID){
        String[] datos = {"Articulo","Precio", "Descuento", "IVA"};
        String a[] = SQL.llenar(ID, datos, datos.length, "productos", "Clave");
        bNombre.setText(a[0]);
        bPrecio.setText(a[1]);
        bCantidad.setText("1");
        bDescuento.setText(a[2]);
        bIVA.setText(a[3]);
        bImporte.setText(String.valueOf(importe()));
        bTotal.setText(String.valueOf(total()));
    }

    public void fillTable(String sql, String table, String FSentense){
        //String[] columnas = { "ID Producto","Nombre","Descripcion","Precio","Precio Venta","U.M.","Cantidad","Marca","Proveedor","Ubicacion","Minimo","Maximo","Fecha Cotizacion"};
        String[] columnas ={"Clave", "Articulo", "Cantidad", "Precio", "Descuento", "I.V.A."}; 
        if(sql.equals("") && table.equals("") && FSentense.equals("")){
            sql = "SELECT * FROM";
            table = "`productos`";
            FSentense = "ORDER BY Articulo ASC";
        }
        TablaPro.setModel(SQL.getTables(sql, table, FSentense, columnas.length, columnas));
    }
    
    public void Busqueda(boolean nom){
        String a = IDpro.getText();
        String b = Nombrepro.getText();
        String c = "";
        String what = "";
        if(nom){
            c = a;
            what = "Clave";
        }else{
            c = b;
            what = "Articulo";
        }
        
        fillTable("SELECT * FROM", "`productos`", "WHERE `"+what+"` LIKE '%"+c+"%' ORDER BY Articulo ASC");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Productopane = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        IDpro = new javax.swing.JTextField();
        Nombrepro = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jPanel20 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaPro = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaProductos = new javax.swing.JTable();
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
        jButton4 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        textTotal = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();

        Productopane.setLayout(new javax.swing.BoxLayout(Productopane, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel18.setMaximumSize(new java.awt.Dimension(32767, 23));
        jPanel18.setMinimumSize(new java.awt.Dimension(0, 23));
        jPanel18.setPreferredSize(new java.awt.Dimension(637, 23));
        jPanel18.setLayout(new javax.swing.BoxLayout(jPanel18, javax.swing.BoxLayout.LINE_AXIS));

        jPanel14.setMaximumSize(new java.awt.Dimension(73, 32767));
        jPanel14.setMinimumSize(new java.awt.Dimension(73, 0));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 73, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 23, Short.MAX_VALUE)
        );

        jPanel18.add(jPanel14);

        jPanel15.setMaximumSize(new java.awt.Dimension(65465, 14));
        jPanel15.setMinimumSize(new java.awt.Dimension(60, 14));
        jPanel15.setPreferredSize(new java.awt.Dimension(320, 14));
        jPanel15.setLayout(new javax.swing.BoxLayout(jPanel15, javax.swing.BoxLayout.LINE_AXIS));

        jLabel11.setText("Codigo");
        jPanel15.add(jLabel11);

        jPanel18.add(jPanel15);

        jPanel16.setMaximumSize(new java.awt.Dimension(4545, 14));
        jPanel16.setPreferredSize(new java.awt.Dimension(318, 14));
        jPanel16.setLayout(new javax.swing.BoxLayout(jPanel16, javax.swing.BoxLayout.LINE_AXIS));

        jLabel12.setText("Nombre");
        jPanel16.add(jLabel12);

        jPanel18.add(jPanel16);

        Productopane.add(jPanel18);

        jPanel19.setMaximumSize(new java.awt.Dimension(32767, 30));
        jPanel19.setMinimumSize(new java.awt.Dimension(0, 30));
        jPanel19.setPreferredSize(new java.awt.Dimension(637, 30));
        jPanel19.setLayout(new javax.swing.BoxLayout(jPanel19, javax.swing.BoxLayout.LINE_AXIS));

        jButton5.setText("Buscar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel19.add(jButton5);

        IDpro.setMaximumSize(new java.awt.Dimension(2147483647, 30));
        IDpro.setMinimumSize(new java.awt.Dimension(6, 30));
        IDpro.setPreferredSize(new java.awt.Dimension(6, 30));
        jPanel19.add(IDpro);

        Nombrepro.setMaximumSize(new java.awt.Dimension(2147483647, 30));
        Nombrepro.setMinimumSize(new java.awt.Dimension(6, 30));
        Nombrepro.setPreferredSize(new java.awt.Dimension(6, 30));
        Nombrepro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                NombreproKeyReleased(evt);
            }
        });
        jPanel19.add(Nombrepro);

        jButton6.setText("Quitar Filtro");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel19.add(jButton6);

        Productopane.add(jPanel19);

        jPanel20.setLayout(new javax.swing.BoxLayout(jPanel20, javax.swing.BoxLayout.LINE_AXIS));

        TablaPro.setModel(new javax.swing.table.DefaultTableModel(
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
        TablaPro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaProMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TablaPro);

        jPanel20.add(jScrollPane2);

        Productopane.add(jPanel20);

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));

        TablaProductos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(TablaProductos);

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

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
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
        jLabel9.setMaximumSize(new java.awt.Dimension(120, 14));
        jLabel9.setMinimumSize(new java.awt.Dimension(120, 14));
        jLabel9.setPreferredSize(new java.awt.Dimension(120, 14));
        jPanel9.add(jLabel9);

        jPanel1.add(jPanel9);

        jPanel5.setMaximumSize(new java.awt.Dimension(2147483647, 27));
        jPanel5.setMinimumSize(new java.awt.Dimension(634, 27));
        jPanel5.setPreferredSize(new java.awt.Dimension(683, 27));
        jPanel5.setLayout(new javax.swing.BoxLayout(jPanel5, javax.swing.BoxLayout.LINE_AXIS));

        jLabel10.setText("Producto");
        jPanel5.add(jLabel10);

        jButton1.setText("...");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton1);

        bCodigo.setMaximumSize(new java.awt.Dimension(150, 2147483647));
        bCodigo.setMinimumSize(new java.awt.Dimension(150, 20));
        bCodigo.setPreferredSize(new java.awt.Dimension(150, 20));
        bCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                bCodigoKeyReleased(evt);
            }
        });
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

        jButton4.setText("Agregar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton4);

        jPanel1.add(jPanel5);

        jPanel10.setMaximumSize(new java.awt.Dimension(32767, 10));
        jPanel10.setMinimumSize(new java.awt.Dimension(0, 10));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 763, Short.MAX_VALUE)
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

        textTotal.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        textTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel6.add(textTotal);

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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        producto = new JFrame();
        producto.setContentPane(Productopane);
        producto.setIconImage(Toolkit.getDefaultToolkit().getImage(SPV.class.getResource("Jumbo.png")));
        producto.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        producto.setSize(600,450);
        producto.setResizable(true);
        producto.setVisible(true);
        producto.setLocationRelativeTo(null);
        producto.setTitle("Busqueda de Producto");
        TablaPro.setRowSelectionAllowed(true);
        fillTable("","","");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        Busqueda(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void NombreproKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NombreproKeyReleased
        Busqueda(false);
    }//GEN-LAST:event_NombreproKeyReleased

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        fillTable("","","");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void TablaProMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaProMouseClicked
        if(evt.getClickCount() == 2){
            traer();
        }
    }//GEN-LAST:event_TablaProMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        agregar();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void bCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bCodigoKeyReleased
        int key = evt.getKeyCode();
        if(key == KeyEvent.VK_ENTER){
            if(!bCodigo.getText().equals("") || bCodigo.getText().equals(" ")){
                rellenar(bCodigo.getText());
                agregar();
            }else{
                JOptionPane.showMessageDialog(null, "Escriba algun Codigo en el Campo de Codigo");
            }
        }
    }//GEN-LAST:event_bCodigoKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField IDpro;
    private javax.swing.JTextField Nombrepro;
    private javax.swing.JPanel Productopane;
    private javax.swing.JTable TablaPro;
    private javax.swing.JTable TablaProductos;
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
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField textTotal;
    // End of variables declaration//GEN-END:variables
}
