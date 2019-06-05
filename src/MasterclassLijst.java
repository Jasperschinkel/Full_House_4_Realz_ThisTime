import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


    public class MasterclassLijst extends JFrame {

        DefaultTableModel model = new DefaultTableModel();
        Container cnt = this.getContentPane();
        JTable jtbl = new JTable(model);

        public MasterclassLijst(){

            cnt.setLayout(new GridLayout());
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



            JScrollPane pg = new JScrollPane(jtbl);
            cnt.add(pg);
            this.pack();
        }

    }


