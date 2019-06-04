import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VerwijderenOfToevoegen extends JFrame implements ActionListener {
    private int soort;
    JButton toevoegen = new JButton("Toevoegen");
    JButton verwijderen = new JButton("Verwijderen");
    JButton terug = new JButton("Terug");

    public VerwijderenOfToevoegen(int soort){
        this.soort = soort;


        setTitle("Toevoegen of verwijderen");
        setLayout(null);
        setVisible(true);
        setSize(450, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setComponentBounds();
        addComponents();
        addActionListeners();
    }

    public void setComponentBounds(){
        toevoegen.setBounds(15,80,150,40);
        verwijderen.setBounds(285,80,150,40);
        terug.setBounds(349,169,100,30);
    }

    public void addComponents(){
        add(toevoegen);
        add(verwijderen);
        add(terug);
    }

    public void addActionListeners(){
        toevoegen.addActionListener(this);
        verwijderen.addActionListener(this);
        terug.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == terug){
            if(soort == 1){
                Toevoegen toevoegen = new Toevoegen(1);
                dispose();
            }
            if(soort == 2){
                Toevoegen toernooiToevoegen = new Toevoegen(2);
                dispose();
            }
            if(soort == 3){
                Toevoegen masterclassToevoegen = new Toevoegen(3);
                dispose();
            }
        }
    }
}
