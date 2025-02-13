package dominio;

public class Conexion {
    private int kilometros;

    public Conexion(int kms){
        this.kilometros = kms;
    }

    public int getKilometros() {
        return kilometros;
    }

    public void setKilometros(int kilometros) {
        this.kilometros = kilometros;
    }

    @Override
    public String toString() {
        return "Conexion{" +
                kilometros +
                "kms}";
    }
}
