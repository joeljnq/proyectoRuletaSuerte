package ruletadelasuerte;

import java.util.Scanner;

/**
 *
 * @author a18jaimejnq
 */
public class Jugador {

    Scanner teclado = new Scanner(System.in, "ISO-8859-1");
    private int comodin = 0; // indica el numero de comodines
    private int dinero = 100;//Inicializado a 100 para hacer pruebas
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

    public String getNombre() {
        return nombre;
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
        boolean repetirLoop=false; //Sirve para poder repetir el bucle en caso de ser necesario
        if (dinero > 50) {
            do {//Este do while se repetirá hasta que selecciones una opción corecta
                vocalComprada = true;//Esto por ahora no tiene función
                System.out.println("Que vocal quieres comprar?");
                char vocalElegida = teclado.nextLine().charAt(0); //Vocal comprada por el usuario
                switch (vocalElegida) {//Comprueba si es una vocal lo que hemos indroducido
                    case 'a','e','i','o','u' -> {
                        System.out.println("La vocal elegida es " + vocalElegida);
                        dinero = dinero - 50;
                        repetirLoop=false;
                    }
                    default -> {
                        System.out.println("El caracter no es una vocal!\n");
                        repetirLoop = true;
                    }
                    //TODO Pedro 11/01 Falta comprobar si la vocal que compró el jugador es realmente correcta
                }
            } while (repetirLoop == true);

        } else {
            System.out.println("Dinero insuficiente para comprar vocal");
        }

    }

}
