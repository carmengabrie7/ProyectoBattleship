package proyectobattleship;

import javax.swing.JFrame;
import javax.swing.JLabel;


public class menuInicial extends JFrame {
    public menuInicial(){
        setTitle("Sea ⚓︎ Strike - Menu Inicial");
        setSize(600,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        
        JLabel fondo = new JLabel();
        
        
        
        
    }
    
    public static void main (String[]args){
        menuInicial menuI = new menuInicial();
        menuI.setVisible(true);
                }
}
