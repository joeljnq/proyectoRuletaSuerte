package ruletadelasuerte;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author a18jaimejnq
 */
public class Ronda {


    /*
                TODO LIST MÍNIMOS
            -Poner la posibilidad 2 jugadores
            -Resetear TODO cada vez que se ejecuta menuPartida()
            -Aplicar el comprobar consonante tras tirar ruleta(?) //En proceso por Pedro
            -Acabar rondas,llevar un conteo de ellas y acabar la partida cuando se llega al limite.
            -Crear varias frases para el panel y aplicarlas para que salgan aleatoriamente cada ronda
            -
                TODO EXTRAS
            -Reorganizar ruleta(Igual su existencia es innecesaria?)
            -Reorganizar menú para que no quede tan chueco al usar comprarVocal()
            -Poner una opción para los creditos en la que se digan los creadores, página de github y demás
            -Revisar las variables qu eestán en static 
            -Añadir la posibilidad de poner cantidad y nombre a los jugadores de la partida
            -Revisar comentarios y borrar o crear los necesarios para el entendimiento del código en un futuro
            -Hacer array de consonantes comprobarConsonante en la clase jugador
            -
 
     */
    
    public static String frase = "esto es una prueba";
    public static char[] panelUsuario = new char[frase.length()]; //Panel Usuario es el panel que se le mostrará al usuario por consola
    static boolean panelcreado = false;

    public static int rondaActual = 1;
    static Scanner teclado = new Scanner(System.in, "ISO-8859-1");

    static Jugador[] jugadores = {new Jugador("pedro", true), new Jugador("joel", false)};

    static Jugador j1 = new Jugador("Juan", true); //Nombre del jugador y si está en su turno
    static Jugador j2 = new Jugador("Pepe", false);
    static Jugador j3 = new Jugador("toni", false);
    static int eleccionOpcionMenu;
//for 

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
            menuPartida(jugadores[0].getNombre(), jugadores[0].getDinero());
        }
    }

    static public void menuPartida(String nome, int dinero) {
        //Menú de partida

        if (!panelcreado) {
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
                    System.out.println(Ronda.girarRuleta());
                    //  menuPartida(); Eliminamos el menu partida por un submenu con las opciones que deben estar
                    menuTrasGirarRuleta();
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
        System.out.print(panelUsuario);
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
    public static char comprobarConsonante() { //Este metodo pide la consonante y la comprueba a la vez
        char consonanteElegidaPorUsuario = j1.decirConsonante();

        for (int i = 0; i < frase.length(); i++) {
            if (frase.charAt(i) == consonanteElegidaPorUsuario) { //Este if comprueba si la consonante está en la frase, si lo está, aplica al panel usuario esta vocal
                panelUsuario[i] = consonanteElegidaPorUsuario;
            }
        }
        return consonanteElegidaPorUsuario;
    }

    public static void menuTrasGirarRuleta() { //Giras, te toca dinero, tienes que decir consonante, si el consonante está en el panel aparecerán las opciones comprar vocal o resolver panel, en caso de que no esté piernes turno

        comprobarConsonante();
        System.out.println("Despues de comprobar consonante");

        System.out.println("Elige ");
        System.out.println("\n              MENU\n----------------------------------\n");
        System.out.println("Ronda:" + Ronda.rondaActual + "\n\nJugador:" + j1.getNombre() + "\nDinero:" + j1.getDinero() + "\n");
        System.out.println("Que acción quieres llevar a cabo?\n");
        System.out.println("1.Resolver panel");
        System.out.println("2.Comprar vocal");
        System.out.println("3.Salir de la partida");
        System.out.println("\n \t PANEL ACTUAL: ");
        System.out.print("\t");
        mostrarPanel();
        System.out.println("");

        eleccionOpcionMenu = teclado.nextInt();
        switch (eleccionOpcionMenu) {
            case 1 -> {
                System.out.println("Tienes el siguiente panel resuelto");
                mostrarPanel();
                System.out.println("Que frase piensas que es?");
                resolverPanel();
            }
            case 2 -> {
                System.out.println("\n\nComprando vocal...");
                System.out.println(comprobarVocal()); //TODO Pedro por alguna razon al comprar vocal, no se guarda el resultado en PanelUsuario[]
                menuPartida(jugadores[i].getNombre(), jugadores[i].getDinero());
            }
            case 3 -> {
                System.out.println("Saliendo de la partida... \n\n\n\n");
                menuPrincipal();
            }
            default ->
                System.out.println("Elige un valor valido");
        }
    }

    /**
     * Este metodo se utiliza para girar la ruleta mediante un numero aleatorio que sera un posición de la string rule.
     *
     * @return 
     */
    public static String girarRuleta() {
        int[] rule = Ruleta.rule();
        String toret = "";
        Random rnd = new Random();
        int aleatorio = rnd.nextInt(7); //genera un numero random 

        switch (rule[aleatorio]) {
            case 1 -> {
                j1.setDinero(0);
                toret = "quiebra";
                Ronda.usarComodin();
            }
            case 2 -> {
                j1.setComodin(j1.getComodin() + 1);
                toret = "comodin";
            }
            case 3 -> {
                j1.setTurno(false);
                toret = "pierde Turno";
            }
            case 10 -> {
                j1.setDinero(j1.getDinero() + 10);
                toret = "10 PESOS VENEZOLANOS";
            }
            case 20 -> {
                j1.setDinero(j1.getDinero() + 20);
                toret = "20 PESOS VENEZOLANOS";
            }
            case 50 -> {
                j1.setDinero(j1.getDinero() + 50);
                toret = "50 PESOS VENEZOLANOS";
            }
            case 100 -> {
                j1.setDinero(j1.getDinero() + 100);
                toret = "100 PESOS VENEZOLANOS";
            }
            case 200 -> {
                j1.setDinero(j1.getDinero() + 200);
                toret = "200 PESOS VENEZOLANOS";    //Bro con esto no me da ni pa un chicle
            }

        }
        if (j2.isTurno()) {
            switch (rule[aleatorio]) {
                case 1 -> {
                    j2.setDinero(0);
                    toret = "quiebra";
                    Ronda.usarComodin();
                }
                case 2 -> {
                    j2.setComodin(j2.getComodin() + 1);
                    toret = "comodin";
                }
                case 3 -> {
                    j2.setTurno(false);
                    toret = "pierde Turno";
                }
                case 10 -> {
                    j2.setDinero(j2.getDinero() + 10);
                    toret = "10 PESOS VENEZOLANOS";
                }
                case 20 -> {
                    j2.setDinero(j2.getDinero() + 20);
                    toret = "20 PESOS VENEZOLANOS";
                }
                case 50 -> {
                    j2.setDinero(j2.getDinero() + 50);
                    toret = "50 PESOS VENEZOLANOS";
                }
                case 100 -> {
                    j2.setDinero(j2.getDinero() + 100);
                    toret = "100 PESOS VENEZOLANOS";
                }
                case 200 -> {
                    j2.setDinero(j2.getDinero() + 200);
                    toret = "200 PESOS VENEZOLANOS";    //Bro con esto no me da ni pa un chicle
                }

            }
        }

        return toret;
    }

    /**
     * Usar comoodin en caso de que caigas en quiebra o en pierde turno...
     *
     * @return
     */
    public static String usarComodin() {
        teclado.nextLine();
        if (j1.getComodin() > 0) {
            System.out.println("tienes " + j1.getComodin() + " comodines" + "quieres usarlos?");
            System.out.println("Responde con SI o NO");
            String pregunta = teclado.nextLine();
            if (pregunta.equalsIgnoreCase("si")) {
                j1.setTurno(true);
            } else {
                j1.setTurno(false);
            }
        }
        return "";
    }

    public static void jugadores() { //AQUI HABRÍA Q METER PRACTICAMENTE TODO :')
        
        for (int i = 0; i < jugadores.length; i++) {
            if (jugadores[i].isTurno()) {
                Ronda.menuPartida(jugadores[i].getNombre(), jugadores[i].getDinero());
                Ronda.girarRuleta();
            }

        }

    }

//hola edelegado que putas haces
    // ns a que queires llegar
    //pero ya esta?
    //
    //
    //
    //
    //
    //
    //
    //
    //
    //
}
