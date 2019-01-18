/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package d.craft;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicSpinnerUI;

/**
 *
 * @author antoi
 */
public class Dialogue extends javax.swing.JFrame {

    String strIngr[] = {"", "", "", ""};
    String strNbre[] = {"", "", "", ""};
    String strType[] = {"","","",""};
    String IdIdole = "";
    
    /**
     * Creates new form Dialogue
     */
    public Dialogue(String IDidole, String NOMidole) {
        
        initComponents();
        
        this.setLocationRelativeTo(null);
        
        jPanel2.setBackground(new Color(201,191,156));

        IdIdole = IDidole;
        
        BDD bdd = new BDD();
        bdd.getConnection();
        bdd.getRecette(this);
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Recette de " + NOMidole);
        JLnomIdole.setText(NOMidole + "  x");
        setResizable(false);
        setVisible(true);
        
        Ingr1.setText(strIngr[0]);
        Ingr2.setText(strIngr[1]);
        Ingr3.setText(strIngr[2]);
        Ingr4.setText(strIngr[3]);
        
        Nbre1.setText(strNbre[0]);
        Nbre2.setText(strNbre[1]);
        Nbre3.setText(strNbre[2]);
        Nbre4.setText(strNbre[3]);
        
        Type1.setText(strType[0]);
        Type2.setText(strType[1]);
        Type3.setText(strType[2]);
        Type4.setText(strType[3]);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btAnnuler = new javax.swing.JButton();
        Ingr4 = new javax.swing.JTextField();
        Nbre4 = new javax.swing.JTextField();
        Type4 = new javax.swing.JTextField();
        Type3 = new javax.swing.JTextField();
        Nbre3 = new javax.swing.JTextField();
        Ingr3 = new javax.swing.JTextField();
        Ingr2 = new javax.swing.JTextField();
        Nbre2 = new javax.swing.JTextField();
        Type2 = new javax.swing.JTextField();
        Type1 = new javax.swing.JTextField();
        Nbre1 = new javax.swing.JTextField();
        Ingr1 = new javax.swing.JTextField();
        JLnomIdole = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btAnnuler.setBackground(new java.awt.Color(255, 97, 0));
        btAnnuler.setForeground(new java.awt.Color(255, 255, 255));
        btAnnuler.setText("Retour");
        btAnnuler.setToolTipText("");
        btAnnuler.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(86, 86, 86), 1, true));
        btAnnuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAnnulerActionPerformed(evt);
            }
        });

        Ingr4.setEditable(false);
        Ingr4.setBackground(new java.awt.Color(181, 171, 141));
        Ingr4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        Ingr4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ingr4ActionPerformed(evt);
            }
        });

        Nbre4.setEditable(false);
        Nbre4.setBackground(new java.awt.Color(181, 171, 141));
        Nbre4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Nbre4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        Nbre4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Nbre4ActionPerformed(evt);
            }
        });

        Type4.setEditable(false);
        Type4.setBackground(new java.awt.Color(181, 171, 141));
        Type4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        Type3.setEditable(false);
        Type3.setBackground(new java.awt.Color(181, 171, 141));
        Type3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        Nbre3.setEditable(false);
        Nbre3.setBackground(new java.awt.Color(181, 171, 141));
        Nbre3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Nbre3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        Nbre3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Nbre3ActionPerformed(evt);
            }
        });

        Ingr3.setEditable(false);
        Ingr3.setBackground(new java.awt.Color(181, 171, 141));
        Ingr3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        Ingr3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ingr3ActionPerformed(evt);
            }
        });

        Ingr2.setEditable(false);
        Ingr2.setBackground(new java.awt.Color(181, 171, 141));
        Ingr2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        Ingr2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ingr2ActionPerformed(evt);
            }
        });

        Nbre2.setEditable(false);
        Nbre2.setBackground(new java.awt.Color(181, 171, 141));
        Nbre2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Nbre2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        Type2.setEditable(false);
        Type2.setBackground(new java.awt.Color(181, 171, 141));
        Type2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        Type2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Type2ActionPerformed(evt);
            }
        });

        Type1.setEditable(false);
        Type1.setBackground(new java.awt.Color(181, 171, 141));
        Type1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        Nbre1.setEditable(false);
        Nbre1.setBackground(new java.awt.Color(181, 171, 141));
        Nbre1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Nbre1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        Ingr1.setEditable(false);
        Ingr1.setBackground(new java.awt.Color(181, 171, 141));
        Ingr1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        Ingr1.setMinimumSize(new java.awt.Dimension(20, 20));
        Ingr1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ingr1ActionPerformed(evt);
            }
        });

        JLnomIdole.setText("Wakata Bom Bom Youpy ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(Ingr3, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Nbre3))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(Ingr1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Ingr2, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Nbre2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Nbre1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(Ingr4, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(Nbre4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Type1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Type2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Type3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Type4, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btAnnuler, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JLnomIdole))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JLnomIdole, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Ingr1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Nbre1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Type1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Nbre2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Ingr2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Type2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Ingr3)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Nbre3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Type3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Ingr4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Nbre4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Type4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btAnnuler, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Nbre4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Nbre4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Nbre4ActionPerformed

    private void Nbre3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Nbre3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Nbre3ActionPerformed

    private void Ingr3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ingr3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Ingr3ActionPerformed

    private void Ingr2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ingr2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Ingr2ActionPerformed

    private void Ingr1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ingr1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Ingr1ActionPerformed

    private void btAnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAnnulerActionPerformed
        dispose();
    }//GEN-LAST:event_btAnnulerActionPerformed

    private void Ingr4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ingr4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Ingr4ActionPerformed

    private void Type2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Type2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Type2ActionPerformed

    //Couleurs Spinner
    public class MySpinnerUI extends BasicSpinnerUI {
        protected JButton createDecreaseButton(int orientation) {
            return new BasicArrowButton(orientation,
                                     (new Color(201,191,156)),
                                     (new Color(81,73,60)),
                                     (new Color(81,73,60)),
                                     (new Color(81,73,60)));
        }
        protected JButton createIncreaseButton(int orientation) {
    	    return new BasicArrowButton(orientation,
                                     (new Color(201,191,156)),
                                     (new Color(81,73,60)),
                                     (new Color(81,73,60)),
                                     (new Color(81,73,60)));
        }
    }
    
    public String delID;

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Dialogue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dialogue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dialogue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dialogue.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dialogue("0", "").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Ingr1;
    private javax.swing.JTextField Ingr2;
    private javax.swing.JTextField Ingr3;
    private javax.swing.JTextField Ingr4;
    private javax.swing.JLabel JLnomIdole;
    private javax.swing.JTextField Nbre1;
    private javax.swing.JTextField Nbre2;
    private javax.swing.JTextField Nbre3;
    private javax.swing.JTextField Nbre4;
    private javax.swing.JTextField Type1;
    private javax.swing.JTextField Type2;
    private javax.swing.JTextField Type3;
    private javax.swing.JTextField Type4;
    private javax.swing.JButton btAnnuler;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
