package ruletadelasuerte;

import java.util.Scanner;

/**
 *
 * @author a18jaimejnq
 */
public class Jugador {

    Scanner teclado = new Scanner(System.in, "ISO-8859-1");
    private static int comodin = 0; // indica el numero de comodines
    private int dinero = 100;//Inicializado a 100 para hacer pruebas
    private String nombre;

    public Jugador(String nombre) {
        this.nombre = nombre;
       
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


    public int getComodin() {
        return comodin;
    }

    public int getDinero() {
        return dinero;
    }

    public String getNombre() {
        return nombre;
    }



    /**
     * Esta función compra una vocal al ser llamada y se cumplen con el
     * requerimiento de tener 50 dineros
     */
    public char comprarVocal() {

        boolean repetirLoop = true; //Sirve para poder repetir el bucle en caso de ser necesario
        char vocalElegida = ' ';
        if (dinero >= 50) {

            do {//Este do while se repetirá hasta que selecciones una opción corecta
                System.out.println("\nQue vocal quieres comprar?");
                vocalElegida = teclado.nextLine().charAt(0); //Vocal comprada por el usuario
                switch (vocalElegida) {//Comprueba si es una vocal lo que hemos indroducido
                    case 'a','e','i','o','u' -> {
                        System.out.println("La vocal elegida es " + vocalElegida);
                        dinero = dinero - 50;
                        repetirLoop = false;
                    }
                    default -> {
                        System.out.println("El caracter no es una vocal!\n");

                    }
                    
                }
            } while (repetirLoop == true);

        } else {
            System.out.println("Dinero insuficiente para comprar vocal");
        }
        return vocalElegida;

    }

    public char decirConsonante() {
        boolean repetirLoop = true; //Sirve para poder repetir el bucle en caso de ser necesario
        char consonanteElegida;
        do {
            System.out.println("Que consonante quieres elegir?");
            consonanteElegida = teclado.nextLine().charAt(0);
            switch (consonanteElegida) {
                case 'b','c','d','f','g','h','j','k','l','m','n','ñ','p','q','r','s','t','v','w','x','y','z':
                    System.out.println("La consonante elegida es " + consonanteElegida);
                    repetirLoop=false;
                    break;
                default:System.out.println("El valor no es una consonante (tiene que ser minúscula)");
                    
            }
        } while (repetirLoop == true);

        return consonanteElegida;

    }

//para no olvidarme en caso de que el jugador pierda turno se puede dar una metodo que permita utilizar el comodin para que no pierda, en caso de que quede en quiebra...el comodin solo se puede usar para no perder turno.
}
