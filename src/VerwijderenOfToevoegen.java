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


        setTitle("Wijzigen of verwijderen");
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == terug){
            if(soort == 1){
                Wijzigen toevoegen = new Wijzigen(1);
                dispose();
            }
            if(soort == 2){
                Wijzigen toernooiToevoegen = new Wijzigen(2);
                dispose();
            }
            if(soort == 3){
                Wijzigen masterclassToevoegen = new Wijzigen(3);
                dispose();
            }
        }
        if(e.getSource() == toevoegen){
            if(soort == 1){
                ToevoegenSpeler toevoegenSpeler = new ToevoegenSpeler();
                dispose();
            }
            if(soort == 2){
                ToevoegenToernooi toevoegenToernooi = new ToevoegenToernooi();
                dispose();
            }
            if(soort == 3){
                ToevoegenMasterclass toevoegenMasterclass = new ToevoegenMasterclass();
            }
        }
    }
}
