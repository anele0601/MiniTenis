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
    private Sprite pelota, raqueta, raquetaDos, pelotaDos, pelotaTres, pelotaFantasmaUno, pelotaFantasmaDos;
    private int puntuacion;
    private double contadorT;
    private DecimalFormat formato = new DecimalFormat("#.##");
    private String tiempo;
    private boolean jugador;

    public PantallaJuego(PanelJuego panel) {
        this.panel = panel;
        puntuacion = 0;
        tiempo = "";
        inicializarPantalla();
    }

    public PantallaJuego(PanelJuego panel, boolean jugador) {
        this.panel = panel;
        this.jugador = jugador;
        puntuacion = 0;
        tiempo = "";
        inicializarPantalla();
    }

    @Override
    public void inicializarPantalla() {
        if (!jugador) {
            pelota = new Sprite("imagenes/pelota.png", 50, 50, 10, 10, 5, 5);
            raqueta = new Sprite(Utilidades.NEGRO, 100, 20, (panel.getWidth() / 2) - 50, panel.getHeight() / 2 + 240, 4,
                    0);
            contadorT = System.nanoTime();
        } else {
            pelota = new Sprite("imagenes/pelota.png", 50, 50, 10, 10, 5, 5);
            raqueta = new Sprite(Utilidades.NEGRO, 100, 20, (panel.getWidth() / 2) - 50, panel.getHeight() / 2 + 240, 4,
                    0);
            raquetaDos = new Sprite(Utilidades.NEGRO, 100, 20, (panel.getWidth() / 2) + 50, panel.getHeight() / 2 - 240,
                    4, 0);
            contadorT = System.nanoTime();
        }
    }

    @Override
    public void pintarPantalla(Graphics g) {
        rellenarFondo(g);
        pelota.estampar(g);
        raqueta.estampar(g);
        if (jugador) {
            raquetaDos.estampar(g);
        }

        // Pintamos la puntuación
        g.setColor(Utilidades.VERDE);
        g.setFont(Utilidades.FUENTE_PEQUE);
        g.drawString("" + puntuacion, panel.getWidth() - 40, panel.getHeight() - 20);

        // Pintamos el tiempo
        g.drawString(tiempo = formato.format((System.nanoTime() - contadorT) / 1e9), 30, 30);

        if (pelotaDos != null) {
            pelotaDos.estampar(g);
        }
        if (pelotaTres != null) {
            pelotaTres.estampar(g);
        }

        if (pelotaFantasmaDos != null) {
            pelotaFantasmaUno.estampar(g);
        }
        if (pelotaFantasmaDos != null) {
            pelotaFantasmaDos.estampar(g);
        }
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

    public void sumarPelota() {
        if (tiempo.contains("10,0")) {
            pelotaDos = new Sprite("imagenes/pelota.png", 50, 50, panel.getWidth(), 10, 6, 6);
        }
        if (tiempo.contains("25,0")) {
            pelotaTres = new Sprite("imagenes/pelota.png", 50, 50, 10, 10, 5, 5);
        }
        // Pelotas fantasmas para engañar al jugador y aumentar la dificultad
        if (tiempo.contains("40,0")) {
            pelotaFantasmaUno = new Sprite("imagenes/pelota.png", 50, 50, 10, 10, 5, 5);
        }
        if (tiempo.contains("50,0")) {
            pelotaFantasmaDos = new Sprite("imagenes/pelota.png", 50, 50, panel.getWidth(), 10, 5, 5);
        }
    }

    @Override
    public void ejecutarFrame() {
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Fin de Juego y/o puntuacion
        if (pelota.getPosY() >= raqueta.getPosY() + raqueta.getAlto()) {
            panel.setPantalla(new PantallaFin(panel, puntuacion, tiempo));
        } else {
            if (pelota.colisionar(raqueta)) {
                puntuacion++;
            }
        }
        if (pelotaDos != null && raqueta.getPosY() + raqueta.getAlto() <= pelotaDos.getPosY()) {
            panel.setPantalla(new PantallaFin(panel, puntuacion, tiempo));
        } else {
            if (pelotaDos != null && pelotaDos.colisionar(raqueta)) {
                puntuacion++;
            }
        }

        if (pelotaTres != null && raqueta.getPosY() + raqueta.getAlto() <= pelotaTres.getPosY()) {
            panel.setPantalla(new PantallaFin(panel, puntuacion, tiempo));
        } else {
            if (pelotaTres != null && pelotaTres.colisionar(raqueta)) {
                puntuacion++;
            }
        }

        // Mover
        pelota.mover(panel.getWidth(), panel.getHeight());
        if (pelotaDos != null) {
            pelotaDos.mover(panel.getWidth(), panel.getHeight());
        }
        if (pelotaTres != null) {
            pelotaTres.mover(panel.getWidth(), panel.getHeight());
        }
        if (pelotaFantasmaUno != null) {
            pelotaFantasmaUno.mover(panel.getWidth(), panel.getHeight());
        }
        if (pelotaFantasmaDos != null) {
            pelotaFantasmaDos.mover(panel.getWidth(), panel.getHeight());
        }

        sumarPelota();
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

        // 2 jugadores
        if (jugador) {
            if (e.getKeyCode() == KeyEvent.VK_A) {
                raquetaDos.moverIzquierda();
            }
            if (e.getKeyCode() == KeyEvent.VK_D) {
                raquetaDos.moverDerecha(panel.getWidth(), raqueta.getAncho());
            }
        }
    }
}