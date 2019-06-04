import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame implements ActionListener {


    private String loggedInUser;

    JLabel passwordLabel = new JLabel("Password: ");
    JLabel usernameLabel = new JLabel("Username: ");
    JTextField userField = new JTextField();
    JPasswordField passField = new JPasswordField();
    JButton loginButton=new JButton("Login");
    JButton resetButton=new JButton("Reset");
    JCheckBox showPassword=new JCheckBox("Show Password");



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

    public void setComponentBounds(){
        usernameLabel.setBounds(50,75,100,30);
        passwordLabel.setBounds(50,150,100,30);
        userField.setBounds(150,75,150,30);
        passField.setBounds(150,150,150,30);
        showPassword.setBounds(50,250,150,30);
        loginButton.setBounds(50,300,100,30);
        resetButton.setBounds(250,300,100,30);
    }

    public void addComponents(){

        add(usernameLabel);
        add(passwordLabel);
        add(userField);
        add(passField);
        add(showPassword);
        add(loginButton);
        add(resetButton);
    }

    public void addActionListeners(){
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);

    }

    public boolean validateUser(){
        // add some future logic

        loggedInUser = "Registratie Medewerker";

        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            System.out.println("Login button was clicked");
            if(validateUser()){
                if(loggedInUser.equals("Registratie Medewerker")){
                    dispose();
                    HoofdMenu menu = new HoofdMenu();


                }
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
