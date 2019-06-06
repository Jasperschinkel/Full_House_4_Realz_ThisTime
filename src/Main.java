import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.*;

public class Main {

    public static void main(String[] args) throws Exception {
      LoginFrame frame = new LoginFrame();
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






}