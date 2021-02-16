package Pantallas;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import Generico.Utilidades;
import Principal.Interface;
import Principal.PanelJuego;

/**
 * Clase PantallaFin.
 */
public class PantallaFin implements Interface {
    private PanelJuego panel;

    /** Constructor */
    public PantallaFin(PanelJuego panel, int cont, String tiempo) {
        this.panel = panel;
        inicializarPantalla();
    }

    @Override
    public void inicializarPantalla() {
    }

    @Override
    public void pintarPantalla(Graphics g) {
        g.setColor(Utilidades.NEGRO);
    }

    @Override
    public void ejecutarFrame() {
    }

    /**
     * Método que controla si pulsamos en el ratón. Si pulsamos significa que
     * queremos volver a jugar, por lo que establecemos otra pantalla de juego.
     */
    @Override
    public void pulsarRaton(MouseEvent e) {
        panel.setPantalla(new PantallaJuego(panel));
    }

    @Override
    public void moverRaton(MouseEvent e) {
    }

    @Override
    public void redimensionarPantalla() {
    }

    @Override
    public void pulsarTeclado(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void soltarTeclado(KeyEvent e) {
        // TODO Auto-generated method stub

    }
}