package com.mycompany.gestiondato;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class Logica {
    public static void Insertar(String Cedula, String Nombres,String Apellidos){
    
        Conector objetoConexion = new Conector();
        
        
        String consulta ="insert into Datos (Cedula, Nombres, Apellidos) values(?,?,?);";
        
        try {
            
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            
            cs.setString(1, Cedula);
            cs.setString(2, Nombres);
            cs.setString(3, Apellidos);
            
            cs.execute();
            
            
            JOptionPane.showMessageDialog(null, "Se insertaron correctamente los datos");
            
            
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "No se insertaron correctamente los datos, error: "+e.toString());
        }
        
        
    
    }
    
    public void Mostrar(JTable paramTablaDatos){
    
        Conector objetoConexion = new Conector();
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        TableRowSorter<TableModel> OrdenarTabla= new TableRowSorter<TableModel>(modelo);
        paramTablaDatos.setRowSorter(OrdenarTabla);
        
        String sql="";
        
        modelo.addColumn("Id");
        modelo.addColumn("Cedula");
        modelo.addColumn("Nombres");
        modelo.addColumn("Apellidos");
        
        paramTablaDatos.setModel(modelo); 
        
        
        sql ="select * from Datos;";
        
        
        String[] datos = new String[4];
        Statement st;
        
        try {
            st= objetoConexion.estableceConexion().createStatement();
            
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
            
                datos[0]=rs.getString(1);
                datos[1]=rs.getString(2);
                datos[2]=rs.getString(3);
                datos[3]=rs.getString(4);
                
                modelo.addRow(datos);
            }
            
            paramTablaDatos.setModel(modelo);
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null,"No se pudo mostrar los registros, error: "+ e.toString());
        }
     
    }
    
    public void Seleccionar(JTable tbDatos,JTextField paramId, JTextField paramCedula, JTextField paramNombres, JTextField paramApellidos){
    
        try {
            int fila = tbDatos.getSelectedRow();
            
            if (fila >=0) {
                
                paramId.setText((tbDatos.getValueAt(fila, 0).toString()));
                paramCedula.setText((tbDatos.getValueAt(fila, 1).toString()));
                paramNombres.setText((tbDatos.getValueAt(fila, 2).toString()));
                paramApellidos.setText((tbDatos.getValueAt(fila, 3).toString()));
                
            }
            
            else
            {
                JOptionPane.showMessageDialog(null,"Fila no seleccionada");
            }
        } catch (Exception e) {
            
                JOptionPane.showMessageDialog(null,"Error de seleccion, error: "+ e.toString());
        }
        
    }
    
    public void Modificar (JTextField paramId, JTextField paramCedula, JTextField paramNombres, JTextField paramApellidos){
        
        
        Conector objetoConexion = new Conector();
        
        String consulta = "UPDATE Datos SET Datos.Cedula=?, Datos.Nombres = ?, Datos.Apellidos =? WHERE Datos.Id=?;";
        
        try {
            
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            
            cs.setString(1, paramCedula.getText());
            cs.setString(2, paramNombres.getText());
            cs.setString(3, paramApellidos.getText());
            cs.setString(4, paramId.getText());
            
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null,"Modificación Exitosa");
            
            
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"No se modificó, error:"+ e.toString());
        }
    }
    public void Eliminar(JTextField paramCedula){
    
        
        
        Conector objetoConexion = new Conector();
        
        String consulta = "DELETE FROM Datos WHERE Datos.Cedula=?;";
        
        try {
             CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
             cs.setString(1, paramCedula.getText());
             cs.execute();
             
             JOptionPane.showMessageDialog(null,"Se eliminaron correctamente los datos");
            
            
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null,"No se pudo eliminar, error: "+ e.toString());
        }
        
    }
    
}
