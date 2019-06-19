import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.*;

public class Inschrijven extends JFrame implements ActionListener {
    //Labels
    private JLabel spelerIDLabel = new JLabel("Spelercode: ");
    private JLabel typeLabel = new JLabel("Type inschrijving: ");
    private JLabel codeLabel = new JLabel ("Code: ");
    private JLabel heeftBetaaldLabel = new JLabel("Heeft betaald: ");

    //Textfields
    private JTextField spelerIDField= new JTextField();
    private JTextField typeField = new JTextField();
    private JTextField codeField = new JTextField();
    private JTextField heeftBetaaldField = new JTextField();

    //Buttons
    private JButton terugButton = new JButton("Terug");
    private JButton klaarButton = new JButton("Klaar");


    public Inschrijven(){
        setTitle("Inschrijven");
        setLayout(null);
        setVisible(true);
        setSize(400, 360);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setComponentBounds();
        addComponents();
        addActionListeners();

    }

    public void emptyTextField(){
        spelerIDField.setText("");
        typeField.setText("");
        codeField.setText("");
        heeftBetaaldField.setText("");
    }

    public void setComponentBounds(){
        spelerIDLabel.setBounds(40,10,100,40);
        typeLabel.setBounds(40,110,100,40);
        codeLabel.setBounds(40,160,200,40);
        heeftBetaaldLabel.setBounds(40, 210, 200, 40);

        spelerIDField.setBounds(250, 10, 100, 40);
        typeField.setBounds(250, 110, 100, 40);
        codeField.setBounds(250, 160, 100, 40);
        heeftBetaaldField.setBounds(250, 210, 100, 40);

        terugButton.setBounds(275,260,75,40);
        klaarButton.setBounds(175,260,100,40);




    }

    public void addComponents(){
        add(spelerIDLabel);
        add(typeLabel);
        add(codeLabel);
        add(heeftBetaaldLabel);

        add(spelerIDField);
        add(typeField);
        add(codeField);
        add(heeftBetaaldField);

        add(klaarButton);
        add(terugButton);

    }

    public void addActionListeners(){
        terugButton.addActionListener(this);
        klaarButton.addActionListener(this);

    }

    public void countSpelers() {
        if (typeField.getText().equals("Toernooi")) {
            try {
                Connection con = Main.getConnection();
                PreparedStatement st = con.prepareStatement("SELECT COUNT (*) as geteld FROM Inschrijvingen where toernooi = ?");
                st.setInt(1, Integer.parseInt(codeField.getText()));
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    int geteld = rs.getInt("geteld");
                    try {
                        Connection con2 = Main.getConnection();
                        PreparedStatement update = con2.prepareStatement("UPDATE Toernooi SET aantal_spelers = ? where TC = ?");
                        update.setInt(1, geteld);
                        update.setInt(1, Integer.parseInt(codeField.getText()));
                        update.executeUpdate();
                    } catch (Exception e) {
                        System.out.println(e);
                        System.out.println("ERROR: er ging iets mis met de database(updateAantalSpelers)");
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("ERROR: er ging iets mis met de database(updateAantalSpelers)");
            }
        } else if (typeField.getText().equals("Masterclass")) {
            try {
                Connection con = Main.getConnection();
                PreparedStatement st = con.prepareStatement("SELECT COUNT (*) as geteld FROM Inschrijvingen where toernooi = ?");
                st.setInt(1, Integer.parseInt(codeField.getText()));
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    int geteld = rs.getInt("geteld");
                    try {
                        Connection con2 = Main.getConnection();
                        PreparedStatement update = con2.prepareStatement("UPDATE Masterclass SET aantal_spelers = ? where MasterclassCode = ?");
                        update.setInt(1, geteld);
                        update.setInt(2, Integer.parseInt(codeField.getText()));
                        update.executeUpdate();
                    } catch (Exception e) {
                        System.out.println(e);
                        System.out.println("ERROR: er ging iets mis met de database(updateAantalSpelers)");
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("ERROR: er is een probleem met de database (countSpelers)");
            }
        }
    }


    public void addInschrijving(){

            try {
                Connection con = Main.getConnection();
                PreparedStatement add = con.prepareStatement("INSERT INTO Inschrijvingen (naam, ranking, type_inschrijving, nummercode, heeft_betaald) VALUES (?,?,?,?,?);");
                add.setString(1, spelerIDField.getText());
                add.setInt(2, Integer.parseInt(spelerIDField.getText()));
                add.setString(3, typeField.getText());
                add.setInt(4, Integer.parseInt(codeField.getText()));
                add.setString(5, heeftBetaaldField.getText());
                add.executeUpdate();
            } catch (Exception e) {
                System.out.println(e);
            }
        }


    public boolean inschrijfControle(){
        if(typeField.getText().equals("Toernooi")) {
            try {
                Connection con = Main.getConnection();
                PreparedStatement st = con.prepareStatement("SELECT COUNT (*) as aantal FROM Inschrijvingen WHERE speler = ? AND toernooi = ?");
                st.setInt(1, Integer.parseInt(spelerIDField.getText()));
                st.setInt(2, Integer.parseInt(codeField.getText()));
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("aantal");
                    if (id < 1) {
                        return false;
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("ERROR: er is een probleem met de database(inschrijfControleToernooi)");
            }
        }else if(typeField.getText().equals("Masterclass")){
            try{
                Connection con = Main.getConnection();
                PreparedStatement st = con.prepareStatement("SELECT COUNT (*) as aantal FROM Inschrijvingen WHERE speler = ? AND masterclass = ?");
                st.setInt(1, Integer.parseInt(spelerIDField.getText()));
                st.setInt(2, Integer.parseInt(codeField.getText()));
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    int id = rs.getInt("aantal");
                    if (id < 1) {
                        return false;
                    }
                }
            }catch(Exception e){
                System.out.println(e);
                System.out.println("ERROR: er is een probleem met de database(inschrijfControleMasterclass)");
            }
        }
        return true;
    }

    public String getGeslacht(){
        try {
            Connection con = Main.getConnection();
            PreparedStatement st = con.prepareStatement("SELECT geslacht FROM Spelers WHERE idcode = ?");
            st.setInt(1, Integer.parseInt(spelerIDField.getText()));
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                String geslacht = rs.getString("geslacht");
                System.out.println(geslacht);
                return geslacht;
                }

        }catch(Exception e){
            System.out.println(e);
            System.out.println("ERROR: er is een probleem met de database (geslacht)");
        }

   return "poepieScheetje";}

    public String getToernooiSoort(){
        if(typeField.getText().equals("Toernooi")){
        try {
            Connection con = Main.getConnection();
            PreparedStatement st = con.prepareStatement("SELECT soort_toernooi FROM Toernooi WHERE TC = ?");
            st.setInt(1, Integer.parseInt(codeField.getText()));
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                String toernooiSoort = rs.getString("soort_toernooi");
                System.out.println(toernooiSoort);
                return toernooiSoort;
            }

        }catch(Exception e){
            System.out.println(e);
            System.out.println("ERROR: er is een probleem met de database(getToernooiSoort)");
        }
        }

        return "poepieScheetje";}

        public boolean validateGeslacht(){
        String geslacht = getGeslacht();
        String toernooiSoort = getToernooiSoort();

        if(!geslacht.equals("F") && toernooiSoort.equals("PinkRibbon")){
            return false;
        }
       else if(geslacht.equals("poepieScheetje") || toernooiSoort.equals("askjeBlap")){
            return true;
        }
        else{return true;}
        }

        public int getRanking() {
            int spelerRanking = -1;

            try {
                Connection conn = Main.getConnection();
                PreparedStatement ps = conn.prepareStatement("SELECT idcode, ranking FROM Spelers WHERE idcode = ?");
                ps.setInt(1, Integer.parseInt(codeField.getText()));
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int result = rs.getInt("idcode");
                    if (result == Integer.parseInt(codeField.getText())) {
                        PreparedStatement check = conn.prepareStatement("SELECT ranking FROM Spelers WHERE idcode = ?");
                        check.setInt(1, Integer.parseInt(codeField.getText()));
                        ResultSet resultaat = check.executeQuery();
                        if (resultaat.next()) {
                             spelerRanking = resultaat.getInt("ranking");
                        }
                    }
                }

            }catch (Exception e) {
                e.printStackTrace();

            }
       return spelerRanking; }


    public boolean checkMaxRanking(){
        try {
            Connection conn = Main.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT MasterclassCode, max_ranking FROM Masterclass WHERE MasterclassCode = ?");
            ps.setInt(1, Integer.parseInt(codeField.getText()));
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                int result = rs.getInt("MasterclassCode");
                if (result == Integer.parseInt(codeField.getText())){
                    PreparedStatement check = conn.prepareStatement("SELECT max_ranking FROM Masterclass WHERE MasterclassCode = ?");
                    check.setInt(1, Integer.parseInt(codeField.getText()));
                    ResultSet resultaat = check.executeQuery();
                    if (resultaat.next()){
                        if (resultaat.getInt("max_ranking") >= getRanking()) {
                            return false;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return true;
    }
    public boolean checkInput(){
        if(spelerIDField.getText().equals("") || typeField.getText().equals("") || codeField.getText().equals("") || heeftBetaaldField.getText().equals("")){
            return true;
        } else { return false; }
        }




//    public int getRanking(){
//        String naam = naamField.getText();
//        int ranking=0;
//        try {
//            Connection con = Main.getConnection();
//            PreparedStatement st = con.prepareStatement("SELECT ranking FROM Spelers WHERE idcode = ?");
//            st.setInt(1, Integer.parseInt(naam));
//            ResultSet rs= st.executeQuery();
//            if(rs.next()) {
//                return rs.getInt("ranking");
//            }
//        }catch(Exception e) {
//            System.out.println(e);
//        }
//        return ranking;
//    }





    public int getMaxAantalInschrijvingen(){
        if(typeField.getText().equals("Toernooi")) {
            try {
                Connection con = Main.getConnection();
                PreparedStatement st = con.prepareStatement("SELECT COUNT (*) as aantal from Inschrijvingen where toernooi = ?" + codeField.getText());
                st.setInt(1, Integer.parseInt(codeField.getText()));
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    int aantal = rs.getInt("aantal");
                    return aantal;
                }
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("ERROR: er is een probleem met de database (getMaxAantalInschrijvingenToernooi)");
            }
        }
        else if(typeField.getText().equals("Masterclass")){
            try {
                Connection con = Main.getConnection();
                PreparedStatement st = con.prepareStatement("SELECT COUNT (*) as aantal from Inschrijvingen where masterclass = ?" + codeField.getText());
                st.setInt(1, Integer.parseInt(codeField.getText()));
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    int aantal = rs.getInt("aantal");
                    return aantal;
                }
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("ERROR: er is een probleem met de database (getMaxAantalInschrijvingenMasterclass)");
            }
        }
        return 0;
    }

    public int getMaxAantal(){
        if(typeField.getText().equals("Toernooi")) {
            try {
                Connection con = Main.getConnection();
                PreparedStatement st = con.prepareStatement("SELECT maximaal_aantal_spelers as max FROM Toernooi WHERE TC = ?" + codeField.getText());
                st.setInt(1, Integer.parseInt(codeField.getText()));
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    int max = rs.getInt("max");
                    return max;
                }
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("ERROR: er is iets mis met de database(getMaxAantalToernooi)");
            }
        }

        else if(typeField.getText().equals("Masterclass")){
             try {
                 Connection con = Main.getConnection();
                 PreparedStatement st = con.prepareStatement("SELECT max_aantal_spelers AS max FROM Masterclass WHERE MasterclassCode = ?" + codeField.getText());
                 st.setInt(1, Integer.parseInt(codeField.getText()));
                 ResultSet rs = st.executeQuery();
                 if (rs.next()) {
                     int max = rs.getInt("max");
                     return max;
                 }
             }catch (Exception e){
                System.out.println(e);
                System.out.println("ERROR: er is iets mis met de database (getMaxAantalMasterclass)");
            }
        }
        return 0;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == terugButton){
            dispose();
            RegistratieMenu menu = new RegistratieMenu();
        }
        if(e.getSource() == klaarButton){
            if (inschrijfControle()){
                JOptionPane.showMessageDialog(this, "ERROR: deze speler is hiervoor al ingeschreven");
            }
            else if(!validateGeslacht()){
                JOptionPane.showMessageDialog(this, "Een man mag zich niet inschrijven voor een Pink Ribbon toernooi");
            }
            else if(getMaxAantalInschrijvingen() > getMaxAantal()){
                JOptionPane.showMessageDialog(this, "Het maximum aantal spelers is al ingeschreven voor dit toernooi");
            }
            else if (checkInput()) {
                JOptionPane.showMessageDialog(this, "Niet alles is ingevuld!");
            }
            else if (checkMaxRanking()){
                JOptionPane.showMessageDialog(this, "Ranking is hoger dan toegestaan in deze Masterclass");
            } else {
                addInschrijving();
                countSpelers();
                JOptionPane.showMessageDialog(this, "Inschrijving toegevoegd");
                emptyTextField();
            }
        }


    }
}
