package com.ritds;


import java.util.Arrays;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
class InputValidation {


    static boolean validateCreateUpdateInput(String[] args)
    {
        if (args.length != 4) {
            System.out.println("ERR Invalid number of arguments passed " + args[0].toUpperCase() +  " command");
            return false;
        }
        // data is not checked since input we pass here is the console input stripped off white spaces
        // if someone entered "zzz eee" this will be split in two => args.length > 5
        return checkId(args[1]) && checkTimestamp(args[2]);
    }

    static boolean checkDelete(String[] args){
        if (args.length == 3) return checkId(args[1]) && checkTimestamp(args[2]);
        if (args.length == 2) return checkId(args[1]);
        else System.out.println("ERR invalid number of arguments passed to DELETE operation");
        return false;
    }

     static boolean checkGet(String[] args) {
        if (args.length !=3){
            System.out.println("ERR Invalid number of arguments passed " + args[0].toUpperCase() +  " command");
            return false;
        }
        return checkId(args[1]) && checkTimestamp(args[2]);
    }

    static boolean checkLatest(String[] args){
        if (args.length !=2) {
            System.out.println("ERR Invalid number of arguments passed " + args[0].toUpperCase() +  " command");
            return false;
        }
        return checkId(args[1]);
    }

    private static boolean checkId(String arg){
        try {
            parseInt(arg);
        } catch (NumberFormatException e){
            System.out.println("ERR Failed to parse argument value, check input for ID:" + "\n" + "stacktrace :\n"
                    + e.toString());
            return false;
        }
        return true;
    }

    private static boolean checkTimestamp(String arg){

        try{
            parseLong(arg);
        }catch (NumberFormatException e){
            System.out.println("ERR Failed to parse argument value, check input for TIMESTAMP:" + "\n" + "stacktrace :\n"
                    + e.toString());
            return false;
        }
        return true;
    }

}
