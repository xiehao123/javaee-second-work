package com.xiehao.gym.Dao;

import com.xiehao.gym.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDao  extends JpaRepository<Account, Integer> {
}
