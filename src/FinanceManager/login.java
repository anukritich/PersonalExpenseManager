package FinanceManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;


public class login extends JFrame implements ActionListener  {
    JLabel label1, label2, label3;
    JTextField textField2;
    JPasswordField passwordField3;
    JButton button1, button2;

    login() {
        super("Expense Manager");

        ImageIcon ii1 = new ImageIcon(ClassLoader.getSystemResource("img/card.png"));
        Image ii2 = ii1.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT);
        ImageIcon ii3 = new ImageIcon(ii2);
        JLabel iimage = new JLabel(ii3);
        iimage.setBounds(50,10,100,100);
        add(iimage);

        label1 = new JLabel("Expense Manager");
        label1.setForeground(new Color(110, 38, 14));
        label1.setFont(new Font("AvantGarde", Font.BOLD, 35));
        label1.setBounds(160, 35, 450, 45);
        add(label1);

        label2 = new JLabel("Username:");
        label2.setFont(new Font("Raleway", Font.BOLD, 28));
        label2.setForeground(new Color(110, 38, 14));
        label2.setBounds(100, 150, 375, 30);
        add(label2);

        textField2 = new JTextField(15);
        textField2.setBounds(300, 150, 230, 30);
        textField2.setFont(new Font("Arial", Font.BOLD, 14));
        add(textField2);

        label3 = new JLabel("Password:");
        label3.setFont(new Font("Raleway", Font.BOLD, 28));
        label3.setForeground(new Color(110, 38, 14));
        label3.setBounds(100, 200, 375, 30);
        add(label3);

        passwordField3 = new JPasswordField(15);
        passwordField3.setBounds(300, 200, 230, 30);
        passwordField3.setFont(new Font("Arial", Font.BOLD, 14));
        add(passwordField3);

        button1 = new JButton("SIGN IN");
        button1.setFont(new Font("Arial", Font.BOLD, 14));
        button1.setForeground(new Color(110, 38, 14));
        button1.setBackground(new Color(218, 160, 109));
        button1.setBounds(300, 250, 100, 30);
        button1.addActionListener(this);
        add(button1);

        button2 = new JButton("SIGN UP");
        button2.setFont(new Font("Arial", Font.BOLD, 14));
        button2.setForeground(new Color(110, 38, 14));
        button2.setBackground(new Color(218, 160, 109));
        button2.setBounds(420, 250, 100, 30);
        button2.addActionListener(this);
        add(button2);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("img/bg.png"));
        Image i2 = i1.getImage().getScaledInstance(600,450,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,600,450);
        add(image);

        setLayout(null);
        setSize(600, 400);
        setLocation(350, 200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == button1) {
                Conn c = new Conn();
                String email = textField2.getText();
                String pass = new String(passwordField3.getPassword());
                String q = "select * from login where email = '" + email + "' and password = '" + pass + "'";
                ResultSet resultSet = c.statement.executeQuery(q);
                if (resultSet.next()) {
                    setVisible(false);
                    new main_Class  (); // Assuming `pin` is `pass`
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Email or Password");
                }
            } else if (e.getSource() == button2) {
                new Signup();
                setVisible(false);
            }
        } catch (Exception E) {
            E.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new login();
    }
}
