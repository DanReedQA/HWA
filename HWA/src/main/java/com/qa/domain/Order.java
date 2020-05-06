package com.qa.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;
//  private Long customerId; <-- Add later
//  private Long cardID; <-- Add later
//  private Long orderValue; <-- Add later

}
