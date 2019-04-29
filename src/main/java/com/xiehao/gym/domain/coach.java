package com.xiehao.gym.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class coach {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int coachid;
    private String coachname;
    private String phone;
}
