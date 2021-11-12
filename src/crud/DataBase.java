/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 * 
 * @author Jhorman Gonzalez << Jhorman967@gmail.com >>
 */
public class DataBase {
    
  // Date Base Conection
        
        String usuario = "root";
        String clave = "";
        String url = "jdbc:mysql://localhost:3306/colegio";
        Connection Con =null; 
        Statement stmt;
        ResultSet rs;
        
        
       public Connection Conexion (){
            
       try {
        
        Class.forName("com.mysql.jdbc.Driver");
       Con = (Connection) DriverManager.getConnection(url,usuario,clave);
        
                
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error de Conexion" + e.getMessage());
            }
            return (Con);
    }
       
       
// Metodo para colocar la concsulta sql dentro de los metodos
public ResultSet consulta(String sql){
    ResultSet res = null;
    try{
        PreparedStatement pstm = Con.prepareStatement(sql);
        res = pstm.executeQuery();
    }catch (SQLException e) {
        System.err.println("Error Consulta : " + e.getMessage());
    }
    return res;
   
}

// metodo para obtener datos de la base de datos 

public DefaultComboBoxModel Obt_profesor(){
    DefaultComboBoxModel ListaDocente = new DefaultComboBoxModel();
    ListaDocente.addElement("Codigo Del Docente");
    ResultSet res = this.consulta("Select * from docentes");
    
    try{
        while(res.next()){
            ListaDocente.addElement(res.getString("id_docente"));
        }
        res.close();
    }catch(SQLException ex){
        System.err.println(ex.getMessage());
    }
    return ListaDocente;
}


}

