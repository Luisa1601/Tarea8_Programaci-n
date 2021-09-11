package datos.bd;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;


//CLASE UTILIZADA PARA REALIZAR LA CONEXIÓN A LA BASE DE DATOS
//SI LA CONEXIÓN NO SE REALIZA CORRECTAMENTE, NO SE PUEDE LEER NI ESCRIBIR DATOS,
public class ClsConexion {
    private static Connection con;


    public static Connection iniciar(){
        try {
            //SE INDICA EL DRIVER QUE USAREMOS PARA LA CONEXIÓN QUE EN ESTE CASO ES UN JDBC
            //LO TENEMOS AGREGADO COMO UNA LIBRERIA EXTERNA EN NUESTRO PROYECTO
            Class.forName("com.mysql.jdbc.Driver");

            //LA URL DE DONDE SE ENCUENTRA ALOJADA LA BASE DE DATOS, EN ESTE CASO COMO ESTAMOS EN SERVIDOR LOCAL
            //POR ESO DICE LOCALHOST Y ESCUCHA POR EL PUERTO 3306, SE AGREGA EL NOMBRE DE LA BASE DE DATOS
            String url ="jdbc:mysql://localhost:3306/bd_comisiones?useSSL=false";
            //EL USUARIO QUE VAMOS A UTILIZAR PARA ACCEDER, EN ESTE CASO ROOT
            String usuario = "root";
            //LA CLAVE ASIGNADA A ESE USUARIO
            String clave = "root";

            //USAMOS EL DRIVER PARA LA CONEXIÓN Y LA REALIZAMOS CON LAS CREDENCIALES ASIGNADAS
            //ESO LO ASIGNAMOS A LA VARIABLE con Y DE ÚLTIMO LA RETORNAMOS
            con = DriverManager.getConnection(url, usuario, clave);
            System.out.println("Conexión exitosa...");

        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Ocurrió un error: " + ex.getMessage());
        }

        //EN ESTA VARIABLE SE RETORNA LA CONEXIÓN SI EL PROCESO FUE EXITOSO.
        return con;
    }
}
