package Generico;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Clase Utilidades. En ella encontraremos los tamaños y colores de las fuentes
 * esadas en las diferentes pantallas o métodos genéricos.
 * 
 * @author Elena Nofuentes
 * @since 20-02-2021
 * @version 1.1
 * 
 */
public class Utilidades {
    /** Fuentes */
    public final static Font FUENTE_PEQUE = new Font("", Font.BOLD, 20);
    public final static Font FUENTE_MEDIANA = new Font("", Font.BOLD, 30);
    public final static Font FUENTE_INTERMEDIA = new Font("", Font.BOLD, 45);
    public final static Font FUENTE_GRANDE = new Font("", Font.BOLD, 60);

    /** Colores */
    public final static Color AZUL = Color.BLUE;
    public final static Color BLANCO = Color.WHITE;
    public final static Color AMARILLO = Color.YELLOW;
    public final static Color NEGRO = Color.BLACK;
    public final static Color ROJO = Color.RED;
    public final static Color VERDE = new Color(34, 151, 80);
    public final static Color VERDE_CLARITO = new Color(76, 145, 65);
    public final static Color VERDE_CLARITO_DOS = new Color(53, 104, 45);

    /**
     * Método para reproducir un sonido cada vez que se le llama.
     * 
     * @param sonido , nombre del sonido a reproducir.
     */
    public static void reproducirSonidos(String sonido) {
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(new File(sonido).getAbsoluteFile());
            Clip clipAudio = AudioSystem.getClip();
            clipAudio.open(audio);
            clipAudio.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("Error de reproducción.");
        }
    }
}