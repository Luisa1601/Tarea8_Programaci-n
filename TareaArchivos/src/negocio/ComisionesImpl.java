
package negocio;

import dominio.mdEmpleado;
import datos.*;
import excepciones.AccesoDatosEx;
import excepciones.EscrituraDatosEx;
import excepciones.LecturaDatosEx;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Luisa
 */
/**
 AQUÍ SE IMPLEMENTAN LOS MÉTODOS DEFINIDOS PARA LA LÓFICA DE NEGOCIOS,
 * AQUÍ SE INDICA CÓMO FUNCIONAR, ES DECIR, CÓMO SE AGREGA UNA COMISIÓN, CÓMO SE LEE ETC.
 **/
public class ComisionesImpl implements IComisiones{

    //SE HACE REFERENCIA AL OBJETO PARA ACCEDER A LOS DATOS
    //RECORDEMOS QUE EN LA CLASE AccessoDatos ESTÁN TODOS LOS MÉTODOS NECESARIOS PARA LEER E INSERTAR DATOS
    private final AccesoDatos datos;

    public ComisionesImpl() throws AccesoDatosEx {
        this.datos = new AccesoDatosImpl(); //poner * en el import datos.*
    }

    @Override
    public void agregarComisiones(mdEmpleado emp) {

        try {
            datos.crear(emp);


        } catch (SQLException | EscrituraDatosEx ex) {
            System.out.println("Error al ingresar en la base de datos");
            ex.printStackTrace();
        }
    }

    @Override
    public List<mdEmpleado> listarComisiones() {
        List<mdEmpleado> empleados = new ArrayList<>();
        try {
            empleados = datos.listar();
            return empleados;
        } catch (LecturaDatosEx | SQLException ex) {
            System.out.println("Error de acceso a datos");
            ex.printStackTrace();
        }
        return empleados;
    }

    public List<mdEmpleado> listarNombres() {
        List<mdEmpleado> empleados = new ArrayList<>();
        try {
            empleados = datos.listar();
            return empleados;
        } catch (LecturaDatosEx | SQLException ex) {
            System.out.println("Error de acceso a datos");
            ex.printStackTrace();
        }
        return empleados;
    }

    @Override
    public List<mdEmpleado> buscarComisiones(String buscar) {
        List<mdEmpleado> resultado = null;
        try {

            resultado = datos.buscar(buscar);
        } catch (LecturaDatosEx | SQLException ex) {
            System.out.println("Error al buscar la peli");
            ex.printStackTrace();
        }

        System.out.println("\nResultado Busqueda:\n");
        for(int i = 0; i < resultado.size(); i++){
            System.out.println("\n\tNombre: " + resultado.get(i).getNombre());
            System.out.println("\tEnero: " + "Q" +resultado.get(i).getEnero());
            System.out.println("\tFebrero: " + "Q" +resultado.get(i).getFebrero());
            System.out.println("\tMarzo: " + "Q" +resultado.get(i).getMarzo());
        }


        return null;
    }

    @Override
    public void eliminarComision(int idComision) {
        try {
            datos.borrar(idComision);
        } catch (SQLException | EscrituraDatosEx ex) {
            System.out.println("Error al eliminar en la base de datos");
            ex.printStackTrace();
        }
    }

    @Override
    public void actualizarComisiones(String nombre, int id) {
        try {
            datos.actualizar(nombre, id);


        } catch (SQLException | EscrituraDatosEx ex) {
            System.out.println("Error al ingresar en la base de datos");
            ex.printStackTrace();
        }
    }


}
