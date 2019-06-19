import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WinnaarFrame extends JFrame implements ActionListener {

    // setting global variables for the class scope

    private int aantalRondes;

    private int toernooiCode;

// initializing all components
    JLabel aantalRondesLabel = new JLabel("Aantal rondes");
    JTextField aantalRondesField = new JTextField();

    JLabel toernooiCodeLabel = new JLabel("Toernooicode");
    JTextField toernooiCodeField = new JTextField();

    JButton bevestigen = new JButton("Klaar");
    JButton terug = new JButton("terug");

   // the constructor
    public WinnaarFrame(){

        setLayout(null);
        setTitle("Hoeveel rondes heeft het toernooi?");
        setVisible(true);
        setResizable(false);
        setSize(400,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setComponentBounds();
        addComponents();
        addActionListeners();
    }

    // laying out all the components on the JFrame, and setting their sizes
    public void setComponentBounds(){
        aantalRondesLabel.setBounds(50,50,100,50);
        toernooiCodeLabel.setBounds(50,110,100,50);
        aantalRondesField.setBounds(250,50,100,50);
        toernooiCodeField.setBounds(250,110,100,50);
        bevestigen.setBounds(180,310,100,50);
        terug.setBounds(300,310,75,50);
    }

    // adding the components to the JFrame
    public void addComponents(){
        add(aantalRondesLabel);
        add(aantalRondesField);
        add(toernooiCodeLabel);
        add(toernooiCodeField);
        add(bevestigen);
        add(terug);
    }

    // adding action listeners to the buttons
    public void addActionListeners(){
        bevestigen.addActionListener(this);
        terug.addActionListener(this);
    }




// getters and setters
    public int getAantalRondes(){
        String aantalRondesString = aantalRondesField.getText();
        int aantalRondes = Integer.valueOf(aantalRondesString);

        this.aantalRondes = aantalRondes;

        return this.aantalRondes;
    }

    public int getToernooiCode(){
        String toernooiCodeString = toernooiCodeField.getText();
        int toernooiCode = Integer.valueOf(toernooiCodeString);

        this.toernooiCode = toernooiCode;

        return this.toernooiCode;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == terug){
            ToernooiMenu toernooiMenu = new ToernooiMenu();
            dispose();
        }
        if(e.getSource() == bevestigen){
            if (toernooiCodeField.getText().equals("") || aantalRondesField.getText().equals("")){
                JOptionPane.showMessageDialog(this, "Niet alles is ingevuld!");
            } else {
                String TCString = toernooiCodeField.getText();
                String rondesString = aantalRondesField.getText();
                int TC = Integer.valueOf(TCString);
                int rondes = Integer.valueOf(rondesString);
                RegistratieWinnaars registratieWinnaars = new RegistratieWinnaars(rondes, TC);
            }
        }
    }

}
