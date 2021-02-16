package Pantallas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import Principal.Interface;
import Principal.PanelJuego;

/**
 * Clase PantallaGanar.
 */

public class PantallaGanar implements Interface {
    private JPanel panel;

    public PantallaGanar(PanelJuego panel, int puntuacion, String tiempo) {
        this.panel = panel;
    }

    @Override
    public void inicializarPantalla() {
    }

    @Override
    public void pintarPantalla(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, panel.getWidth(), panel.getHeight());
    }

    @Override
    public void ejecutarFrame() {
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

    }

    @Override
    public void soltarTeclado(KeyEvent e) {
        // TODO Auto-generated method stub

    }
}