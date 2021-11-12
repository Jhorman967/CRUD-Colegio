/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package crud;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Jhorman Gonzalez << Jhorman967@gmail.com >>
 */
public class Login extends javax.swing.JFrame {
    
    static int contador=0;
    
    DataBase cc = new DataBase();
    Connection con = cc.Conexion();
    
    public void images(){
    
    int velocidad = 3;
        
        Timer timer;
        TimerTask tarea;
        
        int velmil = velocidad*1000;
        
        
       
        tarea = new TimerTask(){
            @Override
            public void run (){
                Icon icono;
                switch(contador){
                    case 0:
                        contador = 1;
                        icono = new ImageIcon(getClass().getResource("/Images/estudiante-estudiando.jpg"));
                        ImagenLogin.setIcon(icono);
                        break;
                    
                    case 1: 
                        contador = 2;
                        icono = new ImageIcon(getClass().getResource("/Images/estudiantes-table.jpg"));
                        ImagenLogin.setIcon(icono);
                        break;
                    case 2: 
                        contador = 3;
                        icono = new ImageIcon(getClass().getResource("/Images/graduacion.jpg"));
                        ImagenLogin.setIcon(icono);
                        break;
                     case 3: 
                        contador = 1;
                        icono = new ImageIcon(getClass().getResource("/Images/estudiante-estudiando.jpg"));
                        ImagenLogin.setIcon(icono);
                        break;  
                }
            }
                  
        };
        
        timer = new Timer();
//        
        timer.scheduleAtFixedRate(tarea,velmil,velmil);
       
    
    
    }
    
    /** Creates new form Login */
    public Login() {
        initComponents();
         this.setLocationRelativeTo(null);
         this.setResizable(false);
        this.images();
        
    }
    
    public void Acceso(){
        
        String user = this.TxtUser.getText();
        String Pass = this.TxtPass.getText();
        int resul = 0;
   try {     
    if (ButtonDoncete.isSelected() && ButtonEstudiante.isSelected()){
        JOptionPane.showMessageDialog(null,"Seleccione solo un rol");
    }else {
     if(ButtonDoncete.isSelected()){
         
         String sql = "select * from docentes where Cedula = '"+user+"' and Password = '"+Pass+"' ";
         Statement st = (Statement) con.createStatement();
         ResultSet res = st.executeQuery(sql);
         
         if (res.next()){
             resul = 1;
             if (resul==1){
                 String Name = res.getString("Nombre");
                 String LstName = res.getString("Apellido");
                  NotasProfesor X = new NotasProfesor();
                    X.LblNombreProfe.setText(Name + " " + LstName);
                    X.setVisible(true);
                    dispose();   
             }
         }else{
             JOptionPane.showMessageDialog(null, "Error en el acceso, vuelva a intentarlo");
         }
     
     }else {
     if(ButtonEstudiante.isSelected()){
        String sql = "select * from estudiantes where Documento = '"+user+"' and Password = '"+Pass+"' ";
         Statement st = (Statement) con.createStatement();
         ResultSet res = st.executeQuery(sql); 
         if (res.next()){
             resul = 1;
             if (resul==1){
                 String Name = res.getString("fst_nombre");
                 String LstName = res.getString("apellido");
                   NotasEstudiante X = new NotasEstudiante();
                    X.LblNombreEstudiante.setText(Name + " " + LstName);
                    X.setVisible(true);
                    dispose();
             }
         }else{
             JOptionPane.showMessageDialog(null, "Error en el acceso, vuelva a intentarlo");
         }
     

     
     }
     }
    
    }
    
    } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error en el acceso, vuelva a intentarlo"+e.getMessage());
             }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        Fondo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        TxtUser = new javax.swing.JTextField();
        ButtonEstudiante = new javax.swing.JRadioButton();
        ButtonDoncete = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        TxtPass = new javax.swing.JPasswordField();
        jPanel1 = new javax.swing.JPanel();
        ImagenLogin = new javax.swing.JLabel();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Fondo.setBackground(new java.awt.Color(255, 255, 255));
        Fondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel1.setText("Clave");
        jLabel1.setToolTipText("");
        Fondo.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 330, -1, -1));

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel2.setText("Usuario");
        Fondo.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 200, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Si no recuerda su contraseña de click ");
        Fondo.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 718, -1, -1));

        TxtUser.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        TxtUser.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TxtUser.setBorder(null);
        TxtUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtUserActionPerformed(evt);
            }
        });
        Fondo.add(TxtUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 250, 335, 40));

        ButtonEstudiante.setBackground(new java.awt.Color(255, 255, 255));
        ButtonEstudiante.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        ButtonEstudiante.setText("Estudiante");
        ButtonEstudiante.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                ButtonEstudianteCaretPositionChanged(evt);
            }
        });
        Fondo.add(ButtonEstudiante, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 440, -1, -1));

        ButtonDoncete.setBackground(new java.awt.Color(255, 255, 255));
        ButtonDoncete.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        ButtonDoncete.setText("Docente");
        ButtonDoncete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonDonceteActionPerformed(evt);
            }
        });
        Fondo.add(ButtonDoncete, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 440, -1, -1));

        jButton1.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Boton Ingreso.png"))); // NOI18N
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jButton1.setFocusPainted(false);
        jButton1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Boton Ingreso.png"))); // NOI18N
        jButton1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Boton-Activado.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        Fondo.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 480, 180, 80));

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton2.setText("AQUI");
        jButton2.setAlignmentX(2.0F);
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setDefaultCapable(false);
        jButton2.setFocusPainted(false);
        jButton2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jButton2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        Fondo.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 710, 50, 30));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/GWS_logo - copia.png"))); // NOI18N
        Fondo.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 40, -1, -1));

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator1.setToolTipText("");
        jSeparator1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(3, 196, 161)));
        Fondo.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, 330, -1));

        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator2.setToolTipText("");
        jSeparator2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(3, 196, 161)));
        Fondo.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 416, 340, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Fondo Loging1.png"))); // NOI18N
        jLabel5.setText("jLabel5");
        Fondo.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 570, -1, -1));

        TxtPass.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        TxtPass.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TxtPass.setBorder(null);
        TxtPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtPassActionPerformed(evt);
            }
        });
        Fondo.add(TxtPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 370, 340, 40));

        getContentPane().add(Fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 490, 780));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ImagenLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/estudiantes-table.jpg"))); // NOI18N
        jPanel1.add(ImagenLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 0, 980, 780));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 0, 960, 780));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Acceso();
       
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void ButtonEstudianteCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_ButtonEstudianteCaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_ButtonEstudianteCaretPositionChanged

    private void TxtUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtUserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtUserActionPerformed

    private void ButtonDonceteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonDonceteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ButtonDonceteActionPerformed

    private void TxtPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtPassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtPassActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        FogetPass X = new FogetPass();
        X.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton ButtonDoncete;
    private javax.swing.JRadioButton ButtonEstudiante;
    private javax.swing.JPanel Fondo;
    public javax.swing.JLabel ImagenLogin;
    private javax.swing.JPasswordField TxtPass;
    private javax.swing.JTextField TxtUser;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables

}
