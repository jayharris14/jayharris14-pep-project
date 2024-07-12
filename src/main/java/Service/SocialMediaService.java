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

    public void addAccount(Account account){
        socialmediaDAO.insertAccount(account);
    }

    public Account getAccountbyUsername(String username){
        return socialmediaDAO.getAccountByUsername(username);
    }

    public Message addMessage(Message message){
        return socialmediaDAO.insertMessage(message);
    }

    public Message getMessageById(int id){
        return socialmediaDAO.getMessageById(id);
    }

    public void deletemessage(int id){
        socialmediaDAO.deleteMessage(id);
    }
    public void updateMessage(String message, int id){
        socialmediaDAO.updateMessage(message, id);
    }

    public List<Message> getMessagesByUserId(int id) throws SQLException{
        return socialmediaDAO.getMessagesByUserId(id);
    }

    




}