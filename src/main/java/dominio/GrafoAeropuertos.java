package dominio;

public class GrafoAeropuertos {
    private Aeropuerto[] aeropuertos;
    private Conexion[][] conexiones;
    private final int maxAeropuertos;
    int cantidad = 0;

    public GrafoAeropuertos(int maxAeropuertos){
        this.maxAeropuertos = maxAeropuertos;
        this.aeropuertos = new Aeropuerto[maxAeropuertos];
        this.conexiones = new Conexion[maxAeropuertos][maxAeropuertos];
    }

    public int getCantidad() {
        return cantidad;
    }

    public void agregarAeropuerto(String codigo, String nombre){
        if(cantidad < maxAeropuertos){
            int posLibre = obtenerPosLibre();
            aeropuertos[posLibre] = new Aeropuerto(codigo, nombre);
            cantidad++;
        }
    }

    public boolean existeAeropuerto(Aeropuerto aeropuerto){
        int posABuscar = buscarPos(aeropuerto);
        return posABuscar >= 0;
    }

    private int buscarPos(Aeropuerto a){
        for (int i = 0; i < aeropuertos.length; i++) {
            if (aeropuertos[i] != null && aeropuertos[i].equals(a)) {
                return i;
            }
        }
        return -1;
    }
    private int obtenerPosLibre(){
        for (int i = 0; i < aeropuertos.length; i++) {
            if (aeropuertos[i] == null) {
                return i;
            }
        }
        return -1;
    }


}
