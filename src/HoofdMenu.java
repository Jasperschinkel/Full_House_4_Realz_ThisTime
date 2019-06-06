import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HoofdMenu extends JFrame implements ActionListener {


    JButton spelerButton = new JButton("Spelers");
    JButton toernooiButton = new JButton("Toernooien");
    JButton masterclassButton = new JButton("Masterclasses");
    JButton logoutButton = new JButton("Log out");

    public HoofdMenu(){

        setLayout(null);
        setVisible(true);
        setSize(600, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Administratie app");

        setComponentBounds();
        addComponents();
        addActionListeners();
    }

    public void setComponentBounds(){
        spelerButton.setBounds(20, 75, 150, 30);
        toernooiButton.setBounds(225,75,150,30);
        masterclassButton.setBounds(430,75,150,30);
        logoutButton.setBounds(495,139,100,30);
    }

    public void addComponents(){
        add(spelerButton);
        add(toernooiButton);
        add(masterclassButton);
        add(logoutButton);
    }

    public void addActionListeners(){
        spelerButton.addActionListener(this);
        toernooiButton.addActionListener(this);
        masterclassButton.addActionListener(this);
        logoutButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == spelerButton) {
            dispose();
            SpelerMenu menu = new SpelerMenu();
        }
        if (e.getSource() == toernooiButton) {
            dispose();
            ToernooiMenu menu2 = new ToernooiMenu();
        }

        if (e.getSource() == masterclassButton) {
            dispose();
            MasterclassMenu menu3 = new MasterclassMenu();
        }
        if(e.getSource() == logoutButton){
            dispose();
            LoginFrame login = new LoginFrame();
        }
    }
}
