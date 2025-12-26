package com.example.demo.util;

public class SkillLevelUtil {
    public static int getLevelValue(String level) {
        switch (level.toUpperCase()) {
            case "EXPERT": return 3;
            case "INTERMEDIATE": return 2;
            case "BEGINNER": return 1;
            default: return 0;
        }
    }

    public static boolean isMet(String volunteerLevel, String requiredLevel) {
        return getLevelValue(volunteerLevel) >= getLevelValue(requiredLevel);
    }
}