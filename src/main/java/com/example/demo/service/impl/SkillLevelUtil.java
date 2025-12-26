// src/main/java/com/example/demo/util/SkillLevelUtil.java
package com.example.demo.util;

import java.util.Map;

public final class SkillLevelUtil {
    private static final Map<String, Integer> RANK = Map.of(
            "BEGINNER", 1,
            "INTERMEDIATE", 2,
            "EXPERT", 3
    );

    private SkillLevelUtil() {}

    public static void validateNonBlankSkillName(String skillName) {
        if (skillName == null || skillName.trim().isEmpty()) {
            throw new IllegalArgumentException("Skill name must not be null or blank");
        }
    }

    public static boolean meetsOrExceeds(String candidateLevel, String requiredLevel) {
        Integer c = RANK.get(candidateLevel);
        Integer r = RANK.get(requiredLevel);
        if (c == null || r == null) return false;
        return c >= r;
    }
}
