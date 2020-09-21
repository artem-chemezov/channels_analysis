package com.deutsche.operator.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VkDataDao {

    @Id
    private String id;

    private BigInteger date;
    private String text;
    private BigInteger owner_id;
}
