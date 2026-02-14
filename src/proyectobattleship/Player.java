package proyectobattleship;


public class Player {
    private String jugador;
    private String usuario;
    private String clave;
    private int puntos;
    
    private String[] logs = new String[10];
    
    public Player(String jugador, String usuario, String clave){
        this.jugador=jugador;
        this.usuario=usuario;
        this.clave=clave;
        this.puntos=0;
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
    
    public String[] getLogs(){
        return logs;
    }
    
    //se encarga de mostrar las ultimas 10 partidas y asegura que el mas reciente tenga el primer puesto.
     public void agregarLog(String log) {
        for (int i = logs.length - 1; i > 0; i--) {
            logs[i] = logs[i - 1];
        }
        logs[0] = log;
    }
     
}
