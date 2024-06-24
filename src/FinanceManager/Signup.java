package FinanceManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Signup extends JFrame implements ActionListener {

    JTextField textName, textEmail, textUsername, phonenumber;
    JPasswordField psw;
    JButton button1;

    Signup() {
        super("Expense Manager");
        ImageIcon ii1 = new ImageIcon(ClassLoader.getSystemResource("img/card.png"));
        Image ii2 = ii1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon ii3 = new ImageIcon(ii2);
        JLabel iimage = new JLabel(ii3);
        iimage.setBounds(50, 10, 100, 100);
        add(iimage);

        JLabel label1 = new JLabel(" Create an Account ");
        label1.setBounds(160, 20, 600, 40);
        label1.setFont(new Font("Raleway", Font.BOLD, 38));
        add(label1);

        JLabel labelName = new JLabel("Full Name:");
        labelName.setFont(new Font("Raleway", Font.BOLD, 25));
        labelName.setBounds(100, 190, 400, 30);
        add(labelName);

        textName = new JTextField();
        textName.setFont(new Font("Raleway", Font.BOLD, 25));
        textName.setBounds(300, 190, 400, 30);
        add(textName);

        JLabel labelmail = new JLabel("Email:");
        labelmail.setFont(new Font("Raleway", Font.BOLD, 25));
        labelmail.setBounds(100, 240, 400, 30);
        add(labelmail);

        textEmail = new JTextField();
        textEmail.setFont(new Font("Raleway", Font.BOLD, 25));
        textEmail.setBounds(300, 240, 400, 30);
        add(textEmail);

        JLabel labelpsw = new JLabel("Password:");
        labelpsw.setFont(new Font("Raleway", Font.BOLD, 25));
        labelpsw.setBounds(100, 290, 400, 30);
        add(labelpsw);

        psw = new JPasswordField();
        psw.setBounds(300, 290, 400, 30);
        add(psw);

        JLabel labelusername = new JLabel("Username:");
        labelusername.setFont(new Font("Raleway", Font.BOLD, 25));
        labelusername.setBounds(100, 340, 400, 30);
        add(labelusername);

        textUsername = new JTextField();
        textUsername.setFont(new Font("Raleway", Font.BOLD, 25));
        textUsername.setBounds(300, 340, 400, 30);
        add(textUsername);

        JLabel labelcontact = new JLabel("Phone Number:");
        labelcontact.setFont(new Font("Raleway", Font.BOLD, 25));
        labelcontact.setBounds(100, 390, 400, 30);
        add(labelcontact);

        phonenumber = new JTextField();
        phonenumber.setFont(new Font("Raleway", Font.BOLD, 25));
        phonenumber.setBounds(300, 390, 400, 30);
        add(phonenumber);

        button1 = new JButton("SIGN UP");
        button1.setFont(new Font("Arial", Font.BOLD, 14));
        button1.setForeground(new Color(110, 38, 14));
        button1.setBackground(new Color(218, 160, 109));
        button1.setBounds(300, 540, 100, 30);
        button1.addActionListener(this);
        add(button1);

        getContentPane().setBackground(new Color(140, 229, 245));
        setLayout(null);
        setSize(850, 700);
        setLocation(250, 40);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Signup button clicked");

        String Name = textName.getText();
        String email = textEmail.getText();
        String username = textUsername.getText();
        String contact = phonenumber.getText();
        String passkey = new String(psw.getPassword());

        try {
            if (Name.isEmpty() || email.isEmpty() || username.isEmpty() || contact.isEmpty() || passkey.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Fill all entries");
            } else {
                String hashedPasskey = hashPassword(passkey);
                Conn con1 = new Conn();
                String q = "INSERT INTO signup (Name, email, username, contact, passkey) VALUES ('" + Name + "', '" + email + "', '" + username + "', '" + contact + "', '" + hashedPasskey + "')";
                con1.statement.executeUpdate(q);  //to input values into table
                System.out.println("Data inserted successfully");
                setVisible(false);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void main(String[] args) {
        new Signup();
    }
}
