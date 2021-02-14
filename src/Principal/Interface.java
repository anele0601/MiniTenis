package Principal;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

/**
 * Interfaz Pantalla. Define la estructura de las pantallas del juego.
 */

public interface Interface {

    public void inicializarPantalla();

    public void pintarPantalla(Graphics g);

    public void ejecutarFrame();

    // Listeners
    public void pulsarRaton(MouseEvent e);

    public void moverRaton(MouseEvent e);

    public void redimensionarPantalla();
}