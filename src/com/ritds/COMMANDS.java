package com.ritds;

import java.util.HashMap;
import java.util.Map;

public enum COMMANDS {

    CREATE,
    UPDATE,
    DELETE,
    GET,
    LATEST,
    QUIT,
    INVALID;

    //This part below is to handle unknown values, usually I use guava for that but since the spec doesn't
    // allow to use external libs this is a way around, in general we don't want to catch runtime exceptions
    // from enums
    private static final Map<String, COMMANDS> nameIndex = new HashMap<>(COMMANDS.values().length);

    static {
        for (COMMANDS cmd : COMMANDS.values()) {
            nameIndex.put(cmd.name(), cmd);
        }
    }

    public static COMMANDS lookupByName(String name) {
        return nameIndex.getOrDefault(name,COMMANDS.INVALID);
    }

    public static void getMap(){
        nameIndex.entrySet().stream().forEach(System.out::println);
    }
}












