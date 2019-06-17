import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ToernooiLijst extends JFrame implements ActionListener {
public int albertus = 0;
    DefaultTableModel model = new DefaultTableModel();
    Container cnt = this.getContentPane();
    JTable jtbl = new JTable(model);

    private TableRowSorter<TableModel> rowSorter = new TableRowSorter(jtbl.getModel());
    private JTextField jtfFilter = new JTextField();
    private JButton verwijderButten = new JButton("Verwijderen");
    private JButton wijzigButton = new JButton("Wijzigen");
    private JButton terugButton = new JButton("Terug");
    private JButton tafelIndeling = new JButton("Tafelindeling");
    private JLabel searchLabel = new JLabel("search: ");
    private JPanel searchPanel = new JPanel(new BorderLayout());
    private JPanel buttonPanel = new JPanel(new BorderLayout());
    private JPanel buttonPanelLineStart = new JPanel(new BorderLayout());
    private int aantalSpelers;
    private ArrayList<String> totaleGeldVoorMij = new ArrayList<String>();


    public ToernooiLijst() {
        jtbl.setRowSorter(rowSorter);
        buttonPanelLineStart.add(terugButton, BorderLayout.LINE_START);
        buttonPanelLineStart.add(tafelIndeling, BorderLayout.CENTER);
        buttonPanel.add(buttonPanelLineStart, BorderLayout.LINE_START);
        buttonPanel.add(verwijderButten, BorderLayout.CENTER);
        buttonPanel.add(wijzigButton, BorderLayout.LINE_END);

        searchPanel.add(jtfFilter, BorderLayout.CENTER);
        searchPanel.add(searchLabel, BorderLayout.LINE_START);
        searchPanel.add(buttonPanel, BorderLayout.LINE_END);
        cnt.setLayout(new BorderLayout());
        cnt.add(searchPanel, BorderLayout.SOUTH);

        setTitle("Toernooi Lijst");
        setPreferredSize(new Dimension(1700, 500));
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pushTotaleInlegGeld();


        jtfFilter.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = jtfFilter.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = jtfFilter.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });


        showLijst();
        addActionlisteners();
        JScrollPane pg = new JScrollPane(jtbl);
        cnt.add(pg);
        this.pack();
    }



    public void showLijst() {
        model.addColumn("TC");
        model.addColumn("Datum");
        model.addColumn("Begintijd");
        model.addColumn("Eindtijd");
        model.addColumn("Beschrijving");
        model.addColumn("Vereisten toernooi");
        model.addColumn("Soort toernooi");
        model.addColumn("Max. aantal spelers");
        model.addColumn("Inleggeld");
        model.addColumn("Uiterste inschrijfdatum");
        model.addColumn("aantal_spelers");
        model.addColumn("totale_inleggeld");
        model.addColumn("is_gespeeld");
        model.addColumn("winnaar");
        model.addColumn("tweede_plaats");
        model.addColumn("Locatie");


        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://meru.hhs.nl/18095240", "18095240", "Ene3shaise");
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM Toernooi");
            ResultSet Rs = pstm.executeQuery();
            while (Rs.next()) {
                model.addRow(new Object[]{Rs.getString(1), Rs.getString(2), Rs.getString(3), Rs.getString(4), Rs.getString(5), Rs.getString(6), Rs.getString(7), Rs.getString(8), Rs.getString(9), Rs.getString(10), Rs.getString(11), Rs.getString(12), Rs.getString(13), Rs.getString(14), Rs.getString(15), Rs.getString(16)});
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public void wijzigToernooi(JTable table, int row) {
        try {
            Connection con = Main.getConnection();
            PreparedStatement update = con.prepareStatement("UPDATE Toernooi SET datum = ?, begintijd = ?, eindtijd = ?, beschrijving = ?, condities = ?, soort_toernooi = ?, maximaal_aantal_spelers = ?, inleggeld = ?, uiterste_inschrijf_datum = ?, locatie = ? WHERE TC = ?");
            update.setString(1, jtbl.getValueAt(row, 1).toString());
            update.setString(2, jtbl.getValueAt(row, 2).toString());
            update.setString(3, jtbl.getValueAt(row, 3).toString());
            update.setString(4, jtbl.getValueAt(row, 4).toString());
            update.setString(5, jtbl.getValueAt(row, 5).toString());
            update.setString(6, jtbl.getValueAt(row, 6).toString());
            update.setInt(7, Integer.parseInt(jtbl.getValueAt(row, 7).toString()));
            update.setString(8, jtbl.getValueAt(row, 8).toString());
            update.setString(9, jtbl.getValueAt(row, 9).toString());
            update.setString(10, jtbl.getValueAt(row, 10).toString());
            update.setInt(11, Integer.parseInt(jtbl.getValueAt(row, 0).toString()));
            update.executeUpdate();
            update.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void verwijderToernooi() {
        int TCcolumn = 0;
        int row = jtbl.getSelectedRow();
        int tc = Integer.parseInt(jtbl.getModel().getValueAt(row, TCcolumn).toString());
        try {
            Connection con = Main.getConnection();
            PreparedStatement verwijder = con.prepareStatement("DELETE FROM Toernooi WHERE TC = " + tc + ";");
            verwijder.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void makeTafelIndeling() {
        int TCcolumn = 0;
        int row = jtbl.getSelectedRow();
        int tc = Integer.parseInt(jtbl.getModel().getValueAt(row, TCcolumn).toString());
        try {
            Connection con = Main.getConnection();
            PreparedStatement SelectInschrijvingen = con.prepareStatement("SELECT aantal_spelers FROM Toernooi WHERE TC = " + tc + ";");
            ResultSet resultSet = SelectInschrijvingen.executeQuery();
            PreparedStatement aantal_tafels = con.prepareStatement("SELECT aantal_tafels FROM Toernooi WHERE TC = "+tc+";");
            while (resultSet.next()){
                int aantal_spelers = resultSet.getInt("aantal_spelers");
            for (int i = 0; i < 5; i++) {
                if (aantal_spelers % 5 == i && aantal_spelers > 5) {
                    PreparedStatement tafelindeling = con.prepareStatement("UPDATE Toernooi SET aantal_tafels = " + aantal_spelers/5 + " WHERE TC = "+tc+";");
                    tafelindeling.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Het toernooi begint met " + aantal_spelers/5 + " tafels en de "
                    + i + " overige worden willekeurig ingedeeld over de tafels.");
                } else if(aantal_spelers<=5) {
                    PreparedStatement tafelindeling = con.prepareStatement("UPDATE Toernooi SET aantal_tafels = 1 WHERE TC = "+tc+";");
                    tafelindeling.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Het toernooi begint met 1 tafel");
                }
            }
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public int getAantalSpelers(){
        return this.aantalSpelers;
    }

    public void setAantalSpelers(int aantalSpelers){
        this.aantalSpelers = aantalSpelers;
    }

    public ArrayList<String> getTotaleGeldVoorMij(){
        return this.totaleGeldVoorMij;
    }

    public void addTotaleGeldVoorMij(String totaleGeldVoorMij){
        this.totaleGeldVoorMij.add(totaleGeldVoorMij);
    }

    public static ArrayList<ToernooiCode> getAllToernooiCodes() throws ClassNotFoundException, SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://meru.hhs.nl/18095240", "18095240", "Ene3shaise");
        Statement stm;
        stm = conn.createStatement();
        String sql = "Select * From Toernooi";
        ResultSet rst;
        rst = stm.executeQuery(sql);
        ArrayList<ToernooiCode> toernooiCodes = new ArrayList<ToernooiCode>();
        while (rst.next()) {
            ToernooiCode toernooiCode = new ToernooiCode(rst.getString("TC"));
            toernooiCodes.add(toernooiCode);

        }
        return toernooiCodes;

    }

    public void pushIsGeweest() {
        try {
            ArrayList<ToernooiCode> alleToernooiCodes = getAllToernooiCodes();
            for (int i = 0; i < alleToernooiCodes.size(); i++)
                try {
                    Connection con = Main.getConnection();
                    Statement st = con.createStatement();
                    String sql = ("SELECT datum FROM Toernooi WHERE TC LIKE '" + alleToernooiCodes.get(i).getToernooiCode() + "'; ");
                    ResultSet rs = st.executeQuery(sql);
                    if (rs.next()) {
                        String datum = rs.getString("datum");
                        //System.out.println(!datum.substring(0,3).equals("201"));
                        if(!datum.substring(0,3).equals("201")){
                           datum = "2019-06-14";
                            try {
                                datum = "2019-06-14";
                                Connection con3 = Main.getConnection();
                                PreparedStatement add = con3.prepareStatement("UPDATE  Toernooi SET datum = '"+datum+ "'WHERE TC LIKE '" + alleToernooiCodes.get(i).getToernooiCode() + "'; ");
                                add.executeUpdate();
                            } catch (Exception e) {
                                System.out.println(e);
                            }

                        }
                        Date datumSQL = Date.valueOf(datum);
                        LocalDate dateNow = LocalDate.now();
                        Date dateNowSQL = Date.valueOf(dateNow);


                        if (datumSQL.before(dateNowSQL)) {
                            try {
                                Connection con2 = Main.getConnection();
                                PreparedStatement add = con2.prepareStatement("UPDATE  Toernooi SET is_gespeeld = 'J'WHERE TC LIKE '" + alleToernooiCodes.get(i).getToernooiCode() + "'; ");
                                add.executeUpdate();
                            } catch (Exception e) {
                                System.out.println(e);
                            }

                        } else {
                            try {
                                Connection con2 = Main.getConnection();
                                PreparedStatement add = con2.prepareStatement("UPDATE  Toernooi SET is_gespeeld = 'N' WHERE TC LIKE '" + alleToernooiCodes.get(i).getToernooiCode() + "'; ");
                                add.executeUpdate();
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                        }
                    }

                } catch (Exception e) {
                    System.out.println(e);
                    System.out.println("ERROR: er is een probleem met de database");

                }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("caught with ToernooiLijst");
        }
    }


    public void pushTotaleInlegGeld(){
        try {
            ArrayList<ToernooiCode> alleToernooiCodes = getAllToernooiCodes();
            for(int i = 0; i < alleToernooiCodes.size(); i++){
                try {
                    Connection con = Main.getConnection();
                    Statement st = con.createStatement();
                    String sql = ("SELECT inleggeld FROM Toernooi WHERE TC LIKE '" + alleToernooiCodes.get(i).getToernooiCode() + "'; ");
                    ResultSet rs = st.executeQuery(sql);
                    if (rs.next()) {
                    String inleggeld = rs.getString("inleggeld");

                        try {
                            Connection con2 = Main.getConnection();
                            Statement st2 = con2.createStatement();
                            String sql2 = ("SELECT aantal_spelers FROM Toernooi WHERE TC LIKE '" + alleToernooiCodes.get(i).getToernooiCode() + "'; ");
                            ResultSet rs2 = st2.executeQuery(sql2);
                            if (rs2.next()) {
                                int aantalSpelers = rs2.getInt("aantal_spelers");
                                setAantalSpelers(aantalSpelers);


                            }
                        }catch (Exception e) {
                            System.out.println(e);
                            System.out.println("ERROR: er is een probleem met de database");

                        }

                    if(inleggeld.substring(0,1).equals("€")){
                        inleggeld = inleggeld.substring(1,inleggeld.length());
                    }
                   inleggeld = inleggeld.replace(",", ".");
                    double inleggeldDouble = Double.valueOf(inleggeld);

                    double totaleInleggeld = inleggeldDouble * getAantalSpelers();

                    String totaleInleggGeldString = Double.toString(totaleInleggeld);
                    totaleInleggGeldString = "€" + totaleInleggGeldString;
                    totaleInleggGeldString = totaleInleggGeldString.replace(".",",");
                    addTotaleGeldVoorMij(totaleInleggGeldString);
                        

                        try {
                           String totaleInleggeldString = getTotaleGeldVoorMij().get(i);
                            Connection con3 = Main.getConnection();
                            PreparedStatement add = con3.prepareStatement("UPDATE  Toernooi SET totale_inleggeld = '" +totaleInleggeldString+"' WHERE TC LIKE '" + alleToernooiCodes.get(i).getToernooiCode() + "'; ");
                            add.executeUpdate();
                        } catch (Exception e) {
                            System.out.println(e);
                        }

                    }
                }catch (Exception e) {
                    System.out.println(e);
                    System.out.println("ERROR: er is een probleem met de database");

                }

            }
        }catch (ClassNotFoundException | SQLException e) {
            System.out.println("caught with ToernooiLijst");
        }
    }




    public void actionPerformed(ActionEvent e){
        if(e.getSource() == verwijderButten) {
            verwijderToernooi();
            JOptionPane.showMessageDialog(this, "Toernooi verwijderd");
            dispose();
           ToernooiLijst refresh = new ToernooiLijst();
        }
        if(e.getSource() == terugButton) {
            dispose();
            ToernooiMenu menu = new ToernooiMenu();
        }
        if(e.getSource() == wijzigButton){
            wijzigToernooi(jtbl, jtbl.getSelectedRow());
            JOptionPane.showMessageDialog(this, "Toernooi gewijzigd");
            dispose();
            ToernooiLijst refresh = new ToernooiLijst();
        }
        if (e.getSource() == tafelIndeling){
            makeTafelIndeling();
            dispose();
            ToernooiLijst refresh = new ToernooiLijst();
        }
    }

    public void addActionlisteners(){
        verwijderButten.addActionListener(this);
        terugButton.addActionListener(this);
        wijzigButton.addActionListener(this);
        tafelIndeling.addActionListener(this);
    }


    }


