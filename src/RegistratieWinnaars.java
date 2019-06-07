import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import static javax.swing.UIManager.getString;

public class RegistratieWinnaars {

    private int aantalRondes;
    private int toernooiCode;
    private int aantalTafels;

    private ArrayList<String> winnaars = new ArrayList<String >();

    public RegistratieWinnaars(int aantalRondes, int toernooiCode){
        this.aantalRondes = aantalRondes;
        this.toernooiCode = toernooiCode;


    }

    public int getAantalRondes(){
        return this.aantalRondes;
    }

    public int getToernooiCode(){
        return this.toernooiCode;
    }

    public void setAantelTafels(int aantalTafels){
        this.aantalTafels = aantalTafels;
    }

    public int getAantaltafels(){
        return this.aantalTafels;
    }

    private int getAantalTafels(){
       try{
           int toernooiCode = getToernooiCode();
        Connection conn = DriverManager.getConnection("jdbc:mysql://meru.hhs.nl/18095240", "18095240", "Ene3shaise");
        Statement stm;
        stm = conn.createStatement();
        String sql = "Select aantal_tafels From Toernooi WHERE TC =" +toernooiCode+":";
        ResultSet rst;
           rst = stm.executeQuery(sql);
           if(rst.next()) {
               int aantalTafels = rst.getInt("aantal_tafels");
               setAantelTafels(aantalTafels);
       }

    }catch (Exception e) {
           System.out.println(e);}


           return  aantalTafels; }
}
