package proyectobattleship;

public class Barco {

    private String codigo;
    private int tamaño;
    private int impactos;

    public Barco(String codigo, int tamaño) {
        this.codigo = codigo;
        this.tamaño = tamaño;
        this.impactos = 0;
    }

    public void recibirImpacto() {
        impactos++;
    }

    public boolean estaHundido() {
        return impactos >= tamaño;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getTamaño() {
        return tamaño;
    }
}