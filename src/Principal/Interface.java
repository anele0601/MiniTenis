package Principal;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

import Pantallas.PantallaGanar;
import Pantallas.PantallaInicio;
import Pantallas.PantallaJuego;

import java.awt.event.KeyEvent;

/**
 * Interfaz Pantalla. Define la estructura de las pantallas del juego.
 * 
 * @author Elena Nofuentes
 * @since 20-02-2021
 * 
 * @see PantallaInicio
 * @see PantallaJuego
 * @see PantallaGanar
 * @see PantallaFin
 */

public interface Interface {

    public void inicializarPantalla();

    public void pintarPantalla(Graphics g);

    public void ejecutarFrame();

    // Listeners
    public void pulsarRaton(MouseEvent e);

    public void moverRaton(MouseEvent e);

    public void redimensionarPantalla();

    public void pulsarTeclado(KeyEvent e);

    public void soltarTeclado(KeyEvent e);
}