package dominio;

import java.util.Objects;

public class Aeropuerto {
    private String codigo;
    private String nombre;

    public Aeropuerto(String unCod, String unNom){
        this.codigo = unCod;
        this.nombre = unNom;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Aeropuerto{"+ codigo + '\''
                + nombre + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aeropuerto that = (Aeropuerto) o;
        return Objects.equals(codigo, that.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }

    public int compareTo(Aeropuerto otroAeropuerto) {
        // Comparar las aerolíneas según su código
        return this.codigo.compareTo(otroAeropuerto.getCodigo());
    }

}
