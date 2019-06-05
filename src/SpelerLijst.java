import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SpelerLijst extends JFrame {
    DefaultTableModel model = new DefaultTableModel();
    Container cnt = this.getContentPane();
    JTable jtbl = new JTable(model);



    public SpelerLijst(){

        cnt.setLayout(new GridLayout());
        setTitle("Speler Lijst");
        setPreferredSize(new Dimension(1000, 1000));
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        model.addColumn("naam");
        model.addColumn("adres");
        model.addColumn("Postcode");
        model.addColumn("Woonplaats");
        model.addColumn("Geboortedatum");
        model.addColumn("E-Mail");
        model.addColumn("Leeftijd");
        model.addColumn("Geslacht");
        model.addColumn("Ranking");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://meru.hhs.nl/18095240", "18095240", "Ene3shaise");
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM Spelers");
            ResultSet Rs = pstm.executeQuery();
            while(Rs.next()){
                model.addRow(new Object[]{Rs.getString(1), Rs.getString(2),Rs.getString(3),Rs.getString(4),Rs.getString(5),Rs.getString(6),Rs.getString(7),Rs.getString(8),Rs.getString(9)});
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }



        JScrollPane pg = new JScrollPane(jtbl);
        cnt.add(pg);
        this.pack();
    }


    }

