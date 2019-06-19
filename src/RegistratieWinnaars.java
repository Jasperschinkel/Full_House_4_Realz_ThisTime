import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import static javax.swing.UIManager.getString;


public class RegistratieWinnaars extends JFrame {
    // global variables in the class scope
    private int aantalRondes;
    private int toernooiCode;
    private int aantalTafels;
    private ArrayList<String> winnaars = new ArrayList<String >();

    // the constructor
    public RegistratieWinnaars(int aantalRondes, int toernooiCode){

        this.aantalRondes = aantalRondes;
        this.toernooiCode = toernooiCode;
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getAantalTafelsFromDB();
        showDialogs();
    }
    // getters and setters
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


// method to get the amount of tables from the DB
    public int getAantalTafelsFromDB(){
        try{
            int toernooiCode = getToernooiCode();
            Connection conn = Main.getConnection();
            PreparedStatement st = conn.prepareStatement("Select aantal_tafels From Toernooi WHERE TC = ?");
            st.setInt(1, toernooiCode);
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                int aantalTafels = rs.getInt("aantal_tafels");
                setAantelTafels(aantalTafels);
                System.out.println(aantalTafels);
            }
        }catch (Exception e) {
            System.out.println(e);}
        return getAantaltafels(); }

// this is a method for  the dialogs that are to be shown that allow the user to register a winner for every round, for every table
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

// push the changes to the DB
    public void pushWinnaars(){
        try {
            ArrayList<String> winnaars = getWinnaars();
            String winnaar = winnaars.get(winnaars.size() - 1);
            Connection con3 = Main.getConnection();
            PreparedStatement st = con3.prepareStatement("UPDATE Toernooi SET winnaar = ? WHERE TC = ?");
            st.setString(1, winnaar);
            st.setInt(2, getToernooiCode());
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            ArrayList<String> winnaars = getWinnaars();
            String tweedePlaats = winnaars.get(winnaars.size() - 2);
            Connection con4 = Main.getConnection();
            PreparedStatement st = con4.prepareStatement("UPDATE Toernooi SET tweede_plaats = ?" +tweedePlaats+"' WHERE TC = ?" + getToernooiCode());
            st.setString(1, tweedePlaats);
            st.setInt(2, getToernooiCode());
            st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}