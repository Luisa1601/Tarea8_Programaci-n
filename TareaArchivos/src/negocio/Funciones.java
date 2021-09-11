
package negocio;

/**
 *
 * @author Luisa
 */
import dominio.mdEmpleado;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Funciones {

    public static ArrayList<mdEmpleado> list = new ArrayList();

    public static Scanner t = new Scanner(System.in);


    public static void calcTotalMes(List<mdEmpleado> comisiones) {
        Double acumuladorMes;

        acumuladorMes = 0.0;

        for (int fila = 0; fila < comisiones.size(); fila++) {

            acumuladorMes += comisiones.get(fila).getEnero();
        }
        System.out.println("El total de la columna enero es Q" + acumuladorMes);
        acumuladorMes = 0.0;

        for (int fila = 0; fila < comisiones.size(); fila++) {

            acumuladorMes += comisiones.get(fila).getFebrero();
        }
        System.out.println("El total de la columna febrero es Q" + acumuladorMes);
        acumuladorMes = 0.0;
        for (int fila = 0; fila < comisiones.size(); fila++) {

            acumuladorMes += comisiones.get(fila).getMarzo();
        }

        System.out.println("El total de la columna marzo es Q" + acumuladorMes);
        acumuladorMes = 0.0;
    }



    public static void imprimirDecorado(List<mdEmpleado> comisiones) {
        System.out.println("\nVentas por empleado y mes:\n");
        for (int x = 0; x < comisiones.size(); x++) {

            System.out.println("\tEmpleado: " + comisiones.get(x).getNombre());
            System.out.println("\tEnero: Q" + comisiones.get(x).getEnero());
            System.out.println("\tFebrero: Q" + comisiones.get(x).getFebrero());
            System.out.println("\tMarzo: Q" + comisiones.get(x).getMarzo());
            System.out.println("\tTotal: Q" + comisiones.get(x).getTotal());
            System.out.println("\tPromedio: Q" + comisiones.get(x).getPromedio() + " \n");

        }

    }

    public static void listarNombres(List<mdEmpleado> comisiones) {
        System.out.println("\nListado de empleados:\n");
        for (mdEmpleado com : comisiones) {

            System.out.println("\tId: " + com.getId() + " -- Empleado: " + com.getNombre());

        }

    }

    public static void GuardarInfo() {
        String nombre = "";
        int mes = 0;
        String valor = "";

        System.out.println("Ingrese su nombre: ");
        nombre = t.nextLine();
        System.out.println("Ingrese el numero de mes: ");
        mes = Integer.parseInt(t.nextLine());
        System.out.println("Ingrese el nuevo valor: ");
        valor = t.nextLine();

        for (int x = 0; x < list.size(); x++) {
            if (list.get(x).getNombre().equals(nombre)) {
                list.get(x).setMes(mes, Integer.parseInt(valor));
                break;

            }

        }

        System.out.println("Cambio exitoso del valor de: " + mes + " de la persona: " + nombre + " por el valor de: " + valor);
        imprimirDecorado(list);
    }

    public static void imprimirCompleto(List<mdEmpleado> comisiones) {
        calcTotalMeses(comisiones);
        imprimirDecorado(comisiones);
        calcTotalMes(comisiones);
    }

    public static void ventaMaxMin(List<mdEmpleado> comisiones) {

        Double numeroMayor = 0.0;
        Double numeroMenor = 0.0;

        String nombreMayor = "";
        String nombreMenor = "";

        for (int x = 0; x < comisiones.size(); x++) {
            if (comisiones.get(x).getEnero() > numeroMayor) {
                numeroMayor = comisiones.get(x).getEnero();
                nombreMayor = comisiones.get(x).getNombre();
            }

            if (comisiones.get(x).getFebrero() > numeroMayor) {
                numeroMayor = comisiones.get(x).getFebrero();
                nombreMayor = comisiones.get(x).getNombre();
            }
            if (comisiones.get(x).getMarzo() > numeroMayor) {
                numeroMayor = comisiones.get(x).getMarzo();
                nombreMayor = comisiones.get(x).getNombre();
            }
            if (x == 0) {
                numeroMenor = comisiones.get(x).getEnero();
                nombreMenor = comisiones.get(x).getNombre();
            }

            if (comisiones.get(x).getEnero() < numeroMenor) {
                numeroMenor = comisiones.get(x).getEnero();
                nombreMenor = comisiones.get(x).getNombre();
            }

            if (comisiones.get(x).getFebrero() < numeroMenor) {
                numeroMenor = comisiones.get(x).getFebrero();
                nombreMenor = comisiones.get(x).getNombre();
            }
            if (comisiones.get(x).getMarzo() < numeroMenor) {
                numeroMenor = comisiones.get(x).getMarzo();
                nombreMenor = comisiones.get(x).getNombre();
            }

        }

        System.out.println("\n Resumen de ventas:\n");
        System.out.println("\tMayor: Q" + numeroMayor + " Empleado: " + nombreMayor);
        System.out.println("\tMenor: Q" + numeroMenor + " Empleado: " + nombreMenor);
    }

    public static void busqueda(List<mdEmpleado> comisiones) {

        String valor = "";
        String nombre = "";
        int mes = 0;
        System.out.println("Ingrese el valor a buscar: ");
        valor = t.nextLine();
        for (int x = 0; x < comisiones.size(); x++) {
            if (comisiones.get(x).getEnero() == Double.parseDouble(valor)) {
                nombre = (comisiones.get(x).getNombre());
                mes = 1;
            }
            if (comisiones.get(x).getFebrero() == Double.parseDouble(valor)) {
                nombre = (comisiones.get(x).getNombre());
                mes = 1;
            }
            if (comisiones.get(x).getMarzo() == Double.parseDouble(valor)) {
                nombre = (comisiones.get(x).getNombre());
                mes = 1;
            }
        }
        if (nombre.equals("")) {
            System.out.println("\nNo se ha encontrado el valor");

        } else {
            System.out.println("\nVenta: Q" + valor + " Empleado: " + nombre);

        }
    }

    public static void ventaMaxima3Meses(List<mdEmpleado> comisiones) {
        double numeroMayor = 0.0;
        String nombreMayor = "";

        for (int x = 0; x < comisiones.size(); x++) {
            if (comisiones.get(x).getEnero() > numeroMayor) {
                numeroMayor = +comisiones.get(x).getEnero();
                nombreMayor = comisiones.get(x).getNombre();
            }

            if (comisiones.get(x).getFebrero() > numeroMayor) {
                numeroMayor = +comisiones.get(x).getFebrero();
                nombreMayor = comisiones.get(x).getNombre();

            }
            if (comisiones.get(x).getMarzo() > numeroMayor) {
                numeroMayor = +comisiones.get(x).getMarzo();
                nombreMayor = comisiones.get(x).getNombre();

            }

        }

        System.out.println("\nVenta máxima en tres meses: Q" + numeroMayor + " Empleado: " + nombreMayor);
    }

    public static void calcTotalMeses(List<mdEmpleado> comisiones) {

        Double acumuladorMes;
        System.out.println("\nVentas totales del mes:\n");

        for (int fila = 0; fila < comisiones.size(); fila++) {

            acumuladorMes = 0.0;
            acumuladorMes += comisiones.get(fila).getEnero();
            acumuladorMes += comisiones.get(fila).getFebrero();
            acumuladorMes += comisiones.get(fila).getMarzo();
            comisiones.get(fila).setTotal(acumuladorMes);

            System.out.println("\t" + comisiones.get(fila).getNombre() + " Q" + acumuladorMes);
        }

    }

}
