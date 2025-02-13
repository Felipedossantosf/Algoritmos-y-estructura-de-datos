package dominio;

public class ABBAerolinea {

    private NodoABBAerolinea raiz;

    public void insertar(Aerolinea aerolinea){
        if(raiz == null){
            raiz = new NodoABBAerolinea(aerolinea);
        }else{
            insertar(raiz, aerolinea);
        }
    }

    public void insertar(NodoABBAerolinea nodo, Aerolinea aerolinea){
        if(aerolinea.compareTo(nodo.dato) < 0){
            if(nodo.izq == null){
                nodo.izq = new NodoABBAerolinea(aerolinea);
            }else{
                insertar(nodo.izq, aerolinea);
            }
        }else if(aerolinea.compareTo(nodo.dato) > 0){
            if(nodo.der == null){
                nodo.der = new NodoABBAerolinea(aerolinea);
            }else {
                insertar(nodo.der, aerolinea);
            }
        }
    }

    // CANTIDAD DE NODOS (AEROLINEAS)
    // Método público para contar nodos en el árbol
    public int contarNodos() {
        return contarNodos(raiz);
    }

    // Método auxiliar para contar nodos recursivamente
    private int contarNodos(NodoABBAerolinea nodo) {
        if (nodo == null) {
            return 0;
        } else {
            // Contar los nodos en el subárbol izquierdo y derecho
            int izquierdo = contarNodos(nodo.izq);
            int derecho = contarNodos(nodo.der);
            // Sumar el nodo actual y los nodos en los subárboles
            return izquierdo + derecho + 1;
        }
    }

    // PERTENECE
    public boolean pertenece(Aerolinea aerolinea){
        return pertenece(raiz, aerolinea);
    }

    private boolean pertenece(NodoABBAerolinea nodo, Aerolinea aerolinea){
        if(nodo != null){
            if(nodo.dato.equals(aerolinea)){
                return true;
            }else if(aerolinea.compareTo(nodo.dato) < 0){
                return pertenece(nodo.izq, aerolinea);
            }else{
                return pertenece(nodo.der, aerolinea);
            }
        }else {
            return false;
        }
    }

    // LISTAR DESCENDENTE
    public String listarDescendente(){
        return listarDescendente(raiz);
    }

    private String listarDescendente(NodoABBAerolinea nodo){
        if(nodo != null){
            return listarDescendente(nodo.der) +
                    nodo.dato.toString() + "|" +
                    listarDescendente(nodo.izq);
        }else{
            return "";
        }
    }

    // CLASE NODO
    private  class NodoABBAerolinea{
        private Aerolinea dato;
        private NodoABBAerolinea izq;
        private NodoABBAerolinea der;

        public NodoABBAerolinea(Aerolinea dato){
            this.dato = dato;
        }
    }
}