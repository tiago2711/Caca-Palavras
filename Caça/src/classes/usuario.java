package classes;

import connection.banco;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import windows.cadastro;

public class usuario {
    private String nome;
    private String login;
    private String pergunta;
    private String resposta;
    public rank posicao;
    banco bd;
    
    public usuario(banco banco){
        bd = banco;
    }
    
    public void login(ResultSet rs) throws SQLException{
        nome=rs.getString("nome");
        login=rs.getString("login");
        pergunta=rs.getString("pergunta");
        resposta=rs.getString("resposta");
        rs=bd.executeQuery("select * from rank where loginusuario='"+getLogin()+"'");
        if(rs.next()){
            posicao = new rank(rs.getString("loginusuario"),rs.getInt("tempo"),rs.getFloat("pontos"));
        }else{
            posicao = new rank(getLogin(),0,0);
        }
    }
    
    public boolean redefinirConta(String login,String nome,String senhaAntiga,String senhaNova,String resposta,String pergunta){
        ResultSet rs=bd.executeQuery("select * from usuario where login='"+this.login+"' and senha='"+senhaAntiga+"'");
        try {
            if(rs.next()){
                bd.executeUpdate("update usuario set nome='"+nome+"',login='"+login+"', senha='"+senhaNova+"', pergunta='"+pergunta+"', resposta='"+resposta+"'");
                rs=bd.executeQuery("select * from usuario where login='"+login+"' and senha='"+senhaNova+"'");
                login(rs);
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public void atualizarRank(int tempo,String dificuldade){
        double calculo;
        calculo=((10000/tempo)*0.2);
        String query="insert into rank values ('"+login+"','"+tempo+"','"+dificuldade+"','"+calculo+"');";
        bd.executeUpdate(query);
    }

    public String getNome() {
        return nome;
    }

    public String getLogin() {
        return login;
    }

    public String getPergunta() {
        return pergunta;
    }

    public String getResposta() {
        return resposta;
    }
}
