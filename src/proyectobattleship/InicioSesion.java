package proyectobattleship;


import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class InicioSesion extends JFrame{
    public InicioSesion (){
        setTitle ("Sea ⚓︎ Strike - Inicio Sesion");
        setResizable(false);
        setSize(600,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel fondo = new JLabel();
        fondo.setBounds(0, 0, 600, 600);
        fondo.setLayout(null);
        
        JLabel titulo = new JLabel("INICIO DE SESION");
        titulo.setBounds(220, 15, 160, 150);
        titulo.setFont(new Font("Seriff",Font.BOLD,18));
        fondo.add(titulo);
        
        JLabel usuario= new JLabel("Usuario: ");
        
        setContentPane(fondo);
    }
    
    public static void main (String[]args){
        InicioSesion login = new InicioSesion();
        login.setVisible(true);
    }
}
