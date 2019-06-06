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
   private JLabel naamLabel = new JLabel("naam: ");
   private JLabel postcodeLabel = new JLabel("Postcode: ");
   private JLabel leeftijdLabel = new JLabel("Leeftijd: ");
   private JLabel adresLabel = new JLabel("Adres: ");
   private JLabel woonplaatsLabel = new JLabel("Woonplaats: ");
   private JLabel telefoonNummerLabel = new JLabel("Telefoon nr: ");
   private JLabel emailLabel = new JLabel("e-mail: ");
   private JLabel geboorteDatumLabel = new JLabel("Geboortedtm: ");
   private JLabel geslachtLabel = new JLabel("Geslacht: ");

   // All the textfields represent! :
    private JTextField naamField = new JTextField();
    private JTextField postcodeField = new JTextField();
    private JTextField adresField = new JTextField();
    private JTextField leeftijdField = new JTextField();
    private JTextField woonplaatsField = new JTextField();
    private JTextField telefoonNummerField = new JTextField();
    private JTextField emailField = new JTextField();
    private JTextField geslachtField = new JTextField();



   private DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
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
       leeftijdField.setText("");
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
       leeftijdLabel.setBounds(30,60,100,40);
       postcodeLabel.setBounds(30,110,100,40);
       woonplaatsLabel.setBounds(30,160,100,40);
       adresLabel.setBounds(30,210,100,40);
       telefoonNummerLabel.setBounds(30,260,100,40);
       emailLabel.setBounds(30,310,100,40);
       geboorteDatumLabel.setBounds(30,410,100,40);
       geslachtLabel.setBounds(30,360,100,40);

       naamField.setBounds(140,10,100,40);
       leeftijdField.setBounds(140,60,100,40);
       postcodeField.setBounds(140,110,100,40);
       woonplaatsField.setBounds(140,160,100,40);
       adresField.setBounds(140,210,100,40);
       telefoonNummerField.setBounds(140,260,100,40);
       emailField.setBounds(140,310,100,40);
       geboorteDatumField.setBounds(140,410,100,40);
       geslachtField.setBounds(140,360,100,40);

       bevestigen.setBounds(270,459,100,40);
       terug.setBounds(371,459,75,40);


   }

   public void addComponents(){
       add(naamLabel);
       add(leeftijdLabel);
       add(postcodeLabel);
       add(woonplaatsLabel);
       add(adresLabel);
       add(telefoonNummerLabel);
       add(emailLabel);
       add(geslachtLabel);
       add(geboorteDatumLabel);

       add(naamField);
       add(postcodeField);
       add(leeftijdField);

       add(woonplaatsField);
       add(adresField);
       add(telefoonNummerField);
       add(emailField);
       add(geboorteDatumField);
       add(geslachtField);


       add(bevestigen);
       add(terug);
   }





   public void addSpeler(){

       try{
           Connection con = Main.getConnection();
           PreparedStatement add = con.prepareStatement("INSERT INTO Spelers (naam,adres, postcode, woonplaats, telefoonnr, email, geboortedatum, geslacht, leeftijd, ranking) VALUES ('"+naamField.getText()+ "', '"+adresField.getText()+ "', '"+postcodeField.getText()+ "', '"+woonplaatsField.getText()+"', '"+telefoonNummerField.getText()+ "', '"+emailField.getText()+"', '"+ geboorteDatumField.getText()+"', '"+geslachtField.getText()+"', '"+12+"', '"+0+"');");
           add.executeUpdate();
       }catch(Exception e) {
           System.out.println(e);
       }


   }

   public void addActionListeners(){
       terug.addActionListener(this);
       bevestigen.addActionListener(this);
   }

    @Override
    public void actionPerformed(ActionEvent e){
       if(e.getSource() == terug){
           VerwijderenOfToevoegen verwijderenOfToevoegen = new VerwijderenOfToevoegen(1);
           dispose();
       }
       if(e.getSource() == bevestigen) {
           addSpeler();
           JOptionPane.showMessageDialog(this, "Speler toegevoegd");
           emptyTextFields();
       }
    }
}
