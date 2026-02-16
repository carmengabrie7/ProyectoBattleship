package proyectobattleship;

import java.util.*;

public class Battleship {

    //constante, igual para todos los juegos
    private static final int SIZE = 8;

    private Barco[][] tablero1 = new Barco[SIZE][SIZE];
    private Barco[][] tablero2 = new Barco[SIZE][SIZE];

    private boolean[][] disparosJ1 = new boolean[SIZE][SIZE];
    private boolean[][] disparosJ2 = new boolean[SIZE][SIZE];

    private Player jugador1;
    private Player jugador2;
    private Player turnoActual;

    private String dificultad;
    private String modoJuego;

    private int barcosRestantes1;
    private int barcosRestantes2;

    private Fase fase;

    private Map<String,Integer> barcosJ1;
    private Map<String,Integer> barcosJ2;

    private Random random = new Random();

    private enum Fase {
        COLOCANDO_J1,
        COLOCANDO_J2,
        COMBATE,
        TERMINADO
    }

    public Battleship(Player j1, Player j2,
                      String dificultad,
                      String modoJuego) {

        this.jugador1 = j1;
        this.jugador2 = j2;
        this.dificultad = dificultad;
        this.modoJuego = modoJuego;

        barcosJ1 = crearBarcos();
        barcosJ2 = crearBarcos();

        turnoActual = jugador1;
        fase = Fase.COLOCANDO_J1;
    }

    private Map<String,Integer> crearBarcos() {

        Map<String,Integer> mapa = new HashMap<>();

        switch(dificultad) {

            case "EASY":
                mapa.put("PA",1);
                mapa.put("AZ",1);
                mapa.put("SM",1);
                mapa.put("DT",2);
                break;

            case "NORMAL":
                mapa.put("PA",1);
                mapa.put("AZ",1);
                mapa.put("SM",1);
                mapa.put("DT",1);
                break;

            case "EXPERT":
                mapa.put("PA",1);
                mapa.put("AZ",1);
                break;

            case "GENIUS":
                mapa.put("PA",1);
                break;
        }

        return mapa;
    }

    public boolean colocarBarco(Player jugador,
                                String codigo,
                                int fila,
                                int col,
                                boolean horizontal) {

        if(fase == Fase.COMBATE || fase == Fase.TERMINADO)
            return false;

        if(jugador != turnoActual)
            return false;

        Map<String,Integer> disponibles =
                (jugador == jugador1)
                        ? barcosJ1 : barcosJ2;

        if(!disponibles.containsKey(codigo)
           || disponibles.get(codigo) <= 0)
            return false;

        int tamaño = tamañoBarco(codigo);

        Barco[][] tablero =
                (jugador == jugador1)
                        ? tablero1 : tablero2;

        if(!puedeColocar(tablero,fila,col,tamaño,horizontal))
            return false;

        Barco barco = new Barco(codigo,tamaño);

        for(int i=0;i<tamaño;i++) {
            if(horizontal)
                tablero[fila][col+i] = barco;
            else
                tablero[fila+i][col] = barco;
        }

        disponibles.put(codigo,
                disponibles.get(codigo)-1);

        if(todosColocados(disponibles)) {

            if(fase == Fase.COLOCANDO_J1) {
                fase = Fase.COLOCANDO_J2;
                turnoActual = jugador2;
            }
            else {
                fase = Fase.COMBATE;
                turnoActual = jugador1;
                contarBarcos();
            }
        }

        return true;
    }

public String atacar(int fila, int col) {

    if (fase != Fase.COMBATE)
        return "NO_COMBATE";

    if (!posValida(fila, col))
        return "FUERA";

    
    boolean[][] disparos =
            (turnoActual == jugador1)
                    ? disparosJ1
                    : disparosJ2;

    if (disparos[fila][col])
        return "YA_DISPARADO";

    disparos[fila][col] = true;

    Barco[][] enemigo =
            (turnoActual == jugador1)
                    ? tablero2
                    : tablero1;

    Barco barco = enemigo[fila][col];

    if (barco == null) {
        cambiarTurno();
        return "FALLO";
    }

    barco.recibirImpacto();

    if (barco.estaHundido()) {

        if (turnoActual == jugador1)
            barcosRestantes2--;
        else
            barcosRestantes1--;

        if (barcosRestantes1 == 0 || barcosRestantes2 == 0) {

            turnoActual.setPuntos(
                    turnoActual.getPuntos() + 3);

            registrarLogGanador(turnoActual);

            fase = Fase.TERMINADO;
            return "GANADOR";
        }

        moverBarcos(enemigo);

        if (turnoActual == jugador1)
            limpiarDisparos(jugador1);
        else
            limpiarDisparos(jugador2);

        cambiarTurno();
        return "HUNDIDO";
    }

    cambiarTurno();
    return "IMPACTO";
}

    private void moverBarcos(Barco[][] tablero) {

        Set<Barco> barcos = new HashSet<>();

        for(int i=0;i<SIZE;i++)
            for(int j=0;j<SIZE;j++)
                if(tablero[i][j]!=null)
                    barcos.add(tablero[i][j]);

        for(int i=0;i<SIZE;i++)
            Arrays.fill(tablero[i],null);

        for(Barco b: barcos) {

            boolean colocado=false;

            while(!colocado) {

                int f=random.nextInt(SIZE);
                int c=random.nextInt(SIZE);
                boolean h=random.nextBoolean();

                if(puedeColocar(tablero,f,c,
                        b.getTamaño(),h)) {

                    for(int k=0;k<b.getTamaño();k++)
                        if(h)
                            tablero[f][c+k]=b;
                        else
                            tablero[f+k][c]=b;

                    colocado=true;
                }
            }
        }
    }

    private void limpiarDisparos(Player jugador){

        boolean[][] disparos =
                (jugador == jugador1)
                        ? disparosJ1
                        : disparosJ2;

        for(int i=0;i<SIZE;i++)
            for(int j=0;j<SIZE;j++)
                disparos[i][j] = false;
    }
    

    private void contarBarcos(){
        barcosRestantes1 = contar(tablero1);
        barcosRestantes2 = contar(tablero2);
    }

    private void registrarLogGanador(Player ganador) {

    Player perdedor =
            (ganador == jugador1)
                    ? jugador2
                    : jugador1;

    String mensaje =
            ganador.getJugador()
            + " hundió todos los barcos de "
            + perdedor.getJugador()
            + " en modo " + modoJuego
            + " con dificultad " + dificultad;

    jugador1.agregarLog(mensaje);
    jugador2.agregarLog(mensaje);
}
    
    private int contar(Barco[][] t){
        Set<Barco> set=new HashSet<>();
        for(int i=0;i<SIZE;i++)
            for(int j=0;j<SIZE;j++)
                if(t[i][j]!=null)
                    set.add(t[i][j]);
        return set.size();
    }

    private boolean puedeColocar(Barco[][] t,
                                 int f,int c,
                                 int tam,
                                 boolean h){

        if(!posValida(f,c))
            return false;

        if(h){
            if(c+tam>SIZE) return false;
            for(int i=0;i<tam;i++)
                if(t[f][c+i]!=null)
                    return false;
        }
        else{
            if(f+tam>SIZE) return false;
            for(int i=0;i<tam;i++)
                if(t[f+i][c]!=null)
                    return false;
        }

        return true;
    }

    private int tamañoBarco(String c){
        switch(c){
            case "PA": return 5;
            case "AZ": return 4;
            case "SM": return 3;
            case "DT": return 2;
        }
        return 0;
    }

    private boolean todosColocados(Map<String,Integer> m){
        for(int v:m.values())
            if(v>0) return false;
        return true;
    }

    private void cambiarTurno(){
        turnoActual =
                (turnoActual==jugador1)
                        ? jugador2
                        : jugador1;
    }

    private boolean posValida(int f,int c){
        return f>=0 && f<SIZE &&
               c>=0 && c<SIZE;
    }

    public boolean[][] getDisparosJ1(){ return disparosJ1; }
    public boolean[][] getDisparosJ2(){ return disparosJ2; }

    public boolean esCombate(){ return fase==Fase.COMBATE; }
    public boolean esColocacion(){
        return fase==Fase.COLOCANDO_J1
                || fase==Fase.COLOCANDO_J2;
    }

    public Player getTurnoActual(){
        return turnoActual; }
    
    public Player getJugador1(){
        return jugador1; }
    
    public Player getJugador2(){
        return jugador2; }
    
    public Barco[][] getTablero1(){
        return tablero1; }
    
    public Barco[][] getTablero2(){
        return tablero2; }
    
    public String getModoJuego(){
        return modoJuego; }
    
    public String getDificultad(){
        return dificultad; }
}