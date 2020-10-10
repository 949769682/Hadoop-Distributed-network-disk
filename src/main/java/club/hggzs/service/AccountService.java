package club.hggzs.service;

import club.hggzs.domain.Account;
import java.util.List;

public interface AccountService {
    List<Account> findAll();
    Account findByUsername(String username);
    void update(Account account);
    void insert(Account account);
    void delete(String username);
}
