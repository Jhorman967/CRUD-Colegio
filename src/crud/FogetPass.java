/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package crud;

import static crud.Login.contador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Jhorman Gonzalez << Jhorman967@gmail.com >>
 */
public class FogetPass extends javax.swing.JFrame {
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
                        icono = new ImageIcon(getClass().getResource("/Images/graduacion.jpg"));
                        ImagenLogin.setIcon(icono);
                        break;
                    case 2: 
                        contador = 3;
                        icono = new ImageIcon(getClass().getResource("/Images/estudiantes-table.jpg"));
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
    public FogetPass() {
        initComponents();
         this.setLocationRelativeTo(null);
         this.setResizable(false);
         this.images();
    }
    
    public void ModificarPass() {
        
        String user = this.TxtNombre.getText();
        String Pass = this.NewPassword.getText();
        int ID = Integer.parseInt(this.TxtNumeroIdF.getText());
        String Date = (((JTextField)this.DateF.getDateEditor().getUiComponent()).getText());
        int resul = 0 ;
        
        try {
            if (ButtonDoncetef.isSelected() && ButtonEstudiantef.isSelected()){
                 JOptionPane.showMessageDialog(null,"Seleccione solo un rol");
            }else {
                if(ButtonDoncetef.isSelected()){
                String sql = "select * from docentes where Nombre = '"+user+"' and Cedula = '"+ID+"' "  ;
                Statement st = (Statement) con.createStatement();
                ResultSet res = st.executeQuery(sql);
                    
                    if(res.next()){
                        resul=1;
                        if(resul == 1){
                            String Id_Prf = res.getString("id_docente");
                            String sql1 = "UPDATE docentes SET Password = '"+Pass+"' WHERE id_docente = '"+Id_Prf+"' " ;
                            try {
                                PreparedStatement pst = con.prepareStatement(sql1);
                                
                               pst.executeUpdate();
                               JOptionPane.showMessageDialog(null, "Cambio Exitoso");
                                        
                            } catch (Exception e) {
                                 JOptionPane.showMessageDialog(null, "Error cambio" + e.getMessage());
                            }
                            
                        }
                    }
                } else {
                    if (ButtonEstudiantef.isSelected()){
                     String sql = "select * from estudiantes where fst_nombre = '"+user+"' and Documento = '"+ID+"' "  ;
                Statement st = (Statement) con.createStatement();
                ResultSet res = st.executeQuery(sql);
                    
                    if(res.next()){
                        resul=1;
                        if(resul == 1){
                            String Id_est = res.getString("id_estudiante");
                            String sql1 = "UPDATE estudiantes SET Password = '"+Pass+"' WHERE id_estudiante = '"+Id_est+"' " ;
                            try {
                                PreparedStatement pst = con.prepareStatement(sql1);
                                
                               pst.executeUpdate();
                               JOptionPane.showMessageDialog(null, "Cambio Exitoso");
                                        
                            } catch (Exception e) {
                                 JOptionPane.showMessageDialog(null, "Error cambio" + e.getMessage());
                            }
                            
                        }
                    }
                    }
                }
               
            }
        } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error en el acceso, vuelva a intentarlo"+e.getMessage());
        }
        
   
        
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        Fondo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        TxtNombre = new javax.swing.JTextField();
        ButtonEstudiantef = new javax.swing.JRadioButton();
        ButtonDoncetef = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        NewPassword = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        TxtNumeroIdF = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        DateF = new com.toedter.calendar.JDateChooser();
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
        jLabel1.setText("Nueva Clave");
        jLabel1.setToolTipText("");
        Fondo.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 350, -1, -1));

        TxtNombre.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        TxtNombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TxtNombre.setBorder(null);
        TxtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtNombreActionPerformed(evt);
            }
        });
        Fondo.add(TxtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 69, 335, 40));

        ButtonEstudiantef.setBackground(new java.awt.Color(255, 255, 255));
        ButtonEstudiantef.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        ButtonEstudiantef.setText("Estudiante");
        ButtonEstudiantef.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                ButtonEstudiantefCaretPositionChanged(evt);
            }
        });
        Fondo.add(ButtonEstudiantef, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 440, -1, -1));

        ButtonDoncetef.setBackground(new java.awt.Color(255, 255, 255));
        ButtonDoncetef.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        ButtonDoncetef.setText("Docente");
        ButtonDoncetef.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonDoncetefActionPerformed(evt);
            }
        });
        Fondo.add(ButtonDoncetef, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 440, -1, -1));

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

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator1.setToolTipText("");
        jSeparator1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(3, 196, 161)));
        Fondo.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 118, 330, -1));

        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator2.setToolTipText("");
        jSeparator2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(3, 196, 161)));
        Fondo.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 420, 340, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Fondo Loging1.png"))); // NOI18N
        jLabel5.setText("jLabel5");
        Fondo.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 570, -1, -1));

        NewPassword.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        NewPassword.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        NewPassword.setBorder(null);
        NewPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewPasswordActionPerformed(evt);
            }
        });
        Fondo.add(NewPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 380, 340, 40));

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel3.setText("Nombre");
        Fondo.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, -1, -1));

        TxtNumeroIdF.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        TxtNumeroIdF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TxtNumeroIdF.setBorder(null);
        TxtNumeroIdF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtNumeroIdFActionPerformed(evt);
            }
        });
        Fondo.add(TxtNumeroIdF, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, 335, 40));

        jSeparator3.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator3.setToolTipText("");
        jSeparator3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(3, 196, 161)));
        Fondo.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 220, 330, -1));

        jLabel6.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel6.setText("Numero de Documento");
        Fondo.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, -1, -1));

        jLabel7.setFont(new java.awt.Font("Comic Sans MS", 0, 24)); // NOI18N
        jLabel7.setText("Fecha de Nacimiento");
        Fondo.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, -1, -1));

        jSeparator4.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator4.setToolTipText("");
        jSeparator4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(3, 196, 161)));
        Fondo.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 320, 330, -1));

        DateF.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        Fondo.add(DateF, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 282, 320, 30));

        getContentPane().add(Fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 490, 780));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ImagenLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/estudiantes-table.jpg"))); // NOI18N
        jPanel1.add(ImagenLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(-20, 0, 980, 780));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 0, 960, 780));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      ModificarPass();
        
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void TxtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtNombreActionPerformed

    private void ButtonEstudiantefCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_ButtonEstudiantefCaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_ButtonEstudiantefCaretPositionChanged

    private void ButtonDoncetefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonDoncetefActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ButtonDoncetefActionPerformed

    private void TxtNumeroIdFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtNumeroIdFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtNumeroIdFActionPerformed

    private void NewPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NewPasswordActionPerformed

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
            java.util.logging.Logger.getLogger(FogetPass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FogetPass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FogetPass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FogetPass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FogetPass().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton ButtonDoncetef;
    private javax.swing.JRadioButton ButtonEstudiantef;
    private com.toedter.calendar.JDateChooser DateF;
    private javax.swing.JPanel Fondo;
    public javax.swing.JLabel ImagenLogin;
    private javax.swing.JPasswordField NewPassword;
    private javax.swing.JTextField TxtNombre;
    private javax.swing.JTextField TxtNumeroIdF;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables

}
