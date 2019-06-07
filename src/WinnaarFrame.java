import javax.swing.*;

public class WinnaarFrame extends JFrame {

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

    public void setComponentBounds(){}

    public void addComponents(){}

    public void addActionListeners(){}


}
