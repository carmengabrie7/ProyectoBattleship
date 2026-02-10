package proyectobattleship;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class crearPlayer extends JFrame{
    public crearPlayer(){
    setTitle ("Sea ⚓︎ Strike - Crear Player");
        setResizable(false);
        setSize(800,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel fondo = new JLabel();
        fondo.setBounds(0, 0, 800, 600);
        fondo.setLayout(null);
        
        JLabel titulo = new JLabel("CREAR PLAYER");
        titulo.setBounds(345, 110, 160, 200);
        titulo.setFont(new Font("Trebuchet MS",Font.PLAIN,35));
        titulo.setForeground(Color.white);
        fondo.add(titulo);
        
        setContentPane(fondo);
    }
    
    
}
