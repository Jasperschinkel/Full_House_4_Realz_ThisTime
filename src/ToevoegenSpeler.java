import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class ToevoegenSpeler extends JFrame implements ActionListener {

    // All the labels represent! :
   private JLabel voornaamLabel = new JLabel("Voornaam: ");
   private JLabel achternaamLabel = new JLabel("Achternaam: ");
   private JLabel postcodeLabel = new JLabel("Postcode: ");
   private JLabel adresLabel = new JLabel("Adres: ");
   private JLabel woonplaatsLabel = new JLabel("Woonplaats: ");
   private JLabel telefoonNummerLabel = new JLabel("Telefoon nr: ");
   private JLabel emailLabel = new JLabel("e-mail: ");
   private JLabel geboorteDatumLabel = new JLabel("Geboortedatum: ");
   private JLabel geslachtLabel = new JLabel("Geslacht: ");

   // All the textfields represent! :
    private JTextField voornaamField = new JTextField();
    private JTextField achternaamField = new JTextField();
    private JTextField postcodeField = new JTextField();
    private JTextField adresField = new JTextField();
    private JTextField woonplaatsField = new JTextField();
    private JTextField telefoonNummerField = new JTextField();
    private JTextField emailField = new JTextField();

    private JRadioButton mannenButton = new JRadioButton("M",true);
    private JRadioButton vrouwenButton = new JRadioButton("V");
    private JRadioButton trannieButton = new JRadioButton("Anders");

   private DateFormat format = new SimpleDateFormat("dd--MMMM--yyyy");
   private JFormattedTextField geboorteDatumField = new JFormattedTextField(format);

   private JButton bevestigen = new JButton("Klaar");
   private JButton terug = new JButton("Terug");

   public ToevoegenSpeler(){
       setTitle("Toevoegen van speler");
       setLayout(null);
       setVisible(true);
       setSize(450, 500);
       setDefaultCloseOperation(EXIT_ON_CLOSE);
       setResizable(false);
       setComponentBounds();
       addComponents();
       addActionListeners();
   }

   public void addToButtonGroup(){
       ButtonGroup btngrp = new ButtonGroup();
       btngrp.add(mannenButton);
       btngrp.add(vrouwenButton);
       btngrp.add(trannieButton);
   }

   public void setComponentBounds(){
       voornaamLabel.setBounds(30,10,100,40);
       achternaamLabel.setBounds(30,60,100,40);
       postcodeLabel.setBounds(30,110,100,40);
       woonplaatsLabel.setBounds(30,160,100,40);
       adresLabel.setBounds(30,210,100,40);
       telefoonNummerLabel.setBounds(30,260,100,40);
       emailLabel.setBounds(30,310,100,40);
       geboorteDatumLabel.setBounds(30,410,100,40);
       geslachtLabel.setBounds(30,360,100,40);

       voornaamField.setBounds(140,10,100,40);
       achternaamField.setBounds(140,60,100,40);
       postcodeField.setBounds(140,110,100,40);
       woonplaatsField.setBounds(140,160,100,40);
       adresField.setBounds(140,210,100,40);
       telefoonNummerField.setBounds(140,260,100,40);
       emailField.setBounds(140,310,100,40);
       geboorteDatumField.setBounds(140,410,100,40);
       mannenButton.setBounds(140,360,100,40);
       vrouwenButton.setBounds(241,360,100,40);
       trannieButton.setBounds(342,360,100,40);

       bevestigen.setBounds(270,400,100,40);
       terug.setBounds(371,400,75,40);


   }

   public void addComponents(){
       add(voornaamLabel);
       add(achternaamLabel);
       add(postcodeLabel);
       add(adresLabel);
       add(telefoonNummerLabel);
       add(emailLabel);
       add(geslachtLabel);
       add(geboorteDatumLabel);

       add(voornaamField);
       add(achternaamField);
       add(postcodeField);
       add(adresField);
       add(telefoonNummerField);
       add(emailField);
       add(geboorteDatumField);
       add(mannenButton);
       add(vrouwenButton);
       add(trannieButton);

       add(bevestigen);
       add(terug);
   }

   public void addActionListeners(){
       mannenButton.addActionListener(this);
       vrouwenButton.addActionListener(this);
       trannieButton.addActionListener(this);
       terug.addActionListener(this);
       bevestigen.addActionListener(this);
   }

    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource() == terug){
           VerwijderenOfToevoegen verwijderenOfToevoegen = new VerwijderenOfToevoegen(1);
           dispose();
       }
    }
}
