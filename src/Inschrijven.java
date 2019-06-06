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
    private JLabel naamLabel = new JLabel("Naam: ");
    private JLabel rankingLabel = new JLabel("Ranking: ");
    private JLabel typeLabel = new JLabel("Type inschrijving: ");
    private JLabel codeLabel = new JLabel ("Code: ");
    private JLabel heeftBetaaldLabel = new JLabel("Heeft betaald: ");

    //Textfields
    private JTextField naamField = new JTextField();
    private JTextField rankingField = new JTextField();
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
        naamField.setText("");
        rankingField.setText("");
        typeField.setText("");
        codeField.setText("");
        heeftBetaaldField.setText("");
    }
    public String getCodeText(){
        String code = codeField.getText();
        return code;
    }

    public void setComponentBounds(){
        naamLabel.setBounds(40,10,100,40);
        rankingLabel.setBounds(40,60,100,40);
        typeLabel.setBounds(40,110,100,40);
        codeLabel.setBounds(40,160,200,40);
        heeftBetaaldLabel.setBounds(40, 210, 200, 40);

        naamField.setBounds(250, 10, 100, 40);
        rankingField.setBounds(250, 60, 100, 40);
        typeField.setBounds(250, 110, 100, 40);
        codeField.setBounds(250, 160, 100, 40);
        heeftBetaaldField.setBounds(250, 210, 100, 40);

        terugButton.setBounds(275,260,75,40);
        klaarButton.setBounds(175,260,100,40);



    }

    public void addComponents(){
        add(naamLabel);
        add(rankingLabel);
        add(typeLabel);
        add(codeLabel);
        add(heeftBetaaldLabel);

        add(naamField);
        add(rankingField);
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

    public void addInschrijving(){
        try{
            Connection con = Main.getConnection();
            PreparedStatement add = con.prepareStatement("INSERT INTO Inschrijvingen (naam, ranking, type_inschrijving, nummercode, heeft_betaald) VALUES ('"+naamField.getText()+ "', '"+rankingField.getText()+ "', '"+typeField.getText()+ "', '"+codeField.getText()+"', '"+heeftBetaaldField.getText()+"');");
            add.executeUpdate();
        }catch(Exception e) {
            System.out.println(e);
        }

    }

    public boolean inschrijfControle(){
        try {
            Connection con = Main.getConnection();
            Statement st = con.createStatement();
            String sql = ("SELECT COUNT (*) as aantal FROM Inschrijvingen WHERE naam LIKE '" + naamField.getText() + "' AND nummercode LIKE " + codeField.getText());
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                int id = rs.getInt("aantal");
                if (id < 1) {
                    return false;
                }
            }
        }catch(Exception e){
            System.out.println(e);
            System.out.println("ERROR: er is een probleem met de database");
        }
        return true;
    }

    public String getGeslacht(){
        try {
            Connection con = Main.getConnection();
            Statement st = con.createStatement();
            String sql = ("SELECT geslacht FROM Spelers WHERE naam LIKE '" + naamField.getText() + "'; ");
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                String geslacht = rs.getString("geslacht");
                System.out.println(geslacht);
                return geslacht;
                }

        }catch(Exception e){
            System.out.println(e);
            System.out.println("ERROR: er is een probleem met de database");
        }

   return "poepieScheetje";}

    public String getToernooiSoort(){
        try {
            Connection con = Main.getConnection();
            Statement st = con.createStatement();
            String sql = ("SELECT soort_toernooi FROM Toernooi WHERE TC LIKE '" + Integer.valueOf(codeField.getText()) + "'; ");
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                String toernooiSoort = rs.getString("soort_toernooi");
                System.out.println(toernooiSoort);
                return toernooiSoort;
            }

        }catch(Exception e){
            System.out.println(e);
            System.out.println("ERROR: er is een probleem met de database");
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

        public int aantalToernooiInschrijvingen(){
            try {
                Connection con = Main.getConnection();
                Statement st = con.createStatement();
                String sql = ("SELECT COUNT (*) as aantal FROM Inschrijvingen WHERE nummercode LIKE " + codeField.getText());
                ResultSet rs = st.executeQuery(sql);
                if (rs.next()) {
                    int totaal = rs.getInt("aantal");
                    return totaal;
                }
            }catch(Exception e){
                System.out.println(e);
                System.out.println("ERROR: er is een probleem met de database");
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

            else {
                addInschrijving();
                JOptionPane.showMessageDialog(this, "Inschrijving toegevoegd");
                emptyTextField();
            }
        }

    }
}
