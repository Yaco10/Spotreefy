
import java.io.*;

class ArbolUsuarios {
    NodoUsuario usuarios;
    public ArbolUsuarios() {
        usuarios = null;
        cargarUsuarios("archUsuarios");
    }

    public void insertarUsuario(String nombre, String contraseña) {
        this.usuarios = insertarUsuarioRec(this.usuarios, new NodoUsuario(nombre, contraseña));
    }

    private NodoUsuario insertarUsuarioRec(NodoUsuario actual, NodoUsuario nuevo) {
        if (actual == null) {
            actual = nuevo;
        } else if (actual.getNombre().compareToIgnoreCase(nuevo.getNombre()) > 0) {
            actual.setMenores(insertarUsuarioRec(actual.getMenores(), nuevo));
        } else if (actual.getNombre().compareToIgnoreCase(nuevo.getNombre()) < 0) {
            actual.setMayores(insertarUsuarioRec(actual.getMayores(), nuevo));
        }
        return actual;
    }

    public NodoUsuario buscarUsuario(String nombre) {
        return buscarUsuarioRecursivo(usuarios, nombre);
        // ¿boolean?
    }

    private NodoUsuario buscarUsuarioRecursivo(NodoUsuario actual, String nombre){
        if(actual != null){
            if(actual.getNombre().equals(nombre)){
                return actual;
            } else if (actual.getNombre().compareTo(nombre) < 0) {
                return buscarUsuarioRecursivo(actual.getMayores(),nombre);
            } else if (actual.getNombre().compareTo(nombre) > 0) {
                return buscarUsuarioRecursivo(actual.getMenores(),nombre);
            }
        }
        return null;
    }


    public void imprimirOrdenado() {
        imprimirOrdenadoRec(this.usuarios);
    }

    private void imprimirOrdenadoRec(NodoUsuario actual) {
        imprimirOrdenadoRec(actual.getMenores());
        System.out.println("* " + actual.getNombre());
        imprimirOrdenadoRec(actual.getMayores());
    }

    //Archivos

    public void guardarEnArchivo(String archivo) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(archivo))) {
            guardarEnArchivoRec(usuarios, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void guardarEnArchivoRec(NodoUsuario actual, ObjectOutputStream out) throws IOException {
        if (actual != null) {
            out.writeObject(actual);
            guardarEnArchivoRec(actual.getMenores(), out);
            guardarEnArchivoRec(actual.getMayores(), out);
        }
    }

    public void cargarUsuarios(String archivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            NodoUsuario actual;

            while ((actual = (NodoUsuario) ois.readObject()) != null) {
                insertarUsuario(actual.getNombre(), actual.getContraseña());
            }

        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado. Creando archivo nuevo.");
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
            } catch (IOException exc) {
                exc.printStackTrace();
            }
        } catch (EOFException e) {
            System.out.println("Usuarios cargados correctamente.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void guardarEnArchivoPlaylist(String archivo) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(archivo))) {
            guardarEnArchivoPlaylistRec(usuarios, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void guardarEnArchivoPlaylistRec(NodoUsuario actual, ObjectOutputStream out) throws IOException {
        if (actual != null) {
            actual.getPlaylistsPropias().guardarEnArchivoPlaylist(actual.getNombre(), out);
            guardarEnArchivoPlaylistRec(actual.getMenores(), out);
            guardarEnArchivoPlaylistRec(actual.getMayores(), out);
        }
    }

    public void cargarArchivoPlaylist(String archivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            Object obj;

            while (true) {
                try {
                    obj = ois.readObject();
                    if (obj instanceof ArchListasPropias) {
                        ArchListasPropias actual = (ArchListasPropias) obj;
                        NodoUsuario user = buscarUsuario(actual.getNombreUsuario());
                        if (user != null) {
                            user.getPlaylistsPropias().insertarPlaylist(actual.getNombrePlaylist());
                            NodoPlaylistPropia playlistPropia = user.getPlaylistsPropias().buscarPlaylist(actual.getNombrePlaylist());
                            playlistPropia.insertarNodoCancion(actual.getCancion());
                        }
                    } else {
                        System.out.println("Objeto en archivo no es de tipo ArchListasPropias.");
                    }
                } catch (EOFException e) {
                    // Correctamente maneja el fin del archivo
                    System.out.println("Listas Propias cargadas correctamente.");
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo Listas Propias no encontrado, Creando archivo nuevo.");
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
                // Archivo nuevo
            } catch (IOException exc) {
                exc.printStackTrace();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    //ARCHIVOS LISTAS SEGUIDAS

    public void guardarArchivoPlaylistSeguida(String archivo) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(archivo))) {
            guardarArchivoPlaylistSeguidaRec(usuarios, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void guardarArchivoPlaylistSeguidaRec(NodoUsuario actual, ObjectOutputStream out) throws IOException {
        if (actual != null) {
            actual.getPlaylistsSeguidas().guardarPlaylistEnArchivo(actual.getNombre(),out);
            guardarArchivoPlaylistSeguidaRec(actual.getMenores(), out);
            guardarArchivoPlaylistSeguidaRec(actual.getMayores(), out);
        }
    }

    public void cargarArchivoPlaylistSeguida(String archivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            Object obj;

            while (true) {
                try {
                    obj = ois.readObject();
                    if (obj instanceof ArchListaSeguida) {
                        ArchListaSeguida actual = (ArchListaSeguida) obj;
                        NodoUsuario user = buscarUsuario(actual.getNombreUser());
                        if (user != null) {
                            user.getPlaylistsSeguidas().insertarPlaylist(actual.getNombreUserSeguido(), actual.getNombrePlaylist());
                        }
                    } else {
                        System.out.println("Objeto en archivo no es de tipo ArchListasPropias.");
                    }
                } catch (EOFException e) {
                    // Correctamente maneja el fin del archivo
                    System.out.println("Listas Propias cargadas correctamente.");
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo Listas Propias no encontrado, Creando archivo nuevo.");
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
                // Archivo nuevo
            } catch (IOException exc) {
                exc.printStackTrace();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}





