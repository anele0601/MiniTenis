package Principal;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * Clase Sprite. Simula las pelotas y las barras con las que jugamos. Define su
 * aspecto y controla su movimiento.
 * 
 * @author Elena Nofuentes
 * @since 20-02-2021
 * @version 2.4
 * 
 */

public class Sprite {
    /** Atributos de la clase */
    private BufferedImage buffer;
    private Color color = Color.BLACK;
    /** Dimensión: */
    private int ancho;
    private int alto;
    /** Colocación: */
    private int posX;
    private int posY;
    /** Velocidades */
    private int velX;
    private int velY;

    /** Constructores */
    public Sprite(Color color, int ancho, int alto, int posX, int posY, int velX, int velY) {
        this.color = color;
        this.ancho = ancho;
        this.alto = alto;
        this.posX = posX;
        this.posY = posY;
        this.velX = velX;
        this.velY = velY;
        inicializarBuffer();
    }

    public Sprite(String rutaImagen, int ancho, int alto, int posX, int posY, int velX, int velY) {
        this.ancho = ancho;
        this.alto = alto;
        this.posX = posX;
        this.posY = posY;
        this.velX = velX;
        this.velY = velY;
        inicializarBuffer(rutaImagen);
    }

    /**
     * Crea un buffer vacio del color del Sprite.
     */
    private void inicializarBuffer() {
        buffer = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);
        Graphics g = buffer.getGraphics();
        g.setColor(color);
        g.fillRect(0, 0, ancho, alto);
    }

    /**
     * Crea un buffer vacio del color del Sprite por parámetros.
     */
    private void inicializarBuffer(String ruta) {
        BufferedImage imagen = null;
        buffer = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);
        try {
            imagen = ImageIO.read(new File(ruta));
            Graphics g = buffer.getGraphics();
            g.drawImage(imagen.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH), 0, 0, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para estampar los cuadrados en el gráfico
     * 
     * @param g graphics
     */
    public void estampar(Graphics g) {
        g.drawImage(buffer, posX, posY, null);
    }

    /**
     * Método para mover los sprites, dentro de un ancho y alto concretos.
     * 
     * @param width  ancho actual
     * @param height alto actual
     */
    public void mover(int width, int height) {
        // Mover el cuadrado
        posX = (posX + velX);
        posY = (posY + velY);

        // Movimiento derecha horizontalmente.
        if (posX + ancho >= width) {
            velX = -(Math.abs(velX));
        }
        // Movimiento izquierda horizontalmente.
        if (posX < 0) {
            velX = (Math.abs(velX));
        }
        // Movimiento arriba verticalmente.
        if (posY + ancho >= height) {
            velY = -(Math.abs(velY));
        }
        // Movimiento hacia abajo verticalmente.
        if (posY < 0) {
            velY = (Math.abs(velY));
        }
    }

    /**
     * Método para mover los sprites sin controlar que estén dentro de la pantalla.
     */

    public void moverSinBordes() {
        posY = (posY + velY);
        posX = (posX + velX);
    }

    /**
     * Método para mover un sprite hacia la derecha.
     * 
     * @param width        ancho del panel.
     * @param anchoRaqueta ancho del sprite que movemos.
     */
    public void moverDerecha(int width, int anchoRaqueta) {
        if (posX + anchoRaqueta < width) {
            posX = posX + ancho / 6;
        }
    }

    /**
     * Método para mover un sprite hacia la izquierda.
     */
    public void moverIzquierda() {
        if (posX >= 0) {
            posX = posX - ancho / 6;
        }
    }

    /**
     * Metodo para saber cuando los sprites colisionan.
     * 
     * @param sprite Sprite raqueta
     * @return true o false, dependiendo de si hay colisión o no.
     */
    public boolean hayColision(Sprite sprite) {
        boolean colisionX = posX <= sprite.posX ? (posX + ancho > sprite.posX) : (sprite.posX + sprite.ancho >= posX);
        boolean colisionY = posY <= sprite.posY ? (posY + alto > sprite.posY) : (sprite.posY + sprite.alto >= posY);

        return colisionX && colisionY;
    }

    /**
     * Método que comprueba por donde se han realizado las colisiones en el modo 1
     * jugador. En este caso las colisiones pueden ser por arriba, por la derecha o
     * por la izquierda.
     */
    public void colisionar(Sprite sprite) {
        // ARRIBA
        if (posY + alto >= sprite.getPosY() && posX + (ancho / 2) > sprite.getPosX()
                && posX + (ancho / 2) < sprite.getPosX() + sprite.getAncho()) {
            velY = -Math.abs(velY);
            if ((posY + alto) - sprite.getPosY() > 0) {
                posY = (posY - (posY + alto - sprite.getPosY()));
            }

        } else {
            // DERECHA
            if (posX <= sprite.getPosX() + sprite.getAncho() && (posX + ancho >= sprite.getPosX() + sprite.getAncho()
                    && posY < sprite.getPosY() + sprite.getAlto())) {
                velY = -Math.abs(velY);
                velX = Math.abs(velX);
                if (posX - (sprite.getPosX() + sprite.getAncho()) < 0) {
                    posX = (posX + (sprite.getPosX() + sprite.getAncho() - posX));
                    if ((posY + alto) - sprite.getPosY() > 0) {
                        posY = (posY - (posY + alto - sprite.getPosY()));
                    }
                }
            }
            // IZQUIERDA
            if (posX + ancho >= sprite.getPosX()
                    && (posX < sprite.getPosX() && posY < sprite.getPosY() + sprite.getAlto())) {
                velY = -Math.abs(velY);
                velX = -Math.abs(velX);
                if (sprite.getPosX() - (posX + ancho) < 0) {
                    posX = (posX - ((posX + ancho) - sprite.getPosX()));
                    if ((posY + alto) - sprite.getPosY() > 0) {
                        posY = (posY - (posY + alto - sprite.getPosY()));
                    }
                }
            }
        }
    }

    /**
     * Método que comprueba por donde se han realizado las colisiones en el modo 2
     * jugador. En este caso las colisiones pueden ser por abajo, por la derecha o
     * por la izquierda.
     */
    public void colisionarDosJugadores(Sprite sprite) {
        // ABAJO
        if (posY <= sprite.getPosY() + sprite.getAlto() && posX + (ancho / 2) > sprite.getPosX()
                && posX + (ancho / 2) < sprite.getPosX() + sprite.getAncho()) {
            velY = Math.abs(velY);
            if ((sprite.getPosY() + sprite.getAlto()) - posY > 0) {
                posY = (posY + (sprite.getPosY() + sprite.getAlto() - posY));
            }
        } else {
            // DERECHA
            if (posX <= sprite.getPosX() + sprite.getAncho() && (posX + ancho >= sprite.getPosX() + sprite.getAncho()
                    && posY < sprite.getPosY() + sprite.getAlto())) {
                velX = Math.abs(velX);
                velY = Math.abs(velY);
                if ((posX - sprite.getPosX()) + sprite.getAncho() < 0) {
                    posX = (posX + (sprite.getPosX() + sprite.getAncho() - posX));
                }

                if ((sprite.getPosY() + sprite.getAlto()) - posY > 0) {
                    posY = (posY + (sprite.getPosY() + sprite.getAlto() - posY));
                }
            }
            // IZQUIERDA
            if (posX + ancho >= sprite.getPosX()
                    && (posX < sprite.getPosX() && posY < sprite.getPosY() + sprite.getAlto())) {
                velY = Math.abs(velY);
                velX = -Math.abs(velX);
                if ((posX + ancho) - sprite.getPosX() < 0) {
                    posX = (posX - (posX + ancho - sprite.getPosX()));
                }

                if ((sprite.getPosY() + sprite.getAlto()) - posY > 0) {
                    posY = (posY + (sprite.getPosY() + sprite.getAlto() - posY));
                }
            }
        }

    }

    /** Getters y Setters */
    public BufferedImage getBuffer() {
        return buffer;
    }

    public void setBuffer(BufferedImage buffer) {
        this.buffer = buffer;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getVelX() {
        return velX;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public int getVelY() {
        return velY;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }

}