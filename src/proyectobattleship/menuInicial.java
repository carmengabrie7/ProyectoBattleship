package proyectobattleship;

import java.awt.Cursor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class menuInicial extends JFrame {
    public menuInicial(){
        setTitle("Sea ⚓︎ Strike - Menu Inicial");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        
        JLabel fondo = new JLabel();
        fondo.setBounds(0, 0, 800, 600);
        fondo.setLayout(null);
        
        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(180, 400, 100, 50);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogin.addActionListener(e-> {
            InicioSesion logIn = new InicioSesion();
            logIn.setVisible(true);
            this.dispose();
        });
        
        JButton btnCrear = new JButton("Crear Player");
        btnCrear.setBounds(320, 400, 140, 50);
        btnCrear.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCrear.addActionListener(e->{
            crearPlayer signUp = new crearPlayer();
            signUp.setVisible(true);
            this.dispose();
        });
        
        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(500, 400, 100, 50);
        btnSalir.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSalir.addActionListener(e -> System.exit(0));
        
        fondo.add(btnSalir);
        fondo.add(btnCrear);
        fondo.add(btnLogin);
        
        setContentPane(fondo);
    }
    
    public static void main (String[]args){
        menuInicial menuI = new menuInicial();
        menuI.setVisible(true);
                }
}
