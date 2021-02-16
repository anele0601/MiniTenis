package Pantallas;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import Generico.Utilidades;
import Principal.Interface;
import Principal.PanelJuego;
import Principal.Sprite;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase PantallaJuego.
 */

public class PantallaJuego implements Interface {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private PanelJuego panel;
    private Sprite pelota, raqueta;

    public PantallaJuego(PanelJuego panel) {
        this.panel = panel;
        inicializarPantalla();

    }

    @Override
    public void inicializarPantalla() {
        pelota = new Sprite("imagenes/pelota.png", 50, 50, 10, 10, 10, 10);
        raqueta = new Sprite(Utilidades.NEGRO, 100, 20, (panel.getWidth() / 2) - 50, panel.getHeight() / 2 + 240, 4, 0);
    }

    @Override
    public void pintarPantalla(Graphics g) {
        rellenarFondo(g);
        pelota.estampar(g);
        raqueta.estampar(g);
    }

    /**
     * Método para rellenar el fondo del componente.
     * 
     * @param g gráfico.
     */
    private void rellenarFondo(Graphics g) {
        g.setColor(Utilidades.BLANCO);
        g.fillRect(0, 0, panel.getWidth(), panel.getHeight());
    }

    @Override
    public void ejecutarFrame() {
        try {
            Thread.sleep(25);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pelota.mover(panel.getWidth(), panel.getHeight());
        if (pelota.colisionar(raqueta)) {

        }

    }

    @Override
    public void pulsarRaton(MouseEvent e) {

    }

    @Override
    public void moverRaton(MouseEvent e) {
    }

    @Override
    public void redimensionarPantalla() {
    }

    public void redimensionarFondo() {
    }

    @Override
    public void pulsarTeclado(KeyEvent e) {
        // TODO Auto-generated method stub
        if (e.getKeyCode() == KeyEvent.VK_A) {
            System.out.println("Hola");
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            System.out.println("Hola");
        }

    }

    @Override
    public void soltarTeclado(KeyEvent e) {
        // TODO Auto-generated method stub

    }

}