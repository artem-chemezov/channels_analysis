package com.deutsche.users.model;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Currency;


@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class User {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String login;
    @Value("0")
    private Currency balance;
    @Value("false")
    private boolean isPaymentAttached;
}
