package proyectobattleship;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class menuInicial extends JFrame {
    private GestionCuentas gestion;

    public menuInicial(GestionCuentas gestion) {
    this.gestion = gestion;
    
        setTitle("Sea ⚓︎ Strike");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        
        ImageIcon logo = new ImageIcon("src/proyectobattleship/imagenes/logo.png"); 
        setIconImage(logo.getImage()); 
        
        JLabel fondo = new JLabel();
        fondo.setBounds(0, 0, 800, 600);
        ImageIcon fondoImg = new ImageIcon("src/proyectobattleship/imagenes/fondo.png");
        fondo.setIcon(new ImageIcon(fondoImg.getImage().getScaledInstance(800, 600, Image.SCALE_SMOOTH)));
        fondo.setLayout(null);
        
        JLabel titulo = new JLabel();
        ImageIcon iconoOriginal = new ImageIcon(getClass().getResource("/proyectobattleship/imagenes/titulo.png"));
        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(400, 350, Image.SCALE_SMOOTH);

        ImageIcon icono = new ImageIcon(imagenEscalada);

        titulo.setIcon(icono);
        titulo.setBounds(200, 50, 400, 350);


        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(180, 400, 100, 50);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnLogin.addActionListener(e -> {
        InicioSesion logIn = new InicioSesion(gestion);
        logIn.setVisible(true);
        dispose();
        });
        
        JButton btnCrear = new JButton("Crear Player");
        btnCrear.setBounds(320, 400, 140, 50);
        btnCrear.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCrear.addActionListener(e->{
            crearPlayer signUp = new crearPlayer(gestion);
            signUp.setVisible(true);
            dispose();
        });
        
        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(500, 400, 100, 50);
        btnSalir.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSalir.addActionListener(e -> System.exit(0));
        
        fondo.add(titulo);
        fondo.add(btnSalir);
        fondo.add(btnCrear);
        fondo.add(btnLogin);
        
        setContentPane(fondo);
    }

}
