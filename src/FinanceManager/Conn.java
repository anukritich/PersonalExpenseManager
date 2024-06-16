
package FinanceManager;
import java.sql.*;

public class Conn{
    Connection connection;

    public Conn(){
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ExpenseManage","root","data@java")

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}



