import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MasterclassMenu extends JFrame implements ActionListener {

    JButton lijstButton = new JButton("Masterclasslijst");
    JButton wijzigButton = new JButton("Masterclass wijzigen");
    JButton terugButton = new JButton("Terug");
    JButton logoutButton = new JButton("Log out");

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

    public void setComponentBounds() {
        lijstButton.setBounds(20, 75, 150, 30);
        wijzigButton.setBounds(225, 75, 150, 30);
        logoutButton.setBounds(495, 139, 100, 30);
        terugButton.setBounds(440,75,150,30);
    }

    public void addComponents(){
        add(lijstButton);
        add(wijzigButton);
        add(logoutButton);
        add(terugButton);
    }

    public void addActionListeners(){
        lijstButton.addActionListener(this);
        wijzigButton.addActionListener(this);
        logoutButton.addActionListener(this);
        terugButton.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == lijstButton) {
            dispose();
            MasterclassLijst lijst = new MasterclassLijst();
        }
        if (e.getSource() == wijzigButton) {
            dispose();
            Wijzigen wijzig = new Wijzigen(3);
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
