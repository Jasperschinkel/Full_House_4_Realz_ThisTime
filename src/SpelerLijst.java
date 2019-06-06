import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicArrowButton;
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

public class SpelerLijst extends JFrame implements ActionListener {
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


    public SpelerLijst(){
        jtbl.setRowSorter(rowSorter);

        buttonPanel.add(terugButton, BorderLayout.LINE_START);
        buttonPanel.add(verwijderButton, BorderLayout.CENTER);
        buttonPanel.add(wijzigButton, BorderLayout.LINE_END);

        searchPanel.add(jtfFilter,BorderLayout.CENTER);
        searchPanel.add(searchLabel, BorderLayout.LINE_START);
        searchPanel.add(buttonPanel, BorderLayout.LINE_END);
        cnt.setLayout(new BorderLayout());
        cnt.add(searchPanel,BorderLayout.SOUTH);
        showLijst();
        setTitle("Speler Lijst");
        setPreferredSize(new Dimension(1000, 1000));
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addActionListeners();

        jtfFilter.getDocument().addDocumentListener(new DocumentListener(){

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






        JScrollPane pg = new JScrollPane(jtbl);
        cnt.add(pg);
        this.pack();
    }


    public void showLijst(){
        model.addColumn("naam");
        model.addColumn("adres");
        model.addColumn("Postcode");
        model.addColumn("Woonplaats");
        model.addColumn("Telefoonnummer");
        model.addColumn("E-Mail");
        model.addColumn("Geboortedatum");
        model.addColumn("Geslacht");
        model.addColumn("Leeftijd");
        model.addColumn("Ranking");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://meru.hhs.nl/18095240", "18095240", "Ene3shaise");
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM Spelers");
            ResultSet Rs = pstm.executeQuery();
            while(Rs.next()){
                model.addRow(new Object[]{Rs.getString(1), Rs.getString(2),Rs.getString(3),Rs.getString(4),Rs.getString(5),Rs.getString(6),Rs.getString(7),Rs.getString(8),Rs.getString(9), Rs.getString(10)});
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void verwijderSpeler(){
        int naamcolumn = 0;
        int telcolumn = 4;
        int row = jtbl.getSelectedRow();
        String naam = jtbl.getModel().getValueAt(row, naamcolumn).toString();
        String tel = jtbl.getModel().getValueAt(row, telcolumn).toString();
        try {
            Connection con = Main.getConnection();
            PreparedStatement verwijder = con.prepareStatement("DELETE FROM Spelers WHERE naam = '"+naam+"'  AND telefoonnr = '"+tel+"'");
            verwijder.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == verwijderButton) {
            verwijderSpeler();
            JOptionPane.showMessageDialog(this, "Speler verwijderd");
            dispose();
            SpelerLijst refresh = new SpelerLijst();
        }
        if(e.getSource() == terugButton){
            dispose();
            SpelerMenu spelerMenu = new SpelerMenu();
        }
    }

    public void addActionListeners(){
        verwijderButton.addActionListener(this);
    }
}






