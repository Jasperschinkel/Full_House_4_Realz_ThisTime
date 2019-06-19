import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToernooiMenu extends JFrame implements ActionListener {

// initializing all components
    JButton lijstButton = new JButton("Toernooienlijst");
    JButton toevoegButton = new JButton("Toernooi Toevoegen");
    JButton terugButton = new JButton("Terug");
    JButton logoutButton = new JButton("Log out");
    JButton winnaarButton = new JButton("Winnaar registratie");

    // the constructor
    public ToernooiMenu(){

        setLayout(null);
        setVisible(true);
        setSize(780, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Administratie app");

        setComponentBounds();
        addComponents();
        addActionListeners();
    }
    // laying out all the components on the JFrame and setting their sizes
    public void setComponentBounds() {
        lijstButton.setBounds(20, 75, 150, 30);
       toevoegButton.setBounds(225, 75, 150, 30);
        logoutButton.setBounds(640, 139, 100, 30);
        terugButton.setBounds(610,75,150,30);
        winnaarButton.setBounds(420,75,150,30);
    }

    // adding all the components to the JFrame
    public void addComponents(){
        add(lijstButton);
        add(toevoegButton);
        add(logoutButton);
        add(terugButton);
        add(winnaarButton);
    }

    // adding action listeners to the buttons
    public void addActionListeners(){
        lijstButton.addActionListener(this);
        toevoegButton.addActionListener(this);
        logoutButton.addActionListener(this);
        terugButton.addActionListener(this);
        winnaarButton.addActionListener(this);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == lijstButton) {
            dispose();
            ToernooiLijst lijst = new ToernooiLijst();
                    lijst.pushIsGeweest();
        }
        if(e.getSource() == toevoegButton) {
            dispose();
            ToevoegenToernooi toevoeg = new ToevoegenToernooi();
        }
        if(e.getSource() == logoutButton){
            dispose();
            LoginFrame login = new LoginFrame();
        }
        if(e.getSource()== terugButton){
            dispose();
            HoofdMenu hoofd = new HoofdMenu();
        }
        if(e.getSource() == winnaarButton){
            WinnaarFrame winnaarFrame = new WinnaarFrame();
            dispose();
        }
    }



}
