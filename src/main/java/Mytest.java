import DAO.SocialMediaDAO;
import Model.Account;
import Service.SocialMediaService;

public class Mytest{

    SocialMediaService sm=new SocialMediaService();

     public Mytest(){
        test();
    }
    public void test(){
        Account account=new Account("jay", "harris");
        System.out.println(account.toString());
        Account iaccount=sm.addAccount(account);
        System.out.println(iaccount);
    }
}