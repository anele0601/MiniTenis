package Pantallas;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import java.awt.Color;
import Principal.Interface;
import Principal.PanelJuego;
import Principal.Sprite;
import Generico.Utilidades;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Image;

/**
 * Clase PantallaInicio.
 */
public class PantallaInicio implements Interface {
    private PanelJuego panel;
    private Color colorLetraInicio;
    private int velX, velY;
    private ArrayList<Sprite> pelotas;
    private Image fondoR;
    private BufferedImage fondo;

    private final int N_PELOTAS = 3;

    public PantallaInicio(PanelJuego panelJuego) {
        panel = panelJuego;
        pelotas = new ArrayList<>();
        inicializarPantalla();
    }

    @Override
    public void inicializarPantalla() {
        colorLetraInicio = Utilidades.BLANCO;
        for (int i = 0; i < N_PELOTAS; i++) {
            do {
                velX = velocidad();
                velY = velocidad();
            } while (velX == 0 && velY == 0);
            pelotas.add(new Sprite("imagenes/pelota.png", 50, 50, 10, 10, velX, velY));
            pelotas.add(new Sprite("imagenes/pelota.png", 50, 50, 100, 100, velX, velY));
        }
        try {
            fondo = ImageIO.read(new File("imagenes/fondoTenis.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para rellenar el fondo del componente.
     * 
     * @param g gráfico.
     */
    private void rellenarFondo(Graphics g) {
        g.drawImage(fondoR, 0, 0, null);
    }

    @Override
    public void pintarPantalla(Graphics g) {
        rellenarFondo(g);
        g.setFont(Utilidades.FUENTE_GRANDE);
        g.setColor(Utilidades.NEGRO);
        g.drawString(" MINITENIS ", 80, (panel.getHeight() / 2) - 100);
        g.fillRect(90, (panel.getHeight() / 2) - 25, 320, 40);
        g.setColor(colorLetraInicio);
        g.setFont(Utilidades.FUENTE_PEQUE);
        g.drawString("- Pulsa para comenzar a jugar - ", 105, panel.getHeight() / 2);
        for (int i = 0; i < pelotas.size(); i++) {
            pelotas.get(i).estampar(g);
        }
    }

    @Override
    public void ejecutarFrame() {
        try {
            Thread.sleep(25);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        moverPelota();
        // Cambiamos las letras de color
        colorLetraInicio = colorLetraInicio == Utilidades.BLANCO ? Utilidades.VERDE : Utilidades.BLANCO;
    }

    public void moverPelota() {
        for (int i = 0; i < pelotas.size(); i++) {
            pelotas.get(i).mover(panel.getWidth(), panel.getHeight());
        }
    }

    @Override
    public void pulsarRaton(MouseEvent e) {
        panel.setPantalla(new PantallaJuego(panel));
    }

    @Override
    public void moverRaton(MouseEvent e) {
    }

    /** Método que redimensiona la pantalla */
    @Override
    public void redimensionarPantalla() {
        fondoR = fondo.getScaledInstance(panel.getWidth(), panel.getHeight(), Image.SCALE_SMOOTH);
    }

    /**
     * Metodo para retornar la velocidad aleatoria.
     * 
     * @return entero entre -15 y 15.
     */
    public int velocidad() {
        return (int) (Math.random() * 31) - 15;
    }
}