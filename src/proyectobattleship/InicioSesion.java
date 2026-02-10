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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class InicioSesion extends JFrame{
    public InicioSesion (){
        setTitle ("Sea ⚓︎ Strike - Log In");
        setResizable(false);
        setSize(800,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel fondo = new JLabel();
        fondo.setBounds(0, 0, 800, 600);
        ImageIcon fondoImg = new ImageIcon("src/proyectobattleship/imagenes/login2.jpg");
        fondo.setIcon(new ImageIcon(fondoImg.getImage().getScaledInstance(800, 600, Image.SCALE_SMOOTH)));
        fondo.setLayout(null);
        
        JLabel titulo = new JLabel("LOG IN");
        titulo.setBounds(345, 110, 160, 150);
        titulo.setFont(new Font("Trebuchet MS",Font.PLAIN,35));
        titulo.setForeground(Color.white);
        fondo.add(titulo);
        
        JLabel usuario= new JLabel("Usuario: ");
        usuario.setBounds(250, 180, 130, 130);
        usuario.setFont(new Font("Trebuchet MS",Font.PLAIN,20));
        usuario.setForeground(Color.white);
        fondo.add(usuario);
        
        JTextField usertxt = new JTextField();
        usertxt.setBounds(340, 235, 150, 20);
        fondo.add(usertxt);
        
        JLabel clave= new JLabel("Clave: ");
        clave.setBounds(265, 230, 130, 130);
        clave.setFont(new Font("Trebuchet MS",Font.PLAIN,20));
        clave.setForeground(Color.white);
        fondo.add(clave);
        
        JPasswordField clavetxt = new JPasswordField();
        clavetxt.setBounds(340, 285, 150, 20);
        fondo.add(clavetxt);
        
        JButton btnCancelar=new JButton ("Cancelar");
        btnCancelar.setBounds(260, 350, 100, 45);
        btnCancelar.setBorder(BorderFactory.createLineBorder(new Color(172,202,229),5));
        btnCancelar.setForeground(new Color(25,68,113));
        btnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCancelar.setFont(new Font("Trebuchet MS",Font.PLAIN,15));
        btnCancelar.addActionListener(e -> {
            
        });
        fondo.add(btnCancelar);
        
        JButton btnConfirmar=new JButton ("Confirmar");
        btnConfirmar.setBounds(420, 350, 100, 45);
        btnConfirmar.setBorder(BorderFactory.createLineBorder(new Color(172,202,229),5));
        btnConfirmar.setForeground(new Color(25,68,113));
        btnConfirmar.setFont(new Font("Trebuchet MS",Font.PLAIN,15));
        btnConfirmar.setCursor(new Cursor(Cursor.HAND_CURSOR));
         btnConfirmar.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Hora de sumerjirse en esta aventura! ");
        });
        fondo.add(btnConfirmar);
        
        JPanel jPanel1 = new JPanel();
        jPanel1.setBounds(220, 120, 350, 350);
        jPanel1.setBackground(new Color(73, 117, 158, 120)); // 120 = transparencia
        jPanel1.setBorder(BorderFactory.createLineBorder(new Color (172,202,229),5));
        jPanel1.setOpaque(true);
        fondo.add(jPanel1);
        
        setContentPane(fondo);
    }
    
    public static void main (String[]args){
        InicioSesion login = new InicioSesion();
        login.setVisible(true);
    }
}
