package com.xiehao.gym.service;

import com.xiehao.gym.Dao.AccountDao;
import com.xiehao.gym.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountImpl  implements AccountServer {
    @Autowired
    private AccountDao accountDao;

    @Override
    public void updateAccount(Account a) {
        accountDao.save(a);
    }

    @Override
    public void deleteeAccount(Account a) {
        accountDao.delete(a);
    }
}
