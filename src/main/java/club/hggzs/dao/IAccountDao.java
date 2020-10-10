package club.hggzs.dao;

import club.hggzs.domain.Account;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface IAccountDao {
    @Select("select * from Account")
    List<Account> findAll();
    @Select("select * from Account where username=#{username}")
    Account findByUsername(String username);
    @Update("update Account set password=#{password},mailbox=#{mailbox} where username=#{username}")
    void update(Account account);
    @Insert("insert into Account(username,password,mailbox) values(#{username},#{password},#{mailbox})")
    void insert(Account account);
    @Delete("delete from Account where username=#{username}")
    void delete(String username);
}
