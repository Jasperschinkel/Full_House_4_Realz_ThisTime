import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception {

        JFrame frame = new ToevoegenSpeler();


    }

    public static Connection getConnection() throws Exception {
        try{
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://meru.hhs.nl/18095240";
            String username = "18095240";
            String password = "Ene3shaise";
            Class.forName(driver);

            Connection conn = DriverManager.getConnection(url,username,password);
            return conn;
        } catch(Exception e){
            System.out.println(e);}


        return null;
    }



    public static ArrayList<String> getLijst() throws Exception{
        try{
            Connection con = getConnection();
            PreparedStatement state = con.prepareStatement("SELECT * FROM Spelers");
            ResultSet result = state.executeQuery();

            ArrayList<String> array = new ArrayList<String>();
            while(result.next()){
                System.out.print(result.getString("naam"));
                System.out.print(result.getString("adres"));
                System.out.println(result.getString("postcode"));
                array.add(result.getString("naam"));
            }
            System.out.println("done");
            return array;
        } catch(Exception e){
            System.out.println(e);}
        return null;
    }

    public static void createTable() throws Exception{
        try{
            Connection con = getConnection();
            PreparedStatement create = con.prepareStatement("CREATE TABLE IF NOT EXISTS tablename(id int NOT NULL)");
            create.executeUpdate();
        }catch(Exception e){
            System.out.println(e);}

    }
}