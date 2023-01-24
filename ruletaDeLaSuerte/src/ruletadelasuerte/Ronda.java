package ruletadelasuerte;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author a18jaimejnq
 */
public class Ronda {

    public static String frase = "esto es una prueba";
    public static char[] panelUsuario = new char[frase.length()]; //Panel Usuario es el panel que se le mostrará al usuario por consola
    static boolean panelcreado = false;

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

        if (!panelcreado) {
            GeneradorPanelUsuario();
            panelcreado = true;
        }

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
                    System.out.println(Ronda.girarRuleta());  
                }
                case 2 -> {
                    System.out.println("Tienes el siguiente panel resuelto");
                    mostrarPanel();
                    System.out.println("Que frase piensas que es?");
                    resolverPanel();
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

    }

    public static void comprobarConsonante(Jugador decirConson) {
        char[] abc = new char[frase.length()];
        for (int i = 0; i < frase.length(); i++) {

        }

    }

    /* Creo que con los cambios que estuve haciendo este metodo ya no es necesario, pero lo dejo comentado
    por si acaso le tienes cariñito o algo Joel :)
    
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

    /**
     * Este solo sirve para ver el panel en cada momento y hacer debug
     */
    public static void mostrarPanel() {
        System.out.println(panelUsuario);
    }

    public static boolean resolverPanel() {
        String usuarioResuelvePanel = teclado.next();
        boolean panelResuelto = false;

        if (frase.equals(usuarioResuelvePanel)) { //TODO Pedro Aqui por alguna razon no funciona esta wea, echarmle un ojo
            panelResuelto = true;
            System.out.println("HAS RESUELTO EL PANEL");
        } else {
            System.out.println("ERRASTE WEON");
        }

        return panelResuelto;
    }

    /*Segun mi logica (Y lo apunto para que no se me olvide) deberia haber un array
    panel, y la frase completa en la variable frase. El array está lleno de "_" salvo los espacios,
    así para enseñar si una vocal/consonante está dentro del panel, solo habría que bucar en "frase"
    la posicion del caracter donde se encontró y que sobre escriba el "_" que hay en la misma posición en el array
    con la letra que le corresponda.
    Si ves esto Joel, hola, si no entiendes mandame un wass 
     */
    
    public static String girarRuleta(){
        j1.setTurno(true);
       int[]rule = Ruleta.rule();
       String toret="";
        Random rnd = new Random();
        int aleatorio = rnd.nextInt(6); //genera un numero random de 0-8excluido pero con el +1 se incluye
        switch(rule[aleatorio]){
            case 1 -> {
                j1.setDinero(0);
                toret = "quiebra";
            }
            case 2 ->{
                j1.setComodin(j1.getComodin()+1);
                toret="comodin";
            }
            case 3 ->{
                j1.setTurno(false);
                toret= "pierde Turno";
            }
            case 10 ->{
                j1.setDinero(j1.getDinero()+ 10);
                toret= "10 PESOS VENEZOLANOS";
            }
            case 20 ->{
               j1.setDinero(j1.getDinero()+ 20);
                toret= "20 PESOS VENEZOLANOS";
            }
            case 50 ->{
               j1.setDinero(j1.getDinero()+ 50);
                toret= "50 PESOS VENEZOLANOS";  
            }
            case 100 ->{
                 j1.setDinero(j1.getDinero()+ 100);
                toret= "100 PESOS VENEZOLANOS";
            }
            case 200->{
                 j1.setDinero(j1.getDinero()+ 200);
                toret= "200 PESOS VENEZOLANOS";
            }
                
        }
 
     return toret;
    }
    
}
