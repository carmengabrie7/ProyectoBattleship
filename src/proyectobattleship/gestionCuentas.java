package proyectobattleship;

import java.util.ArrayList;
import javax.swing.JOptionPane;


public class GestionCuentas {
    private ArrayList<Player> cuentas = new ArrayList<>(); //guardar cuentas
    private Player currentUser;
private String dificultad = "NORMAL"; // default obligatorio
private int cantidadBarcos = 4;       // normal = 4

private String modoJuego = "TUTORIAL"; // default obligatorio

    
    public boolean crearCuenta(String jugador,String usuario, String clave){
        if (usuario.isEmpty()||clave.isEmpty()||jugador.isEmpty()){
            return false;
        }
        if (clave.length()>5){
            return false;
        }
        
        for (Player p : cuentas) {
            if (p.getUsuario().equalsIgnoreCase(usuario)) {
                return false;
            }
        }
        
       cuentas.add(new Player(jugador, usuario, clave));
        return true;
    } 
    
    public boolean login(String usuario, String clave) {

        for (Player p : cuentas) {
            if (p.getUsuario().equalsIgnoreCase(usuario)
                    && p.getClave().equals(clave)) {

                currentUser = p;
                return true;
            }
        }

        return false;
    }
    
    public boolean usuarioExiste(String usuario) {
    for (Player p : cuentas) {
        if (p.getUsuario().equalsIgnoreCase(usuario)) {
            return true;
        }
    }
    return false;
}
    
    public Player getCurrentUser() {
        return currentUser;
    }

    public void logout() {
        currentUser = null;
    }

    public boolean eliminarCuenta() {

        if (currentUser != null) {
            cuentas.remove(currentUser);
            currentUser = null;
            return true;
        }

        return false;
    }

    public ArrayList<Player> getCuentas() {
        return cuentas;
    }
    
    public String getDificultad() {
    return dificultad;
}

public int getCantidadBarcos() {
    return cantidadBarcos;
}

public String getModoJuego() {
    return modoJuego;
}

public void setDificultad(String dificultad) {

    this.dificultad = dificultad;

    switch (dificultad) {
        case "EASY":
            cantidadBarcos = 5;
            break;
        case "NORMAL":
            cantidadBarcos = 4;
            break;
        case "EXPERT":
            cantidadBarcos = 2;
            break;
        case "GENIUS":
            cantidadBarcos = 1;
            break;
    }
}

public void setModoJuego(String modoJuego) {
    this.modoJuego = modoJuego;
}

}
