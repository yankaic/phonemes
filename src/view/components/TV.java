/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author Yan Kaic
 */
public class TV extends javax.swing.JPanel {

  public static void main(String[] args) {
    new TV();
  }

  /**
   * Creates new form TV
   */
  public TV() {

    initComponents();
   // VideoPlayer video = new VideoPlayer();
   // video.init();
//    workPanel.removeAll();
   // videoPanel.add(video.getPanel());
    //video.setSize(240, 180);
    //video.setPreferredSize(video.getSize());
    
//    Timer timer = new Timer(500, ActionEvent -> {
//      video.getPlayer().play();
//    });
//    timer.setRepeats(false);
////    timer.start();

//    timer = new Timer(1, ActionEvent -> {
//      imagePanel.repaint();
//    });
//    timer.setRepeats(false);
//    timer.start();

  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    blockLabel = new javax.swing.JLabel();
    imagePanel = new javax.swing.JPanel();
    jLabel3 = new javax.swing.JLabel();
    workPanel = new javax.swing.JPanel();
    videoPanel = new javax.swing.JPanel();

    blockLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    blockLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/outTv.png"))); // NOI18N

    setMaximumSize(new java.awt.Dimension(240, 240));
    setMinimumSize(new java.awt.Dimension(240, 240));
    setOpaque(false);
    setPreferredSize(new java.awt.Dimension(240, 240));
    setLayout(new javax.swing.OverlayLayout(this));

    imagePanel.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
    imagePanel.setFocusCycleRoot(true);
    imagePanel.setFocusTraversalPolicyProvider(true);
    imagePanel.setOpaque(false);
    imagePanel.setLayout(new java.awt.BorderLayout());

    jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/tv.png"))); // NOI18N
    imagePanel.add(jLabel3, java.awt.BorderLayout.CENTER);

    add(imagePanel);

    workPanel.setOpaque(false);
    workPanel.setLayout(new java.awt.GridBagLayout());

    videoPanel.setBackground(new java.awt.Color(0, 0, 0));
    videoPanel.setMaximumSize(new java.awt.Dimension(148, 125));
    videoPanel.setMinimumSize(new java.awt.Dimension(148, 125));
    videoPanel.setPreferredSize(new java.awt.Dimension(148, 125));
    videoPanel.setLayout(new java.awt.BorderLayout());
    workPanel.add(videoPanel, new java.awt.GridBagConstraints());

    add(workPanel);
  }// </editor-fold>//GEN-END:initComponents


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JLabel blockLabel;
  private javax.swing.JPanel imagePanel;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JPanel videoPanel;
  private javax.swing.JPanel workPanel;
  // End of variables declaration//GEN-END:variables
}
