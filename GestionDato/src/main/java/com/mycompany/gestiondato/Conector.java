package com.mycompany.gestiondato;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class Conector {
    //Para poder conectarse a la base de datos de MySQL iniciar el proceso de conexion con sus datos.
    //Recuerde crear una base de datos y una lista como el archivo leer.txt indica
    //Cuando ya inicie la conexión verificar esta quitando el comentado de la *linea 24*
    //Tambien verifique que la dependencia mysql-connector-java-8.0.30.jar esté en la carpeta "Dependencies"
    Connection conectar=null; 
    String usuario="root";
    String contrasenia="1228";
    String bd="dbDatos";
    String ip="localhost";
    String puerto="3306";
    
    String cadena = "jdbc:mysql://"+ip+":"+puerto+"/"+bd;
     
    public Connection estableceConexion(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conectar = DriverManager.getConnection(cadena,usuario,contrasenia);
            //JOptionPane.showMessageDialog(null,"La conexión se ha realizado con éxito");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error al conectarse a la base de datos, erro: "+ e.toString());
        }
        return conectar;
    }
}