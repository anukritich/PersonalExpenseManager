package FinanceManager;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class main_Class extends JFrame implements ActionListener {
    JButton button1;
    JLabel label, label1, label2, label3;
    JTextField textname, textprice;
    JDateChooser dateChooser;
    JTextArea expenseListArea;
    JScrollPane scrollPane;
    JLabel totalExpenseLabel;
    double totalExpense = 0.0;
    Color color  = new Color(110, 38, 14);

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
        label.setForeground(color);
        label.setFont(new Font("AvantGarde", Font.PLAIN, 20));
        label.setBounds(80, 10, 1000, 45);
        add(label);

        label1 = new JLabel("Date:");
        label1.setForeground(color);
        label1.setFont(new Font("AvantGarde", Font.ITALIC, 15));
        label1.setBounds(40, 65, 1000, 45);
        add(label1);

        dateChooser = new JDateChooser();
        dateChooser.setForeground(new Color(105, 105, 105));
        dateChooser.setBounds(40, 100, 230, 30);
        add(dateChooser);

        label2 = new JLabel("Name:");
        label2.setForeground(color);
        label2.setFont(new Font("AvantGarde", Font.ITALIC, 15));
        label2.setBounds(40, 120, 1000, 45);
        add(label2);
        textname = new JTextField(15);
        textname.setBounds(40, 155, 230, 30);
        textname.setFont(new Font("Arial", Font.ITALIC, 14));
        add(textname);

        label3 = new JLabel("Price:");
        label3.setForeground(color);
        label3.setFont(new Font("AvantGarde", Font.ITALIC, 15));
        label3.setBounds(40, 175, 1000, 45);
        add(label3);
        textprice = new JTextField(15);
        textprice.setBounds(40, 210, 230, 30);
        textprice.setFont(new Font("Arial", Font.ITALIC, 14));
        add(textprice);

        JLabel expenseListHeading = new JLabel("Expense List");
        expenseListHeading.setFont(new Font("AvantGarde", Font.PLAIN, 20));
        expenseListHeading.setForeground(color);
        expenseListHeading.setBounds(320, 10, 150, 40);
        add(expenseListHeading);

        expenseListArea = new JTextArea();
        expenseListArea.setText("Date\tName\tPrice (INR)\n");
        expenseListArea.setEditable(false);
        scrollPane = new JScrollPane(expenseListArea);
        scrollPane.setBounds(320, 60, 250, 200);
        add(scrollPane);

        totalExpenseLabel = new JLabel("Total Expense: ₹0.00");
        totalExpenseLabel.setFont(new Font("AvantGarde", Font.PLAIN, 15));
        totalExpenseLabel.setBounds(320, 270, 200, 30);
        add(totalExpenseLabel);

        fetchEntriesFromDatabase();
        setLayout(null);
        setSize(650, 400);
        setLocation(350, 200);
        setVisible(true);
    }
    private void fetchEntriesFromDatabase() {
        try {
            Conn c = new Conn();
            String q = "SELECT Date, Name, Price FROM entry";
            ResultSet resultSet = c.statement.executeQuery(q);
            while (resultSet.next()) {
                String d = resultSet.getString("Date");
                String n = resultSet.getString("Name");
                String p = resultSet.getString("Price");
                String expenseItem = d + "\t" + n + "\t" + p + "\n";
                expenseListArea.append(expenseItem);

                // Update total expense
                totalExpense += Double.parseDouble(p);
            }

            // Update total expense label
            totalExpenseLabel.setText("Total Expense: ₹" + String.format("%.2f", totalExpense));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
                    // Update expense list
                    Conn c1 = new Conn();
                    String q = "INSERT INTO entry (Date, Name, Price) VALUES ('" + sqlDate + "', '" + name + "', '" + price + "')";
                    c1.statement.executeUpdate(q);
                    System.out.println("Data inserted successfully");
                    String expenseItem = sqlDate + "\t" + name + "\t" + price + "\n";
                    expenseListArea.append(expenseItem);

                    // Update total expense
                    totalExpense += Double.parseDouble(price);
                    totalExpenseLabel.setText("Total Expense: ₹" + String.format("%.2f", totalExpense));

                    // Clear input fields
                    textname.setText("");
                    textprice.setText("");
                    dateChooser.setDate(null);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid price.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a date.", "Missing Date", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new main_Class();
    }
}