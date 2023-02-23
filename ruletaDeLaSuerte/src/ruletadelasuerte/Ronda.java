package ruletadelasuerte;

import java.util.Random;
import java.util.Scanner;
import javax.print.attribute.standard.Finishings;

/**
 * @author a18jaimejnq
 */
public class Ronda {

    final static int limitRonda = 4; //El limite de rondas de una partida
    public static String frase = "";
    public static char[] panelUsuario; //Panel Usuario es el panel que se le mostrará al usuario por consola
    static boolean panelcreado = false;

    static int turno = 0;

    public static int rondaActual = 1;
    static Scanner teclado = new Scanner(System.in, "ISO-8859-1");
    static Jugador[] jugadores = {new Jugador("pedro"), new Jugador("joel")};
    static int eleccionTrasGirarRuleta;
    static int eleccionOpcionMenu;

    static public void finTurno() {
        turno++;
        if (turno >= jugadores.length) {
            turno = 0;
        }
    }

    public static String crearFrase() {
        int selectorFrase = 0;
        Random rnd = new Random();
        selectorFrase = rnd.nextInt(10);
        switch (selectorFrase) {
            case 0:
                frase = "hola mundo";
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
            case 4:
                frase = "En boca cerrada no entran moscas";
                break;
            case 5:
                frase = "No dejes para mañana lo que puedas hacer hoy";
                break;
            case 6:
                frase = "Más vale tarde que nunca";
                break;
            case 7:
                frase = "La paciencia es una virtud";
                break;
            case 8:
                frase = "No hay peor sordo que el que no quiere oír";
                break;
            case 9:
                frase = "Ojos que no ven corazón que no siente";
                break;

        }
        return frase;
    }

    static public void menuPrincipal() {
        do {
            panelcreado = false;
            resetearDatos();
            turno = 0;

            System.out.println("Bienvenido a LA RULETA DE LA SUERTE");
            System.out.println("\n              MENU\n----------------------------------\n");
            System.out.println("1.Iniciar partida");
            System.out.println("2.Salir");
            eleccionOpcionMenu = teclado.nextInt();
            teclado.nextLine();

            if (eleccionOpcionMenu == 1) {
                cambiarNombre();
                menuPartida();
            } else if (eleccionOpcionMenu == 2) {
                System.out.println("Creadores de este humilde juego...");
                System.out.println("-Joel Ninahuaman Quintanilla :" + "\t https://github.com/joeljnq/");
                System.out.println("-Pedro Campelo Rico: " + "\t https://github.com/WorkPedroCampelo");
            } else if (eleccionOpcionMenu == 3) {
                cambiarNombre();

            }

        } while (eleccionOpcionMenu != 2);
    }

    static public void menuPartida() {

        boolean terminarPartida = false;
        while (rondaActual <= limitRonda && !terminarPartida) {

            if (!panelcreado) {
                crearFrase();
                panelUsuario = new char[frase.length()];
                GeneradorPanelUsuario();
                panelcreado = true;
            }

            do {

                System.out.println("\n              MENU\n----------------------------------\n");
                System.out.println("Ronda:" + Ronda.rondaActual + "\n\nJugador:" + jugadores[turno].getNombre() + "\nDinero:" + jugadores[turno].getDinero() + "\nComodines: " + jugadores[turno].getComodin() + "\n");
                System.out.println("Que acción quieres llevar a cabo?\n");
                System.out.println("1.Girar ruleta");
                System.out.println("2.Resolver panel");
                System.out.println("3.Comprar vocal");
                System.out.println("4.Salir de la partida");
                System.out.println("\n \t PANEL ACTUAL: ");
                System.out.print("\t");
                mostrarPanel();
                System.out.println("");
                eleccionOpcionMenu = teclado.nextInt();

                switch (eleccionOpcionMenu) {
                    case 1 -> {
                        Ronda.girarRuleta();
                    }
                    case 2 -> {
                        System.out.println("Tienes el siguiente panel resuelto");
                        mostrarPanel();
                        System.out.println("Que frase piensas que es?");
                        resolverPanel();
                    }
                    case 3 -> {
                        System.out.println("\n\nComprando vocal...");
                        System.out.println(comprobarVocal());
                    }
                    case 4 -> {
                        System.out.println("Saliendo de la partida... \n\n\n\n");
                        terminarPartida = true;
                    }
                    default ->
                        System.out.println("Elige un valor valido");
                }
            } while (eleccionOpcionMenu < 1 || eleccionOpcionMenu > 4); //Repetir mientras opción no válida
            if (rondaActual == limitRonda + 1) {
                ganadorPartida();
            }
        }

    }

    public static char consonanteElegidaPorUsuario;
    static boolean caidoEnComodin = false;

    /**
     * Este metodo se utiliza para girar la ruleta mediante un numero aleatorio
     * que sera un posición de la string rule.
     *
     * @return
     */
    public static void girarRuleta() {
        boolean comodinUsado = false;

        int[] rule = Ruleta.rule();
        String toret = "";
        Random rnd = new Random();
        int aleatorio = rnd.nextInt(8); //genera un numero random

        switch (rule[aleatorio]) {
            case 1 -> {

                jugadores[turno].setDinero(0);
                toret = "quiebra"; //Por ahora es similar a un pierde turno
                System.out.println(toret);

                //Este if sería lo que hipoteticamente habría que hacer cuando se repare el usarComodín()
                if (jugadores[turno].getComodin() > 0) {
                    Ronda.usarComodin();//Hay que reparar ese metodo!!
                    jugadores[turno].setComodin(jugadores[turno].getComodin() - 1);

                } else {
                    finTurno();
                }

            }
            case 2 -> {
                boolean consonanteRepetida = false;
                boolean acertado = false;
                boolean fin = false;
                toret = "comodin";
                System.out.println(toret);
                consonanteElegidaPorUsuario = jugadores[turno].decirConsonante();

                for (int i = 0; i < frase.length() && fin == false; i++) {

                    if (consonanteElegidaPorUsuario == panelUsuario[i]) {
                        fin = true;
                    }

                    if (consonanteElegidaPorUsuario == frase.charAt(i) && fin == false) {
                        jugadores[turno].setComodin(jugadores[turno].getComodin() + 1);
                        panelUsuario[i] = consonanteElegidaPorUsuario;
                        acertado = true;

                    }

                }

                if (acertado == false && !fin) {
                    System.out.println("La consonante no se encuentra en el panel");
                    finTurno();
                }

                if (fin) {
                    System.out.println("Consonante repetida");
                    finTurno();

                }

            }
            case 3 -> {
                toret = "pierde Turno";
                System.out.println(toret);
                if (jugadores[turno].getComodin() > 0) {
                    usarComodin();
                } else {
                    finTurno();
                }
            }
            case 10, 20, 50, 100, 200 -> {
                boolean acertado = false;
                boolean consonanteRepetida = false;
                toret = "Por " + rule[aleatorio] + " PESOS VENEZOLANOS...";
                System.out.println(toret);
                consonanteElegidaPorUsuario = jugadores[turno].decirConsonante();
                for (int i = 0; i < frase.length(); i++) {
                    if (comprobarConsonante() == frase.charAt(i)) {
                        if (frase.charAt(i) == panelUsuario[i]) {
                            consonanteRepetida = true;
                        }
                        panelUsuario[i] = consonanteElegidaPorUsuario;
                        jugadores[turno].setDinero(jugadores[turno].getDinero() + rule[aleatorio]);
                        acertado = true;
                    }
                }
                if (consonanteRepetida) {
                    jugadores[turno].setDinero(jugadores[turno].getDinero() - rule[aleatorio]);
                    finTurno();
                    System.out.println("Consonante repetida");
                }

                if (acertado == false) {
                    System.out.println("La consonante no se encuentra en el panel");
                    finTurno();
                }
            }

        }

    }

    /**
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

    public static void mostrarPanel() {
        System.out.print(panelUsuario);
    }

    public static boolean resolverPanel() {
        teclado.nextLine();
        String usuarioResuelvePanel = teclado.nextLine();
        boolean panelResuelto = false;

        if (frase.equalsIgnoreCase(usuarioResuelvePanel)) {
            panelResuelto = true;
            System.out.println("HAS RESUELTO EL PANEL");
            finRonda();
        } else {
            System.out.println("ERRASTE WEON");
            finTurno();
        }

        return panelResuelto;
    }

    public static char comprobarConsonante() { //Este metodo pide la consonante y la comprueba a la vez

        for (int i = 0; i < frase.length(); i++) {
            if (frase.charAt(i) == consonanteElegidaPorUsuario) { //Este if comprueba si la consonante está en la frase, si lo está, aplica al panel usuario esta vocal

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
                jugadores[turno].setComodin(jugadores[turno].getComodin() - 1);
            } else {

                finTurno();
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

    public static void resetearDatosExceptoGanador() {
        int dineroGanadorRonda = jugadores[turno].getDinero();
        int comodinesGanadorRonda = jugadores[turno].getComodin();
        resetearDatos();
        jugadores[turno].setDinero(dineroGanadorRonda);
        jugadores[turno].setComodin(comodinesGanadorRonda);

    }

    public static void finRonda() {
        resetearDatosExceptoGanador();
        GeneradorPanelUsuario();
        rondaActual++;
    }

    public static void cambiarNombre() { //Joel toqué esto a ultima hora para darle nombre a los jugadores
        String nome;
        System.out.println("Nombre del jugador 1 : ");
        jugadores[0].setNombre(nome = teclado.nextLine());
        System.out.println("Nombre del jugador 2 : ");
        jugadores[1].setNombre(nome = teclado.nextLine());

    }

    public static void ganadorPartida() {

        if (jugadores[0].getDinero() == jugadores[1].getDinero()) {
            System.out.println("EMPATEEEEEEEE!!!");
        }

        if (jugadores[0].getDinero() > jugadores[1].getDinero()) {
            System.out.println("Ganador: " + jugadores[0].getNombre());
        } else {
            System.out.println("Ganador: " + jugadores[1].getNombre());
        }
    }

}
