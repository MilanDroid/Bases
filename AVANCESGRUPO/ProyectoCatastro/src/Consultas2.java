

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author juang
 */
public class Consultas2 extends javax.swing.JFrame {
   static Connection cn;
 static Statement s;
 static ResultSet rs;
    /**
     * Creates new form Consultas2
     */
    public Consultas2() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cboFiltro = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        txtZona = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        jComboBox3 = new javax.swing.JComboBox<>();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        cboFiltro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CENTRO", "SUR", "NORTE", "ESTE", "OESTE" }));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("DNI DE PROPIETARIOS DE UNA CASA");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("PROPIETARIOS DE UN PISO");

        txtZona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtZonaActionPerformed(evt);
            }
        });

        jButton1.setText(" PISOS DE MAS DE 50M");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("ZONAS SOLO CON CASAS PARTICULARES");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("TAMAÑO MEDIO DE CASAS PARTICULARES");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CENTRO", "SUR", "NORTE", "ESTE", "OESTE" }));

        jButton3.setText("METROS CUADRADOS DE SOLAR POR ZONA");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CRUZ CONDE", "RONDA DE LOS TEJARES", "AVENIDA AMERICA", "LA PAZ", "FELIPE II", "AVENIDA EL BRILLANTE", "DAMASCO" }));

        jLabel4.setText("CALLE");

        jLabel5.setText("NUMERO");

        jLabel6.setText("NUMERO");

        jLabel7.setText("BLOQUE");

        jLabel8.setText("DOMICILIOS PARTICULARES");

        jButton4.setText("BUSCAR");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("BUSCAR");

        jButton6.setText("BUSCAR");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("BUSCAR");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.setBackground(new java.awt.Color(0, 0, 102));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("CATASTRO MUNICIPAL");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(153, 153, 153)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("ZONA");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("ZONA");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel11)
                                            .addComponent(jLabel3)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(175, 175, 175)
                                                .addComponent(jLabel8))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGap(87, 87, 87)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabel2)
                                                            .addComponent(jLabel4)))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGap(212, 212, 212)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(jLabel5)
                                                            .addComponent(jButton5)))))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGap(111, 111, 111)
                                                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(30, 30, 30))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                        .addComponent(jLabel7)
                                                        .addGap(107, 107, 107)))
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel6))))
                                        .addGap(17, 17, 17))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cboFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton4)
                                        .addGap(128, 128, 128)
                                        .addComponent(txtZona, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(122, 122, 122))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton6)
                                        .addGap(106, 106, 106)
                                        .addComponent(jButton3))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton1)
                                        .addGap(130, 130, 130)
                                        .addComponent(jButton2))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel10)))
                        .addGap(64, 64, 64))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton7)
                        .addGap(132, 132, 132)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4)
                            .addComponent(txtZona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2))
                        .addGap(44, 44, 44)
                        .addComponent(jLabel3)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton6)
                            .addComponent(jButton3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel6))
                                .addGap(24, 24, 24))
                            .addComponent(jButton7, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(33, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtZonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtZonaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtZonaActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
 // TODO add your handling code here:
 
    String sql;
    
    if(cboFiltro.getModel().getSelectedItem().toString() == "CENTRO"){
            sql = "SELECT A.DNI_CP \n" +
                                 "FROM CASAPARTICULAR A\n" +
                                 "LEFT JOIN VIVIENDA B\n" +
                                 "ON (A.CALLE = B.CALLE)\n" +
                                 "WHERE B.NOMBRE_ZONA = 'CENTRO'";
            } 
    if(cboFiltro.equals("SUR")) {
                sql = "SELECT A.DNI_CP \n" +
                                 "FROM CASAPARTICULAR A\n" +
                                 "LEFT JOIN VIVIENDA B\n" +
                                 "ON (A.CALLE = B.CALLE)\n" +
                                 "WHERE B.NOMBRE_ZONA = 'SUR'";
            }
    if(cboFiltro.equals("NORTE")) {
                sql = "SELECT A.DNI_CP \n" +
                                 "FROM CASAPARTICULAR A\n" +
                                 "LEFT JOIN VIVIENDA B\n" +
                                 "ON (A.CALLE = B.CALLE)\n" +
                                 "WHERE B.NOMBRE_ZONA = 'NORTE'";
            }
    if(cboFiltro.equals("ESTE")) {
                sql = "SELECT A.DNI_CP \n" +
                                 "FROM CASAPARTICULAR A\n" +
                                 "LEFT JOIN VIVIENDA B\n" +
                                 "ON (A.CALLE = B.CALLE)\n" +
                                 "WHERE B.NOMBRE_ZONA = 'ESTE'";
            }
    else {
                sql = "SELECT A.DNI_CP \n" +
                                 "FROM CASAPARTICULAR A\n" +
                                 "LEFT JOIN VIVIENDA B\n" +
                                 "ON (A.CALLE = B.CALLE)\n" +
                                 "WHERE B.NOMBRE_ZONA = 'OESTE'";
            }
        // TODO add your handling code here:
        try {
            //Para establecer el modelo al JTable
            DefaultTableModel modelo = new DefaultTableModel();
            this.jTable1.setModel(modelo);
            //Para conectarnos a nuestra base de datos
            String url = "jdbc:oracle:thin:@localhost:1521:XE";
            // Establecemos los valores de cadena de conexión, usuario y contraseña
            cn = DriverManager.getConnection(url, "CATASTRO", "PASSWORD");
            
            //Para ejecutar la consulta
            Statement s = cn.createStatement();
            
            //Ejecutamos la consulta y los datos lo almacenamos en un ResultSet
             ResultSet rs = s.executeQuery(sql);
            //Obteniendo la informacion de las columnas que estan siendo consultadas
            ResultSetMetaData rsMd = rs.getMetaData();
            //La cantidad de columnas que tiene la consulta
            int cantidadColumnas = rsMd.getColumnCount();
            //Establecer como cabezeras el nombre de las columnas
            for (int i = 1; i <= cantidadColumnas; i++) {
             modelo.addColumn(rsMd.getColumnLabel(i));
            }
            //Creando las filas para el JTable
            while (rs.next()) {
             Object[] fila = new Object[cantidadColumnas];
             for (int i = 0; i < cantidadColumnas; i++) {
               fila[i]=rs.getObject(i+1);
             }
             modelo.addRow(fila);
            }
            rs.close();
            cn.close();
       } catch (Exception ex) {
        ex.printStackTrace();
       }        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
            //Para establecer el modelo al JTable
            DefaultTableModel modelo = new DefaultTableModel();
            this.jTable1.setModel(modelo);
            //Para conectarnos a nuestra base de datos
            String url = "jdbc:oracle:thin:@localhost:1521:XE";
            // Establecemos los valores de cadena de conexión, usuario y contraseña
            cn = DriverManager.getConnection(url, "CATASTRO", "PASSWORD");
            //Para ejecutar la consulta
            s = cn.createStatement();
            //Ejecutamos la consulta y los datos lo almacenamos en un ResultSet
             rs = s.executeQuery("SELECT CALLE, NUMERO, ESCALERA, PLANTA, PUERTA, METROS_P\n" +
                                 "FROM PISO\n" +
                                 "WHERE METROS_P > 50 AND DNI_P = 44351312");
            //Obteniendo la informacion de las columnas que estan siendo consultadas
            ResultSetMetaData rsMd = rs.getMetaData();
            //La cantidad de columnas que tiene la consulta
            int cantidadColumnas = rsMd.getColumnCount();
            //Establecer como cabezeras el nombre de las colimnas
            for (int i = 1; i <= cantidadColumnas; i++) {
             modelo.addColumn(rsMd.getColumnLabel(i));
            }
            //Creando las filas para el JTable
            while (rs.next()) {
             Object[] fila = new Object[cantidadColumnas];
             for (int i = 0; i < cantidadColumnas; i++) {
               fila[i]=rs.getObject(i+1);
             }
             modelo.addRow(fila);
            }
            rs.close();
            cn.close();
       } catch (Exception ex) {
        ex.printStackTrace();
       }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       // TODO add your handling code here:
         try {
            //Para establecer el modelo al JTable
            DefaultTableModel modelo = new DefaultTableModel();
            this.jTable1.setModel(modelo);
            //Para conectarnos a nuestra base de datos
            String url = "jdbc:oracle:thin:@localhost:1521:XE";
            // Establecemos los valores de cadena de conexión, usuario y contraseña
            cn = DriverManager.getConnection(url, "CATASTRO", "PASSWORD");
            //Para ejecutar la consulta
            s = cn.createStatement();
            //Ejecutamos la consulta y los datos lo almacenamos en un ResultSet
             rs = s.executeQuery("SELECT DISTINCT A.NOMBRE_ZONA\n" +
                                 "FROM\n" +
                                 "(\n" +
                                 "SELECT CALLE, CODIGO_POSTAL, METROS, NOMBRE_ZONA, NUMERO, OD_VIVIENDA, TIPO_VIVIENDA  \n" +
                                 "FROM VIVIENDA\n" +
                                 "WHERE TIPO_VIVIENDA = 'C'\n" +
                                 ") A\n" +
                                 "LEFT JOIN \n" +
                                 "(\n" +
                                 "SELECT CALLE, CODIGO_POSTAL, METROS, NOMBRE_ZONA, NUMERO, OD_VIVIENDA, TIPO_VIVIENDA  \n" +
                                 "FROM VIVIENDA \n" +
                                 "WHERE TIPO_VIVIENDA != 'C'\n" +
                                 ") B\n" +
                                 "ON (A.NOMBRE_ZONA = B.NOMBRE_ZONA)\n" +
                                 "WHERE B.NOMBRE_ZONA IS NULL");
            //Obteniendo la informacion de las columnas que estan siendo consultadas
            ResultSetMetaData rsMd = rs.getMetaData();
            //La cantidad de columnas que tiene la consulta
            int cantidadColumnas = rsMd.getColumnCount();
            //Establecer como cabezeras el nombre de las colimnas
            for (int i = 1; i <= cantidadColumnas; i++) {
             modelo.addColumn(rsMd.getColumnLabel(i));
            }
            //Creando las filas para el JTable
            while (rs.next()) {
             Object[] fila = new Object[cantidadColumnas];
             for (int i = 0; i < cantidadColumnas; i++) {
               fila[i]=rs.getObject(i+1);
             }
             modelo.addRow(fila);
            }
            rs.close();
            cn.close();
       } catch (Exception ex) {
        ex.printStackTrace();
       }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try {
            //Para establecer el modelo al JTable
            DefaultTableModel modelo = new DefaultTableModel();
            this.jTable1.setModel(modelo);
            //Para conectarnos a nuestra base de datos
            String url = "jdbc:oracle:thin:@localhost:1521:XE";
            // Establecemos los valores de cadena de conexión, usuario y contraseña
            cn = DriverManager.getConnection(url, "CATASTRO", "PASSWORD");
            //Para ejecutar la consulta
            s = cn.createStatement();
            //Ejecutamos la consulta y los datos lo almacenamos en un ResultSet
             rs = s.executeQuery("SELECT SUM(METROS)AS METROS, NOMBRE_ZONA\n" +
                                 "FROM VIVIENDA\n" +
                                 "GROUP BY NOMBRE_ZONA\n" +
                                 "ORDER BY METROS");
            //Obteniendo la informacion de las columnas que estan siendo consultadas
            ResultSetMetaData rsMd = rs.getMetaData();
            //La cantidad de columnas que tiene la consulta
            int cantidadColumnas = rsMd.getColumnCount();
            //Establecer como cabezeras el nombre de las colimnas
            for (int i = 1; i <= cantidadColumnas; i++) {
             modelo.addColumn(rsMd.getColumnLabel(i));
            }
            //Creando las filas para el JTable
            while (rs.next()) {
             Object[] fila = new Object[cantidadColumnas];
             for (int i = 0; i < cantidadColumnas; i++) {
               fila[i]=rs.getObject(i+1);
             }
             modelo.addRow(fila);
            }
            rs.close();
            cn.close();
       } catch (Exception ex) {
        ex.printStackTrace();
       }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        String sql;
    if(cboFiltro.equals("CENTRO")){
            sql = "SELECT AVG (METROS) AS METROS \n" +
                                 "FROM VIVIENDA\n" +
                                 "WHERE NOMBRE_ZONA = 'SECTOR CENTRO' AND TIPO_VIVIENDA = 'C'";
            } else if(cboFiltro.equals("SUR")) {
                sql = "SELECT AVG (METROS) AS METROS \n" +
                                 "FROM VIVIENDA\n" +
                                 "WHERE NOMBRE_ZONA = 'SECTOR SUR' AND TIPO_VIVIENDA = 'C'";
            } else if(cboFiltro.equals("NORTE")) {
                sql = "SELECT AVG (METROS) AS METROS \n" +
                                 "FROM VIVIENDA\n" +
                                 "WHERE NOMBRE_ZONA = 'SECTOR NORTE' AND TIPO_VIVIENDA = 'C'";
            } else if(cboFiltro.equals("ESTE")) {
                sql = "SELECT AVG (METROS) AS METROS \n" +
                                 "FROM VIVIENDA\n" +
                                 "WHERE NOMBRE_ZONA = 'SECTOR ESTE' AND TIPO_VIVIENDA = 'C'";
            }else {
                sql = "SELECT AVG (METROS) AS METROS \n" +
                                 "FROM VIVIENDA\n" +
                                 "WHERE NOMBRE_ZONA = 'SECTOR OESTE' AND TIPO_VIVIENDA = 'C'";
            }
        // TODO add your handling code here:
        try {
            //Para establecer el modelo al JTable
            DefaultTableModel modelo = new DefaultTableModel();
            this.jTable1.setModel(modelo);
            //Para conectarnos a nuestra base de datos
            String url = "jdbc:oracle:thin:@localhost:1521:XE";
            // Establecemos los valores de cadena de conexión, usuario y contraseña
            cn = DriverManager.getConnection(url, "CATASTRO", "PASSWORD");
            
            //Para ejecutar la consulta
            Statement s = cn.createStatement();
            
            //Ejecutamos la consulta y los datos lo almacenamos en un ResultSet
             ResultSet rs = s.executeQuery(sql);
            //Obteniendo la informacion de las columnas que estan siendo consultadas
            ResultSetMetaData rsMd = rs.getMetaData();
            //La cantidad de columnas que tiene la consulta
            int cantidadColumnas = rsMd.getColumnCount();
            //Establecer como cabezeras el nombre de las columnas
            for (int i = 1; i <= cantidadColumnas; i++) {
             modelo.addColumn(rsMd.getColumnLabel(i));
            }
            //Creando las filas para el JTable
            while (rs.next()) {
             Object[] fila = new Object[cantidadColumnas];
             for (int i = 0; i < cantidadColumnas; i++) {
               fila[i]=rs.getObject(i+1);
             }
             modelo.addRow(fila);
            }
            rs.close();
            cn.close();
       } catch (Exception ex) {
        ex.printStackTrace();
       }
    }//GEN-LAST:event_jButton6ActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Consultas2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Consultas2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Consultas2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Consultas2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Consultas2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboFiltro;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField txtZona;
    // End of variables declaration//GEN-END:variables
}
