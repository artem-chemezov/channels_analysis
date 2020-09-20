package com.deutsche.groups.infoobjects;


import lombok.Getter;

import java.util.List;
@Getter
public enum ClassificationGroup {
    IT("IT", VkDataConstants.wordsIT, 5),
    MOVIES("Movies", VkDataConstants.wordsMovies, 5),
    ADULTSONLY ("Adults only! (18+)", VkDataConstants.adultsOnly, 5);

    private String category;
    private List<String> matchers;
    private int minimalPercent;

    ClassificationGroup(String category, List<String> matchers, int minimalPercent) {
        this.category = category;
        this.matchers = matchers;
        this.minimalPercent = minimalPercent;
    }
}
