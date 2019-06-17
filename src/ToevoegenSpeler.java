import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class ToevoegenSpeler extends JFrame implements ActionListener {

    private ButtonGroup btngrp = new ButtonGroup();

    // All the labels represent! :
   private JLabel naamLabel = new JLabel("Naam: ");
   private JLabel postcodeLabel = new JLabel("Postcode: ");
   //private JLabel leeftijdLabel = new JLabel("Leeftijd: ");
   private JLabel adresLabel = new JLabel("Adres: ");
   private JLabel woonplaatsLabel = new JLabel("Woonplaats: ");
   private JLabel telefoonNummerLabel = new JLabel("Telefoon nr: ");
   private JLabel emailLabel = new JLabel("e-mail: ");
   private JLabel geboorteDatumLabel = new JLabel("Geboortedatum: ");
   private JLabel geslachtLabel = new JLabel("Geslacht: ");

   // All the textfields represent! :
    private JTextField naamField = new JTextField();
    private JTextField postcodeField = new JTextField();
    private JTextField adresField = new JTextField();
    //private JTextField leeftijdField = new JTextField();
    private JTextField woonplaatsField = new JTextField();
    private JTextField telefoonNummerField = new JTextField();
    private JTextField emailField = new JTextField();
    private JTextField geslachtField = new JTextField();



   private DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
   private JFormattedTextField geboorteDatumField = new JFormattedTextField(format);

   private JButton bevestigen = new JButton("Klaar");
   private JButton terug = new JButton("Terug");

   public ToevoegenSpeler(){
       setTitle("Toevoegen van speler");
       setLayout(null);
       setVisible(true);
       setSize(450, 550);
       setDefaultCloseOperation(EXIT_ON_CLOSE);
       setResizable(false);
       setComponentBounds();
       addComponents();
       addActionListeners();
}



   public void emptyTextFields() {
       naamField.setText("");
       //leeftijdField.setText("");
       postcodeField.setText("");
       adresField.setText("");
       woonplaatsField.setText("");
       telefoonNummerField.setText("");
       emailField.setText("");
       geslachtField.setText("");
       geboorteDatumField.setText("");
   }


   public void setComponentBounds(){
       naamLabel.setBounds(30,10,100,40);
       postcodeLabel.setBounds(30,60,100,40);
       woonplaatsLabel.setBounds(30,110,100,40);
       adresLabel.setBounds(30,160,100,40);
       telefoonNummerLabel.setBounds(30,210,100,40);
       emailLabel.setBounds(30,260,100,40);
       geboorteDatumLabel.setBounds(30,310,100,40);
       geslachtLabel.setBounds(30,360,100,40);

       naamField.setBounds(170,10,100,40);
       postcodeField.setBounds(170,60,100,40);
       woonplaatsField.setBounds(170,110,100,40);
       adresField.setBounds(170,160,100,40);
       telefoonNummerField.setBounds(170,210,100,40);
       emailField.setBounds(170,260,100,40);
       geboorteDatumField.setBounds(170,310,100,40);
       geslachtField.setBounds(170,360,100,40);


       bevestigen.setBounds(270,459,100,40);
       terug.setBounds(371,459,75,40);


   }

   public void addComponents(){
       add(naamLabel);
       //add(leeftijdLabel);
       add(postcodeLabel);
       add(woonplaatsLabel);
       add(adresLabel);
       add(telefoonNummerLabel);
       add(emailLabel);
       add(geslachtLabel);
       add(geboorteDatumLabel);

       add(naamField);
       add(postcodeField);
       //add(leeftijdField);

       add(woonplaatsField);
       add(adresField);
       add(telefoonNummerField);
       add(emailField);
       add(geboorteDatumField);
       add(geslachtField);


       add(bevestigen);
       add(terug);
   }





   public boolean addSpeler() {
       if (naamField.getText().equals("") || adresField.getText().equals("") || postcodeField.getText().equals("") || woonplaatsField.getText().equals("") || telefoonNummerField.getText().equals("") || emailField.getText().equals("") || geboorteDatumField.getText().equals("") || geslachtField.getText().equals("")) {
           return false;
       } else {
           try {
               Connection con = Main.getConnection();
               PreparedStatement add = con.prepareStatement("INSERT INTO Spelers (naam, adres, postcode, woonplaats, telefoonnr, email, geboortedatum, geslacht) VALUES (?,?,?,?,?,?,?,?)");
               add.setString(1,naamField.getText());
               add.setString(2,adresField.getText());
               add.setString(3,postcodeField.getText());
               add.setString(4,woonplaatsField.getText());
               add.setString(5,telefoonNummerField.getText());
               add.setString(6,emailField.getText());
               add.setDate(7,java.sql.Date.valueOf(geboorteDatumField.getText()));
               add.setString(8,geslachtField.getText());
               add.executeUpdate();
               return true;
           } catch (Exception e) {
               System.out.println(e);
           }
           return false;
       }
   }

   public void addActionListeners(){
       terug.addActionListener(this);
       bevestigen.addActionListener(this);
   }

    @Override
    public void actionPerformed(ActionEvent e){
       if(e.getSource() == terug){
           SpelerMenu menu = new SpelerMenu();
           dispose();
       }
       if(e.getSource() == bevestigen) {
           if (addSpeler()){
               JOptionPane.showMessageDialog(this, "Speler toegevoegd");
               emptyTextFields();
               dispose();
               SpelerMenu menu = new SpelerMenu();
           } else {
               JOptionPane.showMessageDialog(this, "Niet alles is ingevuld!");
           }

       }
    }
}
