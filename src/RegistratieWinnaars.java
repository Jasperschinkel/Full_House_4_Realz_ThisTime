import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import static javax.swing.UIManager.getString;


public class RegistratieWinnaars extends JFrame {
    private int aantalRondes;
    private int toernooiCode;
    private int aantalTafels;
    private ArrayList<String> winnaars = new ArrayList<String >();

    public RegistratieWinnaars(int aantalRondes, int toernooiCode){

        this.aantalRondes = aantalRondes;
        this.toernooiCode = toernooiCode;
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getAantalTafelsFromDB();
        showDialogs();
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
    public void addWinnaars(String winnaar){
        winnaars.add(winnaar);
    }
    public ArrayList<String>getWinnaars(){
        return this.winnaars;
    }


    public int getAantalTafelsFromDB(){
        try{
            int toernooiCode = getToernooiCode();
            Connection conn =
                    DriverManager.getConnection("jdbc:mysql://meru.hhs.nl/18095240", "18095240",
                            "Ene3shaise");
            Statement stm;
            stm = conn.createStatement();
            String sql = "Select aantal_tafels From Toernooi WHERE TC =" +toernooiCode+";";
            ResultSet rst;
            rst = stm.executeQuery(sql);
            if(rst.next()) {
                int aantalTafels = rst.getInt("aantal_tafels");
                setAantelTafels(aantalTafels);
                System.out.println(aantalTafels);
            }
        }catch (Exception e) {
            System.out.println(e);}
        return getAantaltafels(); }


    public void showDialogs() {
        int aantalTafels = getAantaltafels();
        System.out.println(aantalTafels);
        int aantalRondes = getAantalRondes();
        int ronde = 0;
        for(int i = 1; i <= aantalRondes; i++){
            ronde++;
            if(ronde > 1){
                if(aantalTafels % 5 != 0){
                    aantalTafels = aantalTafels / 6;}
                else{aantalTafels = aantalTafels /5;}
            }
            for (int j = 1; j <= aantalTafels; j++) {
                String winnaar = JOptionPane.showInputDialog(this,
                        "Wie heeft er gewonnen aan tafel "+j+"en in ronde "+ronde+" ?", null);
                        addWinnaars(winnaar);
            }}
        pushWinnaars(); }


    public void pushWinnaars(){
        try {
            ArrayList<String> winnaars = getWinnaars();
            String winnaar = winnaars.get(winnaars.size() - 1);
            Connection con3 = Main.getConnection();
            PreparedStatement add = con3.prepareStatement("UPDATE Toernooi SET winnaar = '" +winnaar+"' WHERE TC LIKE '" + getToernooiCode()+ "'; ");
            add.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            ArrayList<String> winnaars = getWinnaars();
            String tweedePlaats = winnaars.get(winnaars.size() - 2);
            Connection con4 = Main.getConnection();
            PreparedStatement add2 = con4.prepareStatement("UPDATE Toernooi SET tweede_plaats = '" +tweedePlaats+"' WHERE TC LIKE '" + getToernooiCode()+ "'; ");
            add2.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}