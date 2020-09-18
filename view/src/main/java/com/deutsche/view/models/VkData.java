package com.deutsche.view.models;

import lombok.*;

import java.math.BigInteger;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VkData {
    private String id;
    private BigInteger date;
    private String text;
    private BigInteger owner_id;

    @Override
    public String toString(){
        return text;
    }
}
