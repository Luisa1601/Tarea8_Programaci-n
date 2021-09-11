
package datos;
import excepciones.*;

import java.sql.SQLException;
import java.util.List;
import dominio.mdEmpleado;
/**
 *
 * @author Luisa
 */
public interface AccesoDatos {

    /**
     SE CREA LA INTERFACE DONDE SE DEFINEN LOS MÉTODOS PRINCIPALES DE ACCESO AL REPOSITORIO DE DATOS QUE ES MYSQL,
     * AQUÍ SE INDICAN LO QUE TIENE QUE HACER EL PROGRAMA RELACIONADO A LOS DATOS
     * RECORDEMOS QUE LOS DATOS VAN SEPARADOS DEL NEGOCIO
     * EL ACCESO A DATOS HACE REFERENCIA A CÓMO SE LEE Y ESCRIBE EN LA BASE DE DATOS
     *
     **/
    void crear(mdEmpleado empleado) throws EscrituraDatosEx, SQLException;
    List<mdEmpleado> listar() throws LecturaDatosEx, SQLException;
    void  actualizar(String nombre, int id) throws EscrituraDatosEx, SQLException;
    void borrar(int id) throws EscrituraDatosEx, SQLException;
    List<mdEmpleado> buscar(String palabra) throws LecturaDatosEx, SQLException;
}
