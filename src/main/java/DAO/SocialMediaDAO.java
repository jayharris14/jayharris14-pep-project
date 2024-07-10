package DAO;

/*You will need to design and create your own DAO classes from scratch. 
You should refer to prior mini-project lab examples and course material for guidance.

Please refrain from using a 'try-with-resources' block when connecting to your database. 
The ConnectionUtil provided uses a singleton, and using a try-with-resources will cause issues in the tests.
*/

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.Account;
import Model.Message;
import Util.ConnectionUtil;

public class SocialMediaDAO {


    public Account insertAccount(Account account){
        
    try {
        Connection connection = ConnectionUtil.getConnection();
        //          Write SQL logic here. You should only be inserting with the name column, so that the database may
        //          automatically generate a primary key.
                    String sql = "INSERT INTO account (username, password) values(?, ?)" ;
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
        
                    //write preparedStatement's setString method here.
        
                    preparedStatement.setString(1, account.getUsername());
                    preparedStatement.setString(2, account.getPassword());
                    
                    ResultSet rs = preparedStatement.executeQuery();
                    while(rs.next()){
                        int accountid = rs.getInt(1);
                        System.out.println(accountid);
                        return new Account(accountid, account.getUsername(), account.getPassword());
                    }
                }catch(SQLException e){
                    System.out.println(e.getMessage());
                }
                return null;
            }


            public Message insertMessage(Message message){
                Connection connection = ConnectionUtil.getConnection();
                try {
                    //          Write SQL logic here. You should only be inserting with the name column, so that the database may
                    //          automatically generate a primary key.
                                String sql = "INSERT INTO message (posted_by, message_text, time_posted_epoch) values(?, ?, ?)" ;
                                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    
                                //write preparedStatement's setString method here.
                    
                                preparedStatement.setInt(1, message.getPosted_by());
                                preparedStatement.setString(2, message.getMessage_text());
                                preparedStatement.setLong(3, message.getTime_posted_epoch());
                                
                                preparedStatement.executeUpdate();
                                ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
                                if(pkeyResultSet.next()){
                                    int generated_messageid = (int) pkeyResultSet.getLong(1);
                                    return new Message(generated_messageid, message.getPosted_by(), message.getMessage_text(), message.getTime_posted_epoch());
                                }
                            }catch(SQLException e){
                                System.out.println(e.getMessage());
                            }
                            return null;
                        }
            
public List<Account> getAllAccounts() throws SQLException{
    String query="Select * FROM account";

    Connection connection = ConnectionUtil.getConnection();
    Statement st=connection.createStatement();
    ResultSet rs=st.executeQuery(query);

    List<Account> accs=new ArrayList<>();
try {
    
    while (rs.next()){
        Account account=new Account(
        rs.getInt(1),
        rs.getString(2),
        rs.getString(3));
        accs.add(account);
    }
    System.out.println(accs);
} catch (SQLException e) {
    // TODO: handle exception
}

    return accs;

    
}

public List<Message> getAllMessages() throws SQLException{
    String query="Select * From message";

    Connection connection = ConnectionUtil.getConnection();
    Statement st=connection.createStatement();
    ResultSet rs=st.executeQuery(query);

    ArrayList<Message> msgs=new ArrayList<Message>();
try {
    
    while (rs.next()){
        msgs.add(
        new Message(
        rs.getInt(1),
        rs.getInt(2),
        rs.getString(3),
        rs.getLong(4)
        )
        );
    }
} catch (SQLException e) {
    // TODO: handle exception
}

    return msgs;

    
}

public Account getAccountByUsername(String username){
    Connection connection = ConnectionUtil.getConnection();
    try {
        //Write SQL logic here
        String sql = "select * from account where username=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, username);

        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()){
            Account account = new Account(
                rs.getInt(1),
                rs.getString(2),
                rs.getString(3)
                );
            return account;
        }
    }catch(SQLException e){
        System.out.println(e.getMessage());
    }
    return null;
}

}
