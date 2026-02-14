package proyectobattleship;
//Sea ⚓︎ Strike

public class ProyectoMain {

   
    public static void main(String[] args) {
        GestionCuentas gestion = new GestionCuentas();
        menuInicial menu = new menuInicial(gestion);
        menu.setVisible(true);
    }
    
}
