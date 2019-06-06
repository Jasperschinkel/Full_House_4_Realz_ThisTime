import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InschrijvingenLijst extends JFrame implements ActionListener {
    DefaultTableModel model = new DefaultTableModel();
    Container cnt = this.getContentPane();
    JTable jtbl = new JTable(model);

    private TableRowSorter<TableModel> rowSorter = new TableRowSorter(jtbl.getModel());
    private JTextField jtfFilter = new JTextField();
    private JButton verwijderButton = new JButton("Verwijderen");
    private JButton wijzigButton = new JButton("Wijzigen");
    private JButton terugButton = new JButton("Terug");
    private JLabel searchLabel = new JLabel("search: ");
    private JPanel searchPanel = new JPanel(new BorderLayout());
    private JPanel buttonPanel = new JPanel(new BorderLayout());

    public InschrijvingenLijst() {
        jtbl.setRowSorter(rowSorter);

        buttonPanel.add(terugButton, BorderLayout.LINE_START);
        buttonPanel.add(verwijderButton, BorderLayout.CENTER);
        buttonPanel.add(wijzigButton, BorderLayout.LINE_END);

        searchPanel.add(jtfFilter, BorderLayout.CENTER);
        searchPanel.add(searchLabel, BorderLayout.LINE_START);
        searchPanel.add(buttonPanel, BorderLayout.LINE_END);
        cnt.setLayout(new BorderLayout());
        cnt.add(searchPanel, BorderLayout.SOUTH);

        setTitle("Inschrijvingenlijst");
        setPreferredSize(new Dimension(1000, 500));
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
        model.addColumn("Inschrijving");
        model.addColumn("Naam");
        model.addColumn("Ranking");
        model.addColumn("Type inschrijving");
        model.addColumn("Nummercode");
        model.addColumn("Heeft betaald");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://meru.hhs.nl/18095240", "18095240", "Ene3shaise");
            PreparedStatement pstm = con.prepareStatement("SELECT DISTINCT * FROM Inschrijvingen");
            ResultSet Rs = pstm.executeQuery();
            while (Rs.next()) {
                model.addRow(new Object[]{Rs.getString(1), Rs.getString(2), Rs.getString(3), Rs.getString(4), Rs.getString(5), Rs.getString(6)});
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void verwijderRegistratie() {
        int registratieNummer = 0;
        int row = jtbl.getSelectedRow();
        int inschrijvingNummer = Integer.parseInt(jtbl.getModel().getValueAt(row, registratieNummer).toString());
        try {
            Connection con = Main.getConnection();
            PreparedStatement verwijder = con.prepareStatement("DELETE FROM Inschrijvingen WHERE Inschrijving = '" + inschrijvingNummer + "'");
            verwijder.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void wijzigRegistratie(JTable table, int row){
        try{
            Connection con= Main.getConnection();
            PreparedStatement update = con.prepareStatement("UPDATE Inschrijvingen SET naam = ?, ranking = ?, type_inschrijving = ?, nummercode = ?, heeft_betaald = ? WHERE Inschrijving = ?");
            update.setString(1,jtbl.getValueAt(row,1).toString());
            update.setInt(2,Integer.parseInt(jtbl.getValueAt(row,2).toString()));
            update.setString(3,jtbl.getValueAt(row,3).toString());
            update.setInt(4,Integer.parseInt(jtbl.getValueAt(row,4).toString()));
            update.setString(5,jtbl.getValueAt(row,5).toString());
            update.setInt(6,Integer.parseInt((jtbl.getValueAt(row,0).toString())));
            update.executeUpdate();
            update.close();

        }catch(Exception e) {
            System.out.println(e);
        }
    }

    public void addActionlisteners() {
        verwijderButton.addActionListener(this);
        terugButton.addActionListener(this);
        wijzigButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == terugButton) {
            dispose();
            RegistratieMenu menu = new RegistratieMenu();
        }
        if(e.getSource() == verwijderButton){
            verwijderRegistratie();
            JOptionPane.showMessageDialog(this, "Inschrijving verwijderd");
            dispose();
            InschrijvingenLijst nieuw = new InschrijvingenLijst();
        }
        if(e.getSource() == wijzigButton){
            wijzigRegistratie(jtbl,jtbl.getSelectedRow());
            JOptionPane.showMessageDialog(this, "Inschrijving gewijzigd");
            dispose();
            InschrijvingenLijst nieuw = new InschrijvingenLijst();
        }
    }
}
