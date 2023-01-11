package ruletadelasuerte;

import java.util.Scanner;

/**
 *
 * @author a18jaimejnq
 */
public class Jugador {

    Scanner teclado = new Scanner(System.in, "ISO-8859-1");
    private int comodin = 0; // indica el numero de comodines
    private int dinero = 0;
    private String nombre;
    private boolean turno; // true el juegador puede jugar o false el jugador no puede jugar

    public Jugador(String nombre, boolean turno) {
        this.nombre = nombre;
        this.turno = turno;
    }

    /**
     * esta para modificar el numero de comodines en caso de que le toque en la
     * ruleta
     *
     * @param comodin
     */
    public void setComodin(int comodin) {
        this.comodin = comodin;
    }

    public void setDinero(int dinero) {
        this.dinero = dinero;
    }

    /**
     * cambia en caso de que el jugador no acierte la consonante o la vocal o le
     * toque pierde turno
     *
     * @param turno
     */
    public void setTurno(boolean turno) {
        this.turno = turno;
    }

    public int getComodin() {
        return comodin;
    }

    public int getDinero() {
        return dinero;
    }

    public boolean isTurno() {
        return turno;
    }

    /**
     * Esta función compra una vocal al ser llamada y se cumplen con el
     * requerimiento de tener 50 dineros
     */
    public void comprarVocal() {
        boolean vocalComprada = false;//Esto por ahora no tiene función
        if (dinero > 50) {

            vocalComprada = true;//Esto por ahora no tiene función
            System.out.println("Que vocal quieres comprar?");
            char vocalElegida = teclado.nextLine().charAt(0);

            

            switch (vocalElegida) {//Comprueba si es una vocal lo que hemos indroducido
                case 'a','e','i','o','u' -> {
                    System.out.println("La vocal elegida es " + vocalElegida);
                }
                default -> 
                    System.out.println("El caracter no es una vocal!");
            }

        } else {
            System.out.println("Dinero insuficiente para comprar vocal");
        }

    }

}
