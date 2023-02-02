package ruletadelasuerte;

import java.util.Random;
import java.util.Scanner;
import javax.print.attribute.standard.Finishings;

/**
 *
 * @author a18jaimejnq
 */
public class Ronda {


    /*
                TODO LIST MÍNIMOS
            -Hay que reparar el método Ronda.usarComodin() !!(por si acaso comprobar)
            -Acabar rondas,llevar un conteo de ellas y acabar la partida cuando se llega al limite.
            -Cambiar el orden del dinero a: primero acertar la consonante y despúes añadir los pesos venezolanos.
    
                TODO EXTRAS
            -Reorganizar ruleta(Igual su existencia es innecesaria?)
            -Reorganizar menú para que no quede tan chueco al usar comprarVocal()
            -Poner una opción para los creditos en la que se digan los creadores, página de github y demás
            -Revisar las variables qu eestán en static 
            -Añadir la posibilidad de poner cantidad y nombre a los jugadores de la partida
            -Revisar comentarios y borrar o crear los necesarios para el entendimiento del código en un futuro
            -Hacer array de consonantes comprobarConsonante en la clase jugador
            -Revisar el ambito de las clases (si deberian ser privadas/publicas...)
 
     */
    public static String frase = "esto es una prueba";
    public static char[] panelUsuario; //Panel Usuario es el panel que se le mostrará al usuario por consola
    static boolean panelcreado = false;

    static int turno = 0;

    public static int rondaActual = 1;
    static Scanner teclado = new Scanner(System.in, "ISO-8859-1");
    static Jugador[] jugadores = {new Jugador("pedro"), new Jugador("joel")};
    static int eleccionTrasGirarRuleta;
    static int eleccionOpcionMenu;

    static public void finTurno() {
        if (turno -1 >= jugadores.length) {
            turno = 0;
        } else {
            ++turno;
        }
        menuPartida(jugadores[turno].getNombre(), jugadores[turno].getDinero());
    }

    public static String crearFrase() {
        int selectorFrase = 0;
        Random rnd = new Random();
        selectorFrase = rnd.nextInt(4);
        switch (selectorFrase) {
            case 0:
                frase = "La paciencia es una virtud";
                break;
            case 1:
                frase = "No hay mal que por bien no venga";
                break;
            case 2:
                frase = "El tiempo cura todas las heridas";
                break;
            case 3:
                frase = "La prisa es la enemiga de la perfección";
                break;

        }
        return frase;
    }

    static public void menuPrincipal() {
        resetearDatos();
        //APERTURA DE MENÚ PRINCIPAL
        System.out.println("Bienvenido a LA RULETA DE LA SUERTE");
        System.out.println("\n              MENU\n----------------------------------\n");
        System.out.println("1.Iniciar partida");
        System.out.println("2.Salir");
        System.out.println("3.Creditos(en mantenimiento)");
        eleccionOpcionMenu = teclado.nextInt();
        //CIERRE DE MENU PRINCIPAL
        if (eleccionOpcionMenu == 1) {
            menuPartida(jugadores[turno].getNombre(), jugadores[turno].getDinero());
        }
    }

    static public void menuPartida(String nome, int dinero) {

        //Menú de partida
        if (!panelcreado) {
            crearFrase();
            panelUsuario = new char[frase.length()];
            GeneradorPanelUsuario();
            panelcreado = true;
        }

        do {
            System.out.println("\n              MENU\n----------------------------------\n");
            System.out.println("Ronda:" + Ronda.rondaActual + "\n\nJugador:" + nome + "\nDinero:" + dinero + "\n");
            System.out.println("Que acción quieres llevar a cabo?\n");
            System.out.println("1.Tirar ruleta");
            System.out.println("2.Resolver panel");//TODO Pedro Crear método resolverPanel
            System.out.println("3.Comprar vocal");
            System.out.println("4.Salir de la partida");
            System.out.println("\n \t PANEL ACTUAL: ");
            System.out.print("\t");
            mostrarPanel();
            System.out.println("");

            eleccionOpcionMenu = teclado.nextInt();
            switch (eleccionOpcionMenu) {
                case 1 -> {
                    //EJECUTAR EL METODO TIRAR RULETA
                    Ronda.girarRuleta();
                    menuTrasGirarRuleta(); //Poner un if para que no se haga en caso de 

                    //  menuPartida(); Eliminamos el menu partida por un submenu con las opciones que deben estar
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
                    // menuPartida(); Eliminamos el menu partida por un submenu con las opciones que deben estar
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

    /**
     * Este metodo se utiliza para girar la ruleta mediante un numero aleatorio
     * que sera un posición de la string rule.
     *
     * @return
     */
    public static void girarRuleta() {
        boolean comodinUsado = true;
        int[] rule = Ruleta.rule();
        String toret = "";
        Random rnd = new Random();
        int aleatorio = rnd.nextInt(10); //genera un numero random 

        switch (rule[aleatorio]) {
            case 1 -> {
                jugadores[turno].setDinero(0);
                toret = "quiebra";
                System.out.println(toret);
                Ronda.usarComodin();//Hay que reparar ese metodo!!

                //Este if sería lo que hipoteticamente habría que hacer cuando se repare el usarComodín()
                if (comodinUsado == true) {
                    jugadores[turno].setComodin(jugadores[turno].getComodin() - 1);
                    menuTrasGirarRuleta();
                } else {
                    finTurno();
                }

            }
            case 2 -> {
                for (int i = 0; i < frase.length(); i++) {
                    if (comprobarConsonante() == frase.charAt(i)) {
                        jugadores[turno].setComodin(jugadores[turno].getComodin() + 1);
                    }
                }
                toret = "comodin"; //En este caso tiene q saltar el menú post girar ruleta o acaba turno?
                System.out.println(toret);
            }
            case 3 -> {
                toret = "pierde Turno";
                System.out.println(toret);

                finTurno();

            }
            case 10 -> {
                for (int i = 0; i < frase.length(); i++) {
                    if (comprobarConsonante() == frase.charAt(i)) {
                        jugadores[turno].setDinero(jugadores[turno].getDinero() + 10);
                    }
                }

                toret = "10 PESOS VENEZOLANOS";
                System.out.println(toret);
            }
            case 20 -> {

                for (int i = 0; i < frase.length(); i++) {
                    if (comprobarConsonante() == frase.charAt(i)) {
                        jugadores[turno].setDinero(jugadores[turno].getDinero() + 20);
                    }
                }
                toret = "20 PESOS VENEZOLANOS";
                System.out.println(toret);
            }
            case 50 -> {
                for (int i = 0; i < frase.length(); i++) {
                    if (comprobarConsonante() == frase.charAt(i)) {
                        jugadores[turno].setDinero(jugadores[turno].getDinero() + 50);
                    }
                }
                toret = "50 PESOS VENEZOLANOS";
                System.out.println(toret);
            }
            case 100 -> {

                for (int i = 0; i < frase.length(); i++) {
                    if (comprobarConsonante() == frase.charAt(i)) {
                        jugadores[turno].setDinero(jugadores[turno].getDinero() + 100);
                    }
                }

                toret = "100 PESOS VENEZOLANOS";
                System.out.println(toret);
            }
            case 200 -> {
                for (int i = 0; i < frase.length(); i++) {
                    if (comprobarConsonante() == frase.charAt(i)) {
                        jugadores[turno].setDinero(jugadores[turno].getDinero() + 200);
                    }
                }

                toret = "200 PESOS VENEZOLANOS";
                System.out.println(toret);
            }

        }

    }

    public static void menuTrasGirarRuleta() { //Giras, te toca dinero, tienes que decir consonante, si el consonante está en el panel aparecerán las opciones comprar vocal o resolver panel, en caso de que no esté piernes turno

        comprobarConsonante();
        System.out.println("Despues de comprobar consonante");

        System.out.println("Elige ");
        System.out.println("\n              MENU\n----------------------------------\n");
        System.out.println("Ronda:" + Ronda.rondaActual + "\n\nJugador:" + jugadores[turno].getNombre() + "\nDinero:" + jugadores[turno].getDinero() + "\n");
        System.out.println("Que acción quieres llevar a cabo?\n");
        System.out.println("1.Resolver panel");
        System.out.println("2.Comprar vocal");
        System.out.println("3.Girar Ruleta");
        System.out.println("4.Salir de la partida");
        System.out.println("\n \t PANEL ACTUAL: ");
        System.out.print("\t");
        mostrarPanel();
        System.out.println("");

        eleccionTrasGirarRuleta = teclado.nextInt();
        switch (eleccionTrasGirarRuleta) {
            case 1 -> {
                System.out.println("Tienes el siguiente panel resuelto");
                mostrarPanel();
                System.out.println("Que frase piensas que es?");
                resolverPanel();
            }
            case 2 -> {
                System.out.println("\n\nComprando vocal...");
                System.out.println(comprobarVocal()); //TODO Pedro por alguna razon al comprar vocal, no se guarda el resultado en PanelUsuario[]
                menuPartida(jugadores[turno].getNombre(), jugadores[turno].getDinero());
            }
            case 3 -> {
                System.out.println("Girando Ruleta...");
                girarRuleta();
                menuTrasGirarRuleta();

            }
            case 4 -> {
                System.out.println("Saliendo de la partida... \n\n\n\n");
                menuPrincipal();
            }
            default ->
                System.out.println("Elige un valor valido");
        }
    }

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

        char vocalElegidaPorUsuario = jugadores[turno].comprarVocal();

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
        System.out.print(panelUsuario);
    }

    public static boolean resolverPanel() {
        String usuarioResuelvePanel = teclado.next();
        boolean panelResuelto = false;

        if (frase.equals(usuarioResuelvePanel)) { //TODO Pedro Aqui por alguna razon no funciona esta wea, echarmle un ojo
            panelResuelto = true;
            System.out.println("HAS RESUELTO EL PANEL");
            //Hipoteticamente aqui deberia ir un siguiente Ronda
        } else {
            System.out.println("ERRASTE WEON");
            finTurno();
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
    public static char comprobarConsonante() { //Este metodo pide la consonante y la comprueba a la vez
        char consonanteElegidaPorUsuario = jugadores[turno].decirConsonante();

        for (int i = 0; i < frase.length(); i++) {
            if (frase.charAt(i) == consonanteElegidaPorUsuario) { //Este if comprueba si la consonante está en la frase, si lo está, aplica al panel usuario esta vocal
                panelUsuario[i] = consonanteElegidaPorUsuario;
            }
        }
        return consonanteElegidaPorUsuario;
    }

    /**
     * Usar comoodin en caso de que caigas en quiebra o en pierde turno...
     *
     * @return
     */
    public static String usarComodin() {
        teclado.nextLine();
        if (jugadores[turno].getComodin() > 0) {
            System.out.println("tienes " + jugadores[turno].getComodin() + " comodines" + "quieres usarlos?");
            System.out.println("Responde con SI o NO");
            String pregunta = teclado.nextLine();
            if (pregunta.equalsIgnoreCase("si")) {
                //         jugadores[turno].setTurno(true);
            } else {
                //         jugadores[turno].setTurno(false);
            }
        }
        return "";
    }

    public static void resetearDatos() {
        for (int i = 0; i < jugadores.length; i++) {
            jugadores[i].setDinero(0);
            jugadores[i].setComodin(0);
        }
    }
}
