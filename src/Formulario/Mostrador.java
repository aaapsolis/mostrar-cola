/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formulario;

import Clases.BdMysql;
import Clases.Persona;
import Clases.Cola;
import java.awt.HeadlessException;
import java.awt.List;
import java.sql.*;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

public class Mostrador extends javax.swing.JFrame {

    public Mostrador() {
        initComponents();
        mostrarDatos();
        doWork();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        JPersonasAtendidas = new javax.swing.JTextField();
        JNombre = new javax.swing.JTextField();
        JNit = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(21, 93, 116));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JPersonasAtendidas.setEditable(false);
        jPanel1.add(JPersonasAtendidas, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 180, 270, -1));

        JNombre.setEditable(false);
        jPanel1.add(JNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 250, 270, -1));

        JNit.setEditable(false);
        jPanel1.add(JNit, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 320, 270, -1));

        jLabel1.setForeground(java.awt.Color.white);
        jLabel1.setText("Personas Atendidas:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, -1, 30));

        jLabel2.setForeground(java.awt.Color.white);
        jLabel2.setText("Nombre:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 250, -1, -1));

        jLabel3.setForeground(java.awt.Color.white);
        jLabel3.setText("Nit:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 310, 50, 30));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Atendiendo2.png"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, 130, 130));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 580, 400));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    BdMysql cc = new BdMysql();
    Connection con= cc.conexion();
    
    Cola<Persona> listado = new Cola<>();
    Persona ejem;
    
    
    public void mostrarDatos(){
     
     String [] registros = new String[4];
     
     String SQL="select * from clientes";
     
     try {
         
         Statement st=con.createStatement();
         ResultSet rs=st.executeQuery(SQL);
         
         while (rs.next()) {             
             
             registros[0]=rs.getString("idclientes");
             registros[1]=rs.getString("nombre");
             registros[2]=rs.getString("apellido");
             registros[3]=rs.getString("nit");

             ejem = new Persona(registros[1], registros[2], registros[3]);
             listado.enqueue(ejem);
             
         }
         
         LimpiarBD();

         
     } catch (SQLException e) {
         
          
          System.out.println("Error al mostrar datos " + e.getMessage());
     }
 
 }
    
    public void LimpiarBD(){
    
     try {
         
         String SQL="delete from clientes";
         
         Statement st=con.createStatement();
         
         int n=st.executeUpdate(SQL);

         
     } catch (HeadlessException | SQLException e) {

 
 }
    
    }
     
    int i = 0;
    
    protected void doWork() {
        
        SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>() {
            
            @Override
            protected Void doInBackground() throws Exception {
                while (!listado.isEmpty()) {
                    
                    ejem = listado.dequeue();
                    
                    publish(i++);
                    
                    Thread.sleep(10000);
                }
                
                return null;
            }

            protected void process(java.util.List<Integer> chunks) {
                
                JPersonasAtendidas.setText(chunks.get(chunks.size() - 1).toString());
                JNombre.setText(ejem.getNombre()+" "+ejem.getApellido());
                JNit.setText(String.valueOf(ejem.getNit()));   
                JPersonasAtendidas.setText(Integer.toString(i));

            }

            @Override
            protected void done() {
               mostrarDatos();
               doWork(); 
            }
        };
        worker.execute();
    }
    
     
    
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
            java.util.logging.Logger.getLogger(Mostrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Mostrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Mostrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Mostrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Mostrador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField JNit;
    private javax.swing.JTextField JNombre;
    private javax.swing.JTextField JPersonasAtendidas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
