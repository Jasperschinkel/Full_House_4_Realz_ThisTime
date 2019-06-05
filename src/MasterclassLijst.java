import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



    public class MasterclassLijst extends JFrame {


        DefaultTableModel model = new DefaultTableModel();
        Container cnt = this.getContentPane();
        JTable jtbl = new JTable(model);

        private TableRowSorter<TableModel> rowSorter = new TableRowSorter(jtbl.getModel());
        private JTextField jtfFilter = new JTextField();
        private JButton jbtFilter = new JButton("search");
       private JLabel searchLabel = new JLabel("search: ");
        private JPanel searchPanel = new JPanel(new BorderLayout());

        public MasterclassLijst(){
            jtbl.setRowSorter(rowSorter);

            searchPanel.add(jtfFilter,BorderLayout.CENTER);
            searchPanel.add(jbtFilter,BorderLayout.LINE_END);
            searchPanel.add(searchLabel, BorderLayout.LINE_START);
            cnt.setLayout(new BorderLayout());
            cnt.add(searchPanel,BorderLayout.SOUTH);

            setTitle("Toernooi Lijst");
            setPreferredSize(new Dimension(1000, 500));
            setLocationRelativeTo(null);
            setVisible(true);
            setResizable(false);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            model.addColumn("MasterclassCode");
            model.addColumn("datum");
            model.addColumn("begintijd");
            model.addColumn("eindtijd");
            model.addColumn("kosten");
            model.addColumn("maximale ranking");

            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://meru.hhs.nl/18095240", "18095240", "Ene3shaise");
                PreparedStatement pstm = con.prepareStatement("SELECT * FROM Masterclass");
                ResultSet Rs = pstm.executeQuery();
                while(Rs.next()){
                    model.addRow(new Object[]{Rs.getString(1), Rs.getString(2),Rs.getString(3),Rs.getString(4),Rs.getString(5),Rs.getString(6)});
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

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

    }


