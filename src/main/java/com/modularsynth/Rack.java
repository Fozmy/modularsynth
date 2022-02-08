package com.modularsynth;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main program housing the modules
 */
public class Rack 
{
    private static ArrayList<Module> modules = new ArrayList<>();

    private static void processCommand(String cmd){
        if(cmd.equals("ls") || cmd.equals("list")){
            listModules();
        }
    }

    private static void listModules(){
        for(Module m : modules){
            System.out.println(m);
        }
    }

    private static void setup(){
        modules.add(new audioOut());
    }

    public static void main( String[] args )
    {
        Scanner scan = new Scanner(System.in);
        String input = "";
        setup();
        while(!(input.equals("exit"))){
            input = scan.next();
            processCommand(input);
        }
        scan.close();
    }
}
