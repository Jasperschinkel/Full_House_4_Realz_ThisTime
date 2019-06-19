import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpelerMenu extends JFrame implements ActionListener {

    // initializing all the components
    JButton lijstButton = new JButton("Spelerlijst");
    JButton terugButton = new JButton("Terug");
    JButton toevoegButton = new JButton("Speler Toevoegen");
    JButton logoutButton = new JButton("Log out");

    // the constructor
    public SpelerMenu() {

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

    // laying out all the components on the JFrame and setting their sizes.
    public void setComponentBounds() {
        lijstButton.setBounds(20, 75, 150, 30);
        toevoegButton.setBounds(225, 75, 150, 30);
        logoutButton.setBounds(495, 139, 100, 30);
        terugButton.setBounds(440,75,150,30);
    }

    // adding components to the JFrame
    public void addComponents(){
        add(lijstButton);
        add(toevoegButton);
        add(logoutButton);
        add(terugButton);
    }

    // adding action listeners to the buttons
    public void addActionListeners(){
        lijstButton.addActionListener(this);
        toevoegButton.addActionListener(this);
        logoutButton.addActionListener(this);
        terugButton.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == lijstButton) {
            dispose();
            SpelerLijst lijst = new SpelerLijst();
        }
        if (e.getSource() == toevoegButton) {
            dispose();
            ToevoegenSpeler toevoeg = new ToevoegenSpeler();

        }
        if(e.getSource() == logoutButton){
            dispose();
            LoginFrame login = new LoginFrame();
        }
        if(e.getSource() == terugButton){
            dispose();
            HoofdMenu hoofd = new HoofdMenu();
        }
    }
}
