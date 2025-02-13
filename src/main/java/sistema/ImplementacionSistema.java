package sistema;

import dominio.*;
import interfaz.*;

public class ImplementacionSistema implements Sistema {

    ABBPasajero arbolPasajeros;
    GrafoAeropuertos grafoAeropuertos;
    ABBAerolinea arbolAerolinea;
    int maximoAeropuertos;
    int maximoAerolineas;


    /**
     * INICIALIZAR SISTEMA
     */
    @Override
    public Retorno inicializarSistema(int maxAeropuertos, int maxAerolineas) {
        if(maxAeropuertos <= 5){
            return Retorno.error1("El máximo de aeropuertos tiene que ser mayor a 5.");
        }else if(maxAerolineas <= 3){
            return Retorno.error2("El máximo de aerolineas tiene que ser mayor a 3.");
        }else{
            maximoAeropuertos = maxAeropuertos;
            maximoAerolineas = maxAerolineas;
            arbolPasajeros = new ABBPasajero();
            arbolAerolinea = new ABBAerolinea();
            grafoAeropuertos = new GrafoAeropuertos(maxAeropuertos);
            return Retorno.ok();
        }
    }

    /**
     * REGISTRAR PASAJERO
     */
    @Override
    public Retorno registrarPasajero(String cedula, String nombre, String telefono, Categoria categoria) {
        Pasajero p = new Pasajero(cedula, nombre, telefono, categoria);

        if(cedula == null || cedula.isEmpty() || nombre == null || nombre.isEmpty() || telefono == null || telefono.isEmpty() || categoria == null || categoria.getTexto().isEmpty()){
            return Retorno.error1("Ningún dato puede ser vacío.");
        }else if(!p.validarCedula()){
            return Retorno.error2("Formato de cédula inválido.");
        }else if(arbolPasajeros.pertenece(p)){
            return Retorno.error3("Ya existe un pasajero registrado con esa cédula.");
        }else{
            arbolPasajeros.insertar(p);
            return Retorno.ok();
        }
    }

    /**
     * BUSCAR PASAJERO
     */
    @Override
    public Retorno buscarPasajero(String cedula) {
        if(cedula == null || cedula.isEmpty()){
            return Retorno.error1("Cédula no puede ser vacía.");
        }

        Pasajero p = new Pasajero();
        p.setCedula(cedula);


        if(!p.validarCedula()){
            return Retorno.error2("Formato de cédula inválido.");
        }else if(!arbolPasajeros.pertenece(p)){
            return Retorno.error3("Ya existe un pasajero registrado con esa cédula.");
        }else {
            Pasajero ret = arbolPasajeros.obtenerPasajero(cedula).getPasajero();
            int contador = arbolPasajeros.obtenerPasajero(cedula).getContador();
            return Retorno.ok(contador, ret.toString());
        }
    }

    /**
     * LISTAR PASAJEROS ASCENDENTE
     */
    @Override
    public Retorno listarPasajerosAscendente() {
        String ret = arbolPasajeros.listarAscendente();
        String ret2 = "";
        for(int i = 0; i < ret.length()-1; i++){
            ret2 += ret.charAt(i);
        }

        return Retorno.ok(ret2);
    }

    /**
     * LISTAR PASAJEROS POR CATEGORÍA
     */
    @Override
    public Retorno listarPasajerosPorCategoria(Categoria categoria) {
        ILista ret = arbolPasajeros.listarAscendenteCAT(categoria);
        String ret2 = ret.imprimirDatos();

        if(categoria == null){
            return Retorno.error1("Categoría no puede ser null");
        }
        return Retorno.ok(ret2);
    }

    /**
     * REGISTRAR AEROLINEA
     */
    @Override
    public Retorno registrarAerolinea(String codigo, String nombre) {
        Aerolinea a = new Aerolinea(codigo, nombre);

        if(arbolAerolinea.contarNodos() == maximoAerolineas){
            return Retorno.error1("Cantidad máxima de aerolineas ingresadas.");
        }else if(nombre == null || nombre.isEmpty()  || codigo == null || codigo.isEmpty()){
            return Retorno.error2("El nombre y código no pueden ser vacíos.");
        }else if(arbolAerolinea.pertenece(a)){
            return Retorno.error3("La aerolinea ya está registrada.");
        }else{
            arbolAerolinea.insertar(a);
            return Retorno.ok();
        }
    }

    /**
     * LISTAR AEROLINEAS DESCENDIENTE
     */
    @Override
    public Retorno listarAerolineasDescendente() {
        String ret = arbolAerolinea.listarDescendente();
        String ret2 = "";
        for(int i = 0; i < ret.length()-1; i++){
            ret2 += ret.charAt(i);
        }
        return Retorno.ok(ret2);
    }

    @Override
    public Retorno registrarAeropuerto(String codigo, String nombre) {
        Aeropuerto a = new Aeropuerto(codigo, nombre);
        if(grafoAeropuertos.getCantidad() == maximoAeropuertos){
            return Retorno.error1("Cantidad máxima de aeropuertos alcanzada.");
        }else if(codigo == null || codigo.isEmpty() || nombre == null || nombre.isEmpty()){
            return Retorno.error2("El código y nombre no pueden ser vacíos.");
        }else if(grafoAeropuertos.existeAeropuerto(a)){
            return Retorno.error3("Ya existe ese aeropuerto.");
        }else{
            grafoAeropuertos.agregarAeropuerto(codigo, nombre);
            return Retorno.ok();
        }
    }
    @Override
    public Retorno registrarConexion(String codigoAeropuertoOrigen, String codigoAeropuertoDestino, double kilometros) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarVuelo(String codigoCiudadOrigen, String codigoAeropuertoDestino, String codigoDeVuelo, double combustible, double minutos, double costoEnDolares, String codigoAerolinea) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listadoAeropuertosCantDeEscalas(String codigoAeropuertoOrigen, int cantidad, String codigoAerolinea) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno viajeCostoMinimoKilometros(String codigoCiudadOrigen, String codigoCiudadDestino) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno viajeCostoMinimoEnMinutos(String codigoAeropuertoOrigen, String codigoAeropuertoDestino) {
        return Retorno.noImplementada();
    }


}
