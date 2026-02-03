package proyectobattleship;


public class Usuario {
    public String jugador;
    public String usuario;
    public String clave;
    public int puntos;
    
    public Usuario(String player, String user, String password,int puntos){
        jugador=player;
        usuario=user;
        clave=password;
        puntos=0;
    }
    
    public String getJugador(){
        return jugador;
    }
    public void setJugador(String jugador){
        this.jugador=jugador;
    }
    
    public String getUsuario(){
        return usuario;
    }
    public void setUsuario(String usuario){
        this.usuario=usuario;
    }
    
    public String getClave(){
        return clave;
    }
    public void setClave(String clave){
        this.clave=clave;
    }
    
    public int getPuntos(){
        return puntos;
    }
    public void setPuntos(int puntos){
        this.puntos=puntos;
    }
}
