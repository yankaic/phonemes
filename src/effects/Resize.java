package effects;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import static java.lang.Thread.sleep;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JComponent;

/**
 * Classe para movimentacao de qualquer JComponent (JPanel, JLabel, etc.) de uma
 * forma generica e rapida. Possui algumas funcoes estaticas para deixa-la
 * pronta pra uso.
 *
 * @author Yan Kaic
 * @since 2015
 */
public class Resize extends Thread {

  private final JComponent object;
  private final Dimension startPoint;
  private final Dimension distance;
  private final double sine;
  private final double cosine;
  private final double hypotenuse;
  private final double speed;

  /**
   *
   */
  public static final Semaphore aniMutex = new Semaphore(1);
  private static boolean anitag = false;
  private static final Semaphore mutex = new Semaphore(1);

  /**
   * Construtor padrao para a animacao de um objeto. <br>
   * Este construtor apenas configura como a animacao sera realizada. Para que a
   * animacao comece, basta utilizar a funcao start() de Thread.
   *
   * @param object objeto a ser movimentado
   * @param endPoint ponto que o objeto vai ficar quando a animacao terminar.
   * @param time long: tempo da animação em millisegundos
   */
  public Resize(JComponent object, Dimension endPoint, long time) {
    this.object = object;
    startPoint = object.getSize();

    distance = new Dimension();
    distance.width = endPoint.width - startPoint.width;
    distance.height = endPoint.height - startPoint.height;
    
    hypotenuse = Math.sqrt(Math.pow(distance.width, 2) + Math.pow(distance.height, 2));
    sine = distance.height / hypotenuse;
    cosine = distance.width / hypotenuse;
    
    this.speed=(hypotenuse*100)/(time*6);
  }//fim construtor

  @Override
  public void run() {
    try {
      boolean self = false;
      mutex.acquire();
      if (!anitag) {
        aniMutex.acquire();
        anitag = true;
        self = true;
      }
      mutex.release();

      object.setBorder(BorderFactory.createLineBorder(Color.red, 2));
      sleep(500);

      //Vai aumentando a distancia e descobrindo seus pontos x e y.
      for (int variableHypotenuse = 0; variableHypotenuse < hypotenuse; variableHypotenuse += speed) {

        int AdjacentCateto = (int) (cosine * variableHypotenuse) + startPoint.width;
        int OppositiveCateto = (int) (sine * variableHypotenuse) + startPoint.height;
        object.paintImmediately(object.getLocation().x, object.getLocation().y, AdjacentCateto, OppositiveCateto);
        sleep(20);

      }
      //coloca o objeto no ponto final que foi pedido.
      int adjacentCateto = (int) (cosine * hypotenuse) + startPoint.width;
      int oppositiveCateto = (int) (sine * hypotenuse) + startPoint.height;
      object.paintImmediately(object.getLocation().x, object.getLocation().y, adjacentCateto, oppositiveCateto);

      sleep(500);
      object.setBorder(BorderFactory.createEmptyBorder());
      if (self) {
        aniMutex.release();
        anitag = false;
      }
    }
    catch (InterruptedException ex) {
      Logger.getLogger(Translation.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  /**
   * Esta funcao permite que um objeto seja movimento sem precisar instanciar o
   * objeto animacao dentro do seu codigo. Basta inserir o objeto e ponto final
   * desejado. Nota: observe a velocidade antes de usar esta funcao.
   *
   * @param objeto objeto a ser movido
   * @param destino ponto que deseja que o objeto fique depois da animacao.
   * @param time  long : tempo da animação em milissegundos
   */
  public static void moveObject(JComponent objeto, Point destino, long time) {
    Translation animacao = new Translation(objeto, destino, time);
    animacao.start();
  }//fim moveObject

}//fim class