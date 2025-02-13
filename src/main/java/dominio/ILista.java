package dominio;

public interface ILista {
    void insertar(Pasajero dato);
    void borrar(Pasajero dato);
    boolean existe(Pasajero dato);
    Pasajero recuperar(Pasajero dato);
    boolean esVacia();
    void ordenar();
    String imprimirDatos();
}
