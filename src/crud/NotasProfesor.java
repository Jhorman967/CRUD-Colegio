/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jhorman Gonzalez << Jhorman967@gmail.com >>
 */
public class NotasProfesor extends javax.swing.JFrame {
    
    DataBase cc = new DataBase();
    Connection con = cc.Conexion();
    
    int Contador = 0;

    /** Creates new form Notas */
    public NotasProfesor() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.ComboDocentes.setModel(cc.Obt_profesor());
        this.ShowLogros();
        this.ShowNotas();
        
    }
    
    public void AddStudent (){
    
        String Pass = String.valueOf(this.PassUserConfirm1.getText());
        
        String sql = "insert into estudiantes (fst_nombre, scd_Nombre, apellido, Documento, Direccion, Telefono, Date_Born, Password, id_docente_1)"
                + "values(?,?,?,?,?,?,?,?,?)" ;
        
        try{
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setString(1, this.TxtName1.getText());
            pst.setString(2, this.TxtScname1.getText());
            pst.setString(3, this.TxtLasName1.getText());
            pst.setString(4, this.TxtDocument1.getText());
            pst.setString(5, this.TxtAdress1.getText());
            pst.setString(6, this.TxtTel1.getText());
            pst.setString(7, ((JTextField)this.DateBorn1.getDateEditor().getUiComponent()).getText());
            pst.setString(8, Pass);
            pst.setString(9,this.ComboDocentes.getSelectedItem().toString());
            
            pst.execute();
             JOptionPane.showMessageDialog(null, "registro Existoso");

        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error Registro" + e.getMessage());
        }
                
                
    };
    
    //Add logros in specif this example is about sociales
   
    public void AddLogro (){
    
    String Logro = String.valueOf(this.TxtSociales1.getText());
    String sql = "insert into logro_sociales (Logros) values (?) ";
    
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setString(1, Logro);
           
       
            
            pst.execute(); 
            JOptionPane.showMessageDialog(null, "registro Logro Existoso");
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error Registro Logro" + e.getMessage());
        }
    
    
    }
    
    // function create new column 
    
    public void AddSociales (){
        
       
       String UltimoLogro = "";
       
        try {
            Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM logro_sociales");
             while (rs.next()){
                 UltimoLogro = rs.getString(2);
             }
            
        } catch (Exception e) {
        }
        String sc = "sociales";
        String logro = String.valueOf(this.TxtSociales1.getText());
        logro = logro.replace(" ", "");
        UltimoLogro = UltimoLogro.replace(" ", "");
       String sql = "ALTER TABLE "+sc+" ADD "+logro+" INT NOT NULL AFTER "+UltimoLogro+" " ;
        System.out.println(sql);
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            
           
            pst.execute();
             JOptionPane.showMessageDialog(null, "creacion logro exitoso");
             this.Contador++;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error Creacion" + e.getMessage());
        }
    
    }
    
    //  show notes in the table
    public void ShowNotas() {
        
        int cont = 0;
        
        DefaultTableModel modelo = new DefaultTableModel();
                 modelo.addColumn("Id");
                 modelo.addColumn("Nombre");
                 TableSocialesPro1.setModel(modelo);
        try {
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM logro_sociales");
             while (rs.next()){
                 modelo.addColumn(rs.getString(2));
                 TableSocialesPro1.setModel(modelo);
                 cont++;
             }
             
           cont = cont + 2;  
            
         String [] datos = new String [cont];    
         
            try {
                 Statement st2 = con.createStatement();
                 ResultSet rs2 = st.executeQuery("SELECT * FROM sociales");
                 
                 while (rs2.next()){
                     int j = 1;
                     for (int i = 0; i<cont; i++ ) {
                         
                      datos[i]=rs2.getString(j);
                       j++;
                       
                     }    
                  modelo.addRow(datos);
                  
                 }
                 
                 TableSocialesPro1.setModel(modelo);
            } catch (Exception e) {
            }
             
        } catch (Exception e) {
        }
        
    }
    
    //show the "logros" in the table 
    
    public void ShowLogros() {
       
        
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Logros");
         TabInsSocialePro1.setModel(modelo);
         

         String [] datos = new String [1];
         
         try {
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM logro_sociales");
             
             
             while (rs.next()){
                datos[0]=rs.getString(2);
                modelo.addRow(datos);
                
             }
            
             
             } catch (Exception e) {
                 JOptionPane.showMessageDialog(null, e + "Erro al mostrar los logros ");
             }
         
    }
    
    // update en real time table
    
//    public void ActualizarNotas () {
//        int fila = this.TableSocialesPro1.getSelectedRow();
//        
//        int id = Integer.parseInt(this.TableSocialesPro1.getValueAt(fila, 0).toString());
//        String Lastname = TableSocialesPro1.getValueAt(fila, 1).toString();
//        
//        int cont = 0;
//        try {
//             Statement st = con.createStatement();
//             ResultSet rs = st.executeQuery("SELECT * FROM logro_sociales");
//             
//              cont+=2;
//             while (rs.next()){
//                cont ++;
//                for (int i = 1; i<cont; i++ ) {
//                String Names = "Logro"+(i) ;         
//                String Nombres = TableSocialesPro1.getValueAt(fila, i).toString();
//                
//                       
//                     }    
//             }
//            
//            
//             
//             
//            
//             
//             
//             
//             
//             
//            
//             } catch (Exception e) {
//                 JOptionPane.showMessageDialog(null, e + "Erro al mostrar los logros ");
//             }
//        
//    }
    
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Fondo = new javax.swing.JPanel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        Sociales = new javax.swing.JPanel();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        jPanel10 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        TxtSociales1 = new javax.swing.JTextField();
        ButInstSociales1 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        TabInsSocialePro1 = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        TableSocialesPro1 = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        Ingles = new javax.swing.JPanel();
        jTabbedPane6 = new javax.swing.JTabbedPane();
        jPanel13 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        TxtSociales2 = new javax.swing.JTextField();
        ButInstSociales2 = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        TabInsSocialePro2 = new javax.swing.JTable();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        TableSocialesPro2 = new javax.swing.JTable();
        jButton11 = new javax.swing.JButton();
        Ingles1 = new javax.swing.JPanel();
        jTabbedPane7 = new javax.swing.JTabbedPane();
        jPanel15 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        TxtSociales3 = new javax.swing.JTextField();
        ButInstSociales3 = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        TabInsSocialePro3 = new javax.swing.JTable();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        TableSocialesPro3 = new javax.swing.JTable();
        jButton12 = new javax.swing.JButton();
        Ingles2 = new javax.swing.JPanel();
        jTabbedPane8 = new javax.swing.JTabbedPane();
        jPanel17 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        TxtSociales4 = new javax.swing.JTextField();
        ButInstSociales4 = new javax.swing.JButton();
        jScrollPane11 = new javax.swing.JScrollPane();
        TabInsSocialePro4 = new javax.swing.JTable();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        TableSocialesPro4 = new javax.swing.JTable();
        jButton13 = new javax.swing.JButton();
        Ingles3 = new javax.swing.JPanel();
        jTabbedPane9 = new javax.swing.JTabbedPane();
        jPanel19 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        TxtSociales5 = new javax.swing.JTextField();
        ButInstSociales5 = new javax.swing.JButton();
        jScrollPane13 = new javax.swing.JScrollPane();
        TabInsSocialePro5 = new javax.swing.JTable();
        jPanel20 = new javax.swing.JPanel();
        jScrollPane14 = new javax.swing.JScrollPane();
        TableSocialesPro5 = new javax.swing.JTable();
        jButton14 = new javax.swing.JButton();
        Ingles4 = new javax.swing.JPanel();
        jTabbedPane10 = new javax.swing.JTabbedPane();
        jPanel21 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        TxtSociales6 = new javax.swing.JTextField();
        ButInstSociales6 = new javax.swing.JButton();
        jScrollPane15 = new javax.swing.JScrollPane();
        TabInsSocialePro6 = new javax.swing.JTable();
        jPanel22 = new javax.swing.JPanel();
        jScrollPane16 = new javax.swing.JScrollPane();
        TableSocialesPro6 = new javax.swing.JTable();
        jButton15 = new javax.swing.JButton();
        NewStudent = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        TxtTel1 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        DateBorn1 = new com.toedter.calendar.JDateChooser();
        jLabel27 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        TxtName1 = new javax.swing.JTextField();
        TxtDocument1 = new javax.swing.JTextField();
        TxtLasName1 = new javax.swing.JTextField();
        TxtAdress1 = new javax.swing.JTextField();
        Mensaje = new javax.swing.JLabel();
        TxtScname1 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        PassUserConfirm1 = new javax.swing.JPasswordField();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        PassUser1 = new javax.swing.JPasswordField();
        jButton2 = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel42 = new javax.swing.JLabel();
        ComboDocentes = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        LblNombreProfe = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        BtLogOut = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Fondo.setBackground(new java.awt.Color(255, 255, 255));
        Fondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane4.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(128, 237, 153), 4, true));
        jTabbedPane4.setForeground(new java.awt.Color(34, 87, 122));
        jTabbedPane4.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane4.setToolTipText("");
        jTabbedPane4.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jTabbedPane4.setMaximumSize(new java.awt.Dimension(2147483647, 2147483647));

        Sociales.setBackground(new java.awt.Color(87, 204, 153));
        Sociales.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(34, 87, 122)));
        Sociales.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane5.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane5.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jPanel10.setBackground(new java.awt.Color(34, 87, 122));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Insertar Nota");
        jPanel10.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 350, 150, -1));

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Descripcion");
        jPanel10.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 380, -1, -1));

        TxtSociales1.setBackground(new java.awt.Color(236, 250, 236));
        TxtSociales1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jPanel10.add(TxtSociales1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 410, 280, -1));

        ButInstSociales1.setText("Insertar");
        ButInstSociales1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButInstSociales1ActionPerformed(evt);
            }
        });
        jPanel10.add(ButInstSociales1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 420, -1, -1));

        TabInsSocialePro1.setAutoCreateRowSorter(true);
        TabInsSocialePro1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(87, 204, 153)));
        TabInsSocialePro1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        TabInsSocialePro1.setGridColor(new java.awt.Color(56, 163, 165));
        jScrollPane5.setViewportView(TabInsSocialePro1);

        jPanel10.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 670, 300));

        jTabbedPane5.addTab("Notas", null, jPanel10, "");

        jPanel11.setBackground(new java.awt.Color(34, 87, 122));
        jPanel11.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(128, 237, 153), 3, true));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TableSocialesPro1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        TableSocialesPro1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {}
            },
            new String [] {

            }
        ));
        TableSocialesPro1.setGridColor(new java.awt.Color(56, 163, 165));
        jScrollPane6.setViewportView(TableSocialesPro1);
        if (TableSocialesPro1.getColumnModel().getColumnCount() > 0) {
            TableSocialesPro1.getColumnModel().getColumn(1).setResizable(false);
        }

        jPanel11.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 742, 100));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/refresh.png"))); // NOI18N
        jButton5.setBorderPainted(false);
        jButton5.setContentAreaFilled(false);
        jButton5.setFocusable(false);
        jButton5.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/refresh.png"))); // NOI18N
        jButton5.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/refreshMedium.png"))); // NOI18N
        jPanel11.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 10, -1, -1));

        jTabbedPane5.addTab("Planilla", jPanel11);

        Sociales.add(jTabbedPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 860, 790));

        jTabbedPane4.addTab("SOCIALES                 ", new javax.swing.ImageIcon(getClass().getResource("/Images/Icono Estudio.png")), Sociales); // NOI18N

        Ingles.setBackground(new java.awt.Color(87, 204, 153));
        Ingles.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(34, 87, 122)));
        Ingles.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane6.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane6.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jPanel13.setBackground(new java.awt.Color(34, 87, 122));
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("Insertar Nota");
        jPanel13.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 350, 150, -1));

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Descripcion");
        jPanel13.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 380, -1, -1));

        TxtSociales2.setBackground(new java.awt.Color(236, 250, 236));
        TxtSociales2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jPanel13.add(TxtSociales2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 410, 280, -1));

        ButInstSociales2.setText("Insertar");
        jPanel13.add(ButInstSociales2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 420, -1, -1));

        TabInsSocialePro2.setAutoCreateRowSorter(true);
        TabInsSocialePro2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(87, 204, 153)));
        TabInsSocialePro2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Descripcion"
            }
        ));
        TabInsSocialePro2.setGridColor(new java.awt.Color(56, 163, 165));
        jScrollPane7.setViewportView(TabInsSocialePro2);

        jPanel13.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 670, 300));

        jTabbedPane6.addTab("Notas", null, jPanel13, "");

        jPanel14.setBackground(new java.awt.Color(34, 87, 122));
        jPanel14.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(128, 237, 153), 3, true));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TableSocialesPro2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombre Completo", "Actividad 1", "Actividad 2", "Actividad 3"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableSocialesPro2.setGridColor(new java.awt.Color(56, 163, 165));
        jScrollPane8.setViewportView(TableSocialesPro2);
        if (TableSocialesPro2.getColumnModel().getColumnCount() > 0) {
            TableSocialesPro2.getColumnModel().getColumn(1).setResizable(false);
        }

        jPanel14.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 742, 100));

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/refresh.png"))); // NOI18N
        jButton11.setBorderPainted(false);
        jButton11.setContentAreaFilled(false);
        jButton11.setFocusable(false);
        jButton11.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/refresh.png"))); // NOI18N
        jButton11.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/refreshMedium.png"))); // NOI18N
        jPanel14.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 10, -1, -1));

        jTabbedPane6.addTab("Planilla", jPanel14);

        Ingles.add(jTabbedPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 860, 790));

        jTabbedPane4.addTab("INGLES                      ", new javax.swing.ImageIcon(getClass().getResource("/Images/Icono Estudio.png")), Ingles); // NOI18N

        Ingles1.setBackground(new java.awt.Color(87, 204, 153));
        Ingles1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(34, 87, 122)));
        Ingles1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane7.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane7.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jPanel15.setBackground(new java.awt.Color(34, 87, 122));
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Insertar Nota");
        jPanel15.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 350, 150, -1));

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("Descripcion");
        jPanel15.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 380, -1, -1));

        TxtSociales3.setBackground(new java.awt.Color(236, 250, 236));
        TxtSociales3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jPanel15.add(TxtSociales3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 410, 280, -1));

        ButInstSociales3.setText("Insertar");
        jPanel15.add(ButInstSociales3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 420, -1, -1));

        TabInsSocialePro3.setAutoCreateRowSorter(true);
        TabInsSocialePro3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(87, 204, 153)));
        TabInsSocialePro3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Descripcion"
            }
        ));
        TabInsSocialePro3.setGridColor(new java.awt.Color(56, 163, 165));
        jScrollPane9.setViewportView(TabInsSocialePro3);

        jPanel15.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 670, 300));

        jTabbedPane7.addTab("Notas", null, jPanel15, "");

        jPanel16.setBackground(new java.awt.Color(34, 87, 122));
        jPanel16.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(128, 237, 153), 3, true));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TableSocialesPro3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombre Completo", "Actividad 1", "Actividad 2", "Actividad 3"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableSocialesPro3.setGridColor(new java.awt.Color(56, 163, 165));
        jScrollPane10.setViewportView(TableSocialesPro3);
        if (TableSocialesPro3.getColumnModel().getColumnCount() > 0) {
            TableSocialesPro3.getColumnModel().getColumn(1).setResizable(false);
        }

        jPanel16.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 742, 100));

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/refresh.png"))); // NOI18N
        jButton12.setBorderPainted(false);
        jButton12.setContentAreaFilled(false);
        jButton12.setFocusable(false);
        jButton12.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/refresh.png"))); // NOI18N
        jButton12.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/refreshMedium.png"))); // NOI18N
        jPanel16.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 10, -1, -1));

        jTabbedPane7.addTab("Planilla", jPanel16);

        Ingles1.add(jTabbedPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 860, 790));

        jTabbedPane4.addTab("MATEMATICAS         ", new javax.swing.ImageIcon(getClass().getResource("/Images/Icono Estudio.png")), Ingles1); // NOI18N

        Ingles2.setBackground(new java.awt.Color(87, 204, 153));
        Ingles2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(34, 87, 122)));
        Ingles2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane8.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane8.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jPanel17.setBackground(new java.awt.Color(34, 87, 122));
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("Insertar Nota");
        jPanel17.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 350, 150, -1));

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("Descripcion");
        jPanel17.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 380, -1, -1));

        TxtSociales4.setBackground(new java.awt.Color(236, 250, 236));
        TxtSociales4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jPanel17.add(TxtSociales4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 410, 280, -1));

        ButInstSociales4.setText("Insertar");
        jPanel17.add(ButInstSociales4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 420, -1, -1));

        TabInsSocialePro4.setAutoCreateRowSorter(true);
        TabInsSocialePro4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(87, 204, 153)));
        TabInsSocialePro4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Descripcion"
            }
        ));
        TabInsSocialePro4.setGridColor(new java.awt.Color(56, 163, 165));
        jScrollPane11.setViewportView(TabInsSocialePro4);

        jPanel17.add(jScrollPane11, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 670, 300));

        jTabbedPane8.addTab("Notas", null, jPanel17, "");

        jPanel18.setBackground(new java.awt.Color(34, 87, 122));
        jPanel18.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(128, 237, 153), 3, true));
        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TableSocialesPro4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombre Completo", "Actividad 1", "Actividad 2", "Actividad 3"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableSocialesPro4.setGridColor(new java.awt.Color(56, 163, 165));
        jScrollPane12.setViewportView(TableSocialesPro4);
        if (TableSocialesPro4.getColumnModel().getColumnCount() > 0) {
            TableSocialesPro4.getColumnModel().getColumn(1).setResizable(false);
        }

        jPanel18.add(jScrollPane12, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 742, 100));

        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/refresh.png"))); // NOI18N
        jButton13.setBorderPainted(false);
        jButton13.setContentAreaFilled(false);
        jButton13.setFocusable(false);
        jButton13.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/refresh.png"))); // NOI18N
        jButton13.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/refreshMedium.png"))); // NOI18N
        jPanel18.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 10, -1, -1));

        jTabbedPane8.addTab("Planilla", jPanel18);

        Ingles2.add(jTabbedPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 860, 790));

        jTabbedPane4.addTab("QUIMICA                   ", new javax.swing.ImageIcon(getClass().getResource("/Images/Icono Estudio.png")), Ingles2); // NOI18N

        Ingles3.setBackground(new java.awt.Color(87, 204, 153));
        Ingles3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(34, 87, 122)));
        Ingles3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane9.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane9.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jPanel19.setBackground(new java.awt.Color(34, 87, 122));
        jPanel19.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("Insertar Nota");
        jPanel19.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 350, 150, -1));

        jLabel39.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("Descripcion");
        jPanel19.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 380, -1, -1));

        TxtSociales5.setBackground(new java.awt.Color(236, 250, 236));
        TxtSociales5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jPanel19.add(TxtSociales5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 410, 280, -1));

        ButInstSociales5.setText("Insertar");
        jPanel19.add(ButInstSociales5, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 420, -1, -1));

        TabInsSocialePro5.setAutoCreateRowSorter(true);
        TabInsSocialePro5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(87, 204, 153)));
        TabInsSocialePro5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Descripcion"
            }
        ));
        TabInsSocialePro5.setGridColor(new java.awt.Color(56, 163, 165));
        jScrollPane13.setViewportView(TabInsSocialePro5);

        jPanel19.add(jScrollPane13, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 670, 300));

        jTabbedPane9.addTab("Notas", null, jPanel19, "");

        jPanel20.setBackground(new java.awt.Color(34, 87, 122));
        jPanel20.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(128, 237, 153), 3, true));
        jPanel20.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TableSocialesPro5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombre Completo", "Actividad 1", "Actividad 2", "Actividad 3"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableSocialesPro5.setGridColor(new java.awt.Color(56, 163, 165));
        jScrollPane14.setViewportView(TableSocialesPro5);
        if (TableSocialesPro5.getColumnModel().getColumnCount() > 0) {
            TableSocialesPro5.getColumnModel().getColumn(1).setResizable(false);
        }

        jPanel20.add(jScrollPane14, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 742, 100));

        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/refresh.png"))); // NOI18N
        jButton14.setBorderPainted(false);
        jButton14.setContentAreaFilled(false);
        jButton14.setFocusable(false);
        jButton14.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/refresh.png"))); // NOI18N
        jButton14.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/refreshMedium.png"))); // NOI18N
        jPanel20.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 10, -1, -1));

        jTabbedPane9.addTab("Planilla", jPanel20);

        Ingles3.add(jTabbedPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 860, 790));

        jTabbedPane4.addTab("TECNOLOGIA             ", new javax.swing.ImageIcon(getClass().getResource("/Images/Icono Estudio.png")), Ingles3); // NOI18N

        Ingles4.setBackground(new java.awt.Color(87, 204, 153));
        Ingles4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(34, 87, 122)));
        Ingles4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane10.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane10.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        jTabbedPane10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jPanel21.setBackground(new java.awt.Color(34, 87, 122));
        jPanel21.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel40.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText("Insertar Nota");
        jPanel21.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 350, 150, -1));

        jLabel41.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setText("Descripcion");
        jPanel21.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 380, -1, -1));

        TxtSociales6.setBackground(new java.awt.Color(236, 250, 236));
        TxtSociales6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jPanel21.add(TxtSociales6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 410, 280, -1));

        ButInstSociales6.setText("Insertar");
        jPanel21.add(ButInstSociales6, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 420, -1, -1));

        TabInsSocialePro6.setAutoCreateRowSorter(true);
        TabInsSocialePro6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(87, 204, 153)));
        TabInsSocialePro6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Descripcion"
            }
        ));
        TabInsSocialePro6.setGridColor(new java.awt.Color(56, 163, 165));
        jScrollPane15.setViewportView(TabInsSocialePro6);

        jPanel21.add(jScrollPane15, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 670, 300));

        jTabbedPane10.addTab("Notas", null, jPanel21, "");

        jPanel22.setBackground(new java.awt.Color(34, 87, 122));
        jPanel22.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(128, 237, 153), 3, true));
        jPanel22.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TableSocialesPro6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombre Completo", "Actividad 1", "Actividad 2", "Actividad 3"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TableSocialesPro6.setGridColor(new java.awt.Color(56, 163, 165));
        jScrollPane16.setViewportView(TableSocialesPro6);
        if (TableSocialesPro6.getColumnModel().getColumnCount() > 0) {
            TableSocialesPro6.getColumnModel().getColumn(1).setResizable(false);
        }

        jPanel22.add(jScrollPane16, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 742, 100));

        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/refresh.png"))); // NOI18N
        jButton15.setBorderPainted(false);
        jButton15.setContentAreaFilled(false);
        jButton15.setFocusable(false);
        jButton15.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/refresh.png"))); // NOI18N
        jButton15.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/refreshMedium.png"))); // NOI18N
        jPanel22.add(jButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 10, -1, -1));

        jTabbedPane10.addTab("Planilla", jPanel22);

        Ingles4.add(jTabbedPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 860, 790));

        jTabbedPane4.addTab("ESPAÑOL                    ", new javax.swing.ImageIcon(getClass().getResource("/Images/Icono Estudio.png")), Ingles4); // NOI18N

        NewStudent.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED, null, new java.awt.Color(128, 237, 153), null, new java.awt.Color(128, 237, 153)));
        NewStudent.setForeground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(56, 163, 165));
        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED, null, new java.awt.Color(128, 237, 153), null, new java.awt.Color(128, 237, 153)));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel21.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Segundo Nombre");
        jPanel3.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 100, 170, -1));

        jLabel22.setFont(new java.awt.Font("Comic Sans MS", 1, 36)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Registro Nuevo Usuario");
        jPanel3.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, 470, -1));

        jLabel23.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Fecha de Nacimiento");
        jPanel3.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 210, -1));

        jLabel24.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Primer Nombre");
        jPanel3.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 160, -1));

        TxtTel1.setBackground(new java.awt.Color(87, 204, 153));
        TxtTel1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        TxtTel1.setForeground(new java.awt.Color(255, 255, 255));
        TxtTel1.setBorder(null);
        TxtTel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtTel1ActionPerformed(evt);
            }
        });
        jPanel3.add(TxtTel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 240, 190, -1));

        jLabel25.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Apellidos");
        jPanel3.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 140, -1));

        jLabel26.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Dirreccion de Recidencia ");
        jPanel3.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 270, -1));

        DateBorn1.setBackground(new java.awt.Color(87, 204, 153));
        DateBorn1.setToolTipText("");
        DateBorn1.setDateFormatString("yyyy/MM/dd");
        DateBorn1.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N
        jPanel3.add(DateBorn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 290, 190, 30));

        jLabel27.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Documento");
        jPanel3.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 150, 160, -1));
        jPanel3.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, 830, 20));

        TxtName1.setBackground(new java.awt.Color(87, 204, 153));
        TxtName1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        TxtName1.setForeground(new java.awt.Color(255, 255, 255));
        TxtName1.setBorder(null);
        TxtName1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtName1ActionPerformed(evt);
            }
        });
        jPanel3.add(TxtName1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 100, 190, -1));

        TxtDocument1.setBackground(new java.awt.Color(87, 204, 153));
        TxtDocument1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        TxtDocument1.setForeground(new java.awt.Color(255, 255, 255));
        TxtDocument1.setBorder(null);
        TxtDocument1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtDocument1ActionPerformed(evt);
            }
        });
        jPanel3.add(TxtDocument1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 150, 190, -1));

        TxtLasName1.setBackground(new java.awt.Color(87, 204, 153));
        TxtLasName1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        TxtLasName1.setForeground(new java.awt.Color(255, 255, 255));
        TxtLasName1.setBorder(null);
        TxtLasName1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtLasName1ActionPerformed(evt);
            }
        });
        jPanel3.add(TxtLasName1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 150, 190, -1));

        TxtAdress1.setBackground(new java.awt.Color(87, 204, 153));
        TxtAdress1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        TxtAdress1.setForeground(new java.awt.Color(255, 255, 255));
        TxtAdress1.setBorder(null);
        TxtAdress1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtAdress1ActionPerformed(evt);
            }
        });
        jPanel3.add(TxtAdress1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 240, 190, -1));

        Mensaje.setFont(new java.awt.Font("Comic Sans MS", 3, 24)); // NOI18N
        Mensaje.setForeground(new java.awt.Color(255, 0, 51));
        jPanel3.add(Mensaje, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 540, 370, -1));

        TxtScname1.setBackground(new java.awt.Color(87, 204, 153));
        TxtScname1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        TxtScname1.setForeground(new java.awt.Color(255, 255, 255));
        TxtScname1.setBorder(null);
        TxtScname1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtScname1ActionPerformed(evt);
            }
        });
        jPanel3.add(TxtScname1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 100, 190, -1));

        jLabel29.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Telefono");
        jPanel3.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 240, 90, -1));

        PassUserConfirm1.setBackground(new java.awt.Color(87, 204, 153));
        PassUserConfirm1.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N
        PassUserConfirm1.setBorder(null);
        PassUserConfirm1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                PassUserConfirm1KeyReleased(evt);
            }
        });
        jPanel3.add(PassUserConfirm1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 440, 210, 30));

        jLabel30.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Clave de Usuario");
        jPanel3.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 440, 230, -1));

        jLabel31.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Docente Asignado");
        jPanel3.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 390, 230, -1));

        PassUser1.setBackground(new java.awt.Color(87, 204, 153));
        PassUser1.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N
        PassUser1.setBorder(null);
        jPanel3.add(PassUser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 440, 210, 30));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Boton Crear.png"))); // NOI18N
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setDefaultCapable(false);
        jButton2.setFocusPainted(false);
        jButton2.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Boton Crear.png"))); // NOI18N
        jButton2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Boton Crearxs.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 580, -1, -1));
        jPanel3.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 830, 20));

        jLabel42.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
        jLabel42.setText("Confirmar clave");
        jPanel3.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 440, 160, -1));

        ComboDocentes.setBackground(new java.awt.Color(87, 204, 153));
        ComboDocentes.setFont(new java.awt.Font("Comic Sans MS", 0, 20)); // NOI18N
        ComboDocentes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel3.add(ComboDocentes, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 390, 280, -1));

        javax.swing.GroupLayout NewStudentLayout = new javax.swing.GroupLayout(NewStudent);
        NewStudent.setLayout(NewStudentLayout);
        NewStudentLayout.setHorizontalGroup(
            NewStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NewStudentLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 903, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        NewStudentLayout.setVerticalGroup(
            NewStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NewStudentLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("ESTUDIANTE NUEVO", new javax.swing.ImageIcon(getClass().getResource("/Images/anadir-grupo.png")), NewStudent); // NOI18N

        Fondo.add(jTabbedPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 1210, 760));

        jPanel1.setBackground(new java.awt.Color(34, 87, 122));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(128, 237, 153), 3));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/avatar.png"))); // NOI18N
        jLabel5.setToolTipText("");
        jLabel5.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 110, 110));

        LblNombreProfe.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        LblNombreProfe.setForeground(new java.awt.Color(255, 255, 255));
        LblNombreProfe.setText("Nombre Docente");
        jPanel1.add(LblNombreProfe, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, -1, -1));

        Fondo.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 310, 210));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/LogoBlanco.png"))); // NOI18N
        Fondo.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 40, 130, -1));

        jLabel14.setFont(new java.awt.Font("Comic Sans MS", 0, 36)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("\"Educar es redimir\"");
        Fondo.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 70, 380, 70));

        BtLogOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/log-out.png"))); // NOI18N
        BtLogOut.setToolTipText("");
        BtLogOut.setBorderPainted(false);
        BtLogOut.setContentAreaFilled(false);
        BtLogOut.setDefaultCapable(false);
        BtLogOut.setFocusable(false);
        BtLogOut.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/log-out.png"))); // NOI18N
        BtLogOut.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/log-outMEdium.png"))); // NOI18N
        BtLogOut.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        BtLogOut.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtLogOutActionPerformed(evt);
            }
        });
        Fondo.add(BtLogOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 10, 50, 40));

        jLabel8.setFont(new java.awt.Font("Comic Sans MS", 0, 60)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Fondo Notas.jpg"))); // NOI18N
        Fondo.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, -1570, 1260, 2540));

        getContentPane().add(Fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1240, 970));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TxtScname1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtScname1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtScname1ActionPerformed

    private void TxtAdress1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtAdress1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtAdress1ActionPerformed

    private void TxtLasName1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtLasName1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtLasName1ActionPerformed

    private void TxtDocument1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtDocument1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtDocument1ActionPerformed

    private void TxtName1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtName1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtName1ActionPerformed

    private void TxtTel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtTel1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtTel1ActionPerformed

    private void BtLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtLogOutActionPerformed
       
        
        
        Login X = new Login();
      
        
        X.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BtLogOutActionPerformed

    private void PassUserConfirm1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PassUserConfirm1KeyReleased
        String Pass1 = String.valueOf(PassUser1.getText());
        String Pass1R = String.valueOf(PassUserConfirm1.getText());
        
        if (Pass1.equals(Pass1R)) {
                 
            Mensaje.setText("");
            
        }else {
            Mensaje.setText("*Claves no coinciden");
        }
    }//GEN-LAST:event_PassUserConfirm1KeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       AddStudent();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void ButInstSociales1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButInstSociales1ActionPerformed
        AddSociales();
        AddLogro();
        ShowNotas();
        ShowLogros();
    }//GEN-LAST:event_ButInstSociales1ActionPerformed

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
            java.util.logging.Logger.getLogger(NotasProfesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NotasProfesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NotasProfesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NotasProfesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NotasProfesor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtLogOut;
    private javax.swing.JButton ButInstSociales1;
    private javax.swing.JButton ButInstSociales2;
    private javax.swing.JButton ButInstSociales3;
    private javax.swing.JButton ButInstSociales4;
    private javax.swing.JButton ButInstSociales5;
    private javax.swing.JButton ButInstSociales6;
    private javax.swing.JComboBox<String> ComboDocentes;
    private com.toedter.calendar.JDateChooser DateBorn1;
    private javax.swing.JPanel Fondo;
    private javax.swing.JPanel Ingles;
    private javax.swing.JPanel Ingles1;
    private javax.swing.JPanel Ingles2;
    private javax.swing.JPanel Ingles3;
    private javax.swing.JPanel Ingles4;
    public static javax.swing.JLabel LblNombreProfe;
    private javax.swing.JLabel Mensaje;
    private javax.swing.JPanel NewStudent;
    private javax.swing.JPasswordField PassUser1;
    private javax.swing.JPasswordField PassUserConfirm1;
    private javax.swing.JPanel Sociales;
    private javax.swing.JTable TabInsSocialePro1;
    private javax.swing.JTable TabInsSocialePro2;
    private javax.swing.JTable TabInsSocialePro3;
    private javax.swing.JTable TabInsSocialePro4;
    private javax.swing.JTable TabInsSocialePro5;
    private javax.swing.JTable TabInsSocialePro6;
    private javax.swing.JTable TableSocialesPro1;
    private javax.swing.JTable TableSocialesPro2;
    private javax.swing.JTable TableSocialesPro3;
    private javax.swing.JTable TableSocialesPro4;
    private javax.swing.JTable TableSocialesPro5;
    private javax.swing.JTable TableSocialesPro6;
    private javax.swing.JTextField TxtAdress1;
    private javax.swing.JTextField TxtDocument1;
    private javax.swing.JTextField TxtLasName1;
    private javax.swing.JTextField TxtName1;
    private javax.swing.JTextField TxtScname1;
    private javax.swing.JTextField TxtSociales1;
    private javax.swing.JTextField TxtSociales2;
    private javax.swing.JTextField TxtSociales3;
    private javax.swing.JTextField TxtSociales4;
    private javax.swing.JTextField TxtSociales5;
    private javax.swing.JTextField TxtSociales6;
    private javax.swing.JTextField TxtTel1;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTabbedPane jTabbedPane10;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JTabbedPane jTabbedPane6;
    private javax.swing.JTabbedPane jTabbedPane7;
    private javax.swing.JTabbedPane jTabbedPane8;
    private javax.swing.JTabbedPane jTabbedPane9;
    // End of variables declaration//GEN-END:variables

}
