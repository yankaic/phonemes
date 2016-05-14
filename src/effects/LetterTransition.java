/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package effects;

import entities.FadeComponent;
import entities.GameLabel;
import entities.GameObject;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.media.AudioClip;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import view.SwipeView;
import view.components.Letter;

/**
 *
 * @author shanks
 */
public class LetterTransition extends Thread {

    //letra cuja transição será animada
    private Letter letter;

    //typo da transição
    private boolean type;
    public static boolean UPPER_CASE = true;
    public static boolean LOWER_CASE = false;

    //tamanho da tela
    private Dimension viewDimension;

    //posição do centro da tela
    private Point finalPosition;

    //posição inicial da label sendo animada
    private Point initialPosition;

    //tamanho inicial da label sendo animada
    private Dimension initialDimension;

    //tamanho final da label sendo animada
    private Dimension finalDimension;

    private AudioClip audioClip;

    private String audioSource;

    private static Translation tranlation;

    private static Resize resize;

    private GameLabel image;

    private static Fade fade;

    /**
     * Método construtor da classe de animação da transição de uma letra
     *
     * @param letter Letter : letra que será animada
     * @param type boolean : tipo da animação pode assumir os valores:
     * UPPER_CASE ou LOWER_CASE
     * @param viewDimension Dimension: tamanho da janela
     */
    public LetterTransition(Letter letter, Dimension viewDimension, boolean type) {
        this.letter = letter;//letra sendo animada
        this.type = type;//tipo da animação

        //configurações default
        initialPosition = new Point(770, 150); //posição inicial das labels sendo animadas
        initialDimension = new Dimension(0, 0); //dimensão inicial da label
        finalDimension = new Dimension(255, 369);//dimensão final da label
        this.viewDimension = viewDimension;

    }//fim construtor

    public LetterTransition() {

    }

    /**
     * Método que faz a animação
     */
    @Override
    @SuppressWarnings("empty-statement")
    public void run() {
        try {

            if (type) {

            } //animação das letras minúsculas
            else {
                for (int countImages = 1; countImages <= 3; countImages++) {
                    //icone do examplo
                    String path = letter.getLowerCasePath() + "examples"
                            + File.separator + countImages + File.separator;
                    ImageIcon icon = new ImageIcon(new URL(path + "image.png"));
                    image = new GameLabel();
                    image.setIcon(icon);

                    //posição e dimensão iniciais da label
                    image.setLocation(initialPosition);
                    image.setSize(initialDimension);

                    //calculando a posição final e a dimensão final
                    finalDimension = new Dimension(icon.getIconWidth(), icon.getIconHeight());
                    finalPosition = new Point((int) (viewDimension.width / 2) - (finalDimension.width / 2),
                            (int) (viewDimension.height / 2) - (finalDimension.height / 2));

                    //adicionando o exemplo na janela
                    SwipeView.addTransitionLabels(image);

                    //animando a translação e redimensionamento da label
                    tranlation = Translation.move(image, finalPosition, 2000);
                    resize = Resize.resize(image, finalDimension, 2000);

                    // audioSource = path + "audio.aiff";
                    //audioClip = new AudioClip(audioSource);
                    //              audioClip.play();
                    sleep(6000);

                    //animando o retorno da label para sua posição inicial
                    tranlation = Translation.move(image, initialPosition, 2000);
                    resize = Resize.resize(image, initialDimension, 2000);

                    Thread.sleep(4000);
                }//fim for
            } //fim if-else
        } catch (InterruptedException | MalformedURLException ex) {
            Logger.getLogger(LetterTransition.class.getName()).log(Level.SEVERE, null, ex);
        }
        //fim try-catch
        //fim try-catch
    }//fim run

    /**
     * Método que realiza a transição de uma letra minuscúla a animação de uma
     * letra minúscula é mostrar uma label com a imagem dos objetos da letra e o
     * áudio das palavras mostradas nas imagens
     *
     * @param letter Letter : letra que será animada
     * @param viewSize Point : tamanho (width, height) da tela
     */
    public static void lowerCaseLetter(Letter letter, Dimension viewDimension) {
        new LetterTransition(letter, viewDimension, LOWER_CASE).start();
    }//fim lowerCaseLetter

    /**
     * Método que realiza a transição de uma letra maiúscula a animação de uma
     * letra maiúscula é mostrar um video da pronuncia de uma palavra
     *
     * @param letter Letter : letra que será animada
     * @param viewSize Point: tamanho (width, height) da tela
     */
    public static void upperCaseLetter(Letter letter, Dimension viewDimension) {
        new LetterTransition(letter, viewDimension, UPPER_CASE).start();
    }//fim upperCaseLetter

    /**
     * Método que pausa a animação 
     */
    public void pause() {
        tranlation.suspend();
        resize.suspend();
        this.suspend();
    }//fim pause

    /**
     * Método que inicia ou retoma a animação
     */
    public void play() {
        this.resume();
        resize.resume();
        tranlation.resume();
    }//fim play

    /**
     * Método que reinicia a animação
     * @return LetterTransition
     */
    public LetterTransition replay() {
        LetterTransition letterTransition = new LetterTransition(letter, viewDimension, type);
        letterTransition.start();
        image.setVisible(false);
        this.stop();
        return letterTransition;
    }//fim replay

    /**
     * Método que habilita o audio da animação
     */
    public void enableAudio() {

    }//fim enableAudio

    /**
     * Método que desabilita o audio da animação
     */
    public void disableAudio() {

    }//fim disableAudio

    /**
     * Método que encerra a animação
     */
    public void close() {
        if (tranlation != null) {
            tranlation.stop();
        }//fim if
        if (resize != null) {
            resize.stop();
        }//fim if
        if (image != null) {
            image.setVisible(false);
        }//fim if
        this.stop();
    }//fim close

    /**
     * Método que inicia uma animação dependendo da visibilidade do painel lowerCaseLetterAnimation
     * @param fadeComponent FadeComponent
     * @param fadeScale float 
     * @param visibility boolean 
     */
    public static void fade(FadeComponent fadeComponent, float fadeScale, boolean visibility) {
        if(visibility){//fade in
            fade = Fade.fadeIn(fadeComponent, 3000, fadeScale);
        }else{//fade out
            fade  = Fade.fadeOut(fadeComponent, 1000, 0f);  
        }//fim if-else
    }//fim fade
}//fim class
