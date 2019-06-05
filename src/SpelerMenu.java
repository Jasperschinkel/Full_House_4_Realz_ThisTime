import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpelerMenu extends JFrame implements ActionListener {

    JButton lijstButton = new JButton("Spelerlijst");
    JButton toevoegButton = new JButton("Speler toevoegen");
    JButton verwijderButton = new JButton("Speler verwijderen");
    JButton wijzigButton = new JButton("Speler wijzigen");
    JButton logoutButton = new JButton("Log out");

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

    public void setComponentBounds() {
        lijstButton.setBounds(20, 75, 150, 30);
        wijzigButton.setBounds(225, 75, 150, 30);
        logoutButton.setBounds(495, 139, 100, 30);
    }

    public void addComponents(){
        add(lijstButton);
        add(wijzigButton);
        add(logoutButton);
    }

    public void addActionListeners(){
        lijstButton.addActionListener(this);
        wijzigButton.addActionListener(this);
        logoutButton.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == lijstButton) {
            dispose();
            SpelerLijst lijst = new SpelerLijst();
        }
        if (e.getSource() == wijzigButton) {
            dispose();

        }
        if(e.getSource() == logoutButton){
            dispose();
            LoginFrame login = new LoginFrame();

        }
    }
}
