package windows;

import connection.banco;

public class povoar {
    public static void main(String []args){
        banco bd = new banco();
        float aux = 50;
        String query;
        query = "insert into palavras values ('DESIGN','certa','a concepção de um produto');";
        //query = "";
        bd.executeUpdate(query);
        bd.desconnect();
        System.out.println("foi");
    }
}
