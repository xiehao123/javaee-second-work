package com.xiehao.gym.service;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.xiehao.gym.domain.Account;
import com.xiehao.gym.domain.Info;
import com.xiehao.gym.domain.Lesson;
import com.xiehao.gym.domain.coach;
import com.xiehao.gym.repository.GymRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Service
public class GymServiceImpl implements GymService {

    private GymRepository repository;

    @Autowired
    public GymServiceImpl(GymRepository repository) {
        this.repository = repository;
    }

    @Override
    public Boolean login(String username, String password) {
        Account account=repository.findByUsername(username);
        if(account==null){
            return false;
        }else if(account.getPassword().equals(password)){
            return true;
        }
        return false;
    }

    @Override
    public Boolean register(Account account) {
        if(repository.findByUsername(account.getUsername())!=null){
            return false;
        }
        return true;
    }



    @Override
    public Page<Info> search(int pageNum, int pageSize, String coachname) {
        Pageable pageable = new PageRequest(0,3, Sort.Direction.DESC,"coachid");
        return repository.findInfo(coachname,pageable);
    }

    @Cacheable(value = "emp" ,key = "#username")
    @Override
    public String searchAccount(String username) {
        try {
            Thread.sleep(1000*5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(repository.findByUsername(username)==null){
            return null;
        }
        Account ac=repository.findByUsername(username);
        return ac.getPassword()+"#"+ac.getMobileNumber()+"#"+ac.getAddress();
    }

}
