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
import javax.swing.JTextField;


public class crearPlayer extends JFrame{
    private GestionCuentas gestion;

    public crearPlayer(GestionCuentas gestion) {
    this.gestion = gestion;
    setTitle ("Sea ⚓︎ Strike");
        setResizable(false);
        setSize(800,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel fondo = new JLabel();
        fondo.setBounds(0, 0, 800, 600);
        ImageIcon fondoImg = new ImageIcon("src/proyectobattleship/imagenes/createPlayer.png");
        fondo.setIcon(new ImageIcon(fondoImg.getImage().getScaledInstance(800, 600, Image.SCALE_SMOOTH)));
        fondo.setLayout(null);
        
        
        JLabel nombre = new JLabel("Nombre:");
        nombre.setBounds(247, 150, 250, 200);
        nombre.setFont(new Font("Trebuchet MS",Font.PLAIN,20));
        nombre.setForeground(Color.white);
        
        JTextField nombretxt = new JTextField();
        nombretxt.setBounds(330, 240, 150, 20);
        
        JLabel usuario = new JLabel ("Usuario:");
        usuario.setBounds(250, 230, 130, 130);
        usuario.setFont(new Font("Trebuchet MS",Font.PLAIN,20));
        usuario.setForeground(Color.white);
        
        JTextField usertxt = new JTextField();
        usertxt.setBounds(330, 286, 150, 20);
        
        JLabel clave = new JLabel ("Clave:");
        clave.setBounds(265, 280, 130, 130);
        clave.setFont(new Font("Trebuchet MS",Font.PLAIN,20));
        clave.setForeground(Color.white);
        
        JTextField clavetxt = new JTextField();
        clavetxt.setBounds(330, 336, 150, 20);   
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(260, 390, 100, 45);
        btnCancelar.setBorder(BorderFactory.createLineBorder(new Color(172,202,229),5));
        btnCancelar.setForeground(new Color(25,68,113));
        btnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCancelar.setFont(new Font("Trebuchet MS",Font.PLAIN,15));
        btnCancelar.addActionListener(e->{
            new menuInicial(gestion).setVisible(true);
            dispose();
        });
        
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(420, 390, 100, 45);
        btnGuardar.setBorder(BorderFactory.createLineBorder(new Color(172,202,229),5));
        btnGuardar.setForeground(new Color(25,68,113));
        btnGuardar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnGuardar.setFont(new Font("Trebuchet MS",Font.PLAIN,15));
        btnGuardar.addActionListener(e -> {

    String nombre1 = nombretxt.getText();
    String usuario1 = usertxt.getText();
    String clave1 = clavetxt.getText();

    boolean creado = gestion.crearCuenta(nombre1, usuario1, clave1);

    if (creado) {

        gestion.login(usuario1, clave1);

        JOptionPane.showMessageDialog(null, "Cuenta creada");

        menuPrincipal menu = new menuPrincipal(gestion);
        menu.setVisible(true);
        dispose();

    } else {
        JOptionPane.showMessageDialog(null, "Error en datos");
    }
});
        
        
        fondo.add(nombre);
        fondo.add(nombretxt);
        fondo.add(usuario);
        fondo.add(usertxt);
        fondo.add(clave);
        fondo.add(clavetxt);
        fondo.add(btnGuardar);
        fondo.add(btnCancelar);
        
        JPanel jPanel1 = new JPanel();
        jPanel1.setBounds(220, 210, 360, 250);
        jPanel1.setBackground(new Color(73, 117, 158, 120)); // 120 = transparencia
        jPanel1.setBorder(BorderFactory.createLineBorder(new Color (172,202,229),5));
        jPanel1.setOpaque(true);
        fondo.add(jPanel1);
        
        setContentPane(fondo);
    }
    

    
}
