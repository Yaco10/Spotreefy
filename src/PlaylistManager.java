import java.io.*;

public class PlaylistManager {

    public static void guardarPlaylistsPropias(NodoUsuario usuarios, String archivo) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(archivo))) {
            guardarPlaylistsPropiasRec(usuarios, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void guardarPlaylistsPropiasRec(NodoUsuario actual, ObjectOutputStream out) throws IOException {
        if (actual != null) {
            actual.getPlaylistsPropias().guardarEnArchivoPlaylist(actual.getNombre(), out);
            guardarPlaylistsPropiasRec(actual.getMenores(), out);
            guardarPlaylistsPropiasRec(actual.getMayores(), out);
        }
    }

    public static void cargarPlaylistsPropias(ArbolUsuarios arbol, String archivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            Object obj;

            while (true) {
                try {
                    obj = ois.readObject();
                    if (obj instanceof ArchListasPropias) {
                        ArchListasPropias actual = (ArchListasPropias) obj;
                        NodoUsuario user = arbol.buscarUsuario(actual.getNombreUsuario());
                        if (user != null) {
                            user.getPlaylistsPropias().insertarPlaylist(actual.getNombrePlaylist());
                            NodoPlaylistPropia playlistPropia = user.getPlaylistsPropias().buscarPlaylist(actual.getNombrePlaylist());
                            playlistPropia.insertarNodoCancion(actual.getCancion());
                        }
                    } else {
                        System.out.println("Objeto en archivo no es de tipo ArchListasPropias.");
                    }
                } catch (EOFException e) {
                    System.out.println("Listas Propias cargadas correctamente.");
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo Listas Propias no encontrado, creando archivo nuevo.");
            crearArchivoVacio(archivo);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void guardarPlaylistsSeguidas(NodoUsuario usuarios, String archivo) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(archivo))) {
            guardarPlaylistsSeguidasRec(usuarios, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void guardarPlaylistsSeguidasRec(NodoUsuario actual, ObjectOutputStream out) throws IOException {
        if (actual != null) {
            actual.getPlaylistsSeguidas().guardarPlaylistEnArchivo(actual.getNombre(), out);
            guardarPlaylistsSeguidasRec(actual.getMenores(), out);
            guardarPlaylistsSeguidasRec(actual.getMayores(), out);
        }
    }

    public static void cargarPlaylistsSeguidas(ArbolUsuarios arbol, String archivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            Object obj;

            while (true) {
                try {
                    obj = ois.readObject();
                    if (obj instanceof ArchListaSeguida) {
                        ArchListaSeguida actual = (ArchListaSeguida) obj;
                        NodoUsuario user = arbol.buscarUsuario(actual.getNombreUser());
                        if (user != null) {
                            user.getPlaylistsSeguidas().insertarPlaylist(actual.getNombreUserSeguido(), actual.getNombrePlaylist());
                        }
                    } else {
                        System.out.println("Objeto en archivo no es de tipo ArchListaSeguida.");
                    }
                } catch (EOFException e) {
                    System.out.println("Listas Seguidas cargadas correctamente.");
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo Listas Seguidas no encontrado, creando archivo nuevo.");
            crearArchivoVacio(archivo);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void crearArchivoVacio(String archivo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            // Crear archivo vacío
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
