
package dominio;

/**
 *
 * @author Luisa
 */
public class mdEmpleado {
    int id;
    String nombre;
    double enero;
    double febrero;
    double marzo;
    double promedio;
    double total;

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getEnero() {
        return enero;
    }

    public void setEnero(double enero) {
        this.enero = enero;
    }

    public double getFebrero() {
        return febrero;
    }

    public void setFebrero(double febrero) {
        this.febrero = febrero;
    }

    public double getMarzo() {
        return marzo;
    }

    public void setMarzo(double marzo) {
        this.marzo = marzo;
    }

    public mdEmpleado(int id, String nombre, double enero, double febrero, double marzo, double promedio, double total) {
        this.id = id;
        this.nombre = nombre;
        this.enero = enero;
        this.febrero = febrero;
        this.marzo = marzo;
        this.promedio = promedio;
        this.total = total;
    }
  

    public void setMes(int mes, double valor) {

        switch (mes) {
            case 1:
                setEnero(valor);
                break;
            case 2:
                setFebrero(valor);

                break;
            case 3:
                setMarzo(valor);

                break;

            default:
                System.out.println("Por favor, ingrese el numero correcto de las opciones");
        }

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString(){
        //SOBREESCRIBIMOS EL MÉTODO toString() PARA GUARDAR CADA PROPIEDAD SEPARADA POR COMA
        //ESO NOS SERVIRÁ PARA POSTERIOR PODER ACCEDER A LOS DATOS MÁS FÁCIL
        return this.getNombre() + "," + this.getEnero() + "," + this.getFebrero()+ "," + this.getMarzo();
    }
}
