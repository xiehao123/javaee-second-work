package com.xiehao.gym.repository;

import com.xiehao.gym.domain.Account;
import com.xiehao.gym.domain.Info;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.w3c.dom.ls.LSException;

import javax.annotation.security.PermitAll;
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

    @Query(value = "select * from Account ",nativeQuery = true)
    public List<Account> getall();

    @Query(value = "select * from Account a where a.username=:username ",nativeQuery = true)
    public Account get_account(@Param("username") String username);

    @Modifying
    @Query(value = "update Account a set a.password=:password,a.mobile=:mobile,a.address=:address where a.username=:username",nativeQuery = true)
    public void updata_account(@Param("username") String username, @Param("mobile") String mobile, @Param("address") String address,@Param("password") String password );
}