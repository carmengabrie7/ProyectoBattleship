package proyectobattleship;

import javax.swing.*;
import java.awt.*;

public class juegoGUI extends JFrame {

    private Battleship juego;

    private JButton[][] botones1 = new JButton[8][8];
    private JButton[][] botones2 = new JButton[8][8];

    private JLabel lblTurno;
    private JLabel lblFase;

    private final int SIZE = 8;

   private GestionCuentas gestion;

    public juegoGUI(Battleship juego, GestionCuentas gestion) {
    this.juego = juego;
    this.gestion = gestion;

        setTitle("Sea ⚓ Strike - Juego");
        setSize(1000,650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        getContentPane().setBackground(new Color(8,25,45));
        setContentPane(new JLabel(new ImageIcon("src//proyectobattleship/imagenes/juego.png")));
        getContentPane().setLayout(null);

        crearHeader();
        crearTableros();
        crearBotones();

        actualizarVista();
        mostrarInstrucciones();
    }
    
    private void mostrarInstrucciones() {

    String dificultad = juego.getDificultad();

    String mensaje = "INSTRUCCIONES\n\n";

    switch(dificultad){

        case "EASY":
            mensaje += "Modo EASY:\n"
                    + "- 1 PA (5 casillas)\n"
                    + "- 1 AZ (4 casillas)\n"
                    + "- 1 SM (3 casillas)\n"
                    + "- 2 DT (2 casillas)\n";
            break;

        case "NORMAL":
            mensaje += "Modo NORMAL:\n"
                    + "- 1 PA (5 casillas)\n"
                    + "- 1 AZ (4 casillas)\n"
                    + "- 1 SM (3 casillas)\n"
                    + "- 1 DT (2 casillas)\n";
            break;

        case "EXPERT":
            mensaje += "Modo EXPERT:\n"
                    + "- 1 PA (5 casillas)\n"
                    + "- 1 AZ (4 casillas)\n";
            break;

        case "GENIUS":
            mensaje += "Modo GENIUS:\n"
                    + "- Solo 1 PA (5 casillas)\n";
            break;
    }

    mensaje += "\nReglas especiales:\n"
            + "- Al hundir un barco, el tablero enemigo cambia de posición.\n"
            + "- Los disparos anteriores en ese tablero se reinician.\n"
            + "- El ganador recibe +3 puntos.\n"
            + "- Si te retiras, pierdes -1 punto.\n";

    JOptionPane.showMessageDialog(this, mensaje);
}

    private void crearHeader(){

        JLabel lblDif = new JLabel("Dificultad: " + juego.getDificultad());
        lblDif.setBounds(60,20,200,30);
        lblDif.setForeground(Color.WHITE);

        lblTurno = new JLabel();
        lblTurno.setBounds(380,20,350,30);
        lblTurno.setForeground(Color.WHITE);

        JLabel lblModo = new JLabel("Modo: " + juego.getModoJuego());
        lblModo.setBounds(780,20,200,30);
        lblModo.setForeground(Color.WHITE);

        lblFase = new JLabel();
        lblFase.setBounds(430,50,200,30);
        lblFase.setForeground(Color.WHITE);

        add(lblDif);
        add(lblTurno);
        add(lblModo);
        add(lblFase);
    }

    private void crearTableros(){

        JPanel panel1 = crearPanelTablero();
        panel1.setBounds(60,100,320,320);

        JPanel panel2 = crearPanelTablero();
        panel2.setBounds(600,100,320,320);

        for(int i=0;i<SIZE;i++){
            for(int j=0;j<SIZE;j++){

                JButton b1 = crearCelda();
                JButton b2 = crearCelda();

                final int f=i;
                final int c=j;

                b1.addActionListener(e -> accionCelda(f,c,true));
                b2.addActionListener(e -> accionCelda(f,c,false));

                botones1[i][j]=b1;
                botones2[i][j]=b2;

                panel1.add(b1);
                panel2.add(b2);
            }
        }

        add(panel1);
        add(panel2);

        agregarIndices();
    }

    private void agregarIndices(){

        for(int i=0;i<8;i++){

            JLabel abajo1 = new JLabel(String.valueOf(i+1));
            abajo1.setForeground(Color.WHITE);
            abajo1.setBounds(95 + (i*40), 425, 20, 20);
            add(abajo1);

            JLabel abajo2 = new JLabel(String.valueOf(i+1));
            abajo2.setForeground(Color.WHITE);
            abajo2.setBounds(615 + (i*40), 425, 20, 20);
            add(abajo2);

            JLabel lado1 = new JLabel(String.valueOf(i+1));
            lado1.setForeground(Color.WHITE);
            lado1.setBounds(40,110+(i*40),20,20);
            add(lado1);

            JLabel lado2 = new JLabel(String.valueOf(i+1));
            lado2.setForeground(Color.WHITE);
            lado2.setBounds(580,110+(i*40),20,20);
            add(lado2);
        }

        JLabel lbl1 = new JLabel("#1");
        lbl1.setForeground(Color.WHITE);
        lbl1.setBounds(210, 80, 50, 20);

        JLabel lbl2 = new JLabel("#2");
        lbl2.setForeground(Color.WHITE);
        lbl2.setBounds(750, 80, 50, 20);

        add(lbl1);
        add(lbl2);
    }

    private JPanel crearPanelTablero(){

        JPanel panel = new JPanel(new GridLayout(SIZE,SIZE));
        panel.setBackground(Color.BLACK);
        panel.setBorder(BorderFactory.createLineBorder(Color.GREEN,2));
        return panel;
    }

    private JButton crearCelda(){

        JButton btn = new JButton();
        btn.setBackground(Color.BLACK);
        btn.setForeground(Color.GREEN);
        btn.setFont(new Font("Consolas",Font.BOLD,12));
        btn.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        btn.setFocusPainted(false);
        return btn;
    }

    private void crearBotones(){

        JButton btnColocar = new JButton("Colocar Barco");
        btnColocar.setBounds(300,470,200,40);

        btnColocar.addActionListener(e -> {

            if(juego.esCombate()){
                JOptionPane.showMessageDialog(this,
                        "El combate ya comenzó");
                return;
            }

            colocarBarcoDialog();
        });

        JButton btnRetirarse = new JButton("Retirarse");
        btnRetirarse.setBounds(550,470,200,40);

        btnRetirarse.addActionListener(e -> {

    Player actual = juego.getTurnoActual();
    Player rival = (actual == juego.getJugador1())
            ? juego.getJugador2()
            : juego.getJugador1();

    int opcion = JOptionPane.showConfirmDialog(
            this,
            "Si te retiras perderás 1 punto.\n¿Deseas continuar?",
            "Confirmar retiro",
            JOptionPane.YES_NO_OPTION
    );

    if(opcion == JOptionPane.YES_OPTION){


        if(actual.getPuntos() > 0)
    actual.setPuntos(actual.getPuntos() - 1);


        String mensaje =
        actual.getJugador()
        + " se retiró del juego dejando como ganador a "
        + rival.getJugador()
        + " en modo " + juego.getModoJuego()
        + " dificultad " + juego.getDificultad();

        juego.getJugador1().agregarLog(mensaje);
        juego.getJugador2().agregarLog(mensaje);

        dispose();
        new menuPrincipal(gestion).setVisible(true);
    }
});

        add(btnColocar);
        add(btnRetirarse);
    }

    private void colocarBarcoDialog(){

    String codigo = JOptionPane.showInputDialog(
            this,
            "Código (PA, AZ, SM, DT)");

    if(codigo == null || codigo.trim().isEmpty()){
        JOptionPane.showMessageDialog(this,
                "Debes ingresar un código válido.");
        return;
    }

    codigo = codigo.toUpperCase();

    String filaStr = JOptionPane.showInputDialog("Fila (1-8)");
    String colStr = JOptionPane.showInputDialog("Columna (1-8)");

    if(filaStr == null || colStr == null ||
       filaStr.trim().isEmpty() || colStr.trim().isEmpty()){
        JOptionPane.showMessageDialog(this,
                "No puedes dejar espacios en blanco.");
        return;
    }

    int fila, col;

    try{
        fila = Integer.parseInt(filaStr) - 1;
        col = Integer.parseInt(colStr) - 1;
    } catch(NumberFormatException e){
        JOptionPane.showMessageDialog(this,
                "Debes ingresar números válidos.");
        return;
    }

    int orient = JOptionPane.showConfirmDialog(
            this,
            "¿Horizontal?",
            "Orientación",
            JOptionPane.YES_NO_OPTION);

    boolean horizontal =
            (orient == JOptionPane.YES_OPTION);

    boolean ok = juego.colocarBarco(
            juego.getTurnoActual(),
            codigo,fila,col,horizontal);

    if(!ok){
        JOptionPane.showMessageDialog(this,
                "No se pudo colocar barco");
    }

    actualizarVista();
}

   private void accionCelda(int fila, int col, boolean esTablero1) {

    if (!juego.esCombate()) {
        JOptionPane.showMessageDialog(this,
                "Primero deben colocarse todos los barcos");
        return;
    }

    Player actual = juego.getTurnoActual();

    if (actual == juego.getJugador1() && esTablero1)
        return;

    if (actual == juego.getJugador2() && !esTablero1)
        return;

    String resultado = juego.atacar(fila, col);

    JButton boton;

    if (actual == juego.getJugador1()) {
        boton = botones2[fila][col];
    } else {
        boton = botones1[fila][col];
    }

    if (resultado.equals("YA_DISPARADO")) {
        JOptionPane.showMessageDialog(this,
                "Ya disparaste ahí");
        return;
    }

    if (resultado.equals("FUERA")) {
        JOptionPane.showMessageDialog(this,
                "Coordenada inválida");
        return;
    }

    if (resultado.equals("FALLO")) {
        boton.setBackground(Color.BLUE);
        boton.setText("F");
    }

    else if (resultado.equals("IMPACTO")) {
        boton.setBackground(Color.YELLOW);
        boton.setText("X");
    }

    else if (resultado.equals("HUNDIDO")) {

    JOptionPane.showMessageDialog(this,
            "¡Barco hundido!");

    limpiarTableroVisual(
            actual == juego.getJugador1() ? 2 : 1
    );

    actualizarVista();
    return;
}

    else if (resultado.equals("GANADOR")) {

        boton.setBackground(Color.RED);
        boton.setText("X");

        JOptionPane.showMessageDialog(this,
                "Ganador: " + actual.getJugador());

        dispose();
        new menuPrincipal(gestion).setVisible(true);
        return;
    }
}
   
   private void limpiarTableroVisual(int numeroTablero) {

    JButton[][] tablero = (numeroTablero == 1)
            ? botones1
            : botones2;

    for(int i=0;i<SIZE;i++){
        for(int j=0;j<SIZE;j++){
            tablero[i][j].setBackground(Color.BLACK);
            tablero[i][j].setText("");
        }
    }
}

    private void actualizarVista(){

        lblFase.setText(juego.esCombate()
                ? "Fase: COMBATE"
                : "Fase: COLOCACIÓN");

        if (juego.getTurnoActual() == juego.getJugador1())
            lblTurno.setText("Turno: Jugador 1 (" +
                    juego.getJugador1().getJugador() + ")");
        else
            lblTurno.setText("Turno: Jugador 2 (" +
                    juego.getJugador2().getJugador() + ")");

        actualizarTablero(botones1,
                juego.getTablero1(),
                juego.getJugador1());

        actualizarTablero(botones2,
                juego.getTablero2(),
                juego.getJugador2());
    }
    
    private void limpiarColores() {

    for(int i=0;i<SIZE;i++){
        for(int j=0;j<SIZE;j++){

            botones1[i][j].setBackground(Color.BLACK);
            botones1[i][j].setText("");

            botones2[i][j].setBackground(Color.BLACK);
            botones2[i][j].setText("");
        }
    }
}

    private void actualizarTablero(
        JButton[][] botones,
        Barco[][] tablero,
        Player dueño){

    boolean[][] disparos =
            (dueño == juego.getJugador1())
                    ? juego.getDisparosJ2()
                    : juego.getDisparosJ1();

    for(int i=0;i<SIZE;i++){
        for(int j=0;j<SIZE;j++){

            JButton btn = botones[i][j];

            btn.setBackground(Color.BLACK);
            btn.setText("");
            btn.setForeground(Color.GREEN);


            if(disparos[i][j]){

                
                if(tablero[i][j] != null){

                    if(tablero[i][j].estaHundido()){
                        btn.setBackground(Color.RED);
                    }else{
                        btn.setBackground(Color.YELLOW);
                    }

                    btn.setText("X");
                }
                else{
                    
                    btn.setBackground(Color.BLUE);
                    btn.setText("F");
                }
            }

            
            boolean mostrarBarcos = false;

            if(juego.getModoJuego().equals("TUTORIAL")){
                mostrarBarcos = true;
            }
            else if(juego.getModoJuego().equals("ARCADE")
                    && juego.esColocacion()
                    && dueño == juego.getTurnoActual()){
                mostrarBarcos = true;
            }

            if(tablero[i][j] != null && mostrarBarcos){
                btn.setForeground(
                        dueño == juego.getTurnoActual()
                                ? Color.WHITE
                                : Color.GRAY
                );
                btn.setText(tablero[i][j].getCodigo());
            }
        }
    }
}
}