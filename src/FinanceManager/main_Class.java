package FinanceManager;

import javax.swing.*;
import java.awt.*;

public class main_Class extends JFrame {
    JButton button1;
    JLabel label1;
    main_Class(){
        super("Expense Manager");

        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("img/plus.png"));
        Image scaledImage = icon.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JButton button1 = new JButton();
        button1.setBounds(30, 10, 40, 40);
        button1.setIcon(scaledIcon);
        add(button1);

        label1 = new JLabel("Add Expense");
        label1.setForeground(new Color(170, 229, 245));
        label1.setFont(new Font("AvantGarde", Font.BOLD, 20));
        label1.setBounds(80, 10, 1000, 45);
        add(label1);

        setLayout(null);
        setSize(600, 400);
        setLocation(350, 200);
        setVisible(true);
    }
    public static void main(String[] args){
        new main_Class();
    }
}
