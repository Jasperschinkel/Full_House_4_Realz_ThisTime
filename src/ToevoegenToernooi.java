import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ToevoegenToernooi extends JFrame implements ActionListener, ChangeListener {

    static final int FPS_MIN = 25;
    static final int FPS_MAX = 150;
    static final int FPS_INIT = 50;

    //All the labels represent!:
   private JLabel beschrijvingLabel = new JLabel("Beschrijving: ");
   private JLabel datumLabel = new JLabel("Datum: ");
   private JLabel beginTijdLabel = new JLabel("Begintijd: ");
   private JLabel eindTijdLabel = new JLabel("Eindtijd: ");
   private JLabel soortLabel = new JLabel("Soort toernooi: ");
   private JLabel maxLabel = new JLabel("Max inschrijvingen");
   private JLabel inlegGeldLabel = new JLabel("Inleggeld: ");
   private JLabel uitersteLabel = new JLabel("Uiterlijk inschrijven tot: ");
   private JLabel conditieLabel = new JLabel("Condities: ");
   private JLabel locatieLabel = new JLabel("Locatie: ");


    // All the textfields represent!:
    private JTextField beschrijvingField = new JTextField();
    private JTextField conditieField = new JTextField();
    private JTextField beginTijdField = new JTextField();
    private JTextField eindTijdField = new JTextField();
    private JTextField inlegGeldField = new JTextField("");
    private JTextField locatieField = new JTextField();

    private DateFormat format = new SimpleDateFormat("YYYY-MM-DD");
    private JFormattedTextField datumField = new JFormattedTextField(format);
    private JFormattedTextField uitersteField = new JFormattedTextField(format);

    private JButton terug = new JButton("Terug");
    private JButton bevestigen = new JButton("Klaar");

    private JRadioButton normaal = new JRadioButton("Normaal");
    private JRadioButton pinkRibbon = new JRadioButton("Pink Ribbon");

    private JSlider maxAantalSlider = new JSlider(JSlider.HORIZONTAL,
            FPS_MIN, FPS_MAX, FPS_INIT);


    public ToevoegenToernooi(){
        setTitle("Toevoegen van toernooi");
        setLayout(null);
        setVisible(true);
        setSize(700, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setComponentBounds();
        addGroup();
        addComponents();
        addActionListeners();
        setSliderLabels();
    }

    public void setComponentBounds(){
        datumLabel.setBounds(40,10,100,40);
        beginTijdLabel.setBounds(40,60,100,40);
        eindTijdLabel.setBounds(40,110,100,40);
        soortLabel.setBounds(40,160,200,40);
        maxLabel.setBounds(40,210,200,40);
        inlegGeldLabel.setBounds(40,260,100,40);
        uitersteLabel.setBounds(40,310,200,40);
        beschrijvingLabel.setBounds(40,370,100,40);
        conditieLabel.setBounds(40,485,100,40);
        locatieLabel.setBounds(40,650, 100, 40);

        datumField.setBounds(300,10,100,40);
        beginTijdField.setBounds(300,60,100,40);
        eindTijdField.setBounds(300,110,100,40);
        normaal.setBounds(300,160,100,40);
        pinkRibbon.setBounds(420,160,200,40);
        maxAantalSlider.setBounds(300,210,300,40);
        inlegGeldField.setBounds(300,260,100,40);
        uitersteField.setBounds(300,310,100,40);
        beschrijvingField.setBounds(200,370,400,110);
        conditieField.setBounds(200,500,400,110);
        locatieField.setBounds(300,650,100,40);

        terug.setBounds(600,720,75,40);
        bevestigen.setBounds(490,720,100,40);

    }

    public void setSliderLabels(){
        maxAantalSlider.setMajorTickSpacing(25);
        maxAantalSlider.setMinorTickSpacing(5);
        maxAantalSlider.setPaintTicks(true);
        maxAantalSlider.setPaintLabels(true);
    }

    public void addComponents(){
        add(datumLabel);
        add(beginTijdLabel);
        add(eindTijdLabel);
        add(soortLabel);
        add(maxLabel);
        add(inlegGeldLabel);
        add(uitersteLabel);
        add(beschrijvingLabel);
        add(conditieLabel);
        add(locatieLabel);

        add(conditieField);
        add(datumField);
        add(beginTijdField);
        add(eindTijdField);
        add(normaal);
        add(pinkRibbon);
        add(maxAantalSlider);
        add(inlegGeldField);
        add(uitersteField);
        add(beschrijvingField);
        add(locatieField);

        add(terug);
        add(bevestigen);
    }

    public void addGroup(){
        ButtonGroup grp= new ButtonGroup();
        grp.add(normaal);
        grp.add(pinkRibbon);
    }

    public void emptyTextFields(){
        datumField.setText("");
        beginTijdField.setText("");
        eindTijdField.setText("");
        inlegGeldField.setText("");
        uitersteField.setText("");
        beschrijvingField.setText("");
    }

    public void addToernooi(){
        String radio;
        if(normaal.isSelected()){
            radio= "Normaal";
        }
        else {
          radio= "Pink Ribbon";
        }
        try{
            Connection con = Main.getConnection();
            PreparedStatement add = con.prepareStatement("INSERT INTO Toernooi (datum, begintijd, eindtijd, beschrijving, condities, soort_toernooi, maximaal_aantal_spelers, inleggeld, uiterste_inschrijf_datum, locatie) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            add.setDate(1,java.sql.Date.valueOf(datumField.getText()));
            add.setTime(2,java.sql.Time.valueOf(beginTijdField.getText()));
            add.setTime(3, java.sql.Time.valueOf(eindTijdField.getText()));
            add.setString(4, beschrijvingField.getText());
            add.setString(5, conditieField.getText());
            add.setString(6, radio);
            add.setInt(7, maxAantalSlider.getValue());
            add.setDouble(8, Double.parseDouble(inlegGeldField.getText()));
            add.setDate(9, java.sql.Date.valueOf(uitersteField.getText()));
            add.setString(10,locatieField.getText());
            add.executeUpdate();
        }catch(Exception e) {
            System.out.println(e);
        }
    }

    public void addActionListeners(){
        normaal.addActionListener(this);
        pinkRibbon.addActionListener(this);
        terug.addActionListener(this);
        bevestigen.addActionListener(this);

        maxAantalSlider.addChangeListener(this);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == terug){
            dispose();
            ToernooiMenu menu =  new ToernooiMenu();
        }
        if(e.getSource() == bevestigen) {
            addToernooi();
            JOptionPane.showMessageDialog(this, "Toernooi toegevoegd");
            emptyTextFields();
        }

    }

    @Override
    public void stateChanged(ChangeEvent e) {

    }
}
