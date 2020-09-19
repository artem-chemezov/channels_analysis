package com.deutsche.operator.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigInteger;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Group {
    @Id
    private int id;

    private String name;
    private BigInteger date;
}
