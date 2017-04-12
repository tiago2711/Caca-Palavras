package windows;

import classes.usuario;
import connection.banco;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class login extends javax.swing.JFrame {

    banco bd = new banco();
    
    public login() {
        initComponents();
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/icon.png")));
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setSize(250,297);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        login = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        confirma = new javax.swing.JButton();
        realizarCadastro = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        esqueci_senha = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        senha = new javax.swing.JPasswordField();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Caça Palavras");

        login.setText(null);

        jLabel1.setText("Login :");

        jLabel3.setText("Senha :");

        confirma.setText("OK");
        confirma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmaActionPerformed(evt);
            }
        });

        realizarCadastro.setText("Criar Conta");
        realizarCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                realizarCadastroActionPerformed(evt);
            }
        });

        jLabel4.setText("Não Possue Conta ?");

        esqueci_senha.setText("Esqueci minha senha");
        esqueci_senha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                esqueci_senhaActionPerformed(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/cacapalavras.png"))); // NOI18N
        jLabel5.setText(null);

        senha.setText(null);
        senha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                senhaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(login)
                            .addComponent(senha, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE))
                        .addGap(117, 117, 117))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(realizarCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                        .addGap(115, 115, 115))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(confirma, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(esqueci_senha, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(senha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirma)
                    .addComponent(esqueci_senha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(realizarCadastro)
                    .addComponent(jLabel4)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void confirmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmaActionPerformed
        String query;
        query = "select * from usuario where login='"+login.getText().trim()+"' and senha='"+senha.getText().trim()+"';";
        ResultSet rs = bd.executeQuery(query);
        try {
            if(rs.next()){
                usuario user = new usuario(bd);
                user.login(rs);              
                jogada jogo = new jogada(bd,user);
                this.setVisible(false);
            }else{
                JOptionPane.showMessageDialog(rootPane, "Login ou senha incorreta");
                login.setText("");
                senha.setText("");
            }
        } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_confirmaActionPerformed

    private void realizarCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_realizarCadastroActionPerformed
        cadastro realiza = new cadastro(bd);
    }//GEN-LAST:event_realizarCadastroActionPerformed

    private void esqueci_senhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_esqueci_senhaActionPerformed
        String login,pergunta,resposta;
        login=JOptionPane.showInputDialog("Digita o login");
        pergunta=JOptionPane.showInputDialog("Digita a pergunta secreta");
        resposta=JOptionPane.showInputDialog("Digita a resposta secreta");
        String query = "select * from usuario where login='"+login.trim()+"' and pergunta='"+pergunta.trim()+"' and resposta='"+resposta.trim()+"'";
        ResultSet rs = bd.executeQuery(query);
        try {
            if(rs.next()){
                JOptionPane.showMessageDialog(rootPane, "Usuario encontrado");
                String senha = JOptionPane.showInputDialog("Digita a nova senha");
                query = "update usuario set senha='"+senha+"' where login='"+login+"';";
                bd.executeUpdate(query);
                JOptionPane.showMessageDialog(rootPane, "Senha trocada!\nBom jogo");
            }else{
                JOptionPane.showMessageDialog(rootPane, "Não existe usuario com estas informaçoes");
            }
        } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_esqueci_senhaActionPerformed

    private void senhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_senhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_senhaActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton confirma;
    private javax.swing.JButton esqueci_senha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField login;
    private javax.swing.JButton realizarCadastro;
    private javax.swing.JPasswordField senha;
    // End of variables declaration//GEN-END:variables
}
