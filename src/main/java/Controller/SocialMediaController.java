package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import DAO.SocialMediaDAO;
import Model.Account;
import Model.Message;
import Service.SocialMediaService;
import Util.ConnectionUtil;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */

     SocialMediaService socialmediaservice=new SocialMediaService();

     SocialMediaDAO socialmediadao=new SocialMediaDAO();

    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.post("/register", this::register);
        app.post("/login", this::login);
        app.post("/messages", this::sendmessage);
        app.get("/messages", this::getmessages);
        



        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     * @throws JsonProcessingException 
     * @throws JsonMappingException 
     * @throws SQLException 
     */

     private void getmessages(Context context) throws JsonMappingException, JsonProcessingException, SQLException{
        ObjectMapper mapper=new ObjectMapper();
        List<Message> messages=socialmediaservice.getAllMessages();
        context.json(messages);

     }


    private void sendmessage(Context context) throws JsonMappingException, JsonProcessingException, SQLException{
        ObjectMapper mapper=new ObjectMapper();
        Message message=mapper.readValue(context.body(), Message.class);
        List<Account> iaccounts=socialmediaservice.getAllAccounts();
        int exists=0;
        for (int i=0; i<iaccounts.size(); i++){
            if (iaccounts.get(i).getAccount_id()==message.getPosted_by()){
                exists=1;
            }
        }
        if (exists==1){
            if (message.getMessage_text()!=null && message.getMessage_text().length()<=255){
                Message imessage=socialmediaservice.addMessage(message);
                context.json(imessage);
            }
            else{
                context.status(400);
            }
        }
        else{
            context.status(400);
        }



    }
    private void login(Context context) throws JsonMappingException, JsonProcessingException, SQLException{
        ObjectMapper mapper=new ObjectMapper();
        Account account=mapper.readValue(context.body(), Account.class);
        List<Account> iaccounts=socialmediaservice.getAllAccounts();
        int ilogin=0;
        for (int i=0; i<iaccounts.size(); i++){
            if (iaccounts.get(i).getUsername()==account.getUsername() && iaccounts.get(i).getPassword()==account.getPassword()){
                ilogin=1;
            }
        }
        if (ilogin==1){
           Account iaccount=socialmediaservice.getAccountbyUsername(account.getUsername());
           context.json(iaccount);
        }
        else{
            context.status(401);
        }
          
      

    }

    private void register(Context context) throws JsonMappingException, JsonProcessingException, SQLException {
        ObjectMapper mapper=new ObjectMapper();
        int check=0;
        List<Account> iaccounts;
        Account account=mapper.readValue(context.body(), Account.class);
        if (account.getPassword().length()>=4 && account.getUsername().length()>=1){
                iaccounts=socialmediaservice.getAllAccounts();
                System.out.println(iaccounts);
                for (int i=0; i<iaccounts.size(); i++){
                    if (iaccounts.get(i).getUsername()==account.getUsername()){
                        check=1;
                    } 
                }
            if(check==0){
                    Account iaccount=socialmediaservice.addAccount(account);
                    context.json(iaccount);
            }
            else{
                context.status(400);
            }
        }
        else{
            context.status(400);
        }

    }


}