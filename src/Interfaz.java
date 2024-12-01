import java.util.Scanner;
import java.util.regex.Pattern;

public class Interfaz {
    public static final String REGEX_NOMBRE = "^[A-Za-z0-9]{1,8}$";
    public static final String REGEX_CANCION = "^[A-Za-z0-9]{1,30}$";
    private NodoUsuario usuario;
    private Scanner scanner;
    private ArbolCanciones arbolCanciones;
    private ListaAutores listaAutores;
    private ArbolUsuarios arbolUsuarios;

    public Interfaz(ArbolUsuarios arbolUsuarios, ArbolCanciones arbolCanciones, ListaAutores listaAutores) {
        this.arbolUsuarios = arbolUsuarios;
        this.arbolCanciones = arbolCanciones;
        this.listaAutores = listaAutores;
        scanner = new Scanner(System.in);
    }

    public void inicializarUsuario(NodoUsuario usuario) {
        this.usuario = usuario;
    }

    public void mostrarInterfaz() {
        int opcion;
        do {
            System.out.println("\nBienvenido, " + usuario.getNombre());
            System.out.println("===== Menú Principal =====");
            System.out.println();
            System.out.println("===== Listas Propias =====");
            usuario.getPlaylistsPropias().mostrarPlaylistPropia();
            System.out.println();
            System.out.println("===== Listas Seguidas =====");
            usuario.getPlaylistsSeguidas().mostrarPlaylists();
            System.out.println();
            System.out.println("===== Opciones =====");
            System.out.println();
            System.out.println("1. Agregar Canción");
            System.out.println("2. Crear Playlist Propia");
            System.out.println("3. Agregar Canción a Playlist por Título");
            System.out.println("4. Agregar Canción a Playlist por Autor");
            System.out.println("5. Eliminar Playlist Propia");
            System.out.println("6. Seguir Playlist de otro usuario");
            System.out.println("7. Volver al menú principal");
            System.out.println("Ingrese una opción (o 'm' para volver atrás):");

            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("m")) {
                System.out.println("Volviendo al menú anterior...");
                return; // Sale del método y vuelve atrás.
            }

            try {
                opcion = Integer.parseInt(input);
                switch (opcion) {
                    case 1:
                        agregarCancion();
                        break;
                    case 2:
                        crearListaPropia();
                        break;
                    case 3:
                        agregarCancionAListaPorTitulo();
                        break;
                    case 4:
                        agregarCancionPorAutor();
                        break;
                    case 5:
                        eliminarListaPropia();
                        break;
                    case 6:
                        seguirLista();
                        break;
                    case 7:
                        System.out.println("Volviendo al menú principal...");
                        break;
                    default:
                        System.out.println("Opción inválida. Por favor, intente de nuevo.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número válido o 'm' para volver atrás.");
            }
        } while (true);
    }

    private void agregarCancion() {
        System.out.println("\nIngrese el nombre de la canción (máximo 30 caracteres o 'm' para volver):");
        String nombreCancion = scanner.nextLine();
        if (nombreCancion.equals("m")) return;

        while (!validarCancion(nombreCancion)) {
            System.out.println("Error: Ingrese un nombre de canción válido (máximo 30 caracteres o 'm' para volver):");
            nombreCancion = scanner.nextLine();
            if (nombreCancion.equals("m")) return;
        }

        System.out.println("Ingrese el nombre del autor (máximo 8 caracteres o 'm' para volver):");
        String nombreAutor = scanner.nextLine();
        if (nombreAutor.equals("m")) return;

        while (!validarNombre(nombreAutor)) {
            System.out.println("Error: Ingrese un nombre de autor válido (máximo 8 caracteres o 'm' para volver):");
            nombreAutor = scanner.nextLine();
            if (nombreAutor.equals("m")) return;
        }

        arbolCanciones.insertarCancion(nombreCancion);
        listaAutores.insertarAutor(nombreAutor);
        NodoAutor autor = listaAutores.buscarAutor(nombreAutor);
        NodoCancion cancion = arbolCanciones.buscarCancion(nombreCancion);
        autor.getListaCanciones().insertarCancionOrdenadoCircular(cancion);
        System.out.println("Canción '" + nombreCancion + "' de '" + nombreAutor + "' agregada correctamente.");
    }

    private void crearListaPropia() {
        System.out.println("\nIngrese el nombre de la playlist (máximo 8 caracteres o 'm' para volver):");
        String nombrePlaylist = scanner.nextLine();
        if (nombrePlaylist.equals("m")) return;

        while (!validarNombre(nombrePlaylist)) {
            System.out.println("Error: Ingrese un nombre de playlist válido (máximo 8 caracteres o 'm' para volver):");
            nombrePlaylist = scanner.nextLine();
            if (nombrePlaylist.equals("m")) return;
        }

        usuario.getPlaylistsPropias().insertarPlaylist(nombrePlaylist);
        System.out.println("Playlist '" + nombrePlaylist + "' creada correctamente.");
    }

    private void agregarCancionAListaPorTitulo() {
        boolean encontrado = false;

        while (!encontrado) {
            System.out.println("\nIngrese el nombre de la playlist (o 'm' para volver):");
            String nombrePlaylist = scanner.nextLine();
            if (nombrePlaylist.equals("m")) return;

            NodoPlaylistPropia playlist = usuario.getPlaylistsPropias().buscarPlaylist(nombrePlaylist);
            if (playlist != null) {
                System.out.println("Ingrese el nombre de la canción (o 'm' para volver):");
                String nombreCancion = scanner.nextLine();
                if (nombreCancion.equals("m")) return;

                NodoCancion cancion = arbolCanciones.buscarCancion(nombreCancion);
                if (cancion != null) {
                    playlist.insertarNodoCancion(cancion);
                    System.out.println("Canción '" + nombreCancion + "' agregada correctamente a la playlist '" + nombrePlaylist + "'.");
                    encontrado = true;
                } else {
                    System.out.println("Error: La canción no se encontró.");
                }
            } else {
                System.out.println("Error: La playlist no existe.");
            }
        }
    }

    private void agregarCancionPorAutor() {
        boolean encontrado = false;

        while (!encontrado) {
            System.out.println("\nIngrese el nombre de la playlist (o 'm' para volver):");
            String nombrePlaylist = scanner.nextLine();
            if (nombrePlaylist.equals("m")) return;

            NodoPlaylistPropia playlist = usuario.getPlaylistsPropias().buscarPlaylist(nombrePlaylist);
            if (playlist != null) {
                System.out.println("Ingrese el nombre del autor (o 'm' para volver):");
                String nombreAutor = scanner.nextLine();
                if (nombreAutor.equals("m")) return;

                NodoAutor autor = listaAutores.buscarAutor(nombreAutor);
                if (autor != null) {
                    autor.getListaCanciones().mostrarListaCanciones();
                    System.out.println("Ingrese el nombre de una canción de la lista (o 'm' para volver):");
                    String nombreCancion = scanner.nextLine();
                    if (nombreCancion.equals("m")) return;

                    NodoCancion cancion = autor.getListaCanciones().buscarCancion(nombreCancion);
                    if (cancion != null) {
                        playlist.insertarNodoCancion(cancion);
                        System.out.println("Canción '" + nombreCancion + "' agregada correctamente a la playlist '" + nombrePlaylist + "'.");
                        encontrado = true;
                    } else {
                        System.out.println("Error: La canción no se encontró.");
                    }
                } else {
                    System.out.println("Error: El autor no se encontró.");
                }
            } else {
                System.out.println("Error: La playlist no existe.");
            }
        }
    }

    private void eliminarListaPropia() {
        boolean encontrado = false;

        while (!encontrado) {
            System.out.println("\nIngrese el nombre de la playlist que desea eliminar (o 'm' para volver):");
            String nombrePlaylist = scanner.nextLine();
            if (nombrePlaylist.equals("m")) return;

            if (usuario.getPlaylistsPropias().buscarPlaylist(nombrePlaylist) != null) {
                usuario.getPlaylistsPropias().eliminarPlaylist(nombrePlaylist);
                System.out.println("Playlist '" + nombrePlaylist + "' eliminada correctamente.");
                encontrado = true;
            } else {
                System.out.println("Error: No se encontró la playlist.");
            }
        }
    }

    private void seguirLista() {
        boolean encontrado = false;

        while (!encontrado) {
            System.out.println("\nIngrese el nombre del usuario a seguir (o 'm' para volver):");
            String nombreUsuario = scanner.nextLine();
            if (nombreUsuario.equals("m")) return;

            NodoUsuario userSeguido = arbolUsuarios.buscarUsuario(nombreUsuario);
            if (userSeguido != null) {
                ListaPlaylistsPropias listaUserSeguido = userSeguido.getPlaylistsPropias();
                System.out.println("Listas del usuario '" + nombreUsuario + "':");
                listaUserSeguido.mostrarPlaylistPropia();
                System.out.println("Ingrese el nombre de la playlist que desea seguir (o 'm' para volver):");
                String nombrePlaylistSeguida = scanner.nextLine();
                if (nombrePlaylistSeguida.equals("m")) return;

                NodoPlaylistPropia playlist = listaUserSeguido.buscarPlaylist(nombrePlaylistSeguida);
                if (playlist != null) {
                    usuario.getPlaylistsSeguidas().insertarPlaylist(nombrePlaylistSeguida,nombreUsuario);
                    System.out.println("Playlist '" + nombrePlaylistSeguida + "' añadida a sus playlists seguidas.");
                    encontrado = true;
                } else {
                    System.out.println("Error: No se encontró la playlist.");
                }
            } else {
                System.out.println("Error: El usuario no se encontró.");
            }
        }
    }

    private boolean validarNombre(String nombre) {
        return Pattern.matches(REGEX_NOMBRE, nombre);
    }

    private boolean validarCancion(String nombre) {
        return Pattern.matches(REGEX_CANCION, nombre);
    }
}
