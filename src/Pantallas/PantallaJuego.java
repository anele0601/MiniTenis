package Pantallas;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;

import Generico.Utilidades;
import Principal.Interface;
import Principal.PanelJuego;
import Principal.Sprite;

/**
 * Clase PantallaJuego. Crea y controla el desarrollo del juego. Pueden jugar
 * uno o dos jugadores, controla la elección mediante un booleano que recibe de
 * PantallaInicio.
 * 
 * Los controles del jugador1 (arriba) son : A y D Los controles del jugador2
 * (abajo) son : <- y -> ( flechas de izquierda y derecha)
 * 
 * Una vez entremos en esta pantalla el juego comenzará automáticamente.
 * 
 * Para aumentar la dificultad de juego, según avance el tiempo se añadiran más
 * pelotas a la partida, a su vez, se añadirán "Pelotas Fantasma" que se moveran
 * por la pantalla para engañár a los jugadores, ya que no podremos golpearlas
 * con la raqueta ni nos harán perder la partida.
 * 
 * @author Elena Nofuentes
 * @since 20-02-2021
 * @version 4.0
 * 
 */

public class PantallaJuego implements Interface {
    /** Atributos de la clase */
    private PanelJuego panel;
    private Sprite pelota, raqueta, raquetaDos, pelotaDos, pelotaTres, pelotaFantasmaUno, pelotaFantasmaDos,
            pelotaFantasmaTres;
    private int puntuacion;
    private double contadorT;
    private DecimalFormat formato = new DecimalFormat("#.##");
    private String tiempo;
    private boolean jugador, moverIzquierda, moverDerecha, moverIzquierdaDos, moverDerechaDos;
    private ArrayList<Sprite> pelotas;

    /** Constructor 1 jugador */
    public PantallaJuego(PanelJuego panel) {
        this.panel = panel;
        puntuacion = 0;
        tiempo = "";
        inicializarPantalla();
    }

    /** Constructor 2 juadores */
    public PantallaJuego(PanelJuego panel, boolean jugador) {
        this.panel = panel;
        this.jugador = jugador;
        puntuacion = 0;
        tiempo = "";
        inicializarPantalla();
    }

    /** Método que inicializa los componentes de la pantalla */
    @Override
    public void inicializarPantalla() {
        contadorT = System.nanoTime();
        pelotas = new ArrayList<>();
        if (!jugador) {
            pelota = new Sprite("imagenes/pelota.png", 50, 50, 10, 10, 5, 5);
            raqueta = new Sprite(Utilidades.NEGRO, 100, 20, (panel.getWidth() / 2) - 50, panel.getHeight() / 2 + 240, 4,
                    0);
        } else {
            pelota = new Sprite("imagenes/pelota.png", 50, 50, 10, (panel.getWidth() / 2) - 55, 5, 5);
            raqueta = new Sprite(Utilidades.NEGRO, 100, 20, (panel.getWidth() / 2) - 50, panel.getHeight() / 2 + 240, 4,
                    0);
            raquetaDos = new Sprite(Utilidades.NEGRO, 100, 20, (panel.getWidth() / 2) + 50, panel.getHeight() / 2 - 240,
                    4, 0);
        }
        pelotas.add(pelota);
    }

    /**
     * Método que pinta las componentes de la Pantalla de juego. Se llama
     * automáticamente.
     */
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

        // Pintamos las pelotas
        if (pelotaDos != null) {
            pelotaDos.estampar(g);
        }
        if (pelotaTres != null) {
            pelotaTres.estampar(g);
        }
        if (pelotaFantasmaUno != null) {
            pelotaFantasmaUno.estampar(g);
        }
        if (pelotaFantasmaDos != null) {
            pelotaFantasmaDos.estampar(g);
        }
        if (pelotaFantasmaTres != null) {
            pelotaFantasmaTres.estampar(g);
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

    /**
     * Método que suma pelotas a la partida para aumentar su dificultad.
     * 
     * Añade un tipo de pelotas llamadas "Pelotas Fantamas", ya que se moverán por
     * la pantalla pero realmente no podremos darlas con la raqueta ni harán que
     * perdamos la partida.
     */
    public void sumarPelota() {
        if (tiempo.contains("10,0")) {
            pelotaDos = new Sprite("imagenes/pelota.png", 50, 50, 10, (panel.getWidth() / 2) - 55, 5, 5);
            pelotas.add(pelotaDos);
        }
        if (tiempo.contains("25,0")) {
            pelotaTres = new Sprite("imagenes/pelota.png", 50, 50, 10, (panel.getWidth() / 2) - 55, 5, 5);
            pelotas.add(pelotaTres);
        }
        // Pelotas fantasmas para engañar al jugador y aumentar la dificultad
        if (tiempo.contains("40,0")) {
            pelotaFantasmaUno = new Sprite("imagenes/pelota.png", 50, 50, 10, (panel.getWidth() / 2) - 55, 5, 5);
        }
        if (tiempo.contains("48,0")) {
            pelotaFantasmaDos = new Sprite("imagenes/pelota.png", 50, 50, 10, (panel.getWidth() / 2) - 55, 5, 5);
        }
        if (tiempo.contains("55,0")) {
            pelotaFantasmaTres = new Sprite("imagenes/pelota.png", 50, 50, 10, (panel.getWidth() / 2) - 55, 5, 5);
        }
    }

    /**
     * Método que ejecuta las comprobaciones de la pantallaJuego.
     */
    @Override
    public void ejecutarFrame() {
        try {
            Thread.sleep(25);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        comprobacionesColisiones();
        moverSprite();
        sumarPelota();
    }

    /**
     * Método que comprueba las colisiones de la pelota y la raqueta. A su vez,
     * controla las posiciones de las mismas para comprobar si la partida ha
     * terminado. {@link PantallaFin}
     */
    public void comprobacionesColisiones() {
        for (int i = 0; i < pelotas.size(); i++) {
            // Comprobación fin de juego
            if (pelotas.get(i).getPosY() >= raqueta.getPosY() + raqueta.getAlto()
                    || jugador && pelotas.get(i).getPosY() <= raquetaDos.getPosY() - raquetaDos.getAlto()) {
                panel.setPantalla(new PantallaFin(panel, puntuacion, tiempo));
            } else {
                // Comprobación para un jugador.
                if (pelotas.get(i).hayColision(raqueta)) {
                    pelotas.get(i).colisionar(raqueta);
                    puntuacion++;
                }
                // Comprobación para dos jugadores.
                if (jugador && pelotas.get(i).hayColisionDosJugadores(raquetaDos)) {
                    pelotas.get(i).colisionarDosJugadores(raquetaDos);
                    puntuacion++;
                }
            }
        }
    }

    /**
     * Método que mueve por la pantalla los Sprites de las pelotas y las raquetas.
     */
    public void moverSprite() {
        // Movemos las raquetas según las teclas que estemos pulsando
        if (moverDerecha) {
            raqueta.moverDerecha(panel.getWidth(), raqueta.getAncho());
        }
        if (moverIzquierda) {
            raqueta.moverIzquierda();
        }
        if (moverDerechaDos) {
            raquetaDos.moverDerecha(panel.getWidth(), raquetaDos.getAncho());
        }
        if (moverIzquierdaDos) {
            raquetaDos.moverIzquierda();
        }
        // Movemos las pelotas
        for (int i = 0; i < pelotas.size(); i++) {
            pelotas.get(i).mover(panel.getWidth(), panel.getHeight());
        }
    }

    /**
     * Métodos para controlar las pulsaciones del teclado para poder mover los
     * sprites que simulan las raquetas en el juego.
     * 
     * Para poder controlar las pulsaciones del teclado debemos activar el focus.
     * {@link PanelJuego#PanelJuego()}
     */
    @Override
    public void pulsarTeclado(KeyEvent e) {
        // 1 jugador
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            moverIzquierda = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            moverDerecha = true;
        }
        // 2 jugadores
        if (jugador) {
            if (e.getKeyCode() == KeyEvent.VK_A) {
                moverIzquierdaDos = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_D) {
                moverDerechaDos = true;
            }
        }
    }

    @Override
    public void soltarTeclado(KeyEvent e) {
        // 1 Jugador
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            moverIzquierda = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            moverDerecha = false;
        }
        // 2 jugadores
        if (jugador) {
            if (e.getKeyCode() == KeyEvent.VK_A) {
                moverIzquierdaDos = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_D) {
                moverDerechaDos = false;
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
}