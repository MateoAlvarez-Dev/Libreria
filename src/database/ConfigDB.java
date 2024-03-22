package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {
    //Variable que va a contener el estado de la conexión

    static Connection objConnection = null;

    //Metodo para abrir la conexión entre Java y la base de datos

    public static Connection openConnection(){
        try {

            //Creamos variables con nuestras credenciales de la base de datos

            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://b6aggcgdvevluf65e2zs-mysql.services.clever-cloud.com:3306/b6aggcgdvevluf65e2zs";
            String user = "uirnq6zhde2i5pnf";
            String password = "eUqH0rVEizjzHNFahZ7O";

            /*String url = "jdbc:mysql://localhost:3306/riwi_taller";
            String user = "root";
            String password = "";*/

            //Establecemos la conexión
            objConnection = (Connection) DriverManager.getConnection(url,user,password);
            System.out.println("La conexion se ha realizado correctamente");

        }catch (SQLException e){
            System.out.println("Error >> No se pudo establecer una conexión con la BD " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return objConnection;

    }


    public static void closeConnection(){
        try {
            //Si hay una conexión activa, la cerramos
            if (objConnection != null) objConnection.close();

        }catch (SQLException e){
            System.out.println("ERROR: "+ e.getMessage());
        }
    }


}