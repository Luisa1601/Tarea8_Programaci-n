
package negocio;

import dominio.mdEmpleado;
import java.util.List;

/**
 *
 * @author Luisa
 */

/**
 AQUÍ SE DEFINE LA LÓGICA DE NEGOCIOS, ES DECIR LA FUNCIONALIDAD DEL PROGRAMA
 * POR ESO SE CREA LA INTERFAZ CON MÉTODOS RELACIONADOS A COMISIONES Y EMPLEADOS
 * AGREGAR, LISTAR, BUSCAR, ETC.
 * AQUÍ SOLO SE DEFINEN LA LÓGICA. NADA RELACIONADO A LOS DATOS
 */
public interface IComisiones {
    void agregarComisiones(mdEmpleado empleado);
    List<mdEmpleado> listarComisiones();
    List<mdEmpleado> buscarComisiones(String buscar);
    void eliminarComision (int idComision);
    void actualizarComisiones(String nombre, int id);
}
