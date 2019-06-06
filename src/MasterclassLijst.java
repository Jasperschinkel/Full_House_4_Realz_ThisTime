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



    public class MasterclassLijst extends JFrame implements ActionListener {


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

        public MasterclassLijst(){
            jtbl.setRowSorter(rowSorter);

            buttonPanel.add(terugButton, BorderLayout.LINE_START);
            buttonPanel.add(verwijderButten, BorderLayout.CENTER);
            buttonPanel.add(wijzigButton, BorderLayout.LINE_END);

            searchPanel.add(jtfFilter,BorderLayout.CENTER);
            searchPanel.add(searchLabel, BorderLayout.LINE_START);
            searchPanel.add(buttonPanel, BorderLayout.LINE_END);
            cnt.setLayout(new BorderLayout());
            cnt.add(searchPanel,BorderLayout.SOUTH);

            setTitle("Masterclass Lijst");
            setPreferredSize(new Dimension(1000, 500));

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
            model.addColumn("MasterclassCode");
            model.addColumn("datum");
            model.addColumn("begintijd");
            model.addColumn("eindtijd");
            model.addColumn("kosten");
            model.addColumn("maximale ranking");
            model.addColumn("Bekende speler");
            model.addColumn("Max. aantal spelers");

            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://meru.hhs.nl/18095240", "18095240", "Ene3shaise");
                PreparedStatement pstm = con.prepareStatement("SELECT * FROM Masterclass");
                ResultSet Rs = pstm.executeQuery();
                while(Rs.next()){
                    model.addRow(new Object[]{Rs.getString(1), Rs.getString(2),Rs.getString(3),Rs.getString(4),Rs.getString(5),Rs.getString(6), Rs.getString(7), Rs.getString(8)});
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        public void verwijderToernooi(){
            int MCcolumn = 0;
            int row = jtbl.getSelectedRow();
            int mc = Integer.parseInt(jtbl.getModel().getValueAt(row, MCcolumn).toString());
            try{
                Connection con = Main.getConnection();
                PreparedStatement verwijder = con.prepareStatement("DELETE FROM Masterclass WHERE MasterclassCOde = "+mc+";");
                verwijder.executeUpdate();
            }catch(Exception e){
                System.out.println(e);
            }
        }
        public void actionPerformed(ActionEvent e){
            if(e.getSource() == verwijderButten) {
                verwijderToernooi();
                JOptionPane.showMessageDialog(this, "Masterclass verwijderd");
                dispose();
                MasterclassLijst refresh = new MasterclassLijst();
            }
            if(e.getSource() == terugButton) {
                dispose();
                MasterclassMenu menu = new MasterclassMenu();
            }
        }

        public void addActionlisteners(){
            verwijderButten.addActionListener(this);
            terugButton.addActionListener(this);
        }

    }


