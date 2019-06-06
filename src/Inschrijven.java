import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Inschrijven extends JFrame implements ActionListener {
    //Labels
    private JLabel naamLabel = new JLabel("Naam: ");
    private JLabel rankingLabel = new JLabel("Ranking: ");
    private JLabel typeLabel = new JLabel("Type inschrijving: ");
    private JLabel codeLabel = new JLabel ("Code: ");
    private JLabel heeftBetaaldLabel = new JLabel("Heeft betaald: ");

    //Textfields
    private JTextField naamField = new JTextField();
    private JTextField rankingField = new JTextField();
    private JTextField typeField = new JTextField();
    private JTextField codeField = new JTextField();
    private JTextField heeftBetaaldField = new JTextField();

    //Buttons
    private JButton terugButton = new JButton("Terug");
    private JButton klaarButton = new JButton("Klaar");

    public Inschrijven(){
        setTitle("Inschrijven");
        setLayout(null);
        setVisible(true);
        setSize(400, 360);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setComponentBounds();
        addComponents();
        addActionListeners();

    }

    public void emptyTextField(){
        naamField.setText("");
        rankingField.setText("");
        typeField.setText("");
        codeField.setText("");
        heeftBetaaldField.setText("");
    }

    public void setComponentBounds(){
        naamLabel.setBounds(40,10,100,40);
        rankingLabel.setBounds(40,60,100,40);
        typeLabel.setBounds(40,110,100,40);
        codeLabel.setBounds(40,160,200,40);
        heeftBetaaldLabel.setBounds(40, 210, 200, 40);

        naamField.setBounds(250, 10, 100, 40);
        rankingField.setBounds(250, 60, 100, 40);
        typeField.setBounds(250, 110, 100, 40);
        codeField.setBounds(250, 160, 100, 40);
        heeftBetaaldField.setBounds(250, 210, 100, 40);

        terugButton.setBounds(275,260,75,40);
        klaarButton.setBounds(175,260,100,40);



    }

    public void addComponents(){
        add(naamLabel);
        add(rankingLabel);
        add(typeLabel);
        add(codeLabel);
        add(heeftBetaaldLabel);

        add(naamField);
        add(rankingField);
        add(typeField);
        add(codeField);
        add(heeftBetaaldField);

        add(klaarButton);
        add(terugButton);
    }

    public void addActionListeners(){
        terugButton.addActionListener(this);
        klaarButton.addActionListener(this);
    }

    public void addInschrijving(){
        try{
            Connection con = Main.getConnection();
            PreparedStatement add = con.prepareStatement("INSERT INTO Inschrijvingen (naam, ranking, type_inschrijving, nummercode, heeft_betaald) VALUES ('"+naamField.getText()+ "', '"+rankingField.getText()+ "', '"+typeField.getText()+ "', '"+codeField.getText()+"', '"+heeftBetaaldField.getText()+"');");
            add.executeUpdate();
        }catch(Exception e) {
            System.out.println(e);
        }

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == terugButton){
            dispose();
        }
        if(e.getSource() == klaarButton){
            addInschrijving();
            JOptionPane.showMessageDialog(this, "Masterclass toegevoegd");
            emptyTextField();
        }

    }
}