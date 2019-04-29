package com.xiehao.gym.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
@Data
@Entity
@IdClass(PrimaryKey.class)
@DynamicUpdate
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int lid;
    private String lname;
    private double price;
    @Id
    private int coachid;

    private int sold;

}
