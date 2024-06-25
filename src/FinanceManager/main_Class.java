package FinanceManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.toedter.calendar.JDateChooser;

public class main_Class extends JFrame implements ActionListener {
    JButton button1;
    JLabel label, label1, label2, label3;
    JTextField textname, textprice;
    JDateChooser dateChooser;

    main_Class() {
        super("Expense Manager");

        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("img/plus.png"));
        Image scaledImage = icon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        button1 = new JButton();
        button1.setBounds(30, 10, 40, 40);
        button1.setIcon(scaledIcon);
        button1.addActionListener(this);
        add(button1);

        label = new JLabel("Add Expense");
        label.setForeground(Color.black);
        label.setFont(new Font("AvantGarde", Font.PLAIN, 20));
        label.setBounds(80, 10, 1000, 45);
        add(label);

        label1 = new JLabel("Date:");
        label1.setForeground(Color.black);
        label1.setFont(new Font("AvantGarde", Font.ITALIC, 15));
        label1.setBounds(40, 65, 1000, 45);
        add(label1);

        dateChooser = new JDateChooser();
        dateChooser.setForeground(new Color(105, 105, 105));
        dateChooser.setBounds(40, 100, 230, 30);
        add(dateChooser);

        label2 = new JLabel("Name:");
        label2.setForeground(Color.black);
        label2.setFont(new Font("AvantGarde", Font.ITALIC, 15));
        label2.setBounds(40, 120, 1000, 45);
        add(label2);
        textname = new JTextField(15);
        textname.setBounds(40, 155, 230, 30);
        textname.setFont(new Font("Arial", Font.ITALIC, 14));
        add(textname);

        label3 = new JLabel("Price:");
        label3.setForeground(Color.black);
        label3.setFont(new Font("AvantGarde", Font.ITALIC, 15));
        label3.setBounds(40, 175, 1000, 45);
        add(label3);
        textprice = new JTextField(15);
        textprice.setBounds(40, 210, 230, 30);
        textprice.setFont(new Font("Arial", Font.ITALIC, 14));
        add(textprice);

        setLayout(null);
        setSize(600, 400);
        setLocation(350, 200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Add button clicked");

        java.util.Date utilDate = dateChooser.getDate();
        if (utilDate != null) {
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            String name = textname.getText();
            String price = textprice.getText();

            try {
                if (e.getSource() == button1) {
                    Conn c1 = new Conn();
                    String q = "INSERT INTO entry (Date, Name, Price) VALUES ('" + sqlDate + "', '" + name + "', '" + price + "')";
                    c1.statement.executeUpdate(q);
                    System.out.println("Data inserted successfully");
                    setVisible(false);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select a date");
        }
    }

    public static void main(String[] args) {
        new main_Class();
    }
}
