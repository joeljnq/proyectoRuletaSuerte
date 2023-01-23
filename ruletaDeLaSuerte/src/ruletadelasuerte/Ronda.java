package ruletadelasuerte;

import java.util.Scanner;

/**
 *
 * @author a18jaimejnq
 */
public class Ronda {

    public static String frase = "esto es una prueba";
    public static char[] panelUsuario = new char[frase.length()]; //Panel Usuario es el panel que se le mostrará al usuario por consola

    public static int rondaActual = 1;
    static Scanner teclado = new Scanner(System.in, "ISO-8859-1");
    static Jugador j1 = new Jugador("Juan", true); //Nombre del jugador y si está en su turno
    static int eleccionOpcionMenu;

    public boolean finRonda() {
        return true;
    }

    static public void menuPrincipal() {
        //APERTURA DE MENÚ PRINCIPAL
        System.out.println("Bienvenido a LA RULETA DE LA SUERTE");
        System.out.println("\n              MENU\n----------------------------------\n");
        System.out.println("1.Iniciar partida");
        System.out.println("2.Salir");
        System.out.println("3.Creditos(en mantenimiento)");
        eleccionOpcionMenu = teclado.nextInt();
        //CIERRE DE MENU PRINCIPAL
        if (eleccionOpcionMenu == 1) {
            menuPartida();
        }
    }

    static public void menuPartida() {
        //Menú de partida
        GeneradorPanelUsuario();

        do {
            System.out.println("\n              MENU\n----------------------------------\n");
            System.out.println("Ronda:" + Ronda.rondaActual + "\n\nJugador:" + j1.getNombre() + "\nDinero:" + j1.getDinero() + "\n");
            System.out.println("Que acción quieres llevar a cabo?\n");
            System.out.println("1.Tirar ruleta");
            System.out.println("2.Resolver panel");//TODO Pedro Crear método resolverPanel
            System.out.println("3.Comprar vocal");
            System.out.println("4.Salir de la partida");

            eleccionOpcionMenu = teclado.nextInt();
            switch (eleccionOpcionMenu) {
                case 1 -> {
                    //EJECUTAR EL METODO TIRAR RULETA
                }
                case 2 -> {
                    //EJECUTAR METODO RESOLVER PANEL
                }
                case 3 -> {
                    System.out.println("\n\nComprando vocal...");
                    System.out.println(comprobarVocal()); //TODO Pedro por alguna razon al comprar vocal, no se guarda el resultado en PanelUsuario[]
                    menuPartida();  
                }
                case 4 -> {
                    System.out.println("Saliendo de la partida... \n\n\n\n");
                    menuPrincipal();
                }
                default ->
                    System.out.println("Elige un valor valido");
            }
        } while (eleccionOpcionMenu < 1 || eleccionOpcionMenu > 4);

        // System.out.println("------------------------------------------------------------------------------------");
    }

   
    
    public static void comprobarConsonante(Jugador decirConson) {
        char[] abc = new char[frase.length()];
        for (int i = 0; i < frase.length(); i++) {

        }

    }
/* Creo que con los cambios que estuve haciendo este metodo ya no es necesario, pero lo dejo comentado
    por si acaso le tienes cariñito o algo :)
    
    public static String frase() {
        String frase = "esto es una frase de prueba";
        return frase;
    }
     */
    
    
    /**
     *
     * @return El panel en forma de array de chars a partir de "frase" con los
     * espacios en blanco como ' ' y los demas '_'
     */
    public static char[] GeneradorPanelUsuario() {//Se debe ejecutar una sola vez para generarlo
        for (int i = 0; i < frase.length(); i++) {
            if (Character.isWhitespace(frase.charAt(i))) {
                panelUsuario[i] = ' ';
            } else {
                panelUsuario[i] = '_';
            }
        }
        return panelUsuario;
    }

    /**
     * Este metodo comprueba si la vocal está en la frase del panel
     *
     * @return El panel visible para el usuario
     */
    public static char[] comprobarVocal() {

        char vocalElegidaPorUsuario = j1.comprarVocal();

        for (int i = 0; i < frase.length(); i++) {
            if (frase.charAt(i) == vocalElegidaPorUsuario) { //Este if comprueba si la vocal está en la frase, si lo está, aplica al panel usuario esta vocal
                panelUsuario[i] = vocalElegidaPorUsuario;
            }
        }
        return panelUsuario;

    }

    /*Segun mi logica (Y lo apunto para que no se me olvide) deberia haber un array
    panel, y la frase completa en la variable frase. El array está lleno de "_" salvo los espacios,
    así para enseñar si una vocal/consonante está dentro del panel, solo habría que bucar en "frase"
    la posicion del caracter donde se encontró y que sobre escriba el "_" que hay en la misma posición en el array
    con la letra que le corresponda.
    Si ves esto Joel, hola, si no entiendes mandame un wass 
     */
}
