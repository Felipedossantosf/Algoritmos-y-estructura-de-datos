package dominio;

import interfaz.Categoria;

import java.util.Objects;
import java.util.regex.Pattern;

public class Pasajero implements Comparable<Pasajero>{
    private String cedula;
    private String nombre;
    private String telefono;
    private Categoria categoria;

    public Pasajero(String cedula, String nombre, String telefono, Categoria categoria) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.telefono = telefono;
        this.categoria = categoria;
    }

    public Pasajero(){}
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return  cedula + ";" +
                nombre + ';' +
                telefono + ';' +
                categoria.getTexto() ;
    }


    public boolean validarCedula() {
        Pattern patronCedula = Pattern.compile("[1-9]\\d{0,2}\\.\\d{3}\\.\\d{3}-\\d{1,2}");
        Pattern patronCedulaSinPuntos = Pattern.compile("[1-9]\\d{0,2}\\.\\d{3}-\\d{1,2}");

        return patronCedula.matcher(cedula).matches() || patronCedulaSinPuntos.matcher(cedula).matches();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        //if (!super.equals(o)) return false; ?????

        Pasajero pasajero = (Pasajero) o;
        return Objects.equals(cedula, pasajero.cedula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cedula);
    }

    @Override
    public int compareTo(Pasajero p) {

        return this.cedula.compareTo(p.getCedula());
    }
}
