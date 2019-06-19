import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class LoginFrame extends JFrame implements ActionListener {


    private String loggedInUser;

    // all the components are initialized here

    JLabel passwordLabel = new JLabel("Password: ");
    JLabel usernameLabel = new JLabel("Username: ");
    JTextField userField = new JTextField();
    JPasswordField passField = new JPasswordField();
    JButton loginButton=new JButton("Login");
    JButton resetButton=new JButton("Reset");
    JCheckBox showPassword=new JCheckBox("Show Password");


// constructor
    public LoginFrame(){

        setLayout(null);
        setTitle("Log in");
        setVisible(true);
        setResizable(false);
        setSize(400,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setComponentBounds();
        addComponents();
        addActionListeners();




    }
// laying out all the components on the JFrame and setting their sizes.
    public void setComponentBounds(){
        usernameLabel.setBounds(50,75,100,30);
        passwordLabel.setBounds(50,150,100,30);
        userField.setBounds(150,75,150,30);
        passField.setBounds(150,150,150,30);
        showPassword.setBounds(50,250,150,30);
        loginButton.setBounds(50,300,100,30);
        resetButton.setBounds(250,300,100,30);
    }

    // adding all the components to the JFrame
    public void addComponents(){

        add(usernameLabel);
        add(passwordLabel);
        add(userField);
        add(passField);
        add(showPassword);
        add(loginButton);
        add(resetButton);
    }

    // adding action listeners to the buttons
    public void addActionListeners(){
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);

    }

    // getting all the user information from the DB
    public static ArrayList<Gebruiker> getAllGebruikers() throws ClassNotFoundException, SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://meru.hhs.nl/18095240", "18095240", "Ene3shaise");
        Statement stm;
        stm = conn.createStatement();
        String sql = "Select * From Gebruiker";
        ResultSet rst;
        rst = stm.executeQuery(sql);
        ArrayList<Gebruiker> gebruikerList = new ArrayList<Gebruiker>();
        while (rst.next()) {
            Gebruiker gebruiker = new Gebruiker(rst.getString("gebruikersnaam"), rst.getString("wachtwoord"));
            gebruikerList.add(gebruiker);

        }
        return gebruikerList;

    }

// methods for testing purposes
    public void setPassword(String password){
        passField.setText(password);
    }

    public void setUsername(String username){
        userField.setText(username);
    }

   // validate a user and see if they have the right to log in.
    public boolean validateUser(){
        try
        {
            String gebruikersnaam = userField.getText();
            String wachtwoord = passField.getText();
            ArrayList<Gebruiker> lijstVanGebruikers = getAllGebruikers();
            for(int i = 0; i < lijstVanGebruikers.size(); i++){
                if(lijstVanGebruikers.get(i).getGebruikersnaam().equals(gebruikersnaam) && lijstVanGebruikers.get(i).getWachtwoord().equals(wachtwoord)){
                    return true;
                }
            }
        }
        catch( ClassNotFoundException | SQLException e)
        {
            System.out.println("caught with LogIn");
        }



        loggedInUser = "Registratie Medewerker";

        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            System.out.println("Login button was clicked");
            if(validateUser()){
                    dispose();
                    HoofdMenu menu = new HoofdMenu();
            }

            else{
                JOptionPane.showMessageDialog(this,
                        "You entered a wrong username or password",
                        "Validation error",
                        JOptionPane.WARNING_MESSAGE);
            }

        }

        if(e.getSource() == resetButton){
            userField.setText("");
            passField.setText("");
            System.out.println("Reset button was clicked");
        }

        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passField.setEchoChar((char) 0);
            } else {
                passField.setEchoChar('*');
            }
        }
    }


}