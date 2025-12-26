package com.example.demo.util;

public class SkillLevelUtil {
    public static int levelRank(String level) {
        if (level == null) return 0;
        switch (level.toUpperCase()) {
            case "BEGINNER": return 1;
            case "INTERMEDIATE": return 2;
            case "EXPERT": return 3;
            default: return 0;
        }
    }

    public static int priorityRank(String priority) {
        if (priority == null) return 0;
        switch (priority.toUpperCase()) {
            case "LOW": return 1;
            case "MEDIUM": return 2;
            case "HIGH": return 3;
            default: return 0;
        }
    }
}