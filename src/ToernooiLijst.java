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

public class ToernooiLijst extends JFrame implements ActionListener{

    DefaultTableModel model = new DefaultTableModel();
    Container cnt = this.getContentPane();
    JTable jtbl = new JTable(model);

    private TableRowSorter<TableModel> rowSorter = new TableRowSorter(jtbl.getModel());
    private JTextField jtfFilter = new JTextField();
    private JButton verwijderButten = new JButton("Verwijderen");
    private JButton wijzigButton = new JButton("Wijzigen");
    private JButton terugButton = new JButton ("Terug");
    private JLabel searchLabel = new JLabel("search: ");
    private JPanel searchPanel = new JPanel(new BorderLayout());
    private JPanel buttonPanel = new JPanel(new BorderLayout());





    public ToernooiLijst(){
        jtbl.setRowSorter(rowSorter);

        buttonPanel.add(terugButton, BorderLayout.LINE_START);
        buttonPanel.add(verwijderButten, BorderLayout.CENTER);
        buttonPanel.add(wijzigButton, BorderLayout.LINE_END);

        searchPanel.add(jtfFilter,BorderLayout.CENTER);
        searchPanel.add(searchLabel, BorderLayout.LINE_START);
        searchPanel.add(buttonPanel, BorderLayout.LINE_END);
        cnt.setLayout(new BorderLayout());
        cnt.add(searchPanel,BorderLayout.SOUTH);

        setTitle("Toernooi Lijst");
        setPreferredSize(new Dimension(1000, 500));
        //setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



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


        showLijst();
        addActionlisteners();
        JScrollPane pg = new JScrollPane(jtbl);
        cnt.add(pg);
        this.pack();
    }

    public void showLijst(){
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
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://meru.hhs.nl/18095240", "18095240", "Ene3shaise");
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM Toernooi");
            ResultSet Rs = pstm.executeQuery();
            while(Rs.next()){
                model.addRow(new Object[]{Rs.getString(1), Rs.getString(2),Rs.getString(3),Rs.getString(4),Rs.getString(5),Rs.getString(6),Rs.getString(7),Rs.getString(8),Rs.getString(9), Rs.getString(10)});
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void verwijderToernooi(){
        int TCcolumn = 0;
        int row = jtbl.getSelectedRow();
        int tc = Integer.parseInt(jtbl.getModel().getValueAt(row, TCcolumn).toString());
        System.out.println(tc);
        try{
            Connection con = Main.getConnection();
            PreparedStatement verwijder = con.prepareStatement("DELETE FROM Toernooi WHERE TC = "+tc+";");
            verwijder.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == verwijderButten) {
            verwijderToernooi();
            JOptionPane.showMessageDialog(this, "Toernooi verwijderd");
            dispose();
           ToernooiLijst refresh = new ToernooiLijst();
        }
    }

    public void addActionlisteners(){
        verwijderButten.addActionListener(this);
    }

    }


