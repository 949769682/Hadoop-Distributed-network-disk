package club.hggzs.service.impl;

import club.hggzs.dao.IAccountDao;
import club.hggzs.domain.Account;
import club.hggzs.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service("accountService")
public class AccountServiceImpl implements AccountService {

    private IAccountDao accountDao;
    @Autowired
    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public List<Account> findAll() {
        return accountDao.findAll();
    }

    @Override
    public Account findByUsername(String username) {
        return accountDao.findByUsername(username);
    }

    @Override
    public void update(Account account) {
        accountDao.update(account);
    }

    @Override
    public void insert(Account account) {
        accountDao.insert(account);
    }

    @Override
    public void delete(String username) {
        accountDao.delete(username);
    }
}
