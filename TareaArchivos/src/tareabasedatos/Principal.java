
package tareabasedatos;


import dominio.mdEmpleado;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import excepciones.AccesoDatosEx;
import negocio.ComisionesImpl;
import negocio.Funciones;

/**
 *
 * @author Luisa
 */
public class Principal {

    private static final Scanner scanner = new Scanner(System.in);
    private static int opcion = -1;
    private static ComisionesImpl comisiones;
    private static List<mdEmpleado> empleados = new ArrayList<>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws AccesoDatosEx, SQLException {


        comisiones = new ComisionesImpl();
        while (opcion != 0) {
            try {
                System.out.println("1. Ingresar empleado:");
                System.out.println("2. Venta máxima y venta mínima:");
                System.out.println("3. Venta máxima en los 3 meses:");
                System.out.println("4. Buscar cantidad:");
                System.out.println("5. Buscar empleado:");
                System.out.println("6. Desplegar archivo completo:");
                System.out.println("7. Actualizar empleado:");
                System.out.println("8. Eliminar empleado:");
                System.out.println("0. Salir");

                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {


                    case 1:
                        System.out.println("Ingrese nombre del empleado: ");
                        String nombre = scanner.nextLine();
                        System.out.println("Ingrese información de enero");
                        double enero = Double.parseDouble(scanner.nextLine());
                        System.out.println("Ingrese información de febrero");
                        double febrero = Double.parseDouble(scanner.nextLine());
                        System.out.println("Ingrese información de marzo");
                        double marzo = Double.parseDouble(scanner.nextLine());

                        double total = enero + febrero + marzo;
                        double promedio = (enero + febrero + marzo) / 3;

                        mdEmpleado emp = new mdEmpleado(0, nombre, enero, febrero, marzo, promedio, total);
                        comisiones.agregarComisiones(emp);
                        break;
                    case 2:

                        //AQUÍ ENVIAMOS LA LISTA DE EMPLEADOS QUE VIENE DEL ARCHIVO, Y EL MÉTODO ventaMaxMin DE LA
                        //CLASE Funciones SE ENCARGA DE PROCESAR LOS DATOS Y HACER LOS CÁLCULOS NECESARIOS PARA ESTE CASO
                        //ESTO YA SE HIZO EN LAS CLASES ANTERIORES
                        empleados = comisiones.listarComisiones();
                        Funciones.ventaMaxMin(empleados);
                        break;

                    case 3:
                        empleados = comisiones.listarComisiones();
                        Funciones.ventaMaxima3Meses(empleados);
                        break;

                    case 4:
                        empleados = comisiones.listarComisiones();
                        Funciones.busqueda(empleados);
                        break;
                    case 5:
                        System.out.println("Ingrese nombre del empleado: ");
                        String buscar = scanner.nextLine();
                        empleados = comisiones.buscarComisiones(buscar);
                        break;
                    case 6:
                        empleados = comisiones.listarComisiones();
                        Funciones.imprimirCompleto(empleados);
                        break;
                    case 7:
                        Funciones.listarNombres(comisiones.listarNombres());
                        System.out.println("\nIngrese el id del empleado que desea modificar: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        System.out.println("Ingrese el nuevo nombre del empleado: ");
                        String nombreModificado = scanner.nextLine();
                        comisiones.actualizarComisiones(nombreModificado, id);
                        break;
                    case 8:
                        Funciones.listarNombres(comisiones.listarNombres());
                        System.out.println("\nIngrese el id del empleado que desea eliminar: ");
                        int idEliminar = Integer.parseInt(scanner.nextLine());
                        comisiones.eliminarComision(idEliminar);
                        break;
                    case 0:
                        System.out.println("!Hasta pronto!");
                        break;
                    default:
                        System.out.println("Opción no reconocida");
                        break;
                }
                System.out.println("\n");

            } catch (Exception e) {
                System.out.println("Error!");
            }
        }

    }


}
