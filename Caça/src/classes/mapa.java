package classes;

import connection.banco;
import java.awt.BorderLayout;
import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.SOUTH;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.GroupLayout.Alignment.CENTER;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class mapa extends javax.swing.JPanel{
    private palavra palavraCerta;
    private palavra palavraErrada;
    private JButton [][]matriz = new JButton[20][10];
    private JButton []linha = new JButton[10];
    private int numeroPalavras;
    private palavra[] certas=new palavra[6];
    private palavra[] errada=new palavra[6];
    private banco bd;
    private JTextField dica1 = new JTextField();
    private JTextField dica2 = new JTextField();
    private JTextField dica3 = new JTextField();
    private JTextField dica4 = new JTextField();
    private JTextField dica5 = new JTextField();
    private JTextField dica6 = new JTextField();
    private JPanel primeiro = new JPanel();
    private JPanel segundo = new JPanel();
    public int tempo;
    public ArrayList conferir = new ArrayList();
    private usuario user;
    private JTextField cronometro = new JTextField();
    MouseEventDemo mouse = new MouseEventDemo();
    String nivel;
    
    
    public mapa(String nivel,banco bd,usuario user){
        this.bd=bd;
        this.nivel=nivel;
        this.user=user;
        this.tempo=0;
        this.numeroPalavras=6;
        gerarMapa(nivel);
        setaBotao();
        this.add(primeiro);
        this.add(segundo);
        primeiro.setLayout(new GridLayout(20,10));
        primeiro.setSize(800,600);
        segundo.add(dica1);
        segundo.add(dica2);
        segundo.add(dica3);
        segundo.add(dica4);
        segundo.add(dica5);
        segundo.add(dica6);
        dica1.setEditable(false);
        dica2.setEditable(false);
        dica3.setEditable(false);
        dica4.setEditable(false);
        dica5.setEditable(false);
        dica6.setEditable(false);
        this.addMouseListener(mouse);
        (new Thread(new Cronometro())).start();
        (new Thread(new ControleMouse())).start();
    }
    
    private void gerarMapa(String nivel){
        escolhe();
        cruzar();
        completa();
        switch (nivel){
            case "facil":
                dica1.setText("1°-"+certas[0].getNome());
                dica2.setText("2°-"+certas[1].getNome());
                dica3.setText("3°-"+certas[2].getNome());
                dica4.setText("4°-"+certas[3].getNome());
                dica5.setText("5°-"+certas[4].getNome());
                dica6.setText("6°-"+certas[5].getNome());
                
                break;
            case "medio":
                dica1.setText("1°-"+certas[0].getDica());
                dica2.setText("2°-"+certas[1].getDica());
                dica3.setText("3°-"+certas[2].getDica());
                dica4.setText("4°-"+certas[3].getDica());
                dica5.setText("5°-"+certas[4].getDica());
                dica6.setText("6°-"+certas[5].getDica());
                segundo.setLayout(new GridLayout(6,1));
                break;
            case "dificil":
                dica1.setText("Nivel Difcil!! Unica dica : Palavras relacionadas com APSOO");
                dica2.setVisible(false);
                dica3.setVisible(false);
                dica4.setVisible(false);
                dica5.setVisible(false);
                dica6.setVisible(false);
                break;
        }
    }
    
    private void setaBotao(){
        for(int i=0;i<20;i++){
            for(int y=0;y<10;y++){
                matriz[i][y].setBackground(Color.WHITE);
                primeiro.add(matriz[i][y]);
                matriz[i][y].addMouseListener(new MouseEventDemo());
            }
        }
    }
    
    private void cruzar(){
        Random randomGenerator = new Random();
        int x,y,ang;
        boolean foi;
        for(int i=0;i<certas.length;i++){
            foi = true;
            while(foi){
                x=randomGenerator.nextInt(9);
                y=randomGenerator.nextInt(19);
                ang=randomGenerator.nextInt(2);
                String aux[]=certas[i].getNome().split("");             
                if(ang==1){// 1=palavra segue no X  2=palavra segue o Y
                    if(confere(y,x,ang,aux.length)){
                        for(int j=0;j<aux.length;j++){
                            JButton botao = new JButton();
                            botao.setText(aux[j]);
                            matriz[y][x+j]=botao;
                        }
                        foi=false;
                    }
                }else{
                    if(confere(y,x,ang,aux.length)){
                        for(int j=0;j<aux.length;j++){
                            JButton botao = new JButton();
                            botao.setText(aux[j]);
                            matriz[y+j][x]=botao;
                        }
                        foi=false;
                    }
                }
            }
        }
        for(int i=0;i<errada.length;i++){
            foi = true;
            while(foi){
                x=randomGenerator.nextInt(9);
                y=randomGenerator.nextInt(19);
                ang=randomGenerator.nextInt(2);
                String aux[]=errada[i].getNome().split("");             
                if(ang==1){// 1=palavra segue no X  2=palavra segue o Y
                    if(confere(y,x,ang,aux.length)){
                        for(int j=0;j<aux.length;j++){
                            JButton botao = new JButton();
                            botao.setText(aux[j]);
                            matriz[y][x+j]=botao;
                        }
                        foi=false;
                    }
                }else{
                    if(confere(y,x,ang,aux.length)){
                        for(int j=0;j<aux.length;j++){
                            JButton botao = new JButton();
                            botao.setText(aux[j]);
                            matriz[y+j][x]=botao;
                        }
                        foi=false;
                    }
                }
            }
        }
        //
    }
    
    private void completa(){
        // Por algum motivo o Java transforma o W em ... no nome do Botão. Por esse motivo tirei ele
        String []letras = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","Z","Y","Z"};
        int letra;
        Random randomGenerator = new Random();
        for(int i=0;i<10;i++){
            for(int x=0;x<20;x++){
                letra=randomGenerator.nextInt(24);
                if(matriz[x][i]==null){
                    JButton botao = new JButton();
                    botao.setText(letras[letra]);
                    botao.setPreferredSize(new Dimension(5,5));
                    matriz[x][i]=botao;
                }
            }
        }
    }
    
    private boolean confere(int y,int x,int ang,int m){
        if(ang==1){
            for(int i=0;i<m;i++){
                if(x+i>=10){
                    return false;
                }else{
                    if(matriz[y][x+i]!=null){
                        return false;
                    }  
                }
            }
        }else{
           for(int i=0;i<m;i++){
               if(y+i>=20){
                   return false;
               }else{
                  if(matriz[y+i][x]!=null){
                    return false;
                   }  
               }
           } 
        }
        return true;
    }
    
    private void escolhe(){
        String query = "select * from palavras where tipo='certa'";
        Random randomGenerator = new Random();
        int indice;
        ResultSet rs = bd.executeQuery(query);
        try {
            ArrayList pega = new ArrayList();
            String bug=""; // Arrumar questão de palavras duplicadas
            while(rs.next()){
                palavra aux = new palavra(rs.getString("palavra"),rs.getString("dica"));
                if(aux.getNome().equals(bug)!=true){
                    pega.add(aux);
                }
                bug=aux.getNome();
            }
            for(int i=0;i<6;i++){
                indice = randomGenerator.nextInt(pega.size());
                certas[i] = (palavra) pega.get(indice);
                pega.remove(indice);
            }
            pega.removeAll(pega);
            query = "select * from palavras where tipo='errada'";
            rs = bd.executeQuery(query);
            while(rs.next()){
                palavra aux = new palavra(rs.getString("palavra"),"");
                pega.add(aux);
            }
            for(int i=0;i<6;i++){
                indice = randomGenerator.nextInt(pega.size());
                errada[i] = (palavra) pega.get(indice);
                pega.remove(indice);
            }
        } catch (SQLException ex) {
            Logger.getLogger(mapa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public boolean conferePalavra(String x){
        for(int i=0;i<certas.length;i++){ 
            if(certas[i].getNome().equals(x)){
                numeroPalavras=numeroPalavras-1;
                switch(i){
                    case 0:
                        dica1.setBackground(Color.GREEN);
                        break;
                    case 1:
                        dica2.setBackground(Color.GREEN);
                        break;
                    case 2:
                        dica3.setBackground(Color.GREEN);
                        break;
                    case 3:
                        dica4.setBackground(Color.GREEN);
                        break;
                    case 4:
                        dica5.setBackground(Color.GREEN);
                        break;
                    case 5:
                        dica6.setBackground(Color.GREEN);
                        break;                       
                }
                return true;
            }
        }
        return false;
    }
    
    public void numeroResto(){
        if(numeroPalavras<=0){
            this.removeMouseListener(mouse);
            JOptionPane.showMessageDialog(primeiro, "Parabens! Você conseguiu achar todas palavras!");
            user.atualizarRank(tempo,nivel);           
        }
    }

    class Cronometro implements Runnable{
        @Override
        public void run() {
          while(true){
              try {
                  tempo++;
                  cronometro.setText(String.valueOf(tempo));
                  Thread.sleep(1000);
              } catch (InterruptedException ex) {
                  Logger.getLogger(mapa.class.getName()).log(Level.SEVERE, null, ex);
              }
          }  
        }
    }
    
    public void limpaBotao() throws InterruptedException{
        for(int i=0;i<conferir.size();i++){
            JButton aux;
            aux=(JButton) conferir.get(i);
            aux.setBackground(Color.WHITE);
            aux.setOpaque(true);
        }
        conferir.removeAll(conferir);
    }
    
    // Controle para limpar botão caso usuario aperte errado
    class ControleMouse implements Runnable{
        @Override
        public void run() {
            try {
                int i=conferir.size();
                while(true){
                    Thread.sleep(2000);
                    if(i==conferir.size()){
                       limpaBotao();
                       i=0;
                    }else{
                       i=conferir.size();
                    }
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(mapa.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
      
    class MouseEventDemo extends MouseAdapter{
        public void mouseClicked(MouseEvent e){
            try{
                JButton aux;
                conferir.add(e.getComponent());
                aux=(JButton)e.getComponent();
                aux.setBackground(Color.red);
                if(conferir.size()>=2){
                    String teste="";
                    for(int x=0;x<=conferir.size()-1;x++){
                        aux=(JButton)conferir.get(x);
                        teste=teste+aux.getText();
                    }
                    if(conferePalavra(teste)){
                        for(int x=0;x<=conferir.size()-1;x++){
                            aux=(JButton)conferir.get(x);
                            aux.setEnabled(false);
                            aux.setBackground(Color.GREEN);
                        }     
                        conferir.removeAll(conferir);
                        teste="";
                        numeroResto();
                    }
                }
            }catch (ClassCastException ex) {
                Logger.getLogger(mapa.class.getName()).log(Level.SEVERE, null, ex);
                conferir.removeAll(conferir);
            }
        }
    }
}


