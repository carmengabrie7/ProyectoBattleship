package proyectobattleship;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class menuPrincipal extends JFrame {

    private GestionCuentas gestion;
    private CardLayout cardLayout;
    private JPanel contenedor;
    private JLabel lblPuntosPerfil;

    public menuPrincipal(GestionCuentas gestion) {
        this.gestion = gestion;

        setTitle("Sea ⚓︎ Strike - Menu Principal");
        setResizable(false);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        contenedor = new JPanel(cardLayout);

        contenedor.add(crearPanelMenu(), "menu");
        contenedor.add(crearPanelPerfilMenu(), "perfilMenu");
        contenedor.add(crearPanelPerfilVer(), "perfilVer");
        contenedor.add(crearPanelConfigMenu(), "configMenu");
        contenedor.add(crearPanelDificultad(), "dificultad");
        contenedor.add(crearPanelModoJuego(), "modoJuego");
        contenedor.add(crearPanelReportesMenu(), "reportesMenu");
        contenedor.add(crearPanelUltimosJuegos(), "ultimosJuegos");
        contenedor.add(crearPanelRanking(), "ranking");

        add(contenedor);
    }


    private JLabel crearPanelMenu() {

        JLabel panel = new JLabel(new ImageIcon("src//proyectobattleship/imagenes/4.png"));
        panel.setLayout(null);

        JButton btnJugar = new JButton("Jugar");
        btnJugar.setBounds(500, 100, 150, 50);
        btnJugar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnJugar.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
        btnJugar.addActionListener(e -> {

    if (gestion.getCuentas().size() < 2) {
        JOptionPane.showMessageDialog(this,
                "Se necesitan al menos 2 jugadores registrados.");
        return;
    }

    Player jugador1 = gestion.getCurrentUser();


    java.util.ArrayList<Player> cuentas =
            new java.util.ArrayList<>(gestion.getCuentas());

    cuentas.remove(jugador1);

    String[] opciones = new String[cuentas.size()];

    for (int i = 0; i < cuentas.size(); i++) {
        opciones[i] = cuentas.get(i).getUsuario();
    }

    String seleccionado = (String) JOptionPane.showInputDialog(
            this,
            "Selecciona el oponente:",
            "Elegir Oponente",
            JOptionPane.QUESTION_MESSAGE,
            null,
            opciones,
            opciones[0]
    );


    if (seleccionado == null) return;

    Player jugador2 = null;

    for (Player p : cuentas) {
        if (p.getUsuario().equals(seleccionado)) {
            jugador2 = p;
            break;
        }
    }


    Battleship juego = new Battleship(
            jugador1,
            jugador2,
            gestion.getDificultad(),
            gestion.getModoJuego()
    );

    new juegoGUI(juego, gestion).setVisible(true);
});

        JButton btnConfig = new JButton("Configuracion");
        btnConfig.setBounds(500, 180, 150, 50);
        btnConfig.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnConfig.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
        btnConfig.addActionListener(e -> {
    cardLayout.show(contenedor, "configMenu");
});
        JButton btnReportes = new JButton("Reportes");
        btnReportes.setBounds(500, 260, 150, 50);
        btnReportes.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnReportes.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
        btnReportes.addActionListener(e -> {
    cardLayout.show(contenedor, "reportesMenu");
});

        JButton btnPerfil = new JButton("Mi perfil");
        btnPerfil.setBounds(500, 340, 150, 50);
        btnPerfil.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnPerfil.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
        btnPerfil.addActionListener(e -> {
    cardLayout.show(contenedor, "perfilMenu");
});

        JButton btnSalir = new JButton("Cerrar Sesion");
        btnSalir.setBounds(500, 420, 150, 50);
        btnSalir.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSalir.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
        btnSalir.addActionListener(e -> {
            gestion.logout();
            new menuInicial(gestion).setVisible(true);
            dispose();
        });

        panel.add(btnJugar);
        panel.add(btnConfig);
        panel.add(btnReportes);
        panel.add(btnPerfil);
        panel.add(btnSalir);

        return panel;
    }

    private JLabel crearPanelPerfilMenu() {

    JLabel panel = new JLabel(new ImageIcon("src//proyectobattleship/imagenes/fondoP.png"));
    panel.setLayout(null);
    
    JLabel titulo = new JLabel("MI PERFIL");
    titulo.setBounds(330, 100, 200, 30);

    JButton btnVer = new JButton("Ver Mis Datos");
    btnVer.setBounds(300, 170, 200, 40);
    btnVer.addActionListener(e -> {
        cardLayout.show(contenedor, "perfilVer");
    });

    JButton btnEliminar = new JButton("Eliminar Cuenta");
    btnEliminar.setBounds(300, 230, 200, 40);
    btnEliminar.addActionListener(e -> {

        int opcion = JOptionPane.showConfirmDialog(
                null,
                "¿Seguro que deseas eliminar tu cuenta?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION
        );

        if (opcion == JOptionPane.YES_OPTION) {
            gestion.eliminarCuenta();
            new menuInicial(gestion).setVisible(true);
            dispose();
        }
    });

    JButton btnVolver = new JButton("Regresar");
    btnVolver.setBounds(300, 290, 200, 40);
    btnVolver.addActionListener(e -> {
        cardLayout.show(contenedor, "menu");
    });

    panel.add(titulo);
    panel.add(btnVer);
    panel.add(btnEliminar);
    panel.add(btnVolver);

    return panel;
}
    
   private JLabel crearPanelPerfilVer() {

    JLabel panel = new JLabel(new ImageIcon("src//proyectobattleship/imagenes/fondoP.png"));
    panel.setLayout(null);
    
    Player actual = gestion.getCurrentUser();

    JLabel lblNombre = new JLabel("Nombre:");
    lblNombre.setBounds(200, 60, 100, 30);

    JTextField txtNombre = new JTextField(actual.getJugador());
    txtNombre.setBounds(300, 60, 150, 30);
    txtNombre.setEnabled(false);

    JLabel lblUsuario = new JLabel("Usuario:");
    lblUsuario.setBounds(200, 100, 100, 30);

    JTextField txtUsuario = new JTextField(actual.getUsuario());
    txtUsuario.setBounds(300, 100, 150, 30);
    txtUsuario.setEnabled(false);

    JLabel lblClave = new JLabel("Clave:");
    lblClave.setBounds(200, 140, 100, 30);

    JPasswordField txtClave = new JPasswordField(actual.getClave());
    txtClave.setBounds(300, 140, 150, 30);
    txtClave.setEnabled(false);


    lblPuntosPerfil = new JLabel();
    lblPuntosPerfil.setBounds(200, 180, 200, 30);
    actualizarPerfil();

    JLabel lblImagen = new JLabel();
    lblImagen.setBounds(200, 220, 150, 150);
    lblImagen.setBorder(BorderFactory.createLineBorder(Color.GRAY));

    if (actual.getRutaImagen() != null) {
        ImageIcon icon = new ImageIcon(actual.getRutaImagen());
        Image img = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        lblImagen.setIcon(new ImageIcon(img));
    }

    JButton btnImagen = new JButton("Elegir Imagen");
    btnImagen.setBounds(370, 260, 150, 40);
    btnImagen.setVisible(false);

    JButton btnModificar = new JButton("Modificar");
    btnModificar.setBounds(200, 400, 120, 40);

    JButton btnGuardar = new JButton("Guardar");
    btnGuardar.setBounds(340, 400, 120, 40);
    btnGuardar.setVisible(false);

    btnModificar.addActionListener(e -> {
        txtNombre.setEnabled(true);
        txtUsuario.setEnabled(true);
        txtClave.setEnabled(true);
        btnImagen.setVisible(true);
        btnGuardar.setVisible(true);
    });

    btnImagen.addActionListener(e -> {
        JFileChooser fileChooser = new JFileChooser();
        int resultado = fileChooser.showOpenDialog(null);

        if (resultado == JFileChooser.APPROVE_OPTION) {
            String ruta = fileChooser.getSelectedFile().getAbsolutePath();
            actual.setRutaImagen(ruta);

            ImageIcon icon = new ImageIcon(ruta);
            Image img = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            lblImagen.setIcon(new ImageIcon(img));
        }
    });

    btnGuardar.addActionListener(e -> {

        String nuevoNombre = txtNombre.getText().trim();
        String nuevoUsuario = txtUsuario.getText().trim();
        String nuevaClave = new String(txtClave.getPassword());

        if (nuevoNombre.isEmpty() || nuevoUsuario.isEmpty() || nuevaClave.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Campos vacíos");
            return;
        }

        if (nuevaClave.length() < 5) {
            JOptionPane.showMessageDialog(null, "La clave debe tener al menos 5 caracteres");
            return;
        }

        if (!nuevoUsuario.equalsIgnoreCase(actual.getUsuario())
                && gestion.usuarioExiste(nuevoUsuario)) {

            JOptionPane.showMessageDialog(null, "Ese usuario ya existe");
            return;
        }

        actual.setJugador(nuevoNombre);
        actual.setUsuario(nuevoUsuario);
        actual.setClave(nuevaClave);

        txtNombre.setEnabled(false);
        txtUsuario.setEnabled(false);
        txtClave.setEnabled(false);
        btnImagen.setVisible(false);
        btnGuardar.setVisible(false);

        JOptionPane.showMessageDialog(null, "Datos actualizados");
    });

    JButton btnVolver = new JButton("Volver");
    btnVolver.setBounds(480, 400, 120, 40);
    btnVolver.addActionListener(e -> {
        actualizarPerfil(); 
        cardLayout.show(contenedor, "perfilMenu");
    });

    panel.add(lblNombre);
    panel.add(txtNombre);
    panel.add(lblUsuario);
    panel.add(txtUsuario);
    panel.add(lblClave);
    panel.add(txtClave);
    panel.add(lblPuntosPerfil);
    panel.add(lblImagen);
    panel.add(btnImagen);
    panel.add(btnModificar);
    panel.add(btnGuardar);
    panel.add(btnVolver);

    return panel;
}
   private void actualizarPerfil() {

    Player actual = gestion.getCurrentUser();

    if (lblPuntosPerfil != null) {
        lblPuntosPerfil.setText("Puntos: " + actual.getPuntos());
    }
}
   
   private JLabel crearPanelConfigMenu() {

    JLabel panel = new JLabel(new ImageIcon("src//proyectobattleship/imagenes/fondoP.png"));
    panel.setLayout(null);
    

    JLabel titulo = new JLabel("CONFIGURACIÓN");
    titulo.setBounds(310, 100, 200, 30);

    JButton btnDificultad = new JButton("Dificultad");
    btnDificultad.setBounds(300, 170, 200, 40);
    btnDificultad.addActionListener(e ->
            cardLayout.show(contenedor, "dificultad")
    );

    JButton btnModo = new JButton("Modo de Juego");
    btnModo.setBounds(300, 230, 200, 40);
    btnModo.addActionListener(e ->
            cardLayout.show(contenedor, "modoJuego")
    );

    JButton btnVolver = new JButton("Regresar");
    btnVolver.setBounds(300, 290, 200, 40);
    btnVolver.addActionListener(e ->
            cardLayout.show(contenedor, "menu")
    );

    panel.add(titulo);
    panel.add(btnDificultad);
    panel.add(btnModo);
    panel.add(btnVolver);

    return panel;
}
   
   private JLabel crearPanelDificultad() {

    JLabel panel = new JLabel(new ImageIcon("src//proyectobattleship/imagenes/fondoP.png"));
    panel.setLayout(null);
    

    JLabel titulo = new JLabel("Seleccionar Dificultad");
    titulo.setBounds(280, 80, 250, 30);

    JLabel actual = new JLabel("Actual: " + gestion.getDificultad()
            + " (" + gestion.getCantidadBarcos() + " barcos)");
    actual.setBounds(250, 120, 300, 30);

    String[] opciones = {"EASY", "NORMAL", "EXPERT", "GENIUS"};

    JComboBox<String> combo = new JComboBox<>(opciones);
    combo.setBounds(300, 170, 200, 40);
    combo.setSelectedItem(gestion.getDificultad());

    JButton btnGuardar = new JButton("Guardar");
    btnGuardar.setBounds(300, 230, 200, 40);

    btnGuardar.addActionListener(e -> {

        String seleccion = (String) combo.getSelectedItem();
        gestion.setDificultad(seleccion);

        JOptionPane.showMessageDialog(null,
                "Dificultad cambiada a " + seleccion);

        cardLayout.show(contenedor, "configMenu");
    });

    panel.add(titulo);
    panel.add(actual);
    panel.add(combo);
    panel.add(btnGuardar);

    return panel;
}
   
   private JLabel crearPanelModoJuego() {

    JLabel panel = new JLabel(new ImageIcon("src//proyectobattleship/imagenes/fondoP.png"));
    panel.setLayout(null);

    JLabel titulo = new JLabel("Seleccionar Modo de Juego");
    titulo.setBounds(260, 80, 300, 30);

    JLabel actual = new JLabel("Actual: " + gestion.getModoJuego());
    actual.setBounds(300, 120, 200, 30);

    String[] opciones = {"TUTORIAL", "ARCADE"};

    JComboBox<String> combo = new JComboBox<>(opciones);
    combo.setBounds(300, 170, 200, 40);
    combo.setSelectedItem(gestion.getModoJuego());

    JButton btnGuardar = new JButton("Guardar");
    btnGuardar.setBounds(300, 230, 200, 40);

    btnGuardar.addActionListener(e -> {

        String seleccion = (String) combo.getSelectedItem();
        gestion.setModoJuego(seleccion);

        JOptionPane.showMessageDialog(null,
                "Modo cambiado a " + seleccion);

        cardLayout.show(contenedor, "configMenu");
    });

    panel.add(titulo);
    panel.add(actual);
    panel.add(combo);
    panel.add(btnGuardar);

    return panel;
}

   private JLabel crearPanelReportesMenu() {

    JLabel panel = new JLabel(new ImageIcon("src//proyectobattleship/imagenes/fondoP.png"));
    panel.setLayout(null);
    

    JLabel titulo = new JLabel("REPORTES");
    titulo.setBounds(330, 100, 200, 30);

    JButton btnUltimos = new JButton("Últimos 10 Juegos");
    btnUltimos.setBounds(300, 170, 200, 40);
    btnUltimos.addActionListener(e -> {
        cardLayout.show(contenedor, "ultimosJuegos");
    });

    JButton btnRanking = new JButton("Ranking de Jugadores");
    btnRanking.setBounds(300, 230, 200, 40);
    btnRanking.addActionListener(e -> {
        cardLayout.show(contenedor, "ranking");
    });

    JButton btnVolver = new JButton("Regresar");
    btnVolver.setBounds(300, 290, 200, 40);
    btnVolver.addActionListener(e -> {
        cardLayout.show(contenedor, "menu");
    });

    panel.add(titulo);
    panel.add(btnUltimos);
    panel.add(btnRanking);
    panel.add(btnVolver);

    return panel;
}
   
  private JLabel crearPanelUltimosJuegos() {

    JLabel panel = new JLabel(new ImageIcon("src//proyectobattleship/imagenes/fondoP.png"));
    panel.setLayout(null);

    JLabel titulo = new JLabel("Mis Últimos 10 Juegos");
    titulo.setBounds(280, 40, 300, 30);
    panel.add(titulo);

    Player actual = gestion.getCurrentUser();
    String[] logs = actual.getLogs();

    String[] columnas = {"Descripción"};

    Object[][] datos = new Object[10][1];

    for (int i = 0; i < 10; i++) {

        datos[i][0] = (logs[i] == null)
                ? "---"
                : logs[i];
    }

    JTable tabla = new JTable(datos, columnas);
    tabla.setEnabled(false);

    JScrollPane scroll = new JScrollPane(tabla);
    scroll.setBounds(120, 90, 560, 300);

    JButton btnVolver = new JButton("Volver");
    btnVolver.setBounds(330, 420, 120, 40);
    btnVolver.addActionListener(e ->
            cardLayout.show(contenedor, "reportesMenu")
    );

    panel.add(scroll);
    panel.add(btnVolver);

    return panel;
}
   
  private JLabel crearPanelRanking() {

    JLabel panel = new JLabel(new ImageIcon("src//proyectobattleship/imagenes/fondoP.png"));
    panel.setLayout(null);
    

    JLabel titulo = new JLabel("Ranking de Jugadores");
    titulo.setBounds(300, 40, 300, 30);
    panel.add(titulo);

    java.util.ArrayList<Player> lista =
            new java.util.ArrayList<>(gestion.getCuentas());

    // Ordenar de mayor a menor puntos
    lista.sort((a, b) -> Integer.compare(b.getPuntos(), a.getPuntos()));

    String[] columnas = {"Posición", "Nombre", "Usuario", "Puntos"};

    Object[][] datos = new Object[lista.size()][4];

    for (int i = 0; i < lista.size(); i++) {
        Player p = lista.get(i);
        datos[i][0] = i + 1;
        datos[i][1] = p.getJugador();
        datos[i][2] = p.getUsuario();
        datos[i][3] = p.getPuntos();
    }

    JTable tabla = new JTable(datos, columnas);
    tabla.setEnabled(false);

    JScrollPane scroll = new JScrollPane(tabla);
    scroll.setBounds(80, 90, 640, 300);

    JButton btnVolver = new JButton("Volver");
    btnVolver.setBounds(330, 420, 120, 40);
    btnVolver.addActionListener(e ->
            cardLayout.show(contenedor, "reportesMenu")
    );

    panel.add(scroll);
    panel.add(btnVolver);

    return panel;
}
   
}