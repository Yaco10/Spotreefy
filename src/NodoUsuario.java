import java.io.Serializable;

public class NodoUsuario implements Serializable {
    private String nombreUsuario;
    private String contraseña;
    private NodoUsuario menores;
    private NodoUsuario mayores;

    public NodoUsuario(String nombreUsuario, String contraseña) {
            // Validar el nombre de usuario

            this.nombreUsuario = nombreUsuario;
            this.contraseña = contraseña;
            this.menores = null;
            this.mayores = null;

    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContraseña(){
        return contraseña;
    }

    public void setContraseña(String contraseña){
        this.contraseña = contraseña;
    }

    public NodoUsuario getMayores() {
        return mayores;
    }

    public void setMayores(NodoUsuario mayores) {
        this.mayores = mayores;
    }

    public NodoUsuario getMenores() {
        return menores;
    }

    public void setMenores(NodoUsuario menores) {
        this.menores = menores;
    }





}
