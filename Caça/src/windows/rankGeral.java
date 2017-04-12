package windows;

import connection.banco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class rankGeral extends javax.swing.JPanel {
    banco bd;
    Object [][] matriz;
    
    public rankGeral(banco bd,String escolha) {
        this.bd=bd;
        listar(escolha);
        initComponents(); 
    }

    private void listar(String escolha){
        ResultSet rs;
        rs=bd.executeQuery("select * from rank where nivel='"+escolha+"'");
        try {
            int aux=0;
            while(rs.next()){
                aux++;
            }
            rs=bd.executeQuery("select loginusuario,tempo,pontos from rank where nivel='"+escolha+"' order by nivel");
            if(rs.next()){
                System.out.println(aux);
                matriz=new String [aux][3];
                for(int i=0;rs.next();i++){
                    matriz[i][0]=rs.getString("loginusuario");
                    matriz[i][1]=rs.getString("tempo");
                    matriz[i][2]=rs.getString("pontos");
                }
   
            }
        } catch (SQLException ex) {
            Logger.getLogger(rankGeral.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            matriz,
            new String [] {
                "Nome", "Tempo", "Pontos"
            }
        ));
        jScrollPane1.setViewportView(tabela);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabela;
    // End of variables declaration//GEN-END:variables
}
