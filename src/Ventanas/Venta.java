/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import Conexion.SQL;
import Conexion.XMLWriter;
import java.awt.Color;
import java.awt.Dimension;
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
public class Venta extends javax.swing.JPanel {

    
    JFrame producto;
    DefaultTableModel t;
    
    public Venta() {
        initComponents();
        init();
    }
    
    final void init(){
        tabla(null);
        CBuscar.setText("Introdusca el Nombre del Cliente");
        CBuscar.setForeground(new Color(160,160,160));
        textTotal.setEnabled(false);
        SubImporte.setEnabled(false);
        SubTotal.setEnabled(false);
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
    
    boolean ComprobarValoresNoVacios(){
        if(!Codigo.getText().equals("") && !nombre.getText().equals("") &&  !Cantidad.getText().equals("") && !Precio.getText().equals("") && !Descuento.getText().equals("") && !IVA.getText().equals("") && !SubImporte.getText().equals("") && !SubTotal.getText().equals("")){
            if(!Codigo.getText().equals(" ") && !nombre.getText().equals(" ") &&  !Cantidad.getText().equals(" ") && !Precio.getText().equals(" ") && !Descuento.getText().equals(" ") && !IVA.getText().equals(" ") && !SubImporte.getText().equals(" ") && !SubTotal.getText().equals(" ")){
                if(Codigo.getText() != null && nombre.getText() != null &&  Cantidad.getText() != null && Precio.getText() != null && Descuento.getText() != null && IVA.getText() != null && SubImporte != null && SubTotal != null){
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
    
    public boolean norepeticion(){
        String cod="";
        int fila = TablaProductos.getRowCount();
        if(fila != 0){
            for(int i = fila-1; i >= 0; i--){
                cod=TablaProductos.getValueAt(i, 0).toString();
                if(cod.equals(Codigo.getText())){
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
                String datos[]={Codigo.getText(),nombre.getText(), Cantidad.getText(),Precio.getText(),Descuento.getText(), IVA.getText(), String.valueOf(importe()), String.valueOf(total())};
                t.addRow(datos);
                Limpiar();
                SumaTotal();
            }else{
                JOptionPane.showMessageDialog(null, "Asegurese de que Todos los Campos esten llenos");
            }
        }
    }
    
    void Limpiar(){
        Codigo.setText("");
        nombre.setText("");
        Cantidad.setText("");
        Precio.setText("");
        Descuento.setText("");
        IVA.setText("");
        SubImporte.setText("");
        SubTotal.setText("");
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
        Float iva = Float.parseFloat(IVA.getText());
        Float precio = Float.parseFloat(Precio.getText());
        Float descuento = Float.parseFloat(Descuento.getText());
        
        float r = ((((iva/100) * precio) + precio)-((descuento/100)*precio));
        return r;
    }
    
    float total(){
        Float cantidad = Float.parseFloat(Cantidad.getText());
        return cantidad * importe();
    }
    
    public void traer(){
        int row = TablaPro.getSelectedRow();
        String a = String.valueOf(TablaPro.getValueAt(row, 0));
        Codigo.setText(a);
        rellenar(a);
        producto.dispose();
    }
    
    public void rellenar(String ID){
        String[] datos = {"Articulo","Precio", "Descuento", "IVA"};
        String a[] = SQL.llenar(ID, datos, datos.length, "productos", "Clave");
        if(a[0] != null){
            nombre.setText(a[0]);
            Precio.setText(a[1]);
            Cantidad.setText("1");
            Descuento.setText(a[2]);
            IVA.setText(a[3]);
            SubImporte.setText(String.valueOf(importe()));
            SubTotal.setText(String.valueOf(total()));
        }else{
            int r = JOptionPane.showConfirmDialog(null, "Producto no Encontrado, ¿Desea Agregarlo?");
            if(r == JOptionPane.NO_OPTION || r == JOptionPane.CANCEL_OPTION){
                Codigo.setText("");
            }else{
                //Nueva Ventana de Registro
                JFrame AgregarProd = new JFrame();
                AgregarProd.setSize(new Dimension(478,360));
                AgregarProd.setTitle("Agregar Productos");
                AgregarProd.setResizable(false);
                String enviar = Codigo.getText();
                AgregarProd.setContentPane(new AgregarProducto(enviar));
                AgregarProd.setVisible(true);
                AgregarProd.setLocationRelativeTo(null);
            }
        }
    }
    
    public void fillTable(String sql, String table, String FSentense){
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
    
    void Venta(){
        if(TablaProductos.getRowCount() != 0){
            String a[][] = new String[TablaProductos.getRowCount()][4];
            for(int i = 0; i < TablaProductos.getRowCount(); i++){
                for(int o = 0; o < 4; o++){
                    if(o == 0){
                        a[i][o] = String.valueOf(TablaProductos.getValueAt(i, 1));
                    }
                    if(o == 1){
                        a[i][o] = String.valueOf(TablaProductos.getValueAt(i, 2));
                    }
                    if(o == 2){
                        a[i][o] = String.valueOf(TablaProductos.getValueAt(i, 6));
                    }
                    if(o == 3){
                        a[i][o] = String.valueOf(TablaProductos.getValueAt(i, 7));
                    }
                }
            }
            String ultimo = SQL.Ultimo("ID", "registroventas");
            if(ultimo != null && !ultimo.equals("") && !ultimo.equals(" ")){
                XMLWriter w = new XMLWriter(String.valueOf(Integer.parseInt(ultimo)+1));
                w.Write(a);
            }else{
                XMLWriter w = new XMLWriter("1");
                w.Write(a);
            }
            String cliente = CBuscar.getText();
            if(cliente.equals("") || cliente.equals(" ") || cliente.equals("Introdusca el Nombre del Cliente")){
                cliente = "Sin Cliente";
            }
            String columnas[] = {"Cliente", "Total"};
            String total = String.format("%.02f", Float.parseFloat(textTotal.getText().substring(1, textTotal.getText().length())));
            String resultado[] = {cliente,total};
            SQL.Insert("registroventas", columnas, resultado);
        }else{
            JOptionPane.showMessageDialog(null, "No hay Datos en la Tabla");
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Productopane = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        IDpro = new javax.swing.JTextField();
        Nombrepro = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jPanel20 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaPro = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        VentaTipo = new javax.swing.JComboBox();
        LabelCliente = new javax.swing.JLabel();
        CBuscar = new javax.swing.JTextField();
        CBoton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaProductos = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        Codigo = new javax.swing.JTextField();
        nombre = new javax.swing.JTextField();
        Cantidad = new javax.swing.JTextField();
        Precio = new javax.swing.JTextField();
        Descuento = new javax.swing.JTextField();
        IVA = new javax.swing.JTextField();
        SubImporte = new javax.swing.JTextField();
        SubTotal = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        textTotal = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        bCompra = new javax.swing.JButton();

        Productopane.setLayout(new javax.swing.BoxLayout(Productopane, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel18.setMaximumSize(new java.awt.Dimension(32767, 23));
        jPanel18.setMinimumSize(new java.awt.Dimension(0, 23));
        jPanel18.setPreferredSize(new java.awt.Dimension(637, 23));
        jPanel18.setLayout(new javax.swing.BoxLayout(jPanel18, javax.swing.BoxLayout.LINE_AXIS));

        jPanel11.setMaximumSize(new java.awt.Dimension(73, 32767));
        jPanel11.setMinimumSize(new java.awt.Dimension(73, 0));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 73, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 23, Short.MAX_VALUE)
        );

        jPanel18.add(jPanel11);

        jPanel12.setMaximumSize(new java.awt.Dimension(65465, 14));
        jPanel12.setMinimumSize(new java.awt.Dimension(60, 14));
        jPanel12.setPreferredSize(new java.awt.Dimension(320, 14));
        jPanel12.setLayout(new javax.swing.BoxLayout(jPanel12, javax.swing.BoxLayout.LINE_AXIS));

        jLabel10.setText("Codigo");
        jPanel12.add(jLabel10);

        jPanel18.add(jPanel12);

        jPanel13.setMaximumSize(new java.awt.Dimension(4545, 14));
        jPanel13.setPreferredSize(new java.awt.Dimension(318, 14));
        jPanel13.setLayout(new javax.swing.BoxLayout(jPanel13, javax.swing.BoxLayout.LINE_AXIS));

        jLabel11.setText("Nombre");
        jPanel13.add(jLabel11);

        jPanel18.add(jPanel13);

        Productopane.add(jPanel18);

        jPanel19.setMaximumSize(new java.awt.Dimension(32767, 30));
        jPanel19.setMinimumSize(new java.awt.Dimension(0, 30));
        jPanel19.setPreferredSize(new java.awt.Dimension(637, 30));
        jPanel19.setLayout(new javax.swing.BoxLayout(jPanel19, javax.swing.BoxLayout.LINE_AXIS));

        jButton4.setText("Buscar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel19.add(jButton4);

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

        jButton5.setText("Quitar Filtro");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel19.add(jButton5);

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

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel4.setMaximumSize(new java.awt.Dimension(32767, 70));
        jPanel4.setMinimumSize(new java.awt.Dimension(766, 70));
        jPanel4.setPreferredSize(new java.awt.Dimension(500, 70));
        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel8.setMaximumSize(new java.awt.Dimension(32767, 30));
        jPanel8.setMinimumSize(new java.awt.Dimension(373, 30));
        jPanel8.setPreferredSize(new java.awt.Dimension(820, 30));
        jPanel8.setLayout(new javax.swing.BoxLayout(jPanel8, javax.swing.BoxLayout.LINE_AXIS));

        VentaTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Captura por Cliente", "Captura Continuada" }));
        VentaTipo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                VentaTipoItemStateChanged(evt);
            }
        });
        jPanel8.add(VentaTipo);

        LabelCliente.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        LabelCliente.setText("Cliente");
        jPanel8.add(LabelCliente);

        CBuscar.setMaximumSize(new java.awt.Dimension(2147483647, 30));
        CBuscar.setMinimumSize(new java.awt.Dimension(6, 30));
        CBuscar.setPreferredSize(new java.awt.Dimension(300, 30));
        CBuscar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                CBuscarFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                CBuscarFocusLost(evt);
            }
        });
        jPanel8.add(CBuscar);

        CBoton.setText("Buscar");
        jPanel8.add(CBoton);

        jPanel4.add(jPanel8);

        jPanel1.setMaximumSize(new java.awt.Dimension(32767, 40));
        jPanel1.setMinimumSize(new java.awt.Dimension(766, 40));
        jPanel1.setPreferredSize(new java.awt.Dimension(766, 40));
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

        jLabel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel1.setMaximumSize(new java.awt.Dimension(350, 20));
        jLabel1.setMinimumSize(new java.awt.Dimension(250, 20));
        jLabel1.setPreferredSize(new java.awt.Dimension(250, 20));
        jPanel1.add(jLabel1);

        jLabel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel2.setMaximumSize(new java.awt.Dimension(350, 20));
        jLabel2.setMinimumSize(new java.awt.Dimension(150, 20));
        jLabel2.setPreferredSize(new java.awt.Dimension(150, 20));
        jPanel1.add(jLabel2);

        jPanel4.add(jPanel1);

        add(jPanel4);

        jPanel5.setMaximumSize(new java.awt.Dimension(32767, 1000));
        jPanel5.setMinimumSize(new java.awt.Dimension(0, 90));
        jPanel5.setPreferredSize(new java.awt.Dimension(812, 300));
        jPanel5.setLayout(new javax.swing.BoxLayout(jPanel5, javax.swing.BoxLayout.LINE_AXIS));

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

        jPanel5.add(jPanel2);

        add(jPanel5);

        jPanel6.setMaximumSize(new java.awt.Dimension(32767, 140));
        jPanel6.setMinimumSize(new java.awt.Dimension(0, 140));
        jPanel6.setPreferredSize(new java.awt.Dimension(812, 140));
        jPanel6.setLayout(new javax.swing.BoxLayout(jPanel6, javax.swing.BoxLayout.PAGE_AXIS));

        jPanel7.setMaximumSize(new java.awt.Dimension(32767, 23));
        jPanel7.setMinimumSize(new java.awt.Dimension(100, 23));
        jPanel7.setPreferredSize(new java.awt.Dimension(812, 23));
        jPanel7.setLayout(new javax.swing.BoxLayout(jPanel7, javax.swing.BoxLayout.LINE_AXIS));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Codigo");
        jLabel4.setMaximumSize(new java.awt.Dimension(240, 14));
        jLabel4.setMinimumSize(new java.awt.Dimension(240, 14));
        jLabel4.setPreferredSize(new java.awt.Dimension(240, 14));
        jPanel7.add(jLabel4);

        jLabel5.setText("Nombre");
        jLabel5.setMaximumSize(new java.awt.Dimension(244444, 14));
        jLabel5.setMinimumSize(new java.awt.Dimension(50, 14));
        jLabel5.setPreferredSize(new java.awt.Dimension(175, 14));
        jPanel7.add(jLabel5);

        jLabel6.setText("Cantidad");
        jLabel6.setMaximumSize(new java.awt.Dimension(80, 14));
        jLabel6.setMinimumSize(new java.awt.Dimension(80, 14));
        jLabel6.setPreferredSize(new java.awt.Dimension(80, 14));
        jPanel7.add(jLabel6);

        jLabel7.setText("Precio");
        jLabel7.setMaximumSize(new java.awt.Dimension(55, 14));
        jLabel7.setMinimumSize(new java.awt.Dimension(55, 14));
        jLabel7.setPreferredSize(new java.awt.Dimension(55, 14));
        jPanel7.add(jLabel7);

        jLabel8.setText("Descuento");
        jLabel8.setMaximumSize(new java.awt.Dimension(65, 14));
        jLabel8.setMinimumSize(new java.awt.Dimension(65, 14));
        jLabel8.setPreferredSize(new java.awt.Dimension(65, 14));
        jPanel7.add(jLabel8);

        jLabel9.setText("I.V.A");
        jLabel9.setMaximumSize(new java.awt.Dimension(60, 14));
        jLabel9.setMinimumSize(new java.awt.Dimension(60, 14));
        jLabel9.setPreferredSize(new java.awt.Dimension(60, 14));
        jPanel7.add(jLabel9);

        jLabel13.setText("Importe");
        jLabel13.setMaximumSize(new java.awt.Dimension(80, 14));
        jLabel13.setMinimumSize(new java.awt.Dimension(80, 14));
        jLabel13.setPreferredSize(new java.awt.Dimension(80, 14));
        jPanel7.add(jLabel13);

        jLabel14.setText("Total");
        jLabel14.setMaximumSize(new java.awt.Dimension(120, 14));
        jLabel14.setMinimumSize(new java.awt.Dimension(120, 14));
        jLabel14.setPreferredSize(new java.awt.Dimension(120, 14));
        jPanel7.add(jLabel14);

        jPanel6.add(jPanel7);

        jPanel3.setMaximumSize(new java.awt.Dimension(214748364, 27));
        jPanel3.setMinimumSize(new java.awt.Dimension(575, 27));
        jPanel3.setPreferredSize(new java.awt.Dimension(812, 27));
        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.LINE_AXIS));

        jLabel3.setText("Producto");
        jPanel3.add(jLabel3);

        jButton3.setText("...");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton3);

        Codigo.setMaximumSize(new java.awt.Dimension(150, 2147483647));
        Codigo.setMinimumSize(new java.awt.Dimension(150, 20));
        Codigo.setPreferredSize(new java.awt.Dimension(150, 20));
        Codigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CodigoKeyReleased(evt);
            }
        });
        jPanel3.add(Codigo);
        jPanel3.add(nombre);

        Cantidad.setMaximumSize(new java.awt.Dimension(65, 2147483647));
        Cantidad.setMinimumSize(new java.awt.Dimension(65, 20));
        Cantidad.setName(""); // NOI18N
        Cantidad.setPreferredSize(new java.awt.Dimension(65, 20));
        jPanel3.add(Cantidad);

        Precio.setMaximumSize(new java.awt.Dimension(65, 2147483647));
        Precio.setMinimumSize(new java.awt.Dimension(65, 20));
        Precio.setPreferredSize(new java.awt.Dimension(65, 20));
        jPanel3.add(Precio);

        Descuento.setMaximumSize(new java.awt.Dimension(65, 2147483647));
        Descuento.setMinimumSize(new java.awt.Dimension(65, 20));
        Descuento.setPreferredSize(new java.awt.Dimension(65, 20));
        jPanel3.add(Descuento);

        IVA.setMaximumSize(new java.awt.Dimension(65, 2147483647));
        IVA.setMinimumSize(new java.awt.Dimension(65, 20));
        IVA.setPreferredSize(new java.awt.Dimension(65, 20));
        jPanel3.add(IVA);

        SubImporte.setMaximumSize(new java.awt.Dimension(65, 2147483647));
        SubImporte.setMinimumSize(new java.awt.Dimension(65, 20));
        SubImporte.setPreferredSize(new java.awt.Dimension(65, 20));
        jPanel3.add(SubImporte);

        SubTotal.setMaximumSize(new java.awt.Dimension(65, 2147483647));
        SubTotal.setMinimumSize(new java.awt.Dimension(65, 20));
        SubTotal.setPreferredSize(new java.awt.Dimension(65, 20));
        jPanel3.add(SubTotal);

        jButton1.setText("Agregar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1);

        jPanel6.add(jPanel3);

        jPanel15.setMaximumSize(new java.awt.Dimension(32767, 10));
        jPanel15.setMinimumSize(new java.awt.Dimension(0, 10));
        jPanel15.setPreferredSize(new java.awt.Dimension(812, 10));

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 812, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel6.add(jPanel15);

        jPanel9.setMaximumSize(new java.awt.Dimension(2147483647, 80));
        jPanel9.setMinimumSize(new java.awt.Dimension(6, 80));
        jPanel9.setPreferredSize(new java.awt.Dimension(812, 80));
        jPanel9.setRequestFocusEnabled(false);
        jPanel9.setLayout(new javax.swing.BoxLayout(jPanel9, javax.swing.BoxLayout.LINE_AXIS));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
        );

        jPanel9.add(jPanel14);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel12.setText("TOTAL:");
        jPanel9.add(jLabel12);

        textTotal.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        textTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textTotal.setMaximumSize(new java.awt.Dimension(2147483647, 80));
        textTotal.setMinimumSize(new java.awt.Dimension(6, 80));
        textTotal.setPreferredSize(new java.awt.Dimension(6, 80));
        jPanel9.add(textTotal);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
        );

        jPanel9.add(jPanel10);

        bCompra.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bCompra.setText("Venta");
        bCompra.setMaximumSize(new java.awt.Dimension(90, 80));
        bCompra.setMinimumSize(new java.awt.Dimension(90, 80));
        bCompra.setPreferredSize(new java.awt.Dimension(90, 80));
        bCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCompraActionPerformed(evt);
            }
        });
        jPanel9.add(bCompra);

        jPanel6.add(jPanel9);

        add(jPanel6);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
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
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        Busqueda(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        fillTable("","","");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void NombreproKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NombreproKeyReleased
        Busqueda(false);
    }//GEN-LAST:event_NombreproKeyReleased

    private void TablaProMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaProMouseClicked
        if(evt.getClickCount() == 2){
            traer();
        }
    }//GEN-LAST:event_TablaProMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        agregar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void VentaTipoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_VentaTipoItemStateChanged
        if(VentaTipo.getSelectedItem().toString().equals("Captura por Cliente")){
            CBuscar.setEnabled(true);
            CBoton.setEnabled(true);
            CBuscar.setText("Introdusca el Nombre del Cliente");
            CBuscar.setForeground(new Color(160,160,160));
        }else if (VentaTipo.getSelectedItem().toString().equals("Captura Continuada")){
            CBuscar.setEnabled(false);
            CBoton.setEnabled(false);
            CBuscar.setText("");
        }
    }//GEN-LAST:event_VentaTipoItemStateChanged

    private void CBuscarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_CBuscarFocusGained
        CBuscar.setText("");
        CBuscar.setForeground(new Color(0,0,0));
    }//GEN-LAST:event_CBuscarFocusGained

    private void CBuscarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_CBuscarFocusLost
        if(CBuscar.getText().equals("") || CBuscar.getText().equals(" ") || CBuscar.getText()==(null)){
        CBuscar.setText("Introdusca el Nombre del Cliente");
        CBuscar.setForeground(new Color(160,160,160));
        }
    }//GEN-LAST:event_CBuscarFocusLost

    private void CodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CodigoKeyReleased
        int key = evt.getKeyCode();
        if(key == KeyEvent.VK_ENTER){
            if(!Codigo.getText().equals("") || Codigo.getText().equals(" ")){
                rellenar(Codigo.getText());
                agregar();
            }else{
                JOptionPane.showMessageDialog(null, "Escriba algun Codigo en el Campo de Codigo");
            }
        }
    }//GEN-LAST:event_CodigoKeyReleased

    private void bCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCompraActionPerformed
        Venta();
    }//GEN-LAST:event_bCompraActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CBoton;
    private javax.swing.JTextField CBuscar;
    private javax.swing.JTextField Cantidad;
    private javax.swing.JTextField Codigo;
    private javax.swing.JTextField Descuento;
    private javax.swing.JTextField IDpro;
    private javax.swing.JTextField IVA;
    private javax.swing.JLabel LabelCliente;
    private javax.swing.JTextField Nombrepro;
    private javax.swing.JTextField Precio;
    private javax.swing.JPanel Productopane;
    private javax.swing.JTextField SubImporte;
    private javax.swing.JTextField SubTotal;
    private javax.swing.JTable TablaPro;
    private javax.swing.JTable TablaProductos;
    private javax.swing.JComboBox VentaTipo;
    private javax.swing.JButton bCompra;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JTextField nombre;
    private javax.swing.JTextField textTotal;
    // End of variables declaration//GEN-END:variables
}
