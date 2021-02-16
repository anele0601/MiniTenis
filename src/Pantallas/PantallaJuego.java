package Pantallas;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;

import Generico.Utilidades;
import Principal.Interface;
import Principal.PanelJuego;
import Principal.Sprite;

/**
 * Clase PantallaJuego.
 */

public class PantallaJuego implements Interface {
    private PanelJuego panel;
    private Sprite pelota, raqueta;
    private int puntuacion;
    private double contadorT;
    private DecimalFormat formato = new DecimalFormat("#.##");
    private String tiempo;

    public PantallaJuego(PanelJuego panel) {
        this.panel = panel;
        puntuacion = 0;
        tiempo = "";
        inicializarPantalla();

    }

    @Override
    public void inicializarPantalla() {
        pelota = new Sprite("imagenes/pelota.png", 50, 50, 10, 10, 5, 5);
        raqueta = new Sprite(Utilidades.NEGRO, 100, 20, (panel.getWidth() / 2) - 50, panel.getHeight() / 2 + 240, 4, 0);
        contadorT = System.nanoTime();
    }

    @Override
    public void pintarPantalla(Graphics g) {
        rellenarFondo(g);
        pelota.estampar(g);
        raqueta.estampar(g);

        // Pintamos la puntuación
        g.setColor(Utilidades.VERDE);
        g.setFont(Utilidades.FUENTE_PEQUE);
        g.drawString("" + puntuacion, panel.getWidth() - 50, panel.getHeight() - 30);

        // Pintamos el tiempo
        g.drawString(tiempo = formato.format((System.nanoTime() - contadorT) / 1e9), 40, 40);
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

    public int velocidad() {
        return (int) (Math.random() * 31) - 15;
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
            pelota.setVelY(pelota.getVelY() * -1);
            puntuacion++;
        } else {
            if (pelota.getPosY() > raqueta.getPosY()) {
                panel.setPantalla(new PantallaGanar(panel, puntuacion, tiempo));
            }
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
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            raqueta.moverIzquierda();
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            raqueta.moverDerecha(panel.getWidth(), raqueta.getAncho());
        }
    }

    @Override
    public void soltarTeclado(KeyEvent e) {
        // TODO Auto-generated method stub

    }
}