package connection;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class banco {
    private Connection c = null ;
    private Statement stmt = null;
            
    public banco(){
        connection();
    }
    
    private void connection(){
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:cacapalavra.db");
            stmt=c.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(banco.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(banco.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void executeUpdate(String query){
        String cmd[] = query.split(" ");
        try {
            switch (cmd[0]){
                case "update":
                    stmt.executeUpdate(query);
                case "delete":
                    stmt.executeUpdate(query);
                case "insert":
                    stmt.executeUpdate(query);
                case "create":
                    stmt.executeUpdate(query);
            }
        } catch (SQLException ex) {
            Logger.getLogger(banco.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ResultSet executeQuery(String query){
        ResultSet rs=null;
        try {
            rs = stmt.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(banco.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public void desconnect(){
        try {
            stmt.close();
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(banco.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
