
import club.hggzs.domain.Account;
import club.hggzs.service.AccountService;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.io.*;

public class Test {
    private ApplicationContext a;
    private AccountService accountService;

    @Before
    public void init(){
        a = new ClassPathXmlApplicationContext("SpringConfig.xml");
        accountService = a.getBean("accountService",AccountService.class);
    }
    @org.junit.Test
    public void findAll() throws IOException {
        System.out.println(accountService.findAll());
    }
    @org.junit.Test
    public void findByUsername(){
        System.out.println(accountService.findByUsername("admin"));
    }
    @org.junit.Test
    public void update(){
        Account account = new Account();
        account.setUsername("admin");
        account.setPassword("567");
        account.setMailbox("123456@qq.com");
        accountService.update(account);
    }
    @org.junit.Test
    public void insert(){
        Account account = new Account();
        account.setUsername("admin2");
        account.setPassword("567");
        account.setMailbox("123456@qq.com");
        accountService.insert(account);
    }
    @org.junit.Test
    public void delete(){
        accountService.delete("admin2");
    }
}
