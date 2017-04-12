package classes;

import javax.swing.JOptionPane;

public class rank {
   private String loginusuario;
   private int tempo;
   private float pontos;
   
   public rank(String login,int tempo,float pontos){
       this.loginusuario=login;
       this.tempo=tempo;
       this.pontos=pontos;
   }
   
   public float exibir(){
       return pontos;
   }
}
