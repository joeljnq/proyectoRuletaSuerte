package ruletadelasuerte;

import java.util.Scanner;

/**
 *
 * @author a18jaimejnq
 */
public class Ronda {
    static int rondaActual=1;
    static Scanner teclado = new Scanner(System.in, "ISO-8859-1");
    static Jugador j1 = new Jugador("Juan", true); //Nombre del jugador y si está en su turno
    
    public boolean finRonda(){
        return true;
    }

    
    static public void menu(){
        //APERTURA DE MENÚ PRINCIPAL
        System.out.println("Bienvenido a LA RULETA DE LA SUERTE");
        System.out.println("\n              MENU\n----------------------------------\n");
        System.out.println("1.Iniciar partida");
        System.out.println("2.Salir");
        System.out.println("3.Creditos(en mantenimiento)");
        int eleccionOpcionMenu = teclado.nextInt();
        //CIERRE DE MENU PRINCIPAL
        
        //Menú de partida
        if (eleccionOpcionMenu == 1) {
            System.out.println("\n              MENU\n----------------------------------\n");
            System.out.println("Ronda:" + Ronda.rondaActual + "\n\nJugador:" + j1.getNombre() + "\nDinero:" + j1.getDinero() + "\n");
            System.out.println("Que acción quieres llevar a cabo?\n");
            System.out.println("1.Tirar ruleta");
            System.out.println("2.Resolver panel");//TODO Pedro Crear método resolverPanel
            System.out.println("3.Comprar vocal");//TODO Pedro Esto debería enlazar con j1.comprarVocal
            System.out.println("4.Salir de la partida");
            
            switch (eleccionOpcionMenu) {
                case 1 -> {
                }
                case 2->{
                }
                case 3 ->{
                }
                case 4 ->{
                }
                default -> System.out.println("Eige un valor valido");
            }
            //ESTAS DOS ULTIMAS SON DE PRUEBA
            System.out.println("Comprando vocal...");
            j1.comprarVocal();
        }
    }
}
