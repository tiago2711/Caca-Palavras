package windows;

import classes.mapa;
import classes.usuario;
import connection.banco;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import javax.swing.JOptionPane;

public class jogada extends javax.swing.JFrame {
    
    banco bd;
    usuario user;
    mapa mapa;
    
    public jogada(banco bd,usuario user) {
        this.bd=bd;
        this.user=user;
        initComponents();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/icon.png")));
        this.setSize(400,400);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        box.setLayout(new BorderLayout());
        this.setResizable(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem2 = new javax.swing.JMenuItem();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        box = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        nivelFacil = new javax.swing.JMenuItem();
        nivelMedio = new javax.swing.JMenuItem();
        nivelDificil = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        editarConta = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        listarRank = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        jMenuItem2.setText("jMenuItem2");

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Caça Palavras");

        javax.swing.GroupLayout boxLayout = new javax.swing.GroupLayout(box);
        box.setLayout(boxLayout);
        boxLayout.setHorizontalGroup(
            boxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 402, Short.MAX_VALUE)
        );
        boxLayout.setVerticalGroup(
            boxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 306, Short.MAX_VALUE)
        );

        jMenu1.setText("Novo Jogo");

        nivelFacil.setText("Nivel Facil");
        nivelFacil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nivelFacilActionPerformed(evt);
            }
        });
        jMenu1.add(nivelFacil);

        nivelMedio.setText("Nivel Medio");
        nivelMedio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nivelMedioActionPerformed(evt);
            }
        });
        jMenu1.add(nivelMedio);

        nivelDificil.setText("Nivel Dificl");
        nivelDificil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nivelDificilActionPerformed(evt);
            }
        });
        jMenu1.add(nivelDificil);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Conta");

        editarConta.setText("Editar Conta");
        editarConta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarContaActionPerformed(evt);
            }
        });
        jMenu2.add(editarConta);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Consultar");
        jMenu3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu3ActionPerformed(evt);
            }
        });

        listarRank.setText("Rank Facil");
        listarRank.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listarRankActionPerformed(evt);
            }
        });
        jMenu3.add(listarRank);

        jMenuItem1.setText("Rank Medio");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuItem3.setText("Rank Dificil");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(box, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(box, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nivelDificilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nivelDificilActionPerformed
        mapa = new mapa("dificil",bd,user);
        this.setSize(530,620);
        box.removeAll();
        box.add(mapa, BorderLayout.CENTER);
        box.revalidate();
        box.updateUI();
        this.setLocationRelativeTo(null);
    }//GEN-LAST:event_nivelDificilActionPerformed

    private void listarRankActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listarRankActionPerformed
         escolheRank("facil");
    }//GEN-LAST:event_listarRankActionPerformed

    private void editarContaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarContaActionPerformed
        editar edit = new editar(user,bd);
        box.removeAll();
        box.add(edit, BorderLayout.CENTER);
        box.revalidate();
        box.updateUI();
    }//GEN-LAST:event_editarContaActionPerformed

    private void nivelFacilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nivelFacilActionPerformed
        mapa = new mapa("facil",bd,user);
        this.setSize(530,620);
        box.removeAll();
        box.add(mapa, BorderLayout.CENTER);
        box.revalidate();
        box.updateUI();
        this.setLocationRelativeTo(null);
    }//GEN-LAST:event_nivelFacilActionPerformed

    private void nivelMedioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nivelMedioActionPerformed
        mapa = new mapa("medio",bd,user);
        this.setSize(700,710);
        box.removeAll();
        box.add(mapa, BorderLayout.CENTER);
        box.revalidate();
        box.updateUI();
        this.setLocationRelativeTo(null);
    }//GEN-LAST:event_nivelMedioActionPerformed

    private void jMenu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu3ActionPerformed

    }//GEN-LAST:event_jMenu3ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        escolheRank("medio");
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        escolheRank("dificil");
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void escolheRank(String escRank){
        rankGeral rank = new rankGeral(bd,escRank);
        box.removeAll();
        box.add(rank, BorderLayout.CENTER);
        box.revalidate();
        box.updateUI();
    }
    
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel box;
    private javax.swing.JMenuItem editarConta;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem listarRank;
    private javax.swing.JMenuItem nivelDificil;
    private javax.swing.JMenuItem nivelFacil;
    private javax.swing.JMenuItem nivelMedio;
    // End of variables declaration//GEN-END:variables
}
