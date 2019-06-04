import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Toevoegen extends JFrame implements ActionListener {
   private int soort;
   private String soortString = "";

    JButton toevoegen = new JButton("Toevoegen/Verwijderen" + " " + soortString);
    JButton aanpassen = new JButton("Aanpassen" + " " + soortString);
    JButton inschrijven = new JButton("Spelers inschrijven");
    JButton terug = new JButton("Terug");


   public Toevoegen(int soort){
       this.soort = soort;
       if(soort == 1){
           soortString = "spelers";
       }
       if(soort == 2){
           soortString = "toernooien";
       }

       if(soort == 3){
           soortString = "Masterclasses";
       }
       setTitle("Wijzigen van" + " " + soortString);
       setLayout(null);
       setVisible(true);
       setSize(900, 200);
       setDefaultCloseOperation(EXIT_ON_CLOSE);
       setResizable(false);
       setComponentBounds();
       addComponents();
       addActionListeners();
   }



   public int getSoort(){
       return this.soort;
   }

   public void setSoort(int soort){
       this.soort = soort;
   }


    public void setComponentBounds(){
        toevoegen.setBounds(20, 75, 250, 30);
        aanpassen.setBounds(630, 75, 250, 30);
        terug.setBounds(799,139,100,30);
        if(soort == 2 || soort == 3){
            inschrijven.setBounds(330, 75, 250, 30);
        }
    }


    public void addComponents(){
       add(toevoegen);
       add(aanpassen);
       add(terug);
       if(soort == 2 || soort == 3){
           add(inschrijven);
       }
    }


    public void addActionListeners(){
       toevoegen.addActionListener(this);
       aanpassen.addActionListener(this);
       terug.addActionListener(this);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == terug) {
            if(soort == 1){
                SpelerMenu spelerMenu = new SpelerMenu();
                dispose();
            }
            if(soort == 2){
                ToernooiMenu toernooiMenu = new ToernooiMenu();
                dispose();
            }
            if(soort == 3){
                MasterclassMenu masterclassMenu = new MasterclassMenu();
                dispose();
            }

        }
        else if(e.getSource() == toevoegen){
            VerwijderenOfToevoegen verwijderenOfToevoegen = new VerwijderenOfToevoegen(this.getSoort());
            dispose();
        }
    }

    }
