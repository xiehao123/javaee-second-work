package com.xiehao.gym.service;

import com.xiehao.gym.domain.Account;
import com.xiehao.gym.domain.Info;
import com.xiehao.gym.domain.Lesson;
import com.xiehao.gym.domain.coach;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;

import java.util.List;

public interface GymService {
    public Boolean login(String username, String password);
    public Boolean register(Account account);
    public Page<Info> search(int pageNum, int pageSize, String coachname);
    public String searchAccount(String username);
    public List<Account> getall();
    public Account get_detail(String username);
    public Account update_detail(Account a);
}
