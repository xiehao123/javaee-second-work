package com.xiehao.gym.service;

import com.xiehao.gym.domain.Account;
import org.springframework.stereotype.Service;

public interface AccountServer {
    public void updateAccount(Account a);
    public void deleteeAccount(Account  a);
}
