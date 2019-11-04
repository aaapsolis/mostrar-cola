package Clases;

import javax.swing.JOptionPane;
import java.sql.*;


public class BdMysql {
    
    Connection conectar=null;
    
    public Connection conexion(){
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
           conectar=DriverManager.getConnection("jdbc:mysql://localhost/tienda","root","");
             
        } catch (ClassNotFoundException | SQLException e) {
            
            JOptionPane.showMessageDialog(null, "Error de conexion" + e.getMessage());
        
        }
        
        return conectar;
        
    }
    
}
