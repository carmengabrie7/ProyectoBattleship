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
    
        setTitle("Cruel ⚓︎ Waters");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        
        ImageIcon logo = new ImageIcon("src/proyectobattleship/imagenes/logo.png"); 
        setIconImage(logo.getImage()); 
        
        JLabel fondo = new JLabel();
        fondo.setBounds(0, 0, 800, 600);
        ImageIcon fondoImg = new ImageIcon("src/proyectobattleship/imagenes/2.png");
        fondo.setIcon(new ImageIcon(fondoImg.getImage().getScaledInstance(800, 600, Image.SCALE_SMOOTH)));
        fondo.setLayout(null);
        
        JButton btnLogin = new JButton("LOGIN");
        btnLogin.setBounds(180, 440, 100, 50);
        btnLogin.setBorder(BorderFactory.createLineBorder(Color.gray,5));
        btnLogin.setBackground(new Color(37,30,36));
        btnLogin.setFont(new Font("Trebuchet MS",Font.PLAIN,15));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogin.setFocusPainted(false);

        btnLogin.addActionListener(e -> {
        InicioSesion logIn = new InicioSesion(gestion);
        logIn.setVisible(true);
        dispose();
        });
        
        JButton btnCrear = new JButton("CREAR PLAYER");
        btnCrear.setBounds(320, 440, 140, 50);
        btnCrear.setBorder(BorderFactory.createLineBorder(Color.gray,5));
        btnCrear.setBackground(new Color(37,30,36));
        btnCrear.setFont(new Font("Trebuchet MS",Font.PLAIN,15));
        btnCrear.setForeground(Color.WHITE);
        btnCrear.setFocusPainted(false);
        btnCrear.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCrear.addActionListener(e->{
            crearPlayer signUp = new crearPlayer(gestion);
            signUp.setVisible(true);
            dispose();
        });
        
        JButton btnSalir = new JButton("SALIR");
        btnSalir.setBounds(500, 440, 100, 50);
        btnSalir.setBorder(BorderFactory.createLineBorder(Color.gray,5));
        btnSalir.setBackground(new Color(37,30,36));
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setFocusPainted(false);
        btnSalir.setFont(new Font("Trebuchet MS",Font.PLAIN,15));
        btnSalir.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSalir.addActionListener(e -> System.exit(0));
        
        fondo.add(btnSalir);
        fondo.add(btnCrear);
        fondo.add(btnLogin);
        
        setContentPane(fondo);
    }

}
