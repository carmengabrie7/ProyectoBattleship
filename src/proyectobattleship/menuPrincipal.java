package proyectobattleship;

import java.awt.Cursor;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class menuPrincipal extends JFrame {
    private GestionCuentas gestion;

    public menuPrincipal(GestionCuentas gestion) {
    this.gestion = gestion;
    
        setTitle ("Sea ⚓︎ Strike - Menu Principal");
        setResizable(false);
        setSize(800,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel fondo = new JLabel();
        fondo.setBounds(0, 0, 800, 600);
        fondo.setLayout(null);
        
        JButton btnJugar = new JButton("Jugar");
        btnJugar.setBounds(500, 100, 150, 50);
        btnJugar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnJugar.setFont(new Font("Trebuchet MS",Font.PLAIN,15));
        
        JButton btnConfig = new JButton("Configuracion");
        btnConfig.setBounds(500, 180, 150, 50);
        btnConfig.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnConfig.setFont(new Font("Trebuchet MS",Font.PLAIN,15));
        
        JButton btnLogs = new JButton("Reportes");
        btnLogs.setBounds(500, 260, 150, 50);
        btnLogs.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogs.setFont(new Font("Trebuchet MS",Font.PLAIN,15));
        
        JButton btnPerfil = new JButton("Mi perfil");
        btnPerfil.setBounds(500, 340, 150, 50);
        btnPerfil.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnPerfil.setFont(new Font("Trebuchet MS",Font.PLAIN,15));
        
        JButton btnSalir = new JButton ("Salir");
        btnSalir.setBounds(500, 420, 150, 50);
        btnSalir.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSalir.setFont(new Font("Trebuchet MS",Font.PLAIN,15));
        btnSalir.addActionListener(e -> {
    gestion.logout();
    menuInicial menu = new menuInicial(gestion);
    menu.setVisible(true);
    dispose();
});
        
        fondo.add(btnJugar);
        fondo.add(btnConfig);
        fondo.add(btnLogs);
        fondo.add(btnPerfil);
        fondo.add(btnSalir);
        setContentPane(fondo);
    }

}
