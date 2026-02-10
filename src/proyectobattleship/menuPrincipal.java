package proyectobattleship;

import javax.swing.JFrame;
import javax.swing.JLabel;


public class menuPrincipal extends JFrame {
    public menuPrincipal(){
        setTitle ("Sea ⚓︎ Strike - Menu Principal");
        setResizable(false);
        setSize(800,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel fondo = new JLabel();
        fondo.setBounds(0, 0, 800, 600);
        fondo.setLayout(null);
    }
}
