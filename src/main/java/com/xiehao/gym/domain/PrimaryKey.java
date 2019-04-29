package com.xiehao.gym.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class PrimaryKey implements Serializable {

    private int lid;

    private int coachid;
}
