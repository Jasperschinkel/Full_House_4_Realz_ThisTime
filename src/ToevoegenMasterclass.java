import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;



public class ToevoegenMasterclass extends JFrame implements ActionListener, ChangeListener {
    static final int FPS_MIN = 0;
    static final int FPS_MAX = 300;
    static final int FPS_INIT = 150;

    //All the labels represent!:

    private JLabel datumLabel = new JLabel("Datum: ");
    private JLabel beginTijdLabel = new JLabel("Begint: ");
    private JLabel eindTijdLabel = new JLabel("Eindigt: ");
    private JLabel maxLabel = new JLabel("Max ranking");
    private JLabel kostenLabel = new JLabel("Kosten: ");


    // All the textfields represent!:

    private JTextField beginTijdField = new JTextField();
    private JTextField eindTijdField = new JTextField();
    private JTextField kostenField = new JTextField();

    private DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
    private JFormattedTextField datumField = new JFormattedTextField(format);


    private JButton terug = new JButton("Terug");
    private JButton bevestigen = new JButton("Klaar");


    private JSlider maxAantalSlider = new JSlider(JSlider.HORIZONTAL,
            FPS_MIN, FPS_MAX, FPS_INIT);


    public ToevoegenMasterclass(){
        setTitle("Toevoegen van Masterclass");
        setLayout(null);
        setVisible(true);
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setComponentBounds();
        addComponents();
        addActionListeners();
        setSliderLabels();
    }


    public void emptyTextFields(){
        datumField.setText("");
        beginTijdField.setText("");
        eindTijdField.setText("");
        kostenField.setText("");
    }


    public void setComponentBounds(){
        datumLabel.setBounds(40,10,100,40);
        beginTijdLabel.setBounds(40,110,100,40);
        eindTijdLabel.setBounds(40,220,100,40);
        maxLabel.setBounds(40,330,200,40);
        kostenLabel.setBounds(40,410,100,40);



        datumField.setBounds(250,10,100,40);
        beginTijdField.setBounds(250,110,100,40);
        eindTijdField.setBounds(250,220,100,40);
        maxAantalSlider.setBounds(250,330,300,40);
        kostenField.setBounds(250,410,100,40);



        terug.setBounds(520,430,75,40);
        bevestigen.setBounds(419,430,100,40);



    }

    public void setSliderLabels(){
        maxAantalSlider.setMajorTickSpacing(50);
        maxAantalSlider.setMinorTickSpacing(10);
        maxAantalSlider.setPaintTicks(true);
        maxAantalSlider.setPaintLabels(true);
    }

    public void addComponents(){
        add(datumLabel);
        add(beginTijdLabel);
        add(eindTijdLabel);
        add(maxLabel);
        add(kostenLabel);



        add(datumField);
        add(beginTijdField);
        add(eindTijdField);
        add(maxAantalSlider);
        add(kostenField);



        add(terug);
        add(bevestigen);
    }

    public void addActionListeners(){
        terug.addActionListener(this);
        bevestigen.addActionListener(this);

        maxAantalSlider.addChangeListener(this);

    }

    public void addMasterclass() {
        try {
            Connection con = Main.getConnection();
            PreparedStatement add = con.prepareStatement("INSERT INTO Masterclass (datum, begintijd, eindtijd, kosten, max_ranking) VALUES ('" + datumField.getText() + "', '" + beginTijdField.getText() + "', '" + eindTijdField.getText() + "', '" + kostenField.getText() + "', '" + maxAantalSlider.getValue() + "');");
            add.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == terug){
            dispose();
            VerwijderenOfToevoegen verwijderenOfToevoegen = new VerwijderenOfToevoegen(3);
        }
        if(e.getSource() == bevestigen){
            addMasterclass();
            JOptionPane.showMessageDialog(this, "Masterclass toegevoegd");
            emptyTextFields();
        }

    }

    @Override
    public void stateChanged(ChangeEvent e) {

    }
}


