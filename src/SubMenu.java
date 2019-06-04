import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class SubMenu extends JFrame implements ActionListener {

    JButton lijst = new JButton();
    JButton toevoegen = new JButton();
    JButton terug = new JButton("Terug");

    public SubMenu() {
        setLayout(null);
        setVisible(true);
        setSize(600, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("Kies een optie");


        setComponentBounds();
        addComponents();
        addActionListeners();
    }


    public void setComponentBounds() {
        lijst.setBounds(20, 75, 250, 30);
        toevoegen.setBounds(330, 75, 250, 30);
        terug.setBounds(499, 139, 100, 30);
    }

    public void addComponents() {
        add(lijst);
        add(toevoegen);
        add(terug);
    }

    public void addActionListeners() {
        lijst.addActionListener(this);
        toevoegen.addActionListener(this);
        terug.addActionListener(this);
    }

    public abstract void renameButtons();

    public JButton getLijst() {

        return this.lijst;
    }

    public JButton getToevoegen() {

        return this.toevoegen;
    }

    public void setTextLijst(String text) {
        lijst.setText(text);
    }

    public void setTextToevoegen(String text) {
        toevoegen.setText(text);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == lijst) {
            Lijst lijstframe = new Lijst();
            if (lijst.getText().equals("Spelerlijst")) {
                lijstframe.setSoortLijst(1);
            } else if (lijst.getText().equals("Toernooienlijst")) {
                lijstframe.setSoortLijst(2);
            } else if (lijst.getText().equals("Masterclasses lijst")) {
                lijstframe.setSoortLijst(3);
            }
            dispose();
        } else if (e.getSource() == toevoegen && toevoegen.getText().equals("Speler wijzigen")) {
            Toevoegen toevoegenframe = new Toevoegen(1);
        } else if (e.getSource() == toevoegen && toevoegen.getText().equals("Toernooi wijzigen")) {
            Toevoegen toevoegentoernooi = new Toevoegen(2);
        } else if (e.getSource() == toevoegen && toevoegen.getText().equals("Masterclass wijzigen")) {
            Toevoegen toevoegenMasterclass = new Toevoegen(3);
        } else if (e.getSource() == terug) {
            dispose();
            HoofdMenu menu = new HoofdMenu();
        }
        dispose();
       
    }
}



