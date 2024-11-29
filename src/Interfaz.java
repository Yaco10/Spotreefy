import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Interfaz {
    public static final String REGEX_NOMBRE = "^[A-Za-z0-9]{1,8}$";
    public static final String REGEX_CANCION = "^[A-Za-z0-9]{1,30}$";
    private NodoUsuario usuario;
    private Scanner scanner;
    private ArbolCanciones arbolCanciones;
    private ListaAutores listaAutores;
    private ArbolUsuarios arbolUsuarios;

    public Interfaz (ArbolUsuarios arbolUsuarios ,ArbolCanciones arbolCanciones, ListaAutores listaAutores) {
        this.usuario = null;
        this.arbolUsuarios = arbolUsuarios;
        this.arbolCanciones = arbolCanciones;
        this.listaAutores = listaAutores;
        scanner = new Scanner(System.in);
    }


    public void mostrarInterfaz(){
        int opcion;
        System.out.println("Usuario" + usuario.getNombre());
        System.out.println("Listas Propias");
        usuario.getPlaylistsPropias().mostrarPlaylistPropia();
        System.out.println("Listas Seguidas");
        //usuario.getPlaylistsPropias().mostrarPlaylistPropia();
        System.out.println();
        System.out.println("1.Agregar Cancion");
        System.out.println("2.Crear Playlist Propia");
        System.out.println("3.Agregar Cancion a Playlist por Titulo");
        System.out.println("4.Agregar Cancion a Playlist por Artista");
        System.out.println("5.Eliminar Cancion");
        //System.out.println("6.Eliminar Cancion");
        System.out.println("7.Volver al menu Principal");
        opcion = scanner.nextInt();
        scanner.nextLine();

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
                System.out.println("Volviendo a menu principal...");
                break;
            default:
                System.out.println("Opcion invalida");

        }
    }

    private void agregarCancion () {
        //ingreso cancion
        System.out.println("Ingrese su Cancion");
        String nombreCancion = scanner.nextLine();
        while(!validarCancion(nombreCancion)){ //valido con regex para cancion
            System.out.println("Ingrese un nombre con menos de 30 caracteres");
            nombreCancion = scanner.nextLine();
        }
        arbolCanciones.insertarCancion(nombreCancion);
        //Ingreso autor
        System.out.println("Ingrese el nombre del autor");
        String NombreAutor = scanner.nextLine();
        while(!validarNombre(NombreAutor)){
            System.out.println("Ingrese un nombre con menos de 8 caracteres");
            NombreAutor = scanner.nextLine();
        }
        listaAutores.insertarAutor(NombreAutor);
        NodoAutor autor = listaAutores.buscarAutor(NombreAutor);
        NodoCancion cancion = arbolCanciones.buscarCancion(nombreCancion);
        autor.getListaCanciones().insertarCancionOrdenadoCircular(cancion);
        System.out.println("Cancion" + nombreCancion + "de"+ NombreAutor +"Agregada correctamente");
        System.out.println();
        mostrarInterfaz();
    }

    private void crearListaPropia () {
        System.out.println("Ingrese el nombre de la playlist");
        String nombrePlaylist = scanner.nextLine();
        while(!validarNombre(nombrePlaylist)){
            System.out.println("Ingrese un nombre de la playlist menor a 8 caracteres");
        }
        usuario.getPlaylistsPropias().insertarPlaylist(nombrePlaylist);
        System.out.println("Playlist "+nombrePlaylist+" creada correctamente");
        System.out.println();
        mostrarInterfaz();
    }

    private void agregarCancionAListaPorTitulo () {
        System.out.println("Ingrese el nombre de la playlist");
        String nombrePlaylist = scanner.nextLine();
        NodoPlaylistPropia Playlist = usuario.getPlaylistsPropias().buscarPlaylist(nombrePlaylist); //obtengo nodo perteneciente a playlist
        if(Playlist != null){
            System.out.println("Ingrese nombre de la cancion");
            String nombreCancion = scanner.nextLine();
            NodoCancion Cancion = arbolCanciones.buscarCancion(nombreCancion); //obtengo nodo perteneciente a cancion
            if(Cancion != null){
                Playlist.insertarNodoCancion(Cancion); //inserto nodo cancion en la playlist correspondiente
                System.out.println("Cancion "+nombreCancion+" Agregada correctamente a la playlist "+nombrePlaylist);
                mostrarInterfaz();
            }
            else{
                System.out.println("No se encontro el nombre de la cancion");
                System.out.println();
                mostrarInterfaz();
            }
        }
        else{
            System.out.println("La playlist no existe");
            System.out.println();
            mostrarInterfaz();
        }
    }

    private void agregarCancionPorAutor () {
        System.out.println("Ingrese el nombre de la playlist");
        String nombrePlaylist = scanner.nextLine();
        NodoPlaylistPropia Lista = usuario.getPlaylistsPropias().buscarPlaylist(nombrePlaylist); //obtengo nodo playlist
        if(Lista != null){
            System.out.println("Ingrese nombre del autor");
            String nombreAutor = scanner.nextLine();
            NodoAutor autor = listaAutores.buscarAutor(nombreAutor); //obtengo nodo autor
            if(autor != null){
                autor.getListaCanciones().mostrarListaCanciones();
                System.out.println("Ingrese una cancion de la lista");
                String nombreCancion = scanner.nextLine();
                NodoCancion cancion = autor.getListaCanciones().buscarCancion(nombreCancion);
                if(cancion != null){
                    Lista.insertarNodoCancion(cancion);
                    System.out.println("La cancion "+nombreCancion+ " fue agregada correctamente");
                    mostrarInterfaz();
                }
                else{
                    System.out.println("No se encontro el nombre de la cancion");
                    System.out.println();
                    mostrarInterfaz();
                }
            }
            else{
                System.out.println("No se encontro el nombre del autor");
                System.out.println();
                mostrarInterfaz();
            }
        }
        else{
            System.out.println("La playlist no existe");
            System.out.println();
            mostrarInterfaz();
        }
    }

    private void eliminarListaPropia () {
        System.out.println("Ingrese el nombre de la playlist que desea eliminar");
        String nombrePlaylist = scanner.nextLine();
        if(usuario.getPlaylistsPropias().buscarPlaylist(nombrePlaylist)!=null){
            usuario.getPlaylistsPropias().eliminarPlaylist(nombrePlaylist);
            System.out.println("La playlist "+nombrePlaylist+" eliminada correctamente");
            System.out.println();
            mostrarInterfaz();
        }
        else{
            System.out.println("No se encontro el nombre de la playlist");
            System.out.println();
            mostrarInterfaz();
        }
    }

    private void seguirLista () {

    }

    //VALIDACIONES
    private boolean validarNombre (String texto){
        Pattern pattern = Pattern.compile(REGEX_NOMBRE);
        Matcher matcher = pattern.matcher(texto);
        return matcher.matches();
    }

    private boolean validarCancion (String texto){
        Pattern pattern = Pattern.compile(REGEX_CANCION);
        Matcher matcher = pattern.matcher(texto);
        return matcher.matches();
    }

    //GETTER AND SETTER
    public void setUsuario(NodoUsuario usuario){
        this.usuario = usuario;
    }
}


