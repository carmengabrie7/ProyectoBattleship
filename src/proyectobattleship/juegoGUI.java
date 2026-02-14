package proyectobattleship;

import javax.swing.JFrame;
import javax.swing.JLabel;


public class juegoGUI extends JFrame {
    
    public juegoGUI(){
         setTitle ("Sea ⚓︎ Strike - Juego");
        setResizable(false);
        setSize(800,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel fondo = new JLabel();
        fondo.setBounds(0, 0, 800, 600);
        fondo.setLayout(null);
    }
}
