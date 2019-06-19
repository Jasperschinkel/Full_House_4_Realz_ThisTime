import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class RegistratieMenu extends JFrame implements ActionListener {

    //initializing all the components
    private JButton inschrijvingenLijst = new JButton("Inschrijvingenlijst");
    private JButton inschrijven = new JButton("Nieuwe inschrijving");
    private JButton terug = new JButton("Terug");

    // the constructor
    public RegistratieMenu() {

        setLayout(null);
        setVisible(true);
        setSize(600, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Registratie");

        setComponentBounds();
        addComponents();
        addActionListeners();
    }

    // method for laying out  all the components on the JFrame and set their sizes
    public void setComponentBounds() {
        inschrijvingenLijst.setBounds(20, 75, 150, 30);
        inschrijven.setBounds(225, 75, 150, 30);
        terug.setBounds(430, 75, 150, 30);
    }

    // method to add the components to the JFrame
    public void addComponents() {
        add(inschrijvingenLijst);
        add(inschrijven);
        add(terug);
    }

    // method to add action listeners to the buttons
    public void addActionListeners() {
        inschrijvingenLijst.addActionListener(this);
        inschrijven.addActionListener(this);
        terug.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == inschrijvingenLijst) {
            dispose();
            InschrijvingenLijst lijst = new InschrijvingenLijst();
        }
        if (e.getSource() == inschrijven) {
            dispose();
            Inschrijven inschrijven = new Inschrijven();
        }
        if (e.getSource() == terug) {
            dispose();
            HoofdMenu menu = new HoofdMenu();
        }
    }
}