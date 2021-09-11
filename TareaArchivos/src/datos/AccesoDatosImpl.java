
package datos;

import datos.bd.ClsConexion;
import dominio.mdEmpleado;
import excepciones.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Luisa
 */

//        EN ESTA PARTE SE IMPLEMENTAN LOS MÉTODOS QUE SE DEFINIERON EN LA INTERFACE
//        AQUÍ ES DONDE SE LE INDICA CÓMO LO TIENE QUE HACER

public class AccesoDatosImpl implements AccesoDatos {

    //SE DECLARAN LA VARIABLE DE CONEXIÓN QUE SE VAN A OBTENER POR MEDIO DE LA CLASE DE CONEXIÓN
    //QUE CREAMOS ANTERIORMENTE
    Connection conectar = null;

    //ESTA VARIABLE ES PROPIA LA LIBRERÍA JDBC QUE AGREGAMOS
    //SIRVE PARA PREPARAR UNA CONSULTA QUE SE EJECUTARÁ POSTERIORMENTE, COMO EL INSERT O EL UPDATE
    PreparedStatement pst = null;

    //ESTA VARAIBLE TAMBIÉN ES DE LA LIBRERÍA
    //SE USA PARA EJECTUAR UNA CONSULTA COMO SELECT
    Statement st = null;

    //ESTA TAMBIÉN ES DE JDBC Y SE UTILIZA PARA OBTENER UN RESULTADO DE UNA CONSULTA
    //COMO UN SELECT QUE RETORNA UNA SERIE DE FILAS OBTENIDAS EN LA BASE DE DATOS
    ResultSet rs = null;

    @Override
    public void crear(mdEmpleado empleado) throws SQLException {

        //EL MÉTODO CREAR RECIBE COMO PARÁMETRO UN OBJETO DE TIPO mdEmpleado,
        // DE ESTE OBJETO SE OBTIENEN LOS ATRIBUTOS QUE SE VAN A INSERTAR EN LA TABLA DE
        // LA BASE DE DATOS.

        try {

            //PRIMERO EN LA VARIABLE conectar OBTENEMOS LA CONEXIÓN A LA BASE DE DATOS POR MEDIO DE
            //LA CLASE ClsConexion Y EL MÉTODO INICIAR
            conectar = ClsConexion.iniciar();
            int rows = 0;
            System.out.println("Agregando comisión...");
            String SQL_INSERT = "INSERT INTO tb_empleados (nombre, enero, febrero, marzo, promedio, total)"
                    + "VALUES (?, ?, ?, ?, ?, ?)";

            //SE PREPARA LA CONSULTA QUE SE HARÁ A LA BASE DE DATOS
            //Y LA VEZ SE ASIGNAN LOS PARÁMETROS QUE SE VAN A INSERTAR EN CADA POSICIÓN
            // EN LA POSICIÓN 1, SE OBTENIENE EL NOMBRE DEL EMPLEADO Y ASÍ CON LOS DEMÁS.
            pst = conectar.prepareStatement(SQL_INSERT);
            pst.setString(1, empleado.getNombre());
            pst.setDouble(2, empleado.getEnero());
            pst.setDouble(3, empleado.getFebrero());
            pst.setDouble(4, empleado.getMarzo());
            pst.setDouble(5, empleado.getPromedio());
            pst.setDouble(6, empleado.getTotal());

            System.out.println("Ejecutando query: " + SQL_INSERT);

            // POR ÚLTIMO SE EJECUTA LA CONSULTA Y EN LA VARIABLE rows SE OBTIENE EL NÚMERO DE FILAS AFECTADAS
            rows = pst.executeUpdate();
            System.out.println("Empleado: " + empleado.getNombre() + " insertado correctamente...");
            System.out.println("Registros afectados:" + rows);
        } catch (SQLException ex) {
            System.out.println("Error" + ex);
        }

        // ES IMPORTANTE QUE DESPUÉS DE REALIZAR EL PROCESO A LA BASE DE DATOS
        //YA SEA CONSULTA O INSERCIÓN DE DATOS, CERRAR LA CONEXIÓN PARA ECONOMIZAR RECURSOS
        // SINO LA CONEXIÓN QUEDA ABIERTA PARA CADA CONSULTA

        finally{
            pst.close();
            conectar.close();
            System.out.println("Conexión cerrada...");
        }
    }

    @Override
    public List<mdEmpleado> listar() throws SQLException {

        //ESTE MÉTODO NO RECIBE PARÁMETRO PERO DEVUELVE UNA LSITA DE EMPLEADOS
        //QUE SE VANA OBTENER DE LA BASE DE DATOS

        //DE IGUAL MANERA DE OBTIENE LA CONEXIÓN Y SE GUARDA EN LA VARIABLE conectar.
        // SE HACE EN CADA CONSULTA PORQUE PARA ESO LA CERRAMOS EN CADA UNA.
        conectar = ClsConexion.iniciar();

        //SE CREA LA LISTA DE TIPO mdEmpleado
        List<mdEmpleado> empleados = new ArrayList<>();

        try {
            //EN LA VARIABLE st SE CREA LA SENTENCIA Y POSTERIOR SE EJECUTA,
            //EN LA VARIABLE rs SE GUARDA EL RESULTADO, QUE SERÍA LA LISTA DE EMPLEADOS QUE ESTÉ
            //EN LA BASE DE DATOS.
            st = conectar.createStatement();
            rs = st.executeQuery("SELECT * FROM tb_empleados");

            while (rs.next()){

                //RECORREMOS LA VARIABLE rs Y CREAMOS UNAS VARIABLES
                //PARA IR GUARDANDO LO QUE VENGA EN LA RESPUESTA DE LA CONSULTA
                // SE ACCEDE A CADA PROPIEDAD POR MEDIO DEL NOMBRE DE LOS CAMPOS EN LA TABLA
                // id, enero, febrero, etc.

                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                double enero = rs.getDouble("enero");
                double febrero = rs.getDouble("febrero");
                double marzo = rs.getDouble("marzo");
                double promedio = rs.getDouble("promedio");
                double total = rs.getDouble("total");


                //LUEGO CON TODAS ESAS VARIABLES SE CREA UN OBJETO DE TIPO mdEmpleado

                mdEmpleado empleado = new mdEmpleado(id, nombre, enero, febrero, marzo, promedio, total);

                //ESE OBJETO SE AÑADE A LA LISTA empleados QUE DECLARAMOS ARRIBA
                empleados.add(empleado);
            }
        }catch (SQLException ex){
            System.out.println("Ocurrió un error: " + ex.getMessage());
        }

        //POR ÚLTIMO SE CIERRA LA CONEXIÓN
        finally {
            st.close();
            rs.close();
            conectar.close();
            System.out.println("Conexión cerrada...");
        }

        return empleados;

    }

    @Override
    public void actualizar(String nombre, int id) throws EscrituraDatosEx, SQLException {

        // ESTE MÉTODO ES IGUAL AL DE CREAR
        // PARA ESTE CASO VAMOS A ACTUALIZAR ÚNICAMENTE EL nombre DEL EMPLEADO
        //PERO PODEMOS HACERLO CON TODAS SUS PROPIEDADES.
        //TAMBIÉN SE RECIBE EL id QUE ES EL QUE NOS SERVIRÁ PARA PONER LA CONDICIÓN
        // DE ACTUALIZAR EN LA BASE DE DATOS.
        try {
            conectar = ClsConexion.iniciar();
            int rows = 0;
            System.out.println("Modificando empleado...");


            // LO DEMÁS ES REPETITIVO SE PREPARA LA CONSULTA Y POSTERIOR SE EJECUTA
            //LA ÚNICA DIFERENCIA ES QUE HAY UNA CONDICIÓN WHERE QUE SIRVE PARA SABER
            // QUÉ FILA SERÁ LA QUE SE VA A MODIFICAR.

            String SQL_UPDATE = "UPDATE tb_empleados SET nombre = ? WHERE id = ?";

            pst = conectar.prepareStatement(SQL_UPDATE);
            pst.setString(1, nombre);
            pst.setInt(2, id);

            System.out.println("Ejecutando query: " + SQL_UPDATE);
            rows = pst.executeUpdate();
            System.out.println("Empleado: " + nombre+ " actualizado correctamente...");
            System.out.println("Registros afectados:" + rows);
        } catch (SQLException ex) {
            System.out.println("Error" + ex);
        }
        finally{
            pst.close();
            conectar.close();
            System.out.println("Conexión cerrada...");
        }
    }

    @Override
    public void borrar(int id) throws EscrituraDatosEx, SQLException {

        //EN ESTE MÉTODO SOLO NECESITAMOS EL id DEL ELEMENTO QUE QUEREMOS ELIMINAR

        try {
            //VOLVEMOS A CREAR LA CONEXIÓN
            conectar = ClsConexion.iniciar();
            int rows = 0;
            System.out.println("Eliminando empleado...");

            //EN LA CONSULTA DECIMOS QUE QUEREMOS ELIMINAR DE LA TABLA tb_empleados CUANDO EL id SEA IGUAL A LO
            //QUE NOS PASAN POR PARÁMETRO
            String SQL_UPDATE = "DELETE FROM tb_empleados WHERE id = ?";
            pst = conectar.prepareStatement(SQL_UPDATE);
            pst.setInt(1, id);

            System.out.println("Ejecutando query: " + SQL_UPDATE);
            rows = pst.executeUpdate();
            System.out.println("Empleado eliminado correctamente...");
            System.out.println("Registros afectados:" + rows);
        } catch (SQLException ex) {
            System.out.println("Error" + ex);
        }
        finally{
            pst.close();
            conectar.close();
            System.out.println("Conexión cerrada...");
        }
    }

    @Override
    public List<mdEmpleado> buscar(String palabra) throws LecturaDatosEx, SQLException {
        conectar = ClsConexion.iniciar();
        List<mdEmpleado> empleados = new ArrayList<>();

        //ESTE MÉTODO SE PARECE AL DE LISTAR
        //LA DIFERENCIA ES QUE NO ME VA A LISTAR TODAS LAS FILAS DE LA TABLA
        //SINO QUE DEVUELVE SOLO LO QUE SE PAREZCA AL LO QUE SE PASE EN LA CONSULTA
        //SE RECIBE UN PARÁMETRO QUE ES palabra
        //ESA palabra SE PONE EN LA CONDICIÓN WHERE Y SE PONE EN MINÚSCULA CON lower
        try {
            st = conectar.createStatement();
            rs = st.executeQuery("SELECT * FROM tb_empleados where lower(nombre) LIKE '%" + palabra +"%'");

            while (rs.next()){

                //SI ALGUNA FILA DE LA TABLA CONTIENE UNA PALABRA QUE SE PARECE LO QUE INGRESE
                //NOS DEVUELVE ESE LISTADO Y LO VOLVEMOS A RECORRER IGUAL QUE EL MÉTODO LISTAR
                //DESPUÉS CADA PROPIEDAD SE AGREGA A LA VARIABLE Y POR ÚLTIMO EL OBJETO SE AGREA
                // A LA LISTA Y ESA LISTA SE RETORNA

                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                double enero = rs.getDouble("enero");
                double febrero = rs.getDouble("febrero");
                double marzo = rs.getDouble("marzo");
                double promedio = rs.getDouble("promedio");
                double total = rs.getDouble("total");

                mdEmpleado empleado = new mdEmpleado(id, nombre, enero, febrero, marzo, promedio, total);
                empleados.add(empleado);
            }
        }catch (SQLException ex){
            System.out.println("Ocurrió un error: " + ex.getMessage());
        }
        finally {
            st.close();
            rs.close();
            conectar.close();
            System.out.println("Conexión cerrada...");
        }

        return empleados;
    }

}
