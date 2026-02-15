package proyectobattleship;


public class Player {
    private String jugador;
    private String usuario;
    private String clave;
    private int puntos;
    private String rutaImagen;
    
    private String[] logs = new String[10];
    private int indiceLog = 0;
    
    public Player(String jugador, String usuario, String clave){
        this.jugador=jugador;
        this.usuario=usuario;
        this.clave=clave;
        this.puntos=0;
    }
    
    public String getRutaImagen(){
        return rutaImagen;
    }
    public void setRutaImagen(String rutaImagen) {
    this.rutaImagen = rutaImagen;
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
    

public void agregarLog(String mensaje){

    logs[indiceLog % 10] = mensaje;
    indiceLog++;
}

public String[] getLogs(){
    return logs;
}
     
}
