package dominio;

public class ListaPasajeros implements  ILista{
    private NodoPasajero inicio;
    private NodoPasajero ultimo ;
    private int cantNodos ;

    public ListaPasajeros() {
        this.inicio = null;
    }

    public NodoPasajero getInicio() {
        return inicio;
    }

    public void setInicio(NodoPasajero inicio) {
        this.inicio = inicio;
    }

    public NodoPasajero getUltimo() {
        return ultimo;
    }

    public void setUltimo(NodoPasajero ultimo) {
        this.ultimo = ultimo;
    }

    public int getCantNodos() {
        return cantNodos;
    }

    public void setCantNodos(int cantNodos) {
        this.cantNodos = cantNodos;
    }

    @Override
    public void insertar(Pasajero dato) {
        //inicio = new NodoPasajero(dato, inicio);
        NodoPasajero newPasajero = new NodoPasajero(dato);
        if(this.esVacia()){
            this.setInicio(newPasajero);
            this.setUltimo(newPasajero);
        }else{
            this.ultimo.setSig(newPasajero);
            this.setUltimo(newPasajero);
        }

        ++this.cantNodos;
    }


    private void intercambiarNodos(NodoPasajero n1, NodoPasajero n2){
        Pasajero aux = n1.getDato();
        n1.dato = n2.dato;
        n2.dato = aux;
    }

    public void ordenar(){
        if(this.cantNodos <= 1){
            // La lista ya está ordenada o vacía
            return;
        }

        boolean intercambio;
        do{
            intercambio = false;
            NodoPasajero actual = this.getInicio();
            NodoPasajero siguiente = this.inicio.getSig();

            while(siguiente != null){
                if(actual.getDato().getCedula().compareToIgnoreCase(siguiente.getDato().getCedula()) > 0){
                    // Intercambio de nodos
                    intercambiarNodos(actual, siguiente);
                    intercambio = true;
                }

                actual = siguiente;
                siguiente = siguiente.getSig();
            }

        } while(intercambio);
    }

    @Override
    public void borrar(Pasajero dato) {
        if (inicio.getDato() == dato) {
            inicio = inicio.getSig();
        } else {
            NodoPasajero aux = inicio;
            while (aux.getSig().getDato() != dato) {
                aux = aux.getSig();
            }
            aux.setSig(aux.getSig().getSig());
        }
    }

    @Override
    public boolean existe(Pasajero dato) {
        NodoPasajero aux = inicio;
        while (aux != null) {
            if (aux.getDato().equals(dato)) {
                return true;
            }
            aux = aux.getSig();
        }
        return false;
    }

    @Override
    public Pasajero recuperar(Pasajero dato) {
        NodoPasajero aux = inicio;
        while (aux != null) {
            if (aux.getDato().equals(dato)) {
                return aux.getDato();
            }
            aux = aux.getSig();
        }
        return null;
    }

    @Override
    public boolean esVacia() {
        // Puede ser largo = 0;
        return inicio == null;
    }

    @Override
    public String imprimirDatos() {
        String ret = "";
        NodoPasajero aux = inicio;
        while (aux != null ) {
            if (aux.getSig() != null){
                ret += aux.getDato().getCedula() + ";" +
                        aux.getDato().getNombre() + ";" +
                        aux.getDato().getTelefono() + ";" +
                        aux.getDato().getCategoria().getTexto()+"|";
            }else{
                ret += aux.getDato().getCedula() + ";" +
                        aux.getDato().getNombre() + ";" +
                        aux.getDato().getTelefono() + ";" +
                        aux.getDato().getCategoria().getTexto();
            }
            aux = aux.getSig();
        }
        return ret;
    }

    private class NodoPasajero {

        private Pasajero dato;
        private NodoPasajero sig;

        public NodoPasajero(Pasajero dato) {
            this.dato = dato;
            this.sig = null;
        }

        public NodoPasajero(Pasajero dato, NodoPasajero sig) {
            this.dato = dato;
            this.sig = sig;
        }

        public Pasajero getDato() {
            return dato;
        }

        public void setDato(Pasajero dato) {
            this.dato = dato;
        }

        public NodoPasajero getSig() {
            return sig;
        }

        public void setSig(NodoPasajero sig) {
            this.sig = sig;
        }

        @Override
        public String toString() {
            return dato + "";
        }
    }

}
