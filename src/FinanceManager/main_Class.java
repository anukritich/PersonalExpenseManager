package FinanceManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class main_Class extends JFrame {
    main_Class(){
        super("Expense Manager");

        setLayout(null);
        setSize(600, 400);
        setLocation(350, 200);
        setVisible(true);
    }
    public main_Class(String pass) {
        new main_Class();
    }
}

