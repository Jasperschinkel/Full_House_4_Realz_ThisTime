import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MasterclassMenu extends JFrame implements ActionListener {

    //initializing all the components
    JButton lijstButton = new JButton("Masterclasslijst");
    JButton toevoegButton = new JButton("Masterclass Toevoegen");
    JButton terugButton = new JButton("Terug");
    JButton logoutButton = new JButton("Log out");

    // the constructor
    public MasterclassMenu() {

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

    // method to lay out the components on the JFrame and to set their sizes
    public void setComponentBounds() {
        lijstButton.setBounds(20, 75, 150, 30);
        toevoegButton.setBounds(225, 75, 150, 30);
        logoutButton.setBounds(495, 139, 100, 30);
        terugButton.setBounds(440,75,150,30);
    }

    // method to add components to the JFrame
    public void addComponents(){
        add(lijstButton);
        add(toevoegButton);
        add(logoutButton);
        add(terugButton);
    }

    // method to add action listeners to the buttons.
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
            MasterclassLijst lijst = new MasterclassLijst();
        }
        if (e.getSource() == toevoegButton) {
            dispose();
            ToevoegenMasterclass toevoeg = new ToevoegenMasterclass();
        }
        if(e.getSource() == logoutButton){
            dispose();
            LoginFrame login = new LoginFrame();
        }
        if(e.getSource() == terugButton) {
            dispose();
            HoofdMenu hoofd = new HoofdMenu();
        }
    }
}
