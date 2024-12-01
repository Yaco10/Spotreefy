import java.io.*;

class ListaPlaylistsPropias {
    NodoPlaylistPropia listaPlaylistPropias;

    public ListaPlaylistsPropias() {
    this.listaPlaylistPropias = null;
    }

    public void insertarPlaylist(String nombrePlaylist){
        NodoPlaylistPropia nuevo = new NodoPlaylistPropia(nombrePlaylist);
        if(listaPlaylistPropias == null || nombrePlaylist.compareTo(listaPlaylistPropias.getNombre()) < 0){
            nuevo.setSiguiente(listaPlaylistPropias);
            this.listaPlaylistPropias = nuevo;
        }
        else{
            NodoPlaylistPropia actual = listaPlaylistPropias;
            NodoPlaylistPropia anterior = null;
            while(actual != null && nombrePlaylist.compareTo(actual.getNombre()) > 0){
                anterior = actual;
                actual = actual.getSiguiente();
            }
            if(anterior == null){
                nuevo.setSiguiente(listaPlaylistPropias);
                this.listaPlaylistPropias = nuevo;
            }
            else{
                anterior.setSiguiente(nuevo);
                nuevo.setSiguiente(actual);
            }

        }
    }

    public NodoPlaylistPropia buscarPlaylist(String nombrePlaylist){
        NodoPlaylistPropia actual = listaPlaylistPropias;
        while(actual != null && !(nombrePlaylist.equalsIgnoreCase(actual.getNombre()))){
            actual = actual.getSiguiente();
        }
        if(actual == null){
            return null;
        }
        return actual;
    }

    public void eliminarPlaylist(String nombrePlaylist){
        if(listaPlaylistPropias.getNombre().equals(nombrePlaylist)){
            listaPlaylistPropias = listaPlaylistPropias.getSiguiente();
        }
        else{
            NodoPlaylistPropia actual = listaPlaylistPropias;
            NodoPlaylistPropia anterior = null;
            while(actual != null && !actual.equals(nombrePlaylist)){
                anterior = actual;
                actual = actual.getSiguiente();
            }
            anterior.setSiguiente(actual.getSiguiente());
        }
    }

    public void mostrarPlaylistPropia(){
        NodoPlaylistPropia actual = listaPlaylistPropias;
        while(actual != null){
            System.out.println(actual.getNombre());
            actual = actual.getSiguiente();
        }
        System.out.println();
    }

    //ARCHIVOS

    public void guardarEnArchivoPlaylist(String nombreUsuario, ObjectOutputStream out) throws IOException {
        NodoPlaylistPropia actual = listaPlaylistPropias;
        while(actual != null){
            actual.getSublistaCanciones().guardarCancionesDeLista(nombreUsuario, actual.getNombre(), out);
            actual = actual.getSiguiente();
        }
    }




}