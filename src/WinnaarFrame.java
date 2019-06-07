import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WinnaarFrame extends JFrame implements ActionListener {

    private int aantalRondes;

    private int toernooiCode;

    JLabel aantalRondesLabel = new JLabel("Aantal rondes");
    JTextField aantalRondesField = new JTextField();

    JLabel toernooiCodeLabel = new JLabel("Toernooicode");
    JTextField toernooiCodeField = new JTextField();

    JButton bevestigen = new JButton("Klaar");
    JButton terug = new JButton("terug");

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

    public void setComponentBounds(){
        aantalRondesLabel.setBounds(50,50,100,50);
        toernooiCodeLabel.setBounds(50,110,100,50);
        aantalRondesField.setBounds(250,50,100,50);
        toernooiCodeField.setBounds(250,110,100,50);
        bevestigen.setBounds(215,340,100,50);
        terug.setBounds(220,340,75,50);
    }

    public void addComponents(){
        add(aantalRondesLabel);
        add(aantalRondesField);
        add(toernooiCodeLabel);
        add(toernooiCodeField);
        add(bevestigen);
        add(terug);
    }

    public void addActionListeners(){
        bevestigen.addActionListener(this);
        terug.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {}

    public int getAantalRondes;

    public int getToernooiCode;


}
