package ruletadelasuerte;

/**
 *
 * @author a18jaimejnq
 */
public class Jugador {
private int comodin = 0; // indica el numero de comodines
private int dinero = 0;
private String nombre; 
private boolean turno; // true el juegador puede jugar o false el jugador no puede jugar

    public Jugador(String nombre, boolean turno) {
        this.nombre = nombre;
        this.turno = turno;
    }
/**
 * esta para modificar el numero de comodines en caso de que le toque en la ruleta
 * @param comodin 
 */
    public void setComodin(int comodin) {
        this.comodin = comodin;
    }

    public void setDinero(int dinero) {
        this.dinero = dinero;
    }
/**
 * cambia en caso de que el jugador no acierte la consonante o la vocal o le toque pierde turno
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

    public boolean isTurno() {
        return turno;
    }
    
    
       

}
