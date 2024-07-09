package Service;

import java.sql.SQLException;
import java.util.List;

import DAO.SocialMediaDAO;
import Model.Account;
import Model.Message;

public class SocialMediaService{

    public SocialMediaDAO socialmediaDAO;

    public SocialMediaService(){
        socialmediaDAO = new SocialMediaDAO();
    }

    public List<Account> getAllAccounts() throws SQLException{
        return socialmediaDAO.getAllAccounts();
    }

    public List<Message> getAllMessages() throws SQLException{
        return socialmediaDAO.getAllMessages();
    }

    public Account addAccount(Account account){
        System.out.println(account.toString());
        return socialmediaDAO.insertAccount(account);
    }

    public Account getAccountbyUsername(String username){
        return socialmediaDAO.getAccountByUsername(username);
    }

    public Message addMessage(Message message){
        return socialmediaDAO.insertMessage(message);
    }




}