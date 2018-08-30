package com.ritds;

import java.util.Scanner;

import static com.ritds.InputValidation.*;


public class RunApplication {

    public static void main(String[] args) {

        Storage st = new Storage();
        Scanner sc = new Scanner(System.in);

        boolean loop = true;


        while (loop) {
            String[] argv = sc.nextLine().trim().split("\\s+");
            switch (COMMANDS.lookupByName(argv[0].toUpperCase())){
                case CREATE:
                    if(validateCreateUpdateInput(argv)){
                        System.out.println(
                                st.create(Integer.parseInt(argv[1]),Long.parseLong(argv[2]),argv[3])
                        );

                    }
                    break;

                case UPDATE:
                    if(validateCreateUpdateInput(argv)){
                        System.out.println(
                                st.update(Integer.parseInt(argv[1]),Long.parseLong(argv[2]),argv[3])
                        );
                    }
                    break;

                case DELETE:
                    if(checkDelete(argv)){
                        if (argv.length ==3){
                            System.out.println(
                                    st.delete(Integer.parseInt(argv[1]), Long.parseLong(argv[2]))
                            );
                        }else{
                            System.out.println(st.delete(Integer.parseInt(argv[1])));
                        }
                    }
                    break;

                case GET:
                    if(checkGet(argv)){
                        System.out.println(st.get(Integer.parseInt(argv[1]), Long.parseLong(argv[2])));
                    }
                    break;

                case LATEST:
                    if (checkLatest(argv)){
                        System.out.println(st.latest(Integer.parseInt(argv[1])));
                    }
                    break;

                case QUIT:
                    System.out.println("Bye !");
                    loop = false;
                    break;
                default:
                    System.out.println("Unrecognised command, valid commands are: QUIT, CREATE, UPDATE, DELETE," +
                            "GET, LATEST");
                    break;
            }

        }
    }
}