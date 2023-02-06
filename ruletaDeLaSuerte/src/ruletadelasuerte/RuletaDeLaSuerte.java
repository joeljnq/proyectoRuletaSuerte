package ruletadelasuerte;

import java.lang.reflect.Array;
import java.util.Scanner;

/**
 *
 * @author a18jaimejnq
 */
public class RuletaDeLaSuerte {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        final int limitRonda = 2; //El limite de rondas de una partida
        while(limitRonda!=Ronda.rondaActual){
        Ronda.menuPrincipal();
        }
        System.out.println("Fin del juego, GG");
      
    }

}
