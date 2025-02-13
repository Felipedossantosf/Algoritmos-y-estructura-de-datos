package dominio;

import interfaz.Categoria;

public class ABBPasajero {
    private  NodoABBPasajero raiz;

    public void insertar(Pasajero pasajero){
        if(raiz == null){
            raiz = new NodoABBPasajero(pasajero);
        }else{
            insertar(raiz, pasajero);
        }
    }

    private void insertar(NodoABBPasajero nodo, Pasajero pasajero){
        //if(Integer.parseInt(limpiar(pasajero.getCedula())) < Integer.parseInt(limpiar(nodo.dato.getCedula()))){
          if(pasajero.compareTo(nodo.dato) < 0){
            if(nodo.izq == null){
                nodo.izq = new NodoABBPasajero(pasajero);
            }else{
                insertar(nodo.izq, pasajero);
            }
          }else if(pasajero.compareTo(nodo.dato) > 0){
            if(nodo.der == null){
                nodo.der = new NodoABBPasajero(pasajero);
            }else {
                insertar(nodo.der, pasajero);
            }
          }
    }

    public boolean pertenece(Pasajero pasajero){
        return pertenece(raiz, pasajero);
    }

    private boolean pertenece(NodoABBPasajero nodo, Pasajero pasajero){
        if(nodo != null){
            if(nodo.dato.equals(pasajero)){
                return true;
            }else if(pasajero.compareTo(nodo.dato) < 0){
                return pertenece(nodo.izq, pasajero);
            }else{
                return pertenece(nodo.der, pasajero);
            }
        }else {
            return false;
        }
    }

    public class PasajeroContador{
        private Pasajero pasajero;
        private int contador;

        public PasajeroContador(Pasajero pasajero, int contador){
            this.pasajero = pasajero;
            this.contador = contador;
        }

        public Pasajero getPasajero() {
            return pasajero;
        }

        public void setPasajero(Pasajero pasajero) {
            this.pasajero = pasajero;
        }

        public int getContador() {
            return contador;
        }

        public void setContador(int contador) {
            this.contador = contador;
        }
    }
    // Limpiar cedula
    public String limpiar(String cedula){
        String ret = "";
        for(int i = 0; i < cedula.length(); i++){
            char caracter = cedula.charAt(i);
            String simbolos = ".-";
            char punto = simbolos.charAt(0);
            char guion = simbolos.charAt(1);

            if(caracter != punto && caracter != guion){
                ret += caracter;
            }
        }
        return ret;
    }

    // Obtener pasajero
    public PasajeroContador obtenerPasajero(String cedula){
        return obtenerPasajero(raiz, cedula,0);
    }
    private PasajeroContador obtenerPasajero(NodoABBPasajero nodo, String cedula, int contador ){
        if(nodo != null){
            if(nodo.dato.getCedula().equals(cedula)){
                return new PasajeroContador(nodo.dato, contador);
            }else if(Integer.parseInt(limpiar(cedula)) < Integer.parseInt(limpiar(nodo.dato.getCedula()))){
                return obtenerPasajero(nodo.izq, cedula, contador+1);
            }else{
                return obtenerPasajero(nodo.der, cedula, contador+1);
            }
        }else {
            return null;
        }
    }

    // LISTAR ASCENDENTE (Por ci)
    public String listarAscendente(){
        return listarAscendente(raiz);
    }

    private String listarAscendente(NodoABBPasajero nodo){
        if(nodo != null){
            return listarAscendente(nodo.izq) +
                    nodo.dato.toString() + "|" +
                    listarAscendente(nodo.der);
        }else{
            return "";
        }
    }

    // LISTAR POR CATEGORIA ASCENDENTE
    public ILista listarAscendenteCAT(Categoria cat){
        ILista pasajerosFiltrados = new ListaPasajeros();
        listarAscendenteCAT(raiz, cat, pasajerosFiltrados);
        // nuevo:
        pasajerosFiltrados.ordenar();
        return pasajerosFiltrados;
    }

    private void listarAscendenteCAT(NodoABBPasajero nodo, Categoria cat, ILista lista){
        if (nodo != null ) {
            if(cat == null){
                // Cortamos la función, para que funcione
                return;
            }
            if (nodo.dato.getCategoria().getTexto() == cat.getTexto()) {
                lista.insertar(nodo.dato);

                listarAscendenteCAT(nodo.izq, cat, lista);
                listarAscendenteCAT(nodo.der, cat, lista);
            } else {

                listarAscendenteCAT(nodo.izq, cat, lista);
                listarAscendenteCAT(nodo.der, cat, lista);
            }
        }
    }

    // Nodo ABB de Pasajero
    private class NodoABBPasajero {
        private Pasajero dato;
        private NodoABBPasajero izq;
        private NodoABBPasajero der;

        public NodoABBPasajero(Pasajero dato){
            this.dato = dato;
        }


    }
}