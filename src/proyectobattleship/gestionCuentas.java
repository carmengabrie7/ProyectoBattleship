package proyectobattleship;

import java.util.ArrayList;


public class GestionCuentas {
    private ArrayList<Player> cuentas = new ArrayList<>(); //guardar cuentas
    private Player currentUser;

    
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
    
}
