package com.xiehao.gym.repository;

import com.xiehao.gym.domain.Account;
import com.xiehao.gym.domain.Info;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import javax.persistence.QueryHint;
import java.util.List;

import static org.hibernate.jpa.QueryHints.HINT_COMMENT;

public interface GymRepository extends Repository<Account, Long> {
    public Account findById(Long id);
    public Account findByUsername(String username);

    @QueryHints(value = { @QueryHint(name = HINT_COMMENT, value = "a query for pageable")})
    @Query("select new com.xiehao.gym.domain.Info(l.lname,l.price,co.coachname,co.phone,l.sold) from coach co , Lesson l " +
            "where co.coachname=:key or l.lname=:key and l.coachid=co.coachid ORDER BY l.sold DESC")
    public Page<Info> findInfo(@Param("key")String coachname, Pageable pageable);

    public Account findAccountByUsername(String username);
}