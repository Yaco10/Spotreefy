import java.io.*;

class ListaPlaylistsPropias  {
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

    public void guardarLista(String nombreArchivo) {
        File archivo = new File(nombreArchivo);
        try {
            if (!archivo.exists()) {
                // Crear el archivo si no existe
                archivo.createNewFile();
                System.out.println("Archivo creado: " + archivo.getAbsolutePath());
            }

            // Escribir en el archivo
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
                NodoPlaylistPropia actual = listaPlaylistPropias;
                while (actual != null) {
                    bw.write("Playlist: " + actual.getNombre());
                    bw.newLine();
                    actual.getSublistaCanciones().guardarCanciones(bw);
                    bw.newLine();
                    actual = actual.getSiguiente();
                }
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo: " + e.getMessage());
        }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void cargarLista(String nombreArchivo, ArbolCanciones arbolCanciones) {
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            NodoPlaylistPropia playlistActual = null;

            while ((linea = br.readLine()) != null) {
                linea = linea.trim();

                if (linea.startsWith("Playlist:")) {
                    // Crear una nueva playlist
                    String nombrePlaylist = linea.substring("Playlist:".length()).trim();
                    insertarPlaylist(nombrePlaylist);
                    playlistActual = buscarPlaylist(nombrePlaylist); // Obtener la referencia a la nueva playlist
                } else if (linea.startsWith("- ") && playlistActual != null) {
                    // Buscar la canción en el árbol y agregarla a la playlist actual
                    String nombreCancion = linea.substring(2).trim(); // Eliminar "- "
                    NodoCancion cancion = arbolCanciones.buscarCancion(nombreCancion);

                    if (cancion != null) {
                        playlistActual.insertarNodoCancion(cancion); // Insertar en la sublista de canciones
                    } else {
                        System.err.println("La canción \"" + nombreCancion + "\" no se encontró en el árbol de canciones.");
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar el archivo: " + e.getMessage());
        }
    }



}