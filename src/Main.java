import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Main {

    public static void main(String[] args) throws Exception {
        LoginFrame loginScreen = new LoginFrame();
        createTable();
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

    /*public static void createTable() throws Exception{
        try{
            Connection con = getConnection();
            PreparedStatement create = con.prepareStatement("CREATE TABLE IF NOT EXISTS tablename(id int NOT NULL)");
            create.executeUpdate();
        }catch(Exception e){
            System.out.println(e);}

    } */
}