import java.io.*;

class ArbolUsuarios {
    private NodoUsuario usuarios;

    public ArbolUsuarios() {
        usuarios = null;
        cargarUsuarios("archUsuarios");
    }

    public void insertarUsuario(String nombre, String contrasena) {
        this.usuarios = insertarUsuarioRec(usuarios, new NodoUsuario(nombre, contrasena));
    }

    private NodoUsuario insertarUsuarioRec(NodoUsuario actual, NodoUsuario nuevo) {
        if (actual == null) {
            return nuevo;
        } else if (actual.getNombre().compareToIgnoreCase(nuevo.getNombre()) > 0) {
            actual.setMenores(insertarUsuarioRec(actual.getMenores(), nuevo));
        } else if (actual.getNombre().compareToIgnoreCase(nuevo.getNombre()) < 0) {
            actual.setMayores(insertarUsuarioRec(actual.getMayores(), nuevo));
        }
        return actual;
    }

    public NodoUsuario buscarUsuario(String nombre) {
        return buscarUsuarioRecursivo(usuarios, nombre);
    }

    private NodoUsuario buscarUsuarioRecursivo(NodoUsuario actual, String nombre) {
        if (actual != null) {
            if (actual.getNombre().equals(nombre)) {
                return actual;
            } else if (actual.getNombre().compareTo(nombre) < 0) {
                return buscarUsuarioRecursivo(actual.getMayores(), nombre);
            } else {
                return buscarUsuarioRecursivo(actual.getMenores(), nombre);
            }
        }
        return null;
    }

    public void imprimirOrdenado() {
        imprimirOrdenadoRec(usuarios);
    }

    private void imprimirOrdenadoRec(NodoUsuario actual) {
        if (actual != null) {
            imprimirOrdenadoRec(actual.getMenores());
            System.out.println("* " + actual.getNombre());
            imprimirOrdenadoRec(actual.getMayores());
        }
    }

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
                // Archivo creado vacío
            } catch (IOException exc) {
                exc.printStackTrace();
            }
        } catch (EOFException e) {
            System.out.println("Usuarios cargados correctamente.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void guardarPlaylistsPropias(String archivo) {
        PlaylistManager.guardarPlaylistsPropias(usuarios, archivo);
    }

    public void cargarPlaylistsPropias(String archivo) {
        PlaylistManager.cargarPlaylistsPropias(this, archivo);
    }

    public void guardarPlaylistsSeguidas(String archivo) {
        PlaylistManager.guardarPlaylistsSeguidas(usuarios, archivo);
    }

    public void cargarPlaylistsSeguidas(String archivo) {
        PlaylistManager.cargarPlaylistsSeguidas(this, archivo);
    }
}


