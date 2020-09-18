package com.deutsche.operator.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupStatistics {
    @Id
    private int id;

    private String classGroup;
    //TODO: change field type
    private String wordsInformation;
}
