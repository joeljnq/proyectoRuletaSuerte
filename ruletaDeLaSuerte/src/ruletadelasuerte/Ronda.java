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
            -Reparar el que se pueda añadir una consonante ya añadida (y cobrar por ella)
    
                TODO EXTRAS
            -Reorganizar ruleta(Igual su existencia es innecesaria?)
            -Reorganizar menú para que no quede tan chueco al usar comprarVocal()
            -Revisar las variables qu eestán en static 
            -Añadir la posibilidad de poner cantidad y nombre a los jugadores de la partida
            -Revisar comentarios y borrar o crear los necesarios para el entendimiento del código en un futuro
            -Hacer array de consonantes comprobarConsonante en la clase jugador
            -Revisar el ambito de las clases (si deberian ser privadas/publicas...)
            -IgnoreCase para la resolución del enigma/vocal/consonante
            -Mostrar el ganador de la partida
            
     */
    final static int limitRonda = 3; //El limite de rondas de una partida
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
        selectorFrase = rnd.nextInt(1);
        switch (selectorFrase) {
            case 0:
                //frase = "La paciencia es una virtud";
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

        }
        return frase;
    }

    static public void menuPrincipal() {
        do {
            panelcreado=false;
            resetearDatos();
            turno=0;
            
            //APERTURA DE MENÚ PRINCIPAL
            System.out.println("Bienvenido a LA RULETA DE LA SUERTE");
            System.out.println("\n              MENU\n----------------------------------\n");
            System.out.println("1.Iniciar partida");
            System.out.println("2.Salir");
            eleccionOpcionMenu = teclado.nextInt();
            teclado.nextLine();
            //CIERRE DE MENU PRINCIPAL
            if (eleccionOpcionMenu == 1) {
              menuPartida();
            }else if(eleccionOpcionMenu==2){
                System.out.println("Creadores de este juego humilde con moneda de venezuela PRAY FOR VENEZUELA!!");
                System.out.println("1. Joel Ninahuaman Quintanilla :" + "\t https://github.com/joeljnq/");
                System.out.println("2. Pedro menos Listo Campelo rico: " + "\t https://github.com/WorkPedroCampelo");
            }
        } while (eleccionOpcionMenu != 2);
    }

    static public void menuPartida() {
        boolean terminarPartida = false;
        while (rondaActual <= limitRonda && !terminarPartida) {

            //Menú de partida
            if (!panelcreado) {
                crearFrase();
                panelUsuario = new char[frase.length()];
                GeneradorPanelUsuario();
                panelcreado = true;
            }

            do {
                System.out.println("\n              MENU\n----------------------------------\n");
                System.out.println("Ronda:" + Ronda.rondaActual + "\n\nJugador:" + jugadores[turno].getNombre() + "\nDinero:" + jugadores[turno].getDinero() +"\nComodines: " + jugadores[turno].getComodin()+ "\n");
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
                        //EJECUTAR EL METODO TIRAR RULETA
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
                        System.out.println(comprobarVocal()); //TODO Pedro por alguna razon al comprar vocal, no se guarda el resultado en PanelUsuario[]
                    }
                    case 4 -> {
                        System.out.println("Saliendo de la partida... \n\n\n\n");
                        terminarPartida = true;
                    }
                    default ->
                        System.out.println("Elige un valor valido");
                }
            } while (eleccionOpcionMenu < 1 || eleccionOpcionMenu > 4); //Repetir mientras opción no válida

        }
    }

    /**
     * Este metodo se utiliza para girar la ruleta mediante un numero aleatorio
     * que sera un posición de la string rule.
     *
     * @return
     */
    public static char consonanteElegidaPorUsuario;
    static boolean caidoEnComodin = false;

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
                if (jugadores[turno].getComodin() >0) {
                       Ronda.usarComodin();//Hay que reparar ese metodo!!
                    jugadores[turno].setComodin(jugadores[turno].getComodin() - 1);
                   
                } else {
                    finTurno();
                }

            }
            case 2 -> {
                toret = "comodin"; 
                System.out.println(toret);
               consonanteElegidaPorUsuario = jugadores[turno].decirConsonante();
                for (int i = 0; i < frase.length(); i++) {
                    if (comprobarConsonante()== frase.charAt(i)) {
                        jugadores[turno].setComodin(jugadores[turno].getComodin() + 1);
                    }
                }
                
                
            }
            case 3 -> {
                toret = "pierde Turno";
                System.out.println(toret);
                if (jugadores[turno].getComodin()>0) {
                    usarComodin();
                }else  {
                    finTurno(); 
                }
            }
            case 10, 20, 50, 100, 200  -> {
                boolean acertado = false; //Sirve para saber si en todo el for apareció al menos una coincidencia
                toret = "Por "+rule[aleatorio] +" PESOS VENEZOLANOS...";
                System.out.println(toret);
                consonanteElegidaPorUsuario = jugadores[turno].decirConsonante();
                for (int i = 0; i < frase.length(); i++) {
                    if (comprobarConsonante() == frase.charAt(i)) {
                        jugadores[turno].setDinero(jugadores[turno].getDinero() + rule[aleatorio]);
                        acertado = true;
                    }
                }
                if (acertado == false) {

                    System.out.println("La consonante no se encuentra en el panel");
                    finTurno();
                } 

            }
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
        teclado.nextLine();
        String usuarioResuelvePanel = teclado.nextLine();
        boolean panelResuelto = false;

        if (frase.equalsIgnoreCase(usuarioResuelvePanel)) { //TODO Pedro Aqui por alguna razon no funciona esta wea, echarmle un ojo
            panelResuelto = true;
            System.out.println("HAS RESUELTO EL PANEL");
            finRonda();
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
/*
        for (int i = 0; i < frase.length(); i++) {
            if (frase.charAt(i) == consonanteElegidaPorUsuario) { //Este if comprueba si la consonante está en la frase, si lo está, aplica al panel usuario esta vocal
                panelUsuario[i] = consonanteElegidaPorUsuario;
            }
        }
        */
              //Este codigo debería comprobar si se repite la letra pero queda en un bucle ininito, comprobar :)
                boolean consonanteRepetida;
        do {
            consonanteRepetida = false;
            for (int i = 0; i < frase.length(); i++) {
                if (consonanteElegidaPorUsuario == panelUsuario[i]) {
                    System.out.println("Esta vocal ya ha sido usada");
                    consonanteRepetida = true;
                }
            }

            if (consonanteRepetida == false) {
                for (int i = 0; i < frase.length(); i++) {
                    if (frase.charAt(i) == consonanteElegidaPorUsuario) { //Este if comprueba si la vocal está en la frase, si lo está, aplica al panel usuario esta vocal
                        panelUsuario[i] = consonanteElegidaPorUsuario;
                    }
                }
            }

        } while (consonanteRepetida);
        
        
        
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
               jugadores[turno].setComodin(jugadores[turno].getComodin()-1);
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
    
    public static void cambiarNombre(){
        String nome;
        System.out.println("pulsa 1. si quieres cambiar el nombre del jugador \n pulsa 2. si quieres camiar el nombre del jugador 2");
        int eleccionNombre  = teclado.nextInt();
        teclado.nextLine();
        switch(eleccionNombre){
            case 1 ->{
                System.out.println("Escribe el nombre");
                jugadores[turno].setNombre(nome=teclado.nextLine());
            }
            case 2 ->{
                turno += 1;
                System.out.println("Escribe el nombre");
                jugadores[turno].setNombre(nome = teclado.nextLine());
                turno = 0;
            }
        }
        
    }

}
